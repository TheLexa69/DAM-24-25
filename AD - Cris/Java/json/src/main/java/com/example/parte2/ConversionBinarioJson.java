package com.example.parte2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ConversionBinarioJson {
    public static void main(String[] args) {
        // Declarar la ruta del archivo binario de entrada y del archivo JSON de salida
        String inputFile = "games.bin";
        String outputFile = "games.json";

        try (
            // Crear FileInputStream y ObjectInputStream para leer el archivo binario
            FileInputStream fileIn = new FileInputStream(inputFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            // Leer la lista de objetos Game desde el archivo binario
            List<Game> games = (List<Game>) objectIn.readObject();

            // Crear un ObjectMapper para convertir los objetos Game en JSON
            ObjectMapper mapper = new ObjectMapper();

            // Escribir la lista de juegos en el archivo JSON, usando un formato con indentación
            mapper.writerWithDefaultPrettyPrinter().writeValue(new java.io.File(outputFile), games);

            System.out.println("El archivo JSON se ha creado exitosamente como " + outputFile);
        } catch (Exception e) {
            // Manejo de errores: imprimir el stack trace si ocurre alguna excepción
            e.printStackTrace();
        } 
    }
}
