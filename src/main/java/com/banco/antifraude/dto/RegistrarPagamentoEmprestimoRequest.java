package com.banco.antifraude.dto;

import java.math.BigDecimal;

public class RegistrarPagamentoEmprestimoRequest {
    private Long idEmprestimo;
    private BigDecimal valorPagoParcela; 

    
    public RegistrarPagamentoEmprestimoRequest() {
    }

    
    public RegistrarPagamentoEmprestimoRequest(Long idEmprestimo, BigDecimal valorPagoParcela) {
        this.idEmprestimo = idEmprestimo;
        this.valorPagoParcela = valorPagoParcela;
    }

    
    public Long getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public BigDecimal getValorPagoParcela() {
        return valorPagoParcela;
    }

    public void setValorPagoParcela(BigDecimal valorPagoParcela) {
        this.valorPagoParcela = valorPagoParcela;
    }
}