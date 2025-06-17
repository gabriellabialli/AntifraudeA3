package com.banco.antifraude.dto;

import java.math.BigDecimal;

public class GerarMilhasRequest {
    private Long idUsuario;
    private BigDecimal valorPago;

    
    public GerarMilhasRequest() {
    }

    public GerarMilhasRequest(Long idUsuario, BigDecimal valorPago) {
        this.idUsuario = idUsuario;
        this.valorPago = valorPago;
    }

   
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }
}