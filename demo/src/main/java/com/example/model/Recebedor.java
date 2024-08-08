package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recebedor {

    private String nome;
    private double entrada = 0;
    // private double restoParaDivisao = 0;

    public Recebedor(String nome) {
        this.nome = nome;
    }

    public void addEntrada(double valor) {
        entrada += valor;
        // restoParaDivisao += valor;
    }

    // public void subtraiParaResto(double valor) {
    //     restoParaDivisao -= valor;
    // }

    @Override
    public String toString() {
        return " " + nome + ", receber = " + String.format("%.2f", entrada);
    }
}
