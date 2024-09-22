package Tareas_16_09_2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Variante2 {
    private static Path currentDirectory;

    public static void main(String[] args) {
        currentDirectory = Path.of(System.getProperty("user.dir"));
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("\n\nBienvenido al sistema de archivos de la Fake Shell. (Salir con 'salir')");
        while (true) {//No se para nunca, debe escribir salir
            System.out.print("\n\nFAKE SHELL " + currentDirectory + "> ");
            command = scanner.nextLine().trim();

            if (command.equals("salir") ||command.equals("Salir")) {
                break;
            } else if (command.startsWith("cd ")) {
                changeDirectory(command.substring(3));
            } else if (command.equals("ls")) {
                listDirectory();
            } else if (command.startsWith("cat ")) {
                displayFileContent(command.substring(4));
            } else {
                System.out.println("Comando no reconocido: " + command);
            }
        }

        scanner.close();
    }

    private static void changeDirectory(String path) {
        Path newPath = currentDirectory.resolve(path).normalize();
        File dir = newPath.toFile();
        if (dir.isDirectory()) {
            currentDirectory = newPath;
        } else {
            System.out.println("No es un directorio: " + newPath);
        }
    }

    private static void listDirectory() {
        File dir = currentDirectory.toFile();
        String[] files = dir.list();
        if (files != null) {
            for (String file : files) {
                if (new File(dir, file).isDirectory()) {
                    System.out.println(file + "/");
                } else {
                    System.out.println(file);
                }
            }
        } else {
            System.out.println("Error al listar el directorio.");
        }
    }

    private static void displayFileContent(String fileName) {
        File file = currentDirectory.resolve(fileName).toFile();
        if (file.exists() && file.isFile()) {
            try {
                Files.lines(file.toPath()).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Archivo no encontrado: " + fileName);
        }
    }
}