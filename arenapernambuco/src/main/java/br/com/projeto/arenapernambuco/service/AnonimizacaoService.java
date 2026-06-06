package br.com.projeto.arenapernambuco.service;

import br.com.projeto.arenapernambuco.model.User;
import br.com.projeto.arenapernambuco.repository.UserRepository;
import br.com.projeto.arenapernambuco.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class AnonimizacaoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompraRepository compraRepository;

    // Roda automaticamente todo dia à meia-noite
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void anonimizarUsuariosInativos() {

        List<User> usuarios = userRepository.findAll();

        for (User user : usuarios) {

            // Anonimiza apenas citizens sem nenhuma compra registrada
            if (user.getRole() == User.Role.citizen) {

                List compras = compraRepository.findByEmail(user.getEmail());

                if (compras.isEmpty()) {
                    user.setName("Usuário Anonimizado");
                    userRepository.save(user);
                }
            }
        }
    }
}