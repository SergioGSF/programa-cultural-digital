package br.com.projeto.arenapernambuco.controller;

import br.com.projeto.arenapernambuco.dto.CompraDTO;
import br.com.projeto.arenapernambuco.model.Compra;
import br.com.projeto.arenapernambuco.model.Evento;
import br.com.projeto.arenapernambuco.repository.CompraRepository;
import br.com.projeto.arenapernambuco.repository.EventoRepository;
import br.com.projeto.arenapernambuco.util.MaskUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.security.Principal;
import java.util.List;

@Controller
public class IngressoController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/meus-ingressos")
    @Transactional
    public String meusIngressos(Model model, Principal principal) {

        String emailLogado = principal.getName();
        List<Compra> ingressos = compraRepository.findByEmail(emailLogado);

        List<Map<String, String>> ingressosMascarados = ingressos.stream().map(c -> {
            Map<String, String> map = new java.util.HashMap<>();
            map.put("id", String.valueOf(c.getId()));
            map.put("nome", MaskUtils.mascararNome(c.getNome()));
            map.put("cpf", MaskUtils.mascararCpf(c.getCpf()));
            map.put("numeroCartao", MaskUtils.mascararCartao(c.getNumeroCartao()));
            map.put("eventoTitulo", c.getEvent() != null ? c.getEvent().getTitle() : "");
            map.put("eventoData", c.getEvent() != null ? c.getEvent().getDate().toString() : "");
            return map;
        }).toList();

        model.addAttribute("ingressos", ingressos);
        model.addAttribute("ingressosMascarados", ingressosMascarados);

        return "meus-ingressos";
    }

    @PostMapping("/confirmacao")
    @Transactional
    public String processarCompra(
            @Valid @ModelAttribute CompraDTO dto,
            BindingResult result,
            Principal principal,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "erro",
                    result.getFieldError().getDefaultMessage()
            );

            return "pagamento";
        }

        Evento evento = eventoRepository.findById(dto.getEventId())
                .orElseThrow(() ->
                        new RuntimeException("Evento não encontrado"));

        for (int i = 0; i < dto.getQuantidade(); i++) {

            Compra novaCompra = new Compra();

            novaCompra.setEvent(evento);
            novaCompra.setNome(dto.getNome());
            novaCompra.setCpf(dto.getCpf());
            novaCompra.setEmail(principal.getName());

            compraRepository.save(novaCompra);
        }

        return "redirect:/confirmacao";
    }

    @PostMapping("/cancelar-ingresso/{id}")
    public String cancelarIngresso(
            @PathVariable("id") Long id) {

        compraRepository.deleteById(id);

        return "redirect:/meus-ingressos";
    }
}