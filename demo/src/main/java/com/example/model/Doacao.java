package com.example.model;

import lombok.Getter;

@Getter
public class Doacao {

    private String nome;
    private Recebedor recebedor;
    private double valor;
    
    public Doacao(String nome, Recebedor recebedor, double valor) {
        this.nome = nome;
        this.recebedor = recebedor;
        this.valor = valor;
    }
}
