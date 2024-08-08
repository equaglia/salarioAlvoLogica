package com.example.data;

import java.util.List;

import com.example.model.Bolo;
import com.example.model.CategoriaTrabalho;
import com.example.model.Doacao;
import com.example.model.Trabalhador;

public class Dados {

    public static List<Bolo> getBolos() {
        return List.of(
                new Bolo("SP")      // 0
                , new Bolo("BH")    // 1
                , new Bolo("RJ")    // 2
        );
    }

    public static List<Trabalhador> getTrabalhadores(List<Bolo> bolos) {
        return List.of(
                new Trabalhador("Joaquim", CategoriaTrabalho.INTEGRAL, 4000, 0, bolos.get(0), bolos.get(1)) // 0
                , new Trabalhador("Carlos", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1)) // 1
                , new Trabalhador("Mônica", CategoriaTrabalho.BOLSISTA, 1000, 1000, bolos.get(1), bolos.get(1)) // 2
                , new Trabalhador("Maria", CategoriaTrabalho.BOLSISTA, 1000, 1000, bolos.get(0), bolos.get(0)) // 3
                , new Trabalhador("Manoel", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1)) // 4
                , new Trabalhador("Cesar", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(1), bolos.get(0))  // 5
                , new Trabalhador("Jorge", CategoriaTrabalho.INTEGRAL, 4000, 0, bolos.get(2), bolos.get(2)) // 6
                , new Trabalhador("Sara", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(2), bolos.get(2))   // 7
                , new Trabalhador("Miguel", CategoriaTrabalho.BOLSISTA, 1000, 1000, bolos.get(2), bolos.get(2)) // 8
                , new Trabalhador("Iago", CategoriaTrabalho.INTEGRAL, 4000, 0, bolos.get(0), bolos.get(0)) // 9
                , new Trabalhador("Lucas", CategoriaTrabalho.PARCIAL, 2000, 0, bolos.get(0), bolos.get(0)) // 10
                // , new Trabalhador("Sílvio", CategoriaTrabalho.BOLSISTA, 1000, 1000, bolos.get(2), bolos.get(2)) // 11
                );
    }

    public static List<Doacao> getDoacoes(List<Bolo> bolos, List<Trabalhador> trabalhadores) {
        return List.of(
                new Doacao("Doa1", bolos.get(0), 1000)
                , new Doacao("Doa2", bolos.get(1), 900)
                , new Doacao("Doa3", bolos.get(0), 1000)
                , new Doacao("Doa4", bolos.get(2), 2000)
                , new Doacao("Doa4", trabalhadores.get(0), 1000)
                , new Doacao("Doa5", trabalhadores.get(1), 1000)
                , new Doacao("Doa6", trabalhadores.get(2), 100)
                , new Doacao("Doa7", trabalhadores.get(0), 1000)
                , new Doacao("Doa8", trabalhadores.get(0), 100)
                , new Doacao("Doa9", trabalhadores.get(0), 200)
                , new Doacao("Doa4", trabalhadores.get(0), 1000)
                , new Doacao("Doa5", trabalhadores.get(1), 1000)
                , new Doacao("Doa6", trabalhadores.get(2), 100)
                , new Doacao("Doa7", trabalhadores.get(0), 1000)
                , new Doacao("Doa8", trabalhadores.get(4), 100)
                , new Doacao("Doa9", trabalhadores.get(5), 200)
                , new Doacao("Doa4", trabalhadores.get(3), 1000)
                , new Doacao("Doa5", trabalhadores.get(6), 1000)
                , new Doacao("Doa6", trabalhadores.get(7), 100)
                , new Doacao("Doa7", trabalhadores.get(6), 1000)
                , new Doacao("Doa8", trabalhadores.get(6), 100)
                , new Doacao("Doa9", trabalhadores.get(8), 200)
                , new Doacao("Doa4", trabalhadores.get(0), 100)
                , new Doacao("Doa5", trabalhadores.get(1), 150)
                , new Doacao("Doa6", trabalhadores.get(2), 200)
                , new Doacao("Doa7", trabalhadores.get(3), 250)
                , new Doacao("Doa8", trabalhadores.get(4), 300)
                , new Doacao("Doa9", trabalhadores.get(5), 350)
                , new Doacao("Doa4", trabalhadores.get(6), 400)
                , new Doacao("Doa5", trabalhadores.get(7), 450)
                , new Doacao("Doa6", trabalhadores.get(8), 500)
                , new Doacao("Doa7", trabalhadores.get(9), 550)
                , new Doacao("Doa8", trabalhadores.get(10), 600)
                );
    }
}
