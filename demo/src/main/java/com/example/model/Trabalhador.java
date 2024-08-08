package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trabalhador extends Recebedor {
    private CategoriaTrabalho categoria;
    private double teto;
    private double minimo;
    private Bolo fonte;
    private double percentualFonte = 0;
    private Bolo trabalho;
    private double percentualTrabalho = 0;
    private double acumuladoMesSeguinte;

    public Trabalhador(String nome, CategoriaTrabalho categoria, double teto, double minimo, Bolo fonte, Bolo trabalho) {
        super(nome);
        this.categoria = categoria;
        this.teto = teto;
        this.minimo = minimo;
        this.fonte = fonte;
        this.trabalho = trabalho;
    }

    @Override
    public void addEntrada(double valor) {
        double acumulado = getEntrada() + valor;
        if (acumulado > teto) {
            setEntrada(teto);
            addAcumuladoMesSeguinte(acumulado - teto);
        } else {
            super.addEntrada(valor);
        }
    }

    private void addAcumuladoMesSeguinte(double valor) {
        acumuladoMesSeguinte += valor;
    }

    public void setPercentualTrabalho(double valor) {
        if (valor > 0) {
            percentualTrabalho = (double) (this.teto - getEntrada()) / valor;
        }
    }

    public void setPercentualFonte(double valor) {
        if (valor > 0) {
            percentualFonte = (double) (this.teto - getEntrada()) / valor;
        }
    }

    @Override
    public String toString() {
        String resultado = "fonte: (" + fonte.getNome() + ", " + String.format("%.2f%%", getPercentualFonte() *100)
                + ") , atua: (" + trabalho.getNome() + ", " + String.format("%.2f%%", getPercentualTrabalho() *100) + "),"
                + super.toString() + ", MesSeguinte: " + String.format("%.2f", acumuladoMesSeguinte)
                + ", " + categoria + ", teto: " + String.format("%.2f", teto) + ", min: " + String.format("%.2f", minimo);

        return resultado;
    }
}
