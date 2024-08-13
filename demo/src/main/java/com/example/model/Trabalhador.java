package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trabalhador extends Recebedor {
    private Categoria categoria;
    private double teto;
    private double minimo;
    private Bolo fonte;
    private double percentualFonte = 0;
    private Bolo trabalho;
    private double percentualTrabalho = 0;
    private double acumuladoMesAnterior = 0;
    private double acumuladoMesSeguinte = 0;
    private double liquido = 0;

    public Trabalhador(String nome, Categoria categoria, double teto, double minimo, Bolo fonte, Bolo trabalho,
            double acumulado) {
        super(nome);
        this.categoria = categoria;
        this.teto = teto;
        this.minimo = minimo;
        this.fonte = fonte;
        this.trabalho = trabalho;
        this.acumuladoMesAnterior = acumulado;
    }

    @Override
    public void addBruto(double valor) {
        double acumulado = getBruto() + valor;
        if (acumulado > teto) { // o excedente do teto é acumulado para o proximo mes
            setBruto(teto);
            addAcumuladoMesSeguinte(acumulado - teto);
        } else {
            super.addBruto(valor);
        }
    }

    private void addAcumuladoMesSeguinte(double valor) {
        acumuladoMesSeguinte += valor;
    }

    public void setPercentualTrabalho(double valor) {
        if (valor > 0) {
            percentualTrabalho = (double) (this.teto - getBruto()) / valor;
        }
    }

    public void setPercentualFonte(double valor) {
        if (valor > 0) {
            percentualFonte = (double) (this.teto - getBruto()) / valor;
        }
    }

    @Override
    public String toString() {
        String resultado = "fonte: (" + fonte.getNome() + ", " + String.format("%.2f%%", getPercentualFonte() * 100)
                + ") , atua: (" + trabalho.getNome() + ", " + String.format("%.2f%%", getPercentualTrabalho() * 100)
                + "),"
                + super.toString() + ", líquido: " + String.format("%.2f", getLiquido()) + ", MesSeguinte: "
                + String.format("%.2f", acumuladoMesSeguinte)
                + ", " + categoria + ", teto: " + String.format("%.2f", teto) + ", min: "
                + String.format("%.2f", minimo);

        return resultado;
    }
}
