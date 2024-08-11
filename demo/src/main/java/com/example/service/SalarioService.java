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

    /**
     * Direciona as doações aos recebedores, tanto bolo de cidade quanto trabalhador
     * 
     * @param doacoes
     */
    private void direcionaDoacoesAosRecebedores(List<Doacao> doacoes) {
        doacoes.forEach(doacao -> doacao.getRecebedor().addBruto(doacao.getValor()));
    }

    /**
     * Busca garantir o minimo para cada trabalhador
     * 
     * Se o trabalhador não atingiu o seu mínimo com suas doacoes pessoais,
     * a parcela que falta é transferida do bolo da cidade em que atua para o
     * trabalhador.
     * Se ainda não atingiu o mínimo, a parcela que falta é transferida do bolo da
     * cidade fonte para o trabalhador.
     * Se ainda assim não atingir o mínimo, o sistema gera mensagem de alerta
     * 
     * @param bolos
     * @param trabalhadores
     */
    private void garanteOMinimoParaCadaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        trabalhadores.forEach(trabalhador -> {
            double minimo = Math.max(0, trabalhador.getMinimo() - trabalhador.getBruto());
            if (minimo > 0) {
                for (Bolo bolo : bolos) {
                    double resto = bolo.getAcumuladoParaDivisao();
                    if (trabalhador.getTrabalho().equals(bolo)) {
                        if (resto > minimo) {
                            transfereDoBoloParaTrabalhador(trabalhador, minimo, bolo);
                            minimo = 0;
                            resto = bolo.getAcumuladoParaDivisao();
                            break;
                        } else {
                            transfereDoBoloParaTrabalhador(trabalhador, resto, bolo);
                            minimo -= resto;
                            resto = bolo.getAcumuladoParaDivisao();
                        }
                    }
                    if (trabalhador.getFonte().equals(bolo)) {
                        if (resto > minimo) {
                            transfereDoBoloParaTrabalhador(trabalhador, minimo, bolo);
                            minimo = 0;
                            resto = bolo.getAcumuladoParaDivisao();
                            break;
                        } else {
                            transfereDoBoloParaTrabalhador(trabalhador, resto, bolo);
                            minimo -= resto;
                            resto = bolo.getAcumuladoParaDivisao();
                        }
                    }
                }
                if (minimo > 0) {
                    System.out.println("Entrada insuficiente: " + String.format("%.2f", minimo) + " para o trabalhador "
                            + trabalhador.getNome());
                }
            }
        });
    }

    private void transfereDoBoloParaTrabalhador(Trabalhador trabalhador, double minimo, Bolo bolo) {
        trabalhador.addBruto(minimo);
        bolo.subtraiDoAcumuladoParaDivisao(minimo);
    }

    /**
     * Calcula o percentual de cada trabalhador em cada bolo
     * 
     * Para os trabalhadores que não atingiram o seu teto, estes deverão ter os
     * salários complementados com a divisão dos valores restantes dos bolos aos
     * quais fazem
     * parte:
     * - primeiro do bolo trabalho: cidade onde atua
     * - depois do bolo fonte: cidade que enviou e que garante o sustento do
     * trabalhador
     * 
     * A divisão dos bolos é feita de acordo com o percentual de cada trabalhador em
     * cada bolo, sendo considerado o que falta para completar o teto do trabalhador
     * 
     * Por exemplo:
     * - trabalhador 1: bruto = 1000, teto = 2000, falta = 1000
     * - trabalhador 2: bruto = 1000, teto = 4000, falta = 3000
     * Nessa configuração, o trabalhador 1 tera direito a 25%
     * e o trabalhador 2 tera direito a 75% do bolo correspondente
     * 
     * @param bolos
     * @param trabalhadores
     */
    private void calculaPercentualDeCadaTrabalhadorEmCadaBolo(List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        bolos.forEach(bolo -> {
            bolo.calculaPercentual(trabalhadores);
            System.out.println(bolo);
        });
    }

    /**
     * Transfere do bolo da cidade ONDE TRABALHA para o trabalhador, de acordo ccm o
     * percentual
     * calculado para o trabalhador
     * 
     * @param bolos
     * @param trabalhadores
     */
    private void transfereDoBoloDaCidadeDeTRABALHOParaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        trabalhadores.stream()
                .filter(t -> t.getBruto() < t.getTeto())
                .forEach(t -> {
                    bolos.stream()
                            .filter(b -> t.getTrabalho().equals(b))
                            .forEach(b -> incluiAdicionalAoBruto(t, b));
                });

        atualizaAcumuladoParaDivisao(bolos);
    }

    /**
     * Transfere do bolo da cidade FINANCIADORA para o trabalhador, de acordo ccm o
     * percentual;
     * calculado para o trabalhador
     * 
     * @param bolos
     * @param trabalhadores
     */
    private void transfereDoBoloDaCidadeFINANCIADORAParaTrabalhador(List<Bolo> bolos, List<Trabalhador> trabalhadores) {

        trabalhadores.stream()
                .filter(t -> t.getBruto() < t.getTeto())
                .forEach(t -> {
                    bolos.stream()
                            .filter(b -> t.getFonte().equals(b))
                            .forEach(b -> incluiAdicionalAoBruto(t, b));
                });

        atualizaAcumuladoParaDivisao(bolos);
    }

    /**
     * Atualiza o acumulado de cada bolo.
     * 
     * O acumulado do bolo é atualizado somente depois que todos os trabalhadores
     * tiverem o bruto calculado, garantindo a participação correta dos percentuais
     * dos trabalhadores
     * 
     * @param bolos the list of bolos to update the accumulated value for
     */
    private void atualizaAcumuladoParaDivisao(List<Bolo> bolos) {
        bolos.forEach(bolo -> {
            bolo.subtraiDoAcumuladoParaDivisao(bolo.getRetiradaAuxiliar());
            bolo.setRetiradaAuxiliar(0);
        });
    }

    private void incluiAdicionalAoBruto(Trabalhador t, Bolo b) {
        double adicional = b.getAcumuladoParaDivisao() * t.getPercentualFonte();
        if (t.getBruto() + adicional > t.getTeto()) {
            t.setBruto(t.getTeto());
            b.addRetiradaAuxiliar(t.getTeto());
        } else {
            t.addBruto(adicional);
            b.addRetiradaAuxiliar(adicional);
        }
    }

    /**
     * Aplica o recolhimento do escritório (liquido = bruto - 12%)
     * 
     * @param trabalhadores
     */
    private void calculaSalarioLiquido(List<Trabalhador> trabalhadores) {
        double recolhimentoEscritorio = 0.12;

        trabalhadores.forEach(trabalhador -> {
            trabalhador.setLiquido(trabalhador.getBruto() * (1 - recolhimentoEscritorio));
        });
    }

    public double calculaSalarioTotalMaisAcumulado(List<Trabalhador> trabalhadores) {
        double total = trabalhadores.stream()
                .mapToDouble(t -> t.getBruto() + t.getAcumuladoMesSeguinte())
                .sum();
        return total;
    }

    public double calculaEntradaTotal(List<Doacao> doacoes) {

        double total = doacoes.stream()
                .mapToDouble(d -> d.getValor())
                .sum();
        return total;
    }
}
