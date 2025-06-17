package com.banco.antifraude.dto;

public class AtualizarReputacaoRequest {
    private Long idUsuario;
    private Integer novaReputacao;

    
    public AtualizarReputacaoRequest() {
    }

    
    public AtualizarReputacaoRequest(Long idUsuario, Integer novaReputacao) {
        this.idUsuario = idUsuario;
        this.novaReputacao = novaReputacao;
    }

    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getNovaReputacao() {
        return novaReputacao;
    }

    public void setNovaReputacao(Integer novaReputacao) {
        this.novaReputacao = novaReputacao;
    }
}