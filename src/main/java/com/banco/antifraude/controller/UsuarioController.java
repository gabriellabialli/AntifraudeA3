package com.banco.antifraude.controller;

import com.banco.antifraude.dto.AtualizarReputacaoRequest; 
import com.banco.antifraude.dto.GerarMilhasRequest;
import com.banco.antifraude.dto.ResgatarMilhasRequest;
import com.banco.antifraude.model.Usuario;
import com.banco.antifraude.repository.UsuarioRepository;
import com.banco.antifraude.service.MilhasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal; 

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MilhasService milhasService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/milhas/gerar")
    public ResponseEntity<String> gerarMilhas(@RequestBody GerarMilhasRequest request) {
        try {
            milhasService.acumularMilhas(request.getIdUsuario(), request.getValorPago());
            return ResponseEntity.ok("Milhas geradas com sucesso para o usuário " + request.getIdUsuario());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao gerar milhas: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Um erro inesperado ocorreu: " + e.getMessage());
        }
    }

    @PostMapping("/milhas/resgatar")
    public ResponseEntity<String> resgatarMilhas(@RequestBody ResgatarMilhasRequest request) {
        try {
            milhasService.resgatarMilhas(request.getIdUsuario(), request.getValorResgate());
            return ResponseEntity.ok("Milhas resgatadas com sucesso para o usuário " + request.getIdUsuario() + ". Valor resgatado: " + request.getValorResgate());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao resgatar milhas: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Um erro inesperado ocorreu: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/milhas")
    public ResponseEntity<BigDecimal> consultarMilhas(@PathVariable Long id) {
        try {
            BigDecimal milhas = milhasService.consultarMilhas(id);
            return ResponseEntity.ok(milhas);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping("/reputacao") 
    public ResponseEntity<Usuario> atualizarReputacao(@RequestBody AtualizarReputacaoRequest request) {
        return usuarioRepository.findById(request.getIdUsuario())
            .map(usuario -> {
                usuario.setReputacao(request.getNovaReputacao());
                usuarioRepository.save(usuario);
                return ResponseEntity.ok(usuario);
            })
            .orElse(ResponseEntity.notFound().build());
    }
}