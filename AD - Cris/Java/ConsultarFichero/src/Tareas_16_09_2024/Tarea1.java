package Tareas_16_09_2024;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * 1. Versión simple de ls
 * Haz un programa que reciba el nombre de un directorio y muestre todo su
 * contenido, indicando en cada caso si se trata de un fichero o directorio y
 * los permisos que tenemos sobre él.
 * La salida tendrá un aspecto similar a este:
 * 
 * -rw- archivo
 * 
 * drwx directorio
 * 
 * ...
 * 
 */

public class Tarea1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de un directorio. Ej: C:");
        String nombre = sc.nextLine();
        Path dir = Path.of(nombre);
        System.out.println("Ficheros del directorio " + dir);
        if (Files.isDirectory(dir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
                for (Path fichero : stream) {
                    System.out.printf("%s: %s%s%s%s (%s)\n", fichero.getFileName() ,fichero.toFile().isDirectory() ? "d" : "-", fichero.toFile().canExecute() ? "x" : "-", fichero.toFile().canRead() ? "r" : "-", fichero.toFile().canWrite() ? "w" : "-", fichero.toFile().isDirectory() ? "directorio" : "archivo");
                }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println(dir.toString() + " no es un directorio");
        }

        sc.close();
    }
}
