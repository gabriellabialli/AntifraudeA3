package com.banco.antifraude.service;

import com.banco.antifraude.model.Emprestimo;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class AntiFraudeService {

    public void analisarEmprestimo(Emprestimo emprestimo) {
        
        if (emprestimo.getValorTotal().compareTo(new BigDecimal("10000.00")) > 0) {
            emprestimo.setStatus("REPROVADO - Valor Excedido");
        }
        
        else if (emprestimo.getParcelas() > 36) {
            emprestimo.setStatus("REPROVADO - Parcelas Excedidas");
        }
        
        else {
            emprestimo.setStatus("APROVADO");
        }
    }
}