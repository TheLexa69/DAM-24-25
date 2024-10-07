package com.example.parte2;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscrituraBinariaComentarios {
    public static void main(String[] args) {
        Map<String, String> commentsGameOne = new HashMap<>();
        commentsGameOne.put("user1", "Great game, very enjoyable!");
        commentsGameOne.put("user2", "Nice graphics, but could be better.");

        Map<String, String> commentsGameTwo = new HashMap<>();
        commentsGameTwo.put("user3", "Loved the storyline.");
        commentsGameTwo.put("user4", "Too difficult for beginners.");

        List<Game> games = Arrays.asList(
                new Game("Game One", "Description for Game One", Arrays.asList("English", "Spanish"), commentsGameOne),
                new Game("Game Two", "Description for Game Two", Arrays.asList("French", "German"), commentsGameTwo));

        try (FileOutputStream fileOut = new FileOutputStream("games.bin");
                ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(games);
            System.out.println("Games with comments saved to games.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
