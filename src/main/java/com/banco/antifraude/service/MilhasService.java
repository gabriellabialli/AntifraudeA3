package com.banco.antifraude.service;

import com.banco.antifraude.model.Usuario;
import com.banco.antifraude.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MilhasService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void acumularMilhas(Long idUsuario, BigDecimal valorPago) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));

        BigDecimal fatorAcumulo;
        if (usuario.getReputacao() > 90) {
            fatorAcumulo = new BigDecimal("0.12");
        } else {
            fatorAcumulo = new BigDecimal("0.10");
        }

        BigDecimal milhasGeradas = valorPago.multiply(fatorAcumulo)
                                            .setScale(2, RoundingMode.HALF_UP);

        BigDecimal milhasAtuais = usuario.getMilhasAcumuladas();
        if (milhasAtuais == null) {
            milhasAtuais = BigDecimal.ZERO;
        }

        usuario.setMilhasAcumuladas(milhasAtuais.add(milhasGeradas));

        usuarioRepository.save(usuario);
    }

    @Transactional
    public void resgatarMilhas(Long idUsuario, BigDecimal valorResgate) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));

        
        if (usuario.getReputacao() < 50) {
            throw new RuntimeException("Resgate bloqueado: Reputação do usuário muito baixa (" + usuario.getReputacao() + ").");
        }
        

        BigDecimal milhasAtuais = usuario.getMilhasAcumuladas();
        if (milhasAtuais == null) {
            milhasAtuais = BigDecimal.ZERO;
        }

        if (valorResgate.compareTo(milhasAtuais) > 0) {
            throw new RuntimeException("Saldo de milhas insuficiente para o resgate.");
        }

        usuario.setMilhasAcumuladas(milhasAtuais.subtract(valorResgate));

        usuarioRepository.save(usuario);
    }

    public BigDecimal consultarMilhas(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));

        return usuario.getMilhasAcumuladas() != null ? usuario.getMilhasAcumuladas() : BigDecimal.ZERO;
    }
}