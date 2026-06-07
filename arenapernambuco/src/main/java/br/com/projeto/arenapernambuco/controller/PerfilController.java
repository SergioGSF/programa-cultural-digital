package br.com.projeto.arenapernambuco.controller;

import br.com.projeto.arenapernambuco.model.Compra;
import br.com.projeto.arenapernambuco.model.User;
import br.com.projeto.arenapernambuco.repository.CompraRepository;
import br.com.projeto.arenapernambuco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public String perfil(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        model.addAttribute("user", user);
        return "perfil";
    }

    @GetMapping("/exportar")
    @Transactional
    public ResponseEntity<String> exportarDados(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        List<Compra> compras = compraRepository.findByEmail(user.getEmail());

        StringBuilder sb = new StringBuilder();
        sb.append("===== EXPORTAÇÃO DE DADOS PESSOAIS =====\n");
        sb.append("Plataforma: Arena Pernambuco\n");
        sb.append("Gerado em: ").append(java.time.LocalDateTime.now()).append("\n\n");

        sb.append("--- DADOS DA CONTA ---\n");
        sb.append("Nome: ").append(user.getName()).append("\n");
        sb.append("E-mail: ").append(user.getEmail()).append("\n");
        sb.append("Perfil: ").append(user.getRole()).append("\n");
        sb.append("Consentimento LGPD: ").append(
                user.getAceitouTermos() != null && user.getAceitouTermos()
                        ? "Aceito em " + user.getDataConsentimento()
                        : "Não registrado"
        ).append("\n\n");

        sb.append("--- INGRESSOS COMPRADOS ---\n");
        if (compras.isEmpty()) {
            sb.append("Nenhum ingresso encontrado.\n");
        } else {
            for (Compra c : compras) {
                sb.append("Evento: ").append(c.getEvent() != null ? c.getEvent().getTitle() : "N/A").append("\n");
                sb.append("Titular: ").append(c.getNome()).append("\n");
                sb.append("CPF: ").append(c.getCpf()).append("\n");
                sb.append("----------------------------------------\n");
            }
        }

        sb.append("\nEm conformidade com a Lei nº 13.709/2018 (LGPD) — Art. 18.\n");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"meus-dados-arena.txt\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(sb.toString());
    }

    @PostMapping("/excluir")
    @Transactional
    public String excluirConta(Principal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        compraRepository.findByEmail(user.getEmail())
                .forEach(compraRepository::delete);
        userRepository.delete(user);

        // Faz o logout programático antes de redirecionar
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/events";
    }
}