package br.com.projeto.arenapernambuco.model;

import br.com.projeto.arenapernambuco.config.CryptoConverter;
import jakarta.persistence.*;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = CryptoConverter.class)
    private String nome;

    private String email;

    @Convert(converter = CryptoConverter.class)
    private String cpf;

    private String telefone;

    @Convert(converter = CryptoConverter.class)
    private String numeroCartao;

    @Convert(converter = CryptoConverter.class)
    private String nomeCartao;

    @Convert(converter = CryptoConverter.class)
    private String validade;

    @Convert(converter = CryptoConverter.class)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }
    public String getNomeCartao() { return nomeCartao; }
    public void setNomeCartao(String nomeCartao) { this.nomeCartao = nomeCartao; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public Evento getEvent() { return evento; }
    public void setEvent(Evento evento) { this.evento = evento; }
}