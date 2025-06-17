package com.banco.antifraude.repository; 
import com.banco.antifraude.model.Reputacao; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; 

@Repository

public interface ReputacaoRepository extends JpaRepository<Reputacao, Long> {
    

    Optional<Reputacao> findByIdUsuario(Long idUsuario);
}