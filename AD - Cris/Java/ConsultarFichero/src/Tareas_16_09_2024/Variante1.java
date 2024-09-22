package Tareas_16_09_2024;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Programa que recibe el nombre de un directorio y muestra su contenido,
 * indicando si se trata de un fichero o directorio y los permisos sobre él.
 * Actúa recursivamente, mostrando el contenido de todos los subdirectorios
 * utilizando una pila.
 */
public class Variante1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de un directorio. Ej: C:   Z: ");
        String nombre = sc.nextLine();
        Path dir = Path.of(nombre);
        
        if (Files.isDirectory(dir)) {
            Deque<Path> stack = new ArrayDeque<>(); //Pila para almacenar los directorios tipo FIFO, funciona como una fila real de personas
            stack.push(dir); //Añadimos el directorio inicial a la pila
            
            while (!stack.isEmpty()) {
                Path currentDir = stack.pop(); //Extraemos el directorio de la pila
                System.out.printf("Contenido del directorio %s:\n", currentDir);
                
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir)) {
                    for (Path entry : stream) {
                        //Lo mismo que en la tarea 1
                        System.out.printf("%s: %s%s%s%s\n", 
                            entry.getFileName(), 
                            Files.isDirectory(entry) ? "d" : "-", 
                            Files.isExecutable(entry) ? "x" : "-", 
                            Files.isReadable(entry) ? "r" : "-", 
                            Files.isWritable(entry) ? "w" : "-"
                        );

                        //Si es un directorio, lo añadimos a la pila para procesarlo después. Para la recursividad.
                        if (Files.isDirectory(entry)) {
                            stack.push(entry);
                        }
                    }
                } catch (IOException | DirectoryIteratorException ex) {
                    System.err.println(ex);
                }
            }
        } else {
            System.err.println(dir.toString() + " no es un directorio");
        }

        sc.close();
    }
}