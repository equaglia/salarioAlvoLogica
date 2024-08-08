package com.example;

import java.util.List;

import com.example.data.Dados;
import com.example.model.Bolo;
import com.example.model.Doacao;
import com.example.model.Trabalhador;
import com.example.service.SalarioService;

public class Main {
    public static void main(String[] args) {

 
        // SalarioService contém a maior parte da lógica de negócio
        SalarioService salarioService = new SalarioService();

 
        // Dados contém os dados de entrada simulados, com base nas entidades Bolo, Trabalhador e Doação
        List<Bolo> bolos = Dados.getBolos();
        List<Trabalhador> trabalhadores = Dados.getTrabalhadores(bolos);
        List<Doacao> doacoes = Dados.getDoacoes(bolos, trabalhadores);

 
        // Aqui a mágica acontece, o sistema calcula os salários dos trabalhadores
        salarioService.calculaSalarios(doacoes, bolos, trabalhadores);
        System.out.println();
        
 
        // Daqui pra frente, o sistema mostra os resultados para análise
        for (Trabalhador trabalhador : trabalhadores) {
            System.out.println(trabalhador);
        }
        System.out.println();
        
        double totalSalario = salarioService.calculaSalarioTotalMaisAcumulado(trabalhadores);
        System.out.println("Salario total: " + String.format("%.2f", totalSalario));
        System.out.println();
        
        double totalEntrada = salarioService.calculaEntradaTotal(doacoes);
        System.out.println("Entrada total: " + String.format("%.2f", totalEntrada));
        System.out.println();
        System.out.println("Sobra: Entrada total - Salario total = " + String.format("%.2f", (totalEntrada - totalSalario)));

    }
}