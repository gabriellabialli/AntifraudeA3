package com.banco.antifraude.dto;

import java.math.BigDecimal;

public class ResgatarMilhasRequest {

    private Long idUsuario;
    private BigDecimal valorResgate;

    
    public ResgatarMilhasRequest() {
    }

    
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getValorResgate() {
        return valorResgate;
    }

    public void setValorResgate(BigDecimal valorResgate) {
        this.valorResgate = valorResgate;
    }
}