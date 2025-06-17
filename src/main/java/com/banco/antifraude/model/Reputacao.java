package com.banco.antifraude.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity 
@Table(name = "reputacoes") 
public class Reputacao {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    
    @Column(unique = true, nullable = false) 
    private Long idUsuario; 

    private Integer score; 
    private String nivel;  
    private String status; 

    
    public Reputacao() {
    }

    
    public Reputacao(Long idUsuario, Integer score, String nivel, String status) {
        this.idUsuario = idUsuario;
        this.score = score;
        this.nivel = nivel;
        this.status = status;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override 
    public String toString() {
        return "Reputacao{" +
               "id=" + id +
               ", idUsuario=" + idUsuario +
               ", score=" + score +
               ", nivel='" + nivel + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}