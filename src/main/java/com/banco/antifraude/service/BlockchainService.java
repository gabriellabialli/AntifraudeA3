package com.banco.antifraude.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class BlockchainService {

    private final String blockchainSimulatorUrl;

    
    public BlockchainService(@Value("${blockchain.simulatorUrl}") String blockchainSimulatorUrl) {
        this.blockchainSimulatorUrl = blockchainSimulatorUrl;
    }

    
    public String simularTransacao() { 
        System.out.println("Simulando transação na blockchain em: " + blockchainSimulatorUrl);
        return "Simulação de transação concluída (apenas exemplo de uso da URL)";
    }

    
}