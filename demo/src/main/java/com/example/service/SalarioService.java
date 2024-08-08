package com.example.service;

import java.util.List;

import com.example.model.Bolo;
import com.example.model.Doacao;
import com.example.model.Trabalhador;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SalarioService {

    public void calculaSalarios(List<Doacao> doacoes, List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        direcionaDoacoesAosRecebedores(doacoes);
        
        garanteOMinimoParaCadaTrabalhador(bolos, trabalhadores);
        
        calculaPercentualDeCadaTrabalhadorEmCadaBolo(bolos, trabalhadores);

        transfereDoBoloDaCidadeDeTRABALHOParaTrabalhador(bolos, trabalhadores);

        transfereDoBoloDaCidadeFINANCIADORAParaTrabalhador(bolos, trabalhadores);
    }

    private void transfereDoBoloDaCidadeFINANCIADORAParaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        for (Trabalhador trabalhador : trabalhadores) {
            if (trabalhador.getEntrada() < trabalhador.getTeto()) {
                for (Bolo bolo : bolos) {
                    if (trabalhador.getFonte().equals(bolo)) {
                        double adicional = bolo.getEntrada() * trabalhador.getPercentualFonte();
                        if (trabalhador.getEntrada() + adicional > trabalhador.getTeto()) {
                            trabalhador.setEntrada(trabalhador.getTeto());
                            break;
                        } else {
                            trabalhador.addEntrada(adicional);
                        }
                    }
                }
            }
        }
    }

    private void transfereDoBoloDaCidadeDeTRABALHOParaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        for (Trabalhador trabalhador : trabalhadores) {
            if (trabalhador.getEntrada() < trabalhador.getTeto()) {
                for (Bolo bolo : bolos) {
                    if (trabalhador.getTrabalho().equals(bolo)) {
                        double adicional = bolo.getEntrada() * trabalhador.getPercentualTrabalho();
                        if (trabalhador.getEntrada() + adicional > trabalhador.getTeto()) {
                            trabalhador.setEntrada(trabalhador.getTeto());
                            break;
                        } else {
                            trabalhador.addEntrada(adicional);
                        }
                    }
                }
            }
        }
    }

    private void garanteOMinimoParaCadaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        for (Trabalhador trabalhador : trabalhadores) {
            double minimo = trabalhador.getMinimo() - trabalhador.getEntrada();
            if (minimo > 0) {

                if (trabalhador.getEntrada() < minimo) {
                    for (Bolo bolo : bolos) {
                        double resto = bolo.getRestoParaDivisao();
                        if (trabalhador.getTrabalho().equals(bolo)) {
                            if (resto > minimo) {
                                trabalhador.addEntrada(minimo);
                                bolo.subtraiParaResto(minimo);
                                minimo = 0;
                                break;
                            } else {
                                trabalhador.addEntrada(resto);
                                bolo.subtraiParaResto(resto);
                                minimo -= resto;
                            }
                        }
                        if (trabalhador.getFonte().equals(bolo)) {
                            if (resto > minimo) {
                                trabalhador.addEntrada(minimo);
                                bolo.subtraiParaResto(minimo);
                                minimo = 0;
                                break;
                            } else {
                                trabalhador.addEntrada(resto);
                                bolo.subtraiParaResto(resto);
                                minimo -= resto;
                            }
                        }
                    }
                }
                if (minimo > 0) {
                    throw new RuntimeException(
                            "Nao foi possível garantir o minimo para o trabalhador " + trabalhador.getNome());
                }
            }
        }
    }

    private void calculaPercentualDeCadaTrabalhadorEmCadaBolo(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        for (Bolo bolo : bolos) {
            bolo.calculaPercentual(trabalhadores);
            System.out.println(bolo);
        }
    }

    private void direcionaDoacoesAosRecebedores(List<Doacao> doacoes) {
        for (Doacao doacao : doacoes) {
            doacao.getRecebedor().addEntrada(doacao.getValor());
        }
    }

    public double calculaSalarioTotalMaisAcumulado(List<Trabalhador> trabalhadores) {

        double total = 0;
        for (Trabalhador trabalhador : trabalhadores) {
            total += trabalhador.getEntrada() + trabalhador.getAcumuladoMesSeguinte();
            System.out.println("total: " + String.format("%.2f", total) + ", entrada: " + String.format("%.2f", trabalhador.getEntrada()) + ", acumulado: " + String.format("%.2f", trabalhador.getAcumuladoMesSeguinte())+ ", nome: " + trabalhador.getNome());
        }
        // System.out.println();
        return total;
    }

    public double calculaEntradaTotal(List<Doacao> doacoes) {

        double total = 0;
        for (Doacao doacao : doacoes) {
            total += doacao.getValor();
            System.out.println("total: " + String.format("%.2f", total) + ", doacao: " + String.format("%.2f", doacao.getValor()));
        }
        // System.out.println();
        return total;
    }

}
