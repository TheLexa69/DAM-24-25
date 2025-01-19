package edu.badpals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ListaEncadenada lista = new ListaEncadenada();
        HiloMusica hiloMusica = new HiloMusica();
        hiloMusica.setDaemon(true);
        hiloMusica.start();

        String[] ficheros = {"FICHERO1.txt", "FICHERO2.txt", "FICHERO3.txt", "FICHERO4.txt"};
        Thread[] hilos = new Thread[ficheros.length];
        for (int i = 0; i < ficheros.length; i++) {
            hilos[i] = new HiloLector(ficheros[i], lista);
            hilos[i].start();
        }
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Volcar la lista en un fichero de salida
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"))) {
            System.out.println("Volcando la lista en el archivo de salida...");
            for (Persona persona : lista.getLista()) {
                bw.write(String.format("Cadena: %s, Longitud: %d, Iniciales: %s\n",
                        persona.getCadena(), persona.getLongitud(), persona.getIniciales()));
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de salida: " + e.getMessage());
        }
    }
}