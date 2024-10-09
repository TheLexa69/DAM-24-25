package com.example.parte2;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ConversionBinarioJson {
    public static void main(String[] args) {
        String inputFile = "games.bin";
        String outputFile = "games.json";

        try (
            //Creo FileInputStream y ObjectInputStream para leer el archivo binario
            FileInputStream fileIn = new FileInputStream(inputFile);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn)
        ) {
            //Leo la lista de objetos Game desde el archivo binario
            List<Game> games = (List<Game>) objectIn.readObject();

            //Creo un ObjectMapper para convertir los objetos en Game.bin en JSON
            ObjectMapper mapper = new ObjectMapper();

            //Escribo la lista de juegos en el archivo JSON
            mapper.writerWithDefaultPrettyPrinter().writeValue(new java.io.File(outputFile), games);

            System.out.println("El archivo JSON se ha creado exitosamente como " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
