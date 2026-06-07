package br.com.projeto.arenapernambuco.controller;

import br.com.projeto.arenapernambuco.model.User;
import br.com.projeto.arenapernambuco.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CadastroController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastro")
    public String telaCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(User user) {
        if (user.getRole() == null) {
            user.setRole(User.Role.citizen);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (Boolean.TRUE.equals(user.getAceitouTermos())) {
            user.setDataConsentimento(java.time.LocalDateTime.now());
        }

        userRepository.save(user);
        return "redirect:/login";
    }
}