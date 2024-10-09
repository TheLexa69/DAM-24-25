package com.example.parte2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;

public class LecturaJsonLista {
public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Game> games = mapper.readValue(new File("games.json"), new TypeReference<List<Game>>() {});

            //Imprimo la lista de objetos Game para ver por pantalla
            for (Game game : games) {
                System.out.println(game);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
