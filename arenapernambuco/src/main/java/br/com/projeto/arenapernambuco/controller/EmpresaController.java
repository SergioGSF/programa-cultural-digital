package br.com.projeto.arenapernambuco.controller;

import br.com.projeto.arenapernambuco.model.Evento;
import br.com.projeto.arenapernambuco.model.User;
import br.com.projeto.arenapernambuco.repository.CategoryRepository;
import br.com.projeto.arenapernambuco.repository.CompraRepository;
import br.com.projeto.arenapernambuco.repository.EventoRepository;
import br.com.projeto.arenapernambuco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping("/dashboard")
    @Transactional
    public String dashboard(Model model, Principal principal) {
        User empresa = userRepository.findByEmail(principal.getName()).orElseThrow();
        model.addAttribute("eventos", eventoRepository.findByEmpresa(empresa));
        model.addAttribute("empresa", empresa);
        return "empresa-dashboard";
    }

    @GetMapping("/criar-evento")
    public String criarEventoForm(Model model) {
        model.addAttribute("evento", new Evento());
        model.addAttribute("categorias", categoryRepository.findAll());
        return "empresa-criar-evento";
    }

    @PostMapping("/criar-evento")
    public String criarEvento(Evento evento, Principal principal) {
        User empresa = userRepository.findByEmail(principal.getName()).orElseThrow();
        evento.setEmpresa(empresa);
        evento.setStatus(Evento.Status.PENDENTE);
        eventoRepository.save(evento);
        return "redirect:/empresa/dashboard";
    }

    @GetMapping("/editar-evento/{id}")
    @Transactional
    public String editarEventoForm(@PathVariable Long id, Model model, Principal principal) {
        User empresa = userRepository.findByEmail(principal.getName()).orElseThrow();
        return eventoRepository.findById(id)
                .filter(e -> e.getEmpresa() != null && e.getEmpresa().getId().equals(empresa.getId()))
                .map(e -> {
                    model.addAttribute("evento", e);
                    model.addAttribute("categorias", categoryRepository.findAll());
                    return "empresa-editar-evento";
                })
                .orElse("redirect:/empresa/dashboard");
    }

    @PostMapping("/editar-evento/{id}")
    @Transactional
    public String editarEvento(@PathVariable Long id, Evento eventoAtualizado, Principal principal) {
        User empresa = userRepository.findByEmail(principal.getName()).orElseThrow();
        eventoRepository.findById(id)
                .filter(e -> e.getEmpresa() != null && e.getEmpresa().getId().equals(empresa.getId()))
                .ifPresent(e -> {
                    e.setTitle(eventoAtualizado.getTitle());
                    e.setDate(eventoAtualizado.getDate());
                    e.setImageUrl(eventoAtualizado.getImageUrl());
                    e.setFullPrice(eventoAtualizado.getFullPrice());
                    e.setDescription(eventoAtualizado.getDescription());
                    e.setCategory(eventoAtualizado.getCategory());
                    e.setStatus(Evento.Status.PENDENTE);
                    eventoRepository.save(e);
                });
        return "redirect:/empresa/dashboard";
    }

    @PostMapping("/excluir-evento/{id}")
    public String excluirEvento(@PathVariable Long id, Principal principal) {
        User empresa = userRepository.findByEmail(principal.getName()).orElseThrow();
        eventoRepository.findById(id)
                .filter(e -> e.getEmpresa() != null && e.getEmpresa().getId().equals(empresa.getId()))
                .ifPresent(eventoRepository::delete);
        return "redirect:/empresa/dashboard";
    }

    @GetMapping("/stats")
    @Transactional
    public String stats(Model model, Principal principal) {
        User empresa = userRepository.findByEmail(principal.getName()).orElseThrow();
        List<Evento> eventos = eventoRepository.findByEmpresa(empresa);

        long totalEventos = eventos.size();
        long aprovados = eventos.stream().filter(e -> e.getStatus() == Evento.Status.APROVADO).count();
        long pendentes = eventos.stream().filter(e -> e.getStatus() == Evento.Status.PENDENTE).count();
        long cancelados = eventos.stream().filter(e -> e.getStatus() == Evento.Status.CANCELADO).count();

        Map<Evento, Long> ingressosPorEvento = new LinkedHashMap<>();
        for (Evento e : eventos) {
            ingressosPorEvento.put(e, compraRepository.countByEvento(e));
        }

        long totalIngressos = ingressosPorEvento.values().stream().mapToLong(Long::longValue).sum();
        double receita = eventos.stream()
                .filter(e -> e.getStatus() == Evento.Status.APROVADO && e.getFullPrice() != null)
                .mapToDouble(e -> compraRepository.countByEvento(e) * e.getFullPrice())
                .sum();

        List<Long> vendasPorEvento = new ArrayList<>(ingressosPorEvento.values());

        double mediaIngressos = vendasPorEvento.isEmpty() ? 0.0 :
                vendasPorEvento.stream().mapToLong(Long::longValue).average().orElse(0.0);

        String modaEvento = ingressosPorEvento.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey().getTitle())
                .orElse("N/A");

        double desvioPadraoIngressos = 0.0;
        if (vendasPorEvento.size() >= 2) {
            double variancia = vendasPorEvento.stream()
                    .mapToDouble(v -> Math.pow(v - mediaIngressos, 2))
                    .average()
                    .orElse(0.0);
            desvioPadraoIngressos = Math.sqrt(variancia);
        }

        model.addAttribute("mediaIngressos",        mediaIngressos);
        model.addAttribute("modaEvento",            modaEvento);
        model.addAttribute("desvioPadraoIngressos", desvioPadraoIngressos);

        model.addAttribute("empresa", empresa);
        model.addAttribute("totalEventos", totalEventos);
        model.addAttribute("aprovados", aprovados);
        model.addAttribute("pendentes", pendentes);
        model.addAttribute("cancelados", cancelados);
        model.addAttribute("totalIngressos", totalIngressos);
        model.addAttribute("receita", receita);
        model.addAttribute("ingressosPorEvento", ingressosPorEvento);
        return "empresa-stats";
    }
}
