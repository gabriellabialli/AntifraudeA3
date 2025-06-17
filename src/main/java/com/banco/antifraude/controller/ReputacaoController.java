package com.banco.antifraude.controller;

import com.banco.antifraude.model.Reputacao; 
import com.banco.antifraude.repository.ReputacaoRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/reputacao")
// VOCÊ PRECISA PREENCHER ESTA URL COM A URL DO SEU FRONTEND NA AZURE!
// Por exemplo: "https://seu-frontend-antifraude.azurewebsites.net"
@CrossOrigin(origins = "https://[URL_DO_SEU_FRONTEND_AZURE]") // <-- MUDAR AQUI!
public class ReputacaoController {

    @Autowired
    private ReputacaoRepository reputacaoRepository;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Reputacao> getReputacaoByUsuarioId(@PathVariable Long idUsuario) {
        Optional<Reputacao> reputacao = reputacaoRepository.findByIdUsuario(idUsuario);

        if (reputacao.isPresent()) {
            return ResponseEntity.ok(reputacao.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // --- EXERCÍCIO ADICIONAL (Para testar: Endpoint para CRIAR uma reputação) ---
    // Você pode descomentar este bloco temporariamente para inserir dados no DB da Azure para testar o GET.
    // Lembre-se de importar @PostMapping e @RequestBody se usar.
    /*
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;

    @PostMapping
    public ResponseEntity<Reputacao> criarReputacao(@RequestBody Reputacao novaReputacao) {
        if (novaReputacao.getIdUsuario() == null) {
            return ResponseEntity.badRequest().build();
        }
        // Opcional: Adicionar lógica para garantir que não há reputação duplicada para o mesmo usuário
        // if (reputacaoRepository.findByIdUsuario(novaReputacao.getIdUsuario()).isPresent()) {
        //    return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        // }
        Reputacao reputacaoSalva = reputacaoRepository.save(novaReputacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(reputacaoSalva);
    }
    */
}