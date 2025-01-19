package edu.badpals;

import java.io.*;

public class HiloLector extends Thread {
    private String fichero;
    private ListaEncadenada lista;

    public HiloLector(String fichero, ListaEncadenada lista) {
        this.fichero = fichero;
        this.lista = lista;
    }

    public void run() {
        // Obtenemos el archivo
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fichero);

        // Si existe el archivo
        if (inputStream == null) {
            System.out.println("El archivo no se encontró: " + fichero);
            return;
        }

        // Leer los datos del archivo
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Persona persona = new Persona(linea);
                lista.insertar(persona);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e);
        }
    }
}