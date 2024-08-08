package com.example.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Bolo extends Recebedor {

    private double acumuladoTetoFonte = 0;
    private double acumuladoTetoTrabalho = 0;
    private double acumuladoParaDivisao = 0;
    @Setter
    private double retiradaAuxiliar = 0;

    public void addRetiradaAuxiliar(double valor) {
        retiradaAuxiliar += valor;
    }

    public void subtraiDoAcumuladoParaDivisao(double valor) {
        acumuladoParaDivisao -= valor;
    }

    public Bolo(String nome) {
        super(nome);
    }

    @Override
    public void addBruto(double valor) {
        super.addBruto(valor);
        acumuladoParaDivisao += valor;
    }

    public void calculaPercentual(List<Trabalhador> trabalhadores) {
        for (Trabalhador trabalhador : trabalhadores) {
            if (trabalhador.getTrabalho().equals(this)) {
                acumuladoTetoTrabalho += trabalhador.getTeto() - trabalhador.getBruto();
                // System.out.println("acumuladoTetoTrabalho: " + acumuladoTetoTrabalho);
            }
            if (trabalhador.getFonte().equals(this)) {
                acumuladoTetoFonte += trabalhador.getTeto() - trabalhador.getBruto();
                // System.out.println("acumuladoTetoFonte: " + acumuladoTetoFonte);
            }
        }
        for (Trabalhador trabalhador : trabalhadores) {
            if (trabalhador.getTrabalho().equals(this)) {
                trabalhador.setPercentualTrabalho(acumuladoTetoTrabalho);
            }
            if (trabalhador.getFonte().equals(this)) {
                trabalhador.setPercentualFonte(acumuladoTetoFonte);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()
        // + ", acumuladoTetoOndeAtua: " + String.format("%.2f", acumuladoTetoTrabalho)
        // + ", acumuladoTetoFonte: " + String.format("%.2f", acumuladoTetoFonte)
        ;
    }
}
