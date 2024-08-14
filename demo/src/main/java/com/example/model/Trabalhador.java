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

    /**
     * Calcula o percentual do trabalhador no bolo trabalho
     * 
     * Calcula a divisão do que falta para o trabalhador completar o teto e o valor de todos os trabalhadores que participam nesse bolo de trabalho
     * 
     * ex. este trabalhador: bruto = 1000, teto = 2000, falta = 1000
     * todos os trabalhadores que participam do bolo de trabalho: 
     *  - trab1: bruto = 1000, teto = 4000, falta = 3000
     *  - trab2: bruto = 1000, teto = 4000, falta = 3000    
     *  - trab3: bruto = 1000, teto = 4000, falta = 3000 
     *  - este trabalhador: bruto = 1000, teto = 2000, falta = 1000
     * 
     * Total que falta somando os 4 trabalhadores: 3000 + 3000 + 3000 + 1000 = 10.000
     * 
     *  - trab1: bruto = 1000, teto = 4000, falta = 3000 porcentagem = 10.000 / 3000 = 30%
     *  - trab2: bruto = 1000, teto = 4000, falta = 3000 porcentagem = 10.000 / 3000 = 30%
     *  - trab3: bruto = 1000, teto = 4000, falta = 3000 porcentagem = 10.000 / 3000 = 30%
     *  - este trabalhador: bruto = 1000, teto = 2000, falta = 1000 porcentagem = 10.000 / 1000 = 10%
     * 
     * registra no atributo "percentualTrabalho"
     * 
     * @param valor
     */
    public void setPercentualTrabalho(double valor) {
        if (valor > 0) {
            this.percentualTrabalho = (double) (this.teto - getBruto()) / valor;
        }
    }

    /**
     * Calcula o percentual do trabalhador no bolo fonte
     * 
     * É o mesmo que o percentual do trabalhador no bolo trabalho, mas registra no atributo "percentualFonte"
     * 
     * @param valor
     */
    public void setPercentualFonte(double valor) {
        if (valor > 0) {
            this.percentualFonte = (double) (this.teto - getBruto()) / valor;
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
