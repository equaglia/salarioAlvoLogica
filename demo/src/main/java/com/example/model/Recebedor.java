package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recebedor {

    private String nome;
    private double bruto = 0;

    public Recebedor(String nome) {
        this.nome = nome;
    }

    public void addBruto(double valor) {
        bruto += valor;
    }

    @Override
    public String toString() {
        return " " + nome + ", bruto = " + String.format("%.2f", bruto);
    }
}
