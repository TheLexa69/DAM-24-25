package com.example;

/***
 * 1. Creación de ficheros JSON
 * 1. Queremos exportar la información de nuestra cuenta de Spotify en un fichero JSON llamado music.json. Para eso tenemos que tener en cuenta los siguientes aspectos:

 * Cada lista tendrá un nombre y fecha de creación.
 * Para cada canción añadida a una lista se guardará su título, autor y duración. 
 * Guarda al menos 2 listas con 2 canciones.
 */

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class Tarea1 {
    public static void main(String[] args) {
        System.out.println("\n\nHello world!");
        // Crear la primera lista de música
        JSONObject playlist1 = new JSONObject();
        playlist1.put("nombre", "Lista de Rock");
        playlist1.put("fecha_creacion", "2024-09-29");

        JSONArray canciones1 = new JSONArray();

        JSONObject cancion1 = new JSONObject();
        cancion1.put("titulo", "Bohemian Rhapsody");
        cancion1.put("autor", "Queen");
        cancion1.put("duracion", "5:55");
        canciones1.put(cancion1);

        JSONObject cancion2 = new JSONObject();
        cancion2.put("titulo", "Hotel California");
        cancion2.put("autor", "Eagles");
        cancion2.put("duracion", "6:30");
        canciones1.put(cancion2);

        playlist1.put("canciones", canciones1);

        // Crear la segunda lista de música
        JSONObject playlist2 = new JSONObject();
        playlist2.put("nombre", "Lista de Pop");
        playlist2.put("fecha_creacion", "2024-09-29");

        JSONArray canciones2 = new JSONArray();

        JSONObject cancion3 = new JSONObject();
        cancion3.put("titulo", "Bad Guy");
        cancion3.put("autor", "Billie Eilish");
        cancion3.put("duracion", "3:14");
        canciones2.put(cancion3);

        JSONObject cancion4 = new JSONObject();
        cancion4.put("titulo", "Blinding Lights");
        cancion4.put("autor", "The Weeknd");
        cancion4.put("duracion", "3:22");
        canciones2.put(cancion4);

        playlist2.put("canciones", canciones2);

        // Crear el objeto JSON principal que contiene ambas listas de reproducción
        JSONArray playlists = new JSONArray();
        playlists.put(playlist1);
        playlists.put(playlist2);

        JSONObject spotifyData = new JSONObject();
        spotifyData.put("listas_de_reproduccion", playlists);

        // Escribir el objeto JSON a un archivo
        try (FileWriter file = new FileWriter("music.json")) {
            file.write(spotifyData.toString(4)); // El número 4 es para la indentación
            file.flush();
            System.out.println("Archivo music.json creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}