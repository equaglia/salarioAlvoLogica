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
        SalarioService service = new SalarioService();

 
        // Dados contém os dados de entrada simulados, com base nas entidades Bolo, Trabalhador e Doação
        List<Bolo> bolos = Dados.getBolos();
        List<Trabalhador> trabalhadores = Dados.getTrabalhadores(bolos);
        List<Doacao> doacoes = Dados.getDoacoes(bolos, trabalhadores);

 
        // Aqui a mágica acontece, o sistema calcula os salários dos trabalhadores
        service.calculaSalarios(doacoes, bolos, trabalhadores);
        System.out.println();
        
 
        // Daqui pra frente, o sistema mostra os resultados para análise
        for (Trabalhador trabalhador : trabalhadores) {
            System.out.println(trabalhador);
        }
        System.out.println();
        
        double estava = service.somaEstavaAcumulado(trabalhadores);
        double ficou = service.somaFicouAcumulado(trabalhadores);
        double entrou = service.somaEntrou(doacoes);
        double saiu = service.somaSaiu(trabalhadores);
        System.out.println("Entrou: " + String.format("%.2f", entrou));
        System.out.println("Estava acumulado: " + String.format("%.2f", estava));
        System.out.println("Entrou + Estava = " + String.format("%.2f", (entrou+estava)));
        System.out.println();
        System.out.println("Acumulado para o mês seguinte: " + String.format("%.2f", ficou));
        System.out.println("Saiu: " + String.format("%.2f", saiu));
        System.out.println("Saiu + Acumulou = " + String.format("%.2f", (saiu+ficou)));
        System.out.println();
        System.out.println("(Entrou + Estava) - (Acumulou + Saida) = " + String.format("%.2f", ((entrou+estava) - (ficou+saiu))));
    }
}