package com.banco.antifraude.repository;

import com.banco.antifraude.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; 
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    
    List<Emprestimo> findByStatusInAndDataQuitacaoIsNull(List<String> statuses);
}