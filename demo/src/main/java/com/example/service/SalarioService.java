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

        calculaSalarioLiquido(trabalhadores);
    }
    
    private void direcionaDoacoesAosRecebedores(List<Doacao> doacoes) {
        for (Doacao doacao : doacoes) {
            doacao.getRecebedor().addBruto(doacao.getValor());
        }
    }
    
    private void garanteOMinimoParaCadaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        
        for (Trabalhador trabalhador : trabalhadores) {
            double minimo = Math.max(0, trabalhador.getMinimo() - trabalhador.getBruto());
            if (minimo > 0) {
                
                if (trabalhador.getBruto() < minimo) {
                    for (Bolo bolo : bolos) {
                        double resto = bolo.getAcumuladoParaDivisao();
                        if (trabalhador.getTrabalho().equals(bolo)) {
                            if (resto > minimo) {
                                trabalhador.addBruto(minimo);
                                bolo.subtraiDoAcumuladoParaDivisao(minimo);
                                minimo = 0;
                                resto = bolo.getAcumuladoParaDivisao();
                                break;
                            } else {
                                trabalhador.addBruto(resto);
                                bolo.subtraiDoAcumuladoParaDivisao(resto);
                                minimo -= resto;
                                resto = bolo.getAcumuladoParaDivisao();
                            }
                        }
                        if (trabalhador.getFonte().equals(bolo)) {
                            if (resto > minimo) {
                                trabalhador.addBruto(minimo);
                                bolo.subtraiDoAcumuladoParaDivisao(minimo);
                                minimo = 0;
                                resto = bolo.getAcumuladoParaDivisao();
                                break;
                            } else {
                                trabalhador.addBruto(resto);
                                bolo.subtraiDoAcumuladoParaDivisao(resto);
                                minimo -= resto;
                                resto = bolo.getAcumuladoParaDivisao();
                            }
                        }
                    }
                }
                if (minimo > 0) {
                    System.out.println("Entrada insuficiente: " + String.format("%.2f", minimo) + " para o trabalhador "
                            + trabalhador.getNome());
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

    private void transfereDoBoloDaCidadeDeTRABALHOParaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        
        for (Trabalhador trabalhador : trabalhadores) {
            double bruto = trabalhador.getBruto();
            if (bruto < trabalhador.getTeto()) {
                for (Bolo bolo : bolos) {
                    if (trabalhador.getTrabalho().equals(bolo)) {
                        double adicional = bolo.getAcumuladoParaDivisao() * trabalhador.getPercentualTrabalho();
                        if (bruto + adicional > trabalhador.getTeto()) {
                            trabalhador.setBruto(trabalhador.getTeto());
                            bolo.addRetiradaAuxiliar(trabalhador.getTeto());
                            break;
                        } else {
                            trabalhador.addBruto(adicional);
                            bolo.addRetiradaAuxiliar(adicional);
                        }
                    }
                }
            }
        }
        for (Bolo bolo : bolos) {
            bolo.subtraiDoAcumuladoParaDivisao(bolo.getRetiradaAuxiliar());
            bolo.setRetiradaAuxiliar(0);
        }
    }
    
    private void transfereDoBoloDaCidadeFINANCIADORAParaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        for (Trabalhador trabalhador : trabalhadores) {
            double bruto = trabalhador.getBruto();
            if (bruto < trabalhador.getTeto()) {
                for (Bolo bolo : bolos) {
                    if (trabalhador.getFonte().equals(bolo)) {
                        double adicional = bolo.getAcumuladoParaDivisao() * trabalhador.getPercentualFonte();
                        if (bruto + adicional > trabalhador.getTeto()) {
                            trabalhador.setBruto(trabalhador.getTeto());
                            bolo.addRetiradaAuxiliar(trabalhador.getTeto());
                            break;
                        } else {
                            trabalhador.addBruto(adicional);
                            bolo.addRetiradaAuxiliar(adicional);
                        }
                    }
                }
            }
        }
        for (Bolo bolo : bolos) {
            bolo.subtraiDoAcumuladoParaDivisao(bolo.getRetiradaAuxiliar());
            bolo.setRetiradaAuxiliar(0);
        }
    }
    
        private void calculaSalarioLiquido(List<Trabalhador> trabalhadores) {
            double recolhimentoEscritorio = 0.12;

            for (Trabalhador trabalhador : trabalhadores) {
                trabalhador.setLiquido(trabalhador.getBruto()*(1-recolhimentoEscritorio));
            }
        }
    
    public double calculaSalarioTotalMaisAcumulado(List<Trabalhador> trabalhadores) {
        
        double total = 0;
        for (Trabalhador trabalhador : trabalhadores) {
            total += trabalhador.getBruto() + trabalhador.getAcumuladoMesSeguinte();
            // System.out.println("bruto: " + String.format("%.2f", trabalhador.getBruto()) + ", acumulado: "
            // + String.format("%.2f", trabalhador.getAcumuladoMesSeguinte()) + ", nome: "
            // + trabalhador.getNome());
        }
        return total;
    }

    public double calculaEntradaTotal(List<Doacao> doacoes) {

        double total = 0;
        for (Doacao doacao : doacoes) {
            total += doacao.getValor();
            // System.out.println(
            //         "doacao: " + String.format("%.2f", doacao.getValor()) + " " + doacao.getRecebedor().getNome());
        }
        return total;
    }

}
