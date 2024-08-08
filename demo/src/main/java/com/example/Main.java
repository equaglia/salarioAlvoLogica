package com.example;

import java.util.List;

import com.example.data.Dados;
import com.example.model.Bolo;
import com.example.model.Doacao;
import com.example.model.Trabalhador;
import com.example.service.SalarioService;

public class Main {
    public static void main(String[] args) {

        SalarioService salarioService = new SalarioService();

        List<Bolo> bolos = Dados.getBolos();
        List<Trabalhador> trabalhadores = Dados.getTrabalhadores(bolos);
        List<Doacao> doacoes = Dados.getDoacoes(bolos, trabalhadores);

        salarioService.calculaSalarios(doacoes, bolos, trabalhadores);
        System.out.println();
        
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