package com.banco.antifraude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; 
import org.springframework.scheduling.annotation.Scheduled;     
import org.springframework.beans.factory.annotation.Autowired; 
import com.banco.antifraude.service.EmprestimoService;   

@SpringBootApplication
@EnableScheduling 
public class AntifraudeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntifraudeApplication.class, args);
    }

    @Autowired 
    private EmprestimoService emprestimoService;

    
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void agendarVerificacaoInadimplencia() {
        System.out.println("--------------------------------------------------");
        System.out.println("AGENDADOR: Verificando inadimplência dos empréstimos...");
        emprestimoService.verificarInadimplencia();
        System.out.println("AGENDADOR: Verificação de inadimplência concluída.");
        System.out.println("--------------------------------------------------");
    }
}