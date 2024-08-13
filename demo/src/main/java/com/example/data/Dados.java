package com.example.data;

import java.util.List;

import com.example.model.Bolo;
import com.example.model.Categoria;
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
                new Trabalhador("Joaquim", Categoria.INTEGRAL, 4000, 0, bolos.get(0), bolos.get(1), 100) // 0
                , new Trabalhador("Carlos", Categoria.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1), 30000) // 1
                , new Trabalhador("Mônica", Categoria.BOLSISTA, 1000, 1000, bolos.get(1), bolos.get(1), 0) // 2
                , new Trabalhador("Maria", Categoria.BOLSISTA, 1000, 1000, bolos.get(0), bolos.get(0), 0) // 3
                // , new Trabalhador("Manoel", Categoria.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1), 1251) // 4
                , new Trabalhador("Manoel", Categoria.PARCIAL, 2000, 0, bolos.get(1), bolos.get(1), 3000) // 4
                , new Trabalhador("Cesar", Categoria.PARCIAL, 2000, 0, bolos.get(1), bolos.get(0), 0)  // 5
                , new Trabalhador("Jorge", Categoria.INTEGRAL, 4000, 0, bolos.get(2), bolos.get(2), 0) // 6
                , new Trabalhador("Sara", Categoria.PARCIAL, 2000, 0, bolos.get(2), bolos.get(2), 0)   // 7
                , new Trabalhador("Miguel", Categoria.BOLSISTA, 1000, 1000, bolos.get(2), bolos.get(2), 0) // 8
                , new Trabalhador("Iago", Categoria.INTEGRAL, 4000, 0, bolos.get(0), bolos.get(0), 0) // 9
                , new Trabalhador("Lucas", Categoria.PARCIAL, 2000, 0, bolos.get(0), bolos.get(0), 0) // 10
                , new Trabalhador("Sílvio", Categoria.BOLSISTA, 1000, 1000, bolos.get(2), bolos.get(2), 0) // 11
                , new Trabalhador("Vanda", Categoria.BOLSISTA, 1000, 1000, bolos.get(1), bolos.get(1), 500) // 12
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
