package com.banco.antifraude.repository;

import com.banco.antifraude.model.Usuario; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository; 

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}