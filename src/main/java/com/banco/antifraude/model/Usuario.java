package com.banco.antifraude.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist; 
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CPF", unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "NUMEROCARTAO", length = 16)
    private String numeroCartao;

    @Column(name = "MILHAS_ACUMULADAS", nullable = false)
    private BigDecimal milhasAcumuladas;

    @Column(name = "REPUTACAO", nullable = false) 
    private Integer reputacao;

    public Usuario() {
    }

    
    public Usuario(Long id, String cpf, String email, String senha, String numeroCartao, BigDecimal milhasAcumuladas, Integer reputacao) {
        this.id = id;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.numeroCartao = numeroCartao;
        this.milhasAcumuladas = milhasAcumuladas;
        this.reputacao = reputacao;
    }

    
    @PrePersist
    public void prePersist() {
        if (this.reputacao == null) {
            this.reputacao = 100; 
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public BigDecimal getMilhasAcumuladas() {
        return milhasAcumuladas;
    }

    public void setMilhasAcumuladas(BigDecimal milhasAcumuladas) {
        this.milhasAcumuladas = milhasAcumuladas;
    }

    
    public Integer getReputacao() {
        return reputacao;
    }

    public void setReputacao(Integer reputacao) {
        this.reputacao = reputacao;
    }
}