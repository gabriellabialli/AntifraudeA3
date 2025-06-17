package com.banco.antifraude.controller;

import com.banco.antifraude.model.Emprestimo;
import com.banco.antifraude.repository.EmprestimoRepository;
import com.banco.antifraude.service.AntiFraudeService;
import com.banco.antifraude.service.EmprestimoService; 
import com.banco.antifraude.dto.RegistrarPagamentoEmprestimoRequest; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    
    @Autowired
    private EmprestimoRepository emprestimoRepository; 

    @Autowired
    private AntiFraudeService antiFraudeService;

    @Autowired
    private EmprestimoService emprestimoService; 

    
    @PostMapping
    public ResponseEntity<Emprestimo> solicitarEmprestimo(@RequestBody Emprestimo emprestimo, UriComponentsBuilder uriBuilder) {
        emprestimo.setDataSolicitacao(LocalDate.now());
        antiFraudeService.analisarEmprestimo(emprestimo);

        
        Emprestimo novoEmprestimo = emprestimoService.criarEmprestimo(emprestimo);

        URI uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(novoEmprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(novoEmprestimo);
    }

    @GetMapping
    public List<Emprestimo> listarTodosEmprestimos() {
        
        return emprestimoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable Long id) {
        
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping("/pagar")
    public ResponseEntity<String> registrarPagamento(@RequestBody RegistrarPagamentoEmprestimoRequest request) {
        try {
            emprestimoService.registrarPagamento(request);
            return ResponseEntity.ok("Pagamento de R$" + request.getValorPagoParcela() + " registrado com sucesso para o empréstimo " + request.getIdEmprestimo() + ". Milhas acumuladas!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao registrar pagamento: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Um erro inesperado ocorreu: " + e.getMessage());
        }
    }
}