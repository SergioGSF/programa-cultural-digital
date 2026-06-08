package br.com.projeto.arenapernambuco.service;

import br.com.projeto.arenapernambuco.model.Evento;
import br.com.projeto.arenapernambuco.repository.CompraRepository;
import br.com.projeto.arenapernambuco.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class EstatisticaService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private CompraRepository compraRepository;

    // ─── Métodos existentes ───────────────────────────────────────────────────

    @Async
    public CompletableFuture<Long> totalEventos() {
        return CompletableFuture.completedFuture(eventoRepository.count());
    }

    @Async
    public CompletableFuture<Long> aprovados() {
        return CompletableFuture.completedFuture(
                eventoRepository.countByStatus(Evento.Status.APROVADO));
    }

    @Async
    public CompletableFuture<Long> pendentes() {
        return CompletableFuture.completedFuture(
                eventoRepository.countByStatus(Evento.Status.PENDENTE));
    }

    @Async
    public CompletableFuture<Long> cancelados() {
        return CompletableFuture.completedFuture(
                eventoRepository.countByStatus(Evento.Status.CANCELADO));
    }

    @Async
    public CompletableFuture<Long> ingressosVendidos() {
        return CompletableFuture.completedFuture(compraRepository.count());
    }

    // ─── Estatística Descritiva: Preços ───────────────────────────────────────

    /**
     * Média aritmética dos preços dos eventos aprovados.
     * Dialoga com POO: encapsulado como método reutilizável no service.
     * Dialoga com Estrutura de Dados: usa List<Double> para armazenar os valores.
     */
    @Async
    public CompletableFuture<Double> mediaPrecoEventos() {
        List<Double> precos = listarPrecos();
        double media = precos.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return CompletableFuture.completedFuture(media);
    }

    /**
     * Mediana dos preços: valor central após ordenação da lista.
     * Dialoga com Estrutura de Dados: exige ordenação da lista (sorted()).
     */
    @Async
    public CompletableFuture<Double> medianaPrecoEventos() {
        List<Double> precos = listarPrecos().stream().sorted().toList();
        return CompletableFuture.completedFuture(calcularMediana(precos));
    }

    /**
     * Desvio padrão dos preços: mede a dispersão em relação à média.
     * Útil para identificar se os preços são homogêneos ou muito variados.
     */
    @Async
    public CompletableFuture<Double> desvioPadraoPrecos() {
        List<Double> precos = listarPrecos();
        return CompletableFuture.completedFuture(calcularDesvioPadrao(precos));
    }

    /**
     * Moda de categorias: a categoria com maior número de eventos aprovados.
     * Dialoga com Estrutura de Dados: usa Map<String, Long> para contar frequências.
     */
    @Async
    public CompletableFuture<String> modaCategoria() {
        String moda = eventoRepository.findByStatus(Evento.Status.APROVADO)
                .stream()
                .filter(e -> e.getCategory() != null)
                .collect(Collectors.groupingBy(
                        e -> e.getCategory().getName(),
                        Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
        return CompletableFuture.completedFuture(moda);
    }

    // ─── Estatística de Tendência ─────────────────────────────────────────────

    /**
     * Contagem de eventos agrupados por categoria.
     * Permite visualizar tendências de demanda por tipo de evento.
     * Dialoga com Estrutura de Dados: LinkedHashMap mantém a ordem decrescente.
     * Dialoga com Infraestrutura: resultado enviado ao dashboard via Model (Thymeleaf).
     */
    @Async
    public CompletableFuture<Map<String, Long>> eventosPorCategoria() {
        Map<String, Long> resultado = new LinkedHashMap<>();
        eventoRepository.findAll().stream()
                .filter(e -> e.getCategory() != null)
                .collect(Collectors.groupingBy(
                        e -> e.getCategory().getName(),
                        Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> resultado.put(entry.getKey(), entry.getValue()));
        return CompletableFuture.completedFuture(resultado);
    }

    /**
     * Ticket médio geral da plataforma: receita total / total de ingressos.
     * Indicador financeiro fundamental para tomada de decisão.
     */
    @Async
    public CompletableFuture<Double> ticketMedioGeral() {
        long totalIngressos = compraRepository.count();
        if (totalIngressos == 0) return CompletableFuture.completedFuture(0.0);

        double receitaTotal = compraRepository.findAll().stream()
                .filter(c -> c.getEvent() != null && c.getEvent().getFullPrice() != null)
                .mapToDouble(c -> c.getEvent().getFullPrice())
                .sum();

        return CompletableFuture.completedFuture(receitaTotal / totalIngressos);
    }

    // ─── Métodos auxiliares privados ──────────────────────────────────────────

    /**
     * Retorna lista de preços dos eventos aprovados com preço definido.
     * Centraliza o acesso aos dados para evitar repetição (princípio DRY).
     * Dialoga com Estrutura de Dados: List<Double> como estrutura de armazenamento.
     */
    private List<Double> listarPrecos() {
        return eventoRepository.findByStatus(Evento.Status.APROVADO)
                .stream()
                .filter(e -> e.getFullPrice() != null)
                .map(Evento::getFullPrice)
                .collect(Collectors.toList());
    }

    /**
     * Cálculo de mediana: valor que divide a distribuição ao meio.
     * Se n é par, média dos dois valores centrais; se ímpar, valor do meio.
     */
    private double calcularMediana(List<Double> lista) {
        if (lista.isEmpty()) return 0.0;
        int n = lista.size();
        int meio = n / 2;
        return (n % 2 == 0)
                ? (lista.get(meio - 1) + lista.get(meio)) / 2.0
                : lista.get(meio);
    }

    /**
     * Cálculo de desvio padrão populacional.
     * Fórmula: sqrt( Σ(xi - média)² / n )
     */
    private double calcularDesvioPadrao(List<Double> lista) {
        if (lista.size() < 2) return 0.0;
        double media = lista.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double variancia = lista.stream()
                .mapToDouble(v -> Math.pow(v - media, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variancia);
    }
}