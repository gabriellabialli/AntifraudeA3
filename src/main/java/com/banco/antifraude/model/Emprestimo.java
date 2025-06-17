package com.banco.antifraude.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "emprestimos")
public class Emprestimo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private BigDecimal valorJaPago;

    private Integer parcelas;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDate dataSolicitacao;

    private LocalDate dataQuitacao;

    
    private LocalDate dataPrimeiraParcela;

    private String selfieUrl;
    private String destinoPix;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    
    public Emprestimo() {
        this.dataSolicitacao = LocalDate.now();
        this.valorJaPago = BigDecimal.ZERO;
    }

    
    public Emprestimo(Long id, BigDecimal valorTotal, BigDecimal valorJaPago, Integer parcelas, String status, LocalDate dataSolicitacao, LocalDate dataQuitacao, String selfieUrl, String destinoPix, Usuario usuario) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.valorJaPago = valorJaPago;
        this.parcelas = parcelas;
        this.status = status;
        this.dataSolicitacao = dataSolicitacao;
        this.dataQuitacao = dataQuitacao;
        this.selfieUrl = selfieUrl;
        this.destinoPix = destinoPix;
        this.usuario = usuario;
        
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorJaPago() {
        return valorJaPago;
    }

    public void setValorJaPago(BigDecimal valorJaPago) {
        this.valorJaPago = valorJaPago;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataQuitacao() {
        return dataQuitacao;
    }

    public void setDataQuitacao(LocalDate dataQuitacao) {
        this.dataQuitacao = dataQuitacao;
    }

    // NOVO: Getter e Setter para dataPrimeiraParcela
    public LocalDate getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public String getSelfieUrl() {
        return selfieUrl;
    }

    public void setSelfieUrl(String selfieUrl) {
        this.selfieUrl = selfieUrl;
    }

    public String getDestinoPix() {
        return destinoPix;
    }

    public void setDestinoPix(String destinoPix) {
        this.destinoPix = destinoPix;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", valorTotal=" + valorTotal + ", valorJaPago=" + valorJaPago + ", parcelas=" + parcelas +
               ", status=" + status + ", dataSolicitacao=" + dataSolicitacao + ", dataQuitacao=" + dataQuitacao +
               ", dataPrimeiraParcela=" + dataPrimeiraParcela + 
               ", selfieUrl=" + selfieUrl + ", destinoPix=" + destinoPix +
               ", usuario=" + (usuario != null ? usuario.getId() : "null") + "]";
    }
}