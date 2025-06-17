package com.banco.antifraude.service;

import com.banco.antifraude.dto.RegistrarPagamentoEmprestimoRequest;
import com.banco.antifraude.model.Emprestimo;
import com.banco.antifraude.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List; 

import com.banco.antifraude.service.MilhasService;
import com.banco.antifraude.service.UsuarioService;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private MilhasService milhasService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        if ("APROVADO".equals(emprestimo.getStatus())) {
            emprestimo.setDataPrimeiraParcela(emprestimo.getDataSolicitacao().plusDays(30));
        } else {
            emprestimo.setDataPrimeiraParcela(null);
        }
        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public void registrarPagamento(RegistrarPagamentoEmprestimoRequest request) {
        Emprestimo emprestimo = emprestimoRepository.findById(request.getIdEmprestimo())
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com ID: " + request.getIdEmprestimo()));

        BigDecimal novoValorJaPago = emprestimo.getValorJaPago().add(request.getValorPagoParcela());

        if (novoValorJaPago.compareTo(emprestimo.getValorTotal()) > 0) {
            emprestimo.setValorJaPago(emprestimo.getValorTotal());
            emprestimo.setStatus("PAGO");
            emprestimo.setDataQuitacao(LocalDate.now());
        } else {
            emprestimo.setValorJaPago(novoValorJaPago);
            if (novoValorJaPago.compareTo(emprestimo.getValorTotal()) == 0) {
                emprestimo.setStatus("PAGO");
                emprestimo.setDataQuitacao(LocalDate.now());
            } else {
                emprestimo.setStatus("PARCIALMENTE_PAGO");
            }
        }

        emprestimoRepository.save(emprestimo);

        milhasService.acumularMilhas(emprestimo.getUsuario().getId(), request.getValorPagoParcela());
    }

    public Emprestimo buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com ID: " + id));
    }

    @Transactional
    public void verificarInadimplencia() {
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByStatusInAndDataQuitacaoIsNull(
            List.of("APROVADO", "PARCIALMENTE_PAGO")
        );

        LocalDate hoje = LocalDate.now();
        final int DIAS_CARENCIA_INADIMPLENCIA = 5;

        for (Emprestimo emprestimo : emprestimosAtivos) {
            if (emprestimo.getDataPrimeiraParcela() != null) {
                LocalDate dataLimitePagamentoPrimeiraParcela = emprestimo.getDataPrimeiraParcela().plusDays(DIAS_CARENCIA_INADIMPLENCIA);

                if (hoje.isAfter(dataLimitePagamentoPrimeiraParcela) && emprestimo.getValorJaPago().compareTo(BigDecimal.ZERO) == 0) {
                    emprestimo.setStatus("INADIMPLENTE");
                    emprestimoRepository.save(emprestimo);
                    usuarioService.diminuirReputacaoPorInadimplencia(emprestimo.getUsuario().getId(), 20);
                    System.out.println("Empréstimo " + emprestimo.getId() + " marcado como INADIMPLENTE. Reputação do usuário " + emprestimo.getUsuario().getId() + " diminuída em 20 pontos.");
                }
            }
        }
    }
}