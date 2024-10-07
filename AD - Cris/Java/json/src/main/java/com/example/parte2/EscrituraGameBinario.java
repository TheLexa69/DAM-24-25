package com.example.parte2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscrituraGameBinario {
    public static void main(String[] args) {
        Map<String, String> comentariosJuegoUno = new HashMap<>();
        comentariosJuegoUno.put("user1", "Buen juego, me ha gustado!");
        comentariosJuegoUno.put("user2", "Buenos graficos, buena jugabilidad.");

        Map<String, String> comentariosJuegoDos = new HashMap<>();
        comentariosJuegoDos.put("user3", "Muy facil para principiantes.");
        comentariosJuegoDos.put("user4", "Juego P2W.");

        List<Game> games = Arrays.asList(
                new Game("Legend of Zelda", "Aventurate en el bosque", Arrays.asList("Ingles", "Espanol"), comentariosJuegoUno),
                new Game("Legend of Mushroom", "Evoluciona tu personaje al maximo", Arrays.asList("Portugues", "Espanol"), comentariosJuegoDos));

        try (FileOutputStream fileOut = new FileOutputStream("games.bin");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(games);
            System.out.println("Juegos salvo con los comentarios en games.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
