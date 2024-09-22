package Tareas_16_09_2024;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tarea3 {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String orden;

        do {
            System.out.println("\nIntroduce uno de los ordenes [asc_case, asc_non_case, desc_case, desc_non_case]: ");
            orden = teclado.nextLine();
        } while (!orden.equals("asc_case") && !orden.equals("asc_non_case") &&
                !orden.equals("desc_case") && !orden.equals("desc_non_case"));

        System.out.println("Has elegido: " + orden);

        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader("res/palabras.txt"))) {
            String linea;
            //PUEDO EVITAR USAR EL BUCLE CON FILES.READALLLINES!
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea); // Añadir las líneas a una lista
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            teclado.close();
            return;
        }

        switch (orden) {
            case "asc_case":
                Collections.sort(lineas); // Orden ascendente case-sensitive
                break;
            case "asc_non_case":
                Collections.sort(lineas, String.CASE_INSENSITIVE_ORDER); // Orden ascendente case-insensitive
                break;
            case "desc_case":
                lineas.sort(Collections.reverseOrder()); // Orden descendente case-sensitive
                break;
            case "desc_non_case":
                lineas.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER)); // Orden descendente                                                         // case-insensitive
                break;
        }

        String archivoSalida = "res/palabras_" + orden + ".txt";

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine(); 
            }
            System.out.println("Archivo ordenado guardado como: " + archivoSalida);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        } finally {
            teclado.close();
        }
    }
}
