package br.com.projeto.arenapernambuco.model;

import br.com.projeto.arenapernambuco.config.CryptoConverter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CryptoConverter.class)
    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "aceitou_termos")
    private Boolean aceitouTermos = false;

    @Column(name = "data_consentimento")
    private LocalDateTime dataConsentimento;

    public enum Role {
        citizen, empresa, gestor, admin
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public Boolean getAceitouTermos() { return aceitouTermos; }
    public void setAceitouTermos(Boolean aceitouTermos) { this.aceitouTermos = aceitouTermos; }
    public LocalDateTime getDataConsentimento() { return dataConsentimento; }
    public void setDataConsentimento(LocalDateTime dataConsentimento) { this.dataConsentimento = dataConsentimento; }
}