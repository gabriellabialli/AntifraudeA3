package com.banco.antifraude.service;

import com.banco.antifraude.model.Usuario;
import com.banco.antifraude.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional; 
import java.util.Optional; 

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    
    @Transactional
    public Usuario criarUsuario(Usuario usuario) {
        

        if (usuario.getReputacao() == null) {
            usuario.setReputacao(100); 
        }
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public Usuario atualizarReputacao(Long idUsuario, int novaReputacao) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));
        usuario.setReputacao(novaReputacao);
        return usuarioRepository.save(usuario);
    }

    
    @Transactional 
    public void diminuirReputacaoPorInadimplencia(Long usuarioId, int pontosParaDecrementar) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioId));

        int reputacaoAtual = usuario.getReputacao();
        int novaReputacao = reputacaoAtual - pontosParaDecrementar;

        
        if (novaReputacao < 0) {
            novaReputacao = 0;
        }

        usuario.setReputacao(novaReputacao);
        usuarioRepository.save(usuario);
        System.out.println("Reputação do usuário " + usuarioId + " diminuída de " + reputacaoAtual + " para " + novaReputacao + " devido à inadimplência.");
    }
}