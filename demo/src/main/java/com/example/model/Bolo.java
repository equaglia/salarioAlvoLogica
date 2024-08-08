package com.example.model;

import java.util.List;

import lombok.Getter;

@Getter
public class Bolo extends Recebedor{

    private double tetoFonteTotal = 0;
    private double tetoTrabalhoTotal = 0;
    private double restoParaDivisao = 0;

    public void subtraiParaResto(double valor) {
        restoParaDivisao -= valor;
    }

    public Bolo(String nome) {
        super(nome);
    }

    @Override
    public void addEntrada(double valor) {
        super.addEntrada(valor);
        restoParaDivisao += valor;
    }

    public void calculaPercentual(List<Trabalhador> trabalhadores) {
        for (Trabalhador trabalhador : trabalhadores) {
            if (trabalhador.getTrabalho().equals(this)) {
                tetoTrabalhoTotal += trabalhador.getTeto() - trabalhador.getEntrada();
                // System.out.println("tetoTrabalhoTotal: " + tetoTrabalhoTotal);
            }
            if (trabalhador.getFonte().equals(this)) {
                tetoFonteTotal += trabalhador.getTeto() - trabalhador.getEntrada();
                System.out.println("tetoFonteTotal: " + tetoFonteTotal);
            }
        }
        for (Trabalhador trabalhador : trabalhadores) {
            if (trabalhador.getTrabalho().equals(this)) {
                trabalhador.setPercentualTrabalho(tetoTrabalhoTotal);
            }
            if (trabalhador.getFonte().equals(this)) {
                trabalhador.setPercentualFonte(tetoFonteTotal);
            }
        }
    }


    @Override
    public String toString() {
        return super.toString() + ", tetoTrabalho: " + String.format("%.2f", tetoTrabalhoTotal) + ", tetoFonte: " + String.format("%.2f", tetoFonteTotal);
    }
}
