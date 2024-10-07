package com.example;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 2. Se quiere guardar en un fichero JSON llamado games.json la información de
 * diversos juegos existentes para la plataforma Android.
 * 
 * Para esto ten en cuenta los siguientes aspectos:
 * 
 * De cada juego guardaremos diferentes datos: nombre, versión, descripción,
 * idiomas disponibles, listado de imágenes (normalmente 3 o 4 capturas de
 * pantalla) y opiniones/comentarios de los usuarios.
 * De los usuarios guardaremos un id, avatar y nombre.
 * Guarda al menos 2 juegos, 2 socios y 2 comentarios para cada juego.
 */
public class Tarea2 {
    public static void main(String[] args) {
        // Crear información para el primer juego
        JSONObject game1 = new JSONObject();
        game1.put("nombre", "Adventure Quest");
        game1.put("version", "1.2.5");
        game1.put("descripcion",
                "Un juego de aventuras épicas donde deberás explorar tierras desconocidas y enfrentar a criaturas legendarias.");
        game1.put("idiomas_disponibles", new JSONArray().put("Espanol").put("Ingles").put("Frances"));

        JSONArray imagenes1 = new JSONArray();
        imagenes1.put("imagen1.png");
        imagenes1.put("imagen2.png");
        imagenes1.put("imagen3.png");
        game1.put("imagenes", imagenes1);

        // Opiniones/comentarios para el primer juego
        JSONArray comentarios1 = new JSONArray();

        JSONObject comentario1 = new JSONObject();
        comentario1.put("usuario_id", "u123");
        comentario1.put("avatar", "avatar1.png");
        comentario1.put("nombre_usuario", "Juan Gamer");
        comentario1.put("comentario", "Es un juego increíble, me encanta la historia y la jugabilidad.");
        comentarios1.put(comentario1);

        JSONObject comentario2 = new JSONObject();
        comentario2.put("usuario_id", "u124");
        comentario2.put("avatar", "avatar2.png");
        comentario2.put("nombre_usuario", "Ana Aventurera");
        comentario2.put("comentario", "Los gráficos son impresionantes, pero los controles podrían mejorar.");
        comentarios1.put(comentario2);

        game1.put("comentarios", comentarios1);

        // Crear información para el segundo juego
        JSONObject game2 = new JSONObject();
        game2.put("nombre", "Puzzle Master");
        game2.put("version", "3.0.1");
        game2.put("descripcion", "Un desafiante juego de rompecabezas con niveles cada vez más complicados.");
        game2.put("idiomas_disponibles", new JSONArray().put("Espanol").put("Ingles"));

        JSONArray imagenes2 = new JSONArray();
        imagenes2.put("imagen4.png");
        imagenes2.put("imagen5.png");
        imagenes2.put("imagen6.png");
        game2.put("imagenes", imagenes2);

        // Opiniones/comentarios para el segundo juego
        JSONArray comentarios2 = new JSONArray();

        JSONObject comentario3 = new JSONObject();
        comentario3.put("usuario_id", "u125");
        comentario3.put("avatar", "avatar3.png");
        comentario3.put("nombre_usuario", "Carlos Puzzles");
        comentario3.put("comentario", "Muy entretenido, los niveles son cada vez más complicados y divertidos.");
        comentarios2.put(comentario3);

        JSONObject comentario4 = new JSONObject();
        comentario4.put("usuario_id", "u126");
        comentario4.put("avatar", "avatar4.png");
        comentario4.put("nombre_usuario", "Maria Razonable");
        comentario4.put("comentario", "Es bueno para pasar el tiempo, pero algunos niveles son frustrantes.");
        comentarios2.put(comentario4);

        game2.put("comentarios", comentarios2);

        // Crear el objeto JSON principal que contiene ambos juegos
        JSONArray juegos = new JSONArray();
        juegos.put(game1);
        juegos.put(game2);

        JSONObject androidGamesData = new JSONObject();
        androidGamesData.put("juegos_android", juegos);

        // Escribir el objeto JSON a un archivo
        try (FileWriter file = new FileWriter("games.json")) {
            file.write(androidGamesData.toString(4)); // El número 4 es para la indentación
            file.flush();
            System.out.println("Archivo games.json creado exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
