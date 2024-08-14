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

    /**
     * Calcula o percentual de cada trabalhador em cada bolo
     * 
     * Calcula o que falta para completar o teto do trabalhador (faltaParaCompletar)
     * Calcula a soma desse valor que falta, de todos os trabalhadores participantes do bolo,
     * guardando na variável acumuladoTetoTrabalho e acumuladoTetoFonte. Esses acumulados são
     * passados para a classe trabalhador
     * 
     * @param trabalhadores
     */
    public void calculaPercentual(List<Trabalhador> trabalhadores) {
        for (Trabalhador trabalhador : trabalhadores) {
            double faltaParaCompletar = trabalhador.getTeto() - trabalhador.getBruto();

            if (trabalhador.getTrabalho().equals(this)) {
                this.acumuladoTetoTrabalho += faltaParaCompletar;
            }
            if (trabalhador.getFonte().equals(this)) {
                this.acumuladoTetoFonte += faltaParaCompletar;
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
