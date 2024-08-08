package com.example.data;

import java.util.List;

import com.example.model.Bolo;
import com.example.model.CategoriaTrabalho;
import com.example.model.Doacao;
import com.example.model.Trabalhador;

public class Dados {

    public static List<Bolo> getBolos() {
        return List.of(
                new Bolo("SP"),
                new Bolo("BH"));
    }

    public static List<Trabalhador> getTrabalhadores(List<Bolo> bolos) {
        return List.of(
                new Trabalhador("Joaquim", CategoriaTrabalho.INTEGRAL, 4000, 0, bolos.get(0), bolos.get(1))
                , new Trabalhador("Carlos", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1))
                // , new Trabalhador("Maria", CategoriaTrabalho.BOLSISTA, 1000, 1000, bolos.get(0), bolos.get(0))
                // , new Trabalhador("Manoel", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1))
                // , new Trabalhador("Cesar", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1))
                );
    }

    public static List<Doacao> getDoacoes(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        return List.of(
                new Doacao("Doa1", bolos.get(0), 1000)
                , new Doacao("Doa2", bolos.get(1), 1000)
                // , new Doacao("Doa3", bolos.get(0), 1000)
                , new Doacao("Doa4", trabalhadores.get(0), 1000)
                // , new Doacao("Doa5", trabalhadores.get(1), 1000)
                // , new Doacao("Doa6", trabalhadores.get(2), 100)
                // , new Doacao("Doa7", trabalhadores.get(0), 1000)
                // , new Doacao("Doa8", trabalhadores.get(0), 100)
                // , new Doacao("Doa9", trabalhadores.get(0), 200)
                // , new Doacao("Doa4", trabalhadores.get(0), 1000)
                // , new Doacao("Doa5", trabalhadores.get(1), 1000)
                // , new Doacao("Doa6", trabalhadores.get(2), 100)
                // , new Doacao("Doa7", trabalhadores.get(0), 1000)
                // , new Doacao("Doa8", trabalhadores.get(0), 100)
                // , new Doacao("Doa9", trabalhadores.get(0), 200)
                // , new Doacao("Doa4", trabalhadores.get(3), 1000)
                );
    }
}
