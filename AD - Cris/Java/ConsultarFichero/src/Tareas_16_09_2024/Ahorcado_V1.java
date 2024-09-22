package Tareas_16_09_2024;

import java.util.Scanner;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

public class Ahorcado_V1 {

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\n\n1. Nuevo juego");
            System.out.println("2. Cargar juego");
            System.out.println("3. Dificultad");
            System.out.println("4. Salir");
            System.out.println("Elige una opción: ");
            op = sc.nextInt();
        } while (op < 1 || op > 4);
        return op;
    }

    public static ArrayList<String> setDifficulty() {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        ArrayList<String> devolver = new ArrayList<>();

        do {
            System.out.println("\n\n1. Facil");
            System.out.println("2. Medio");
            System.out.println("3. Dificil");
            System.out.println("4. Injugable");
            System.out.print("Debes elegir una dificultad: ");
            op = sc.nextInt();
        } while (op < 1 || op > 4);

        String[] palabras = null; // Inicializar el array de palabras

        switch (op) {
            case 1:
                System.out.println("Has elegido: Facil");
                palabras = new String[] { "Gato", "Perro", "Casa", "Sol", "Luna",
                        "Agua", "Flor", "Mesa", "Silla", "Arbol",
                        "Cielo", "Dedo", "Rayo", "Nube", "Ventana" };
                devolver.add("facil");
                break;
            case 2:
                System.out.println("Has elegido: Medio");
                palabras = new String[] { "Computadora", "Elefante", "Montaña", "Bicicleta", "Mariposa",
                        "Zapato", "Jardin", "Biblioteca", "Musica", "Estrella",
                        "Platano", "Dinosaurio", "Relampago", "Camiseta", "Espejo" };
                devolver.add("medio");
                break;
            case 3:
                System.out.println("Has elegido: Dificil");
                palabras = new String[] { "Anticonstitucional", "Quimica", "Filosofia", "Inmortalidad",
                        "Electroencefalograma",
                        "Persuasion", "Sinestesia", "Paralelepipedo", "Hipopotomonstrosesquipedaliofobia",
                        "Onomatopeya", "Desoxirribonucleico", "Estereotipia", "Inconmensurable",
                        "Incongruente", "Supercalifragilistico" };
                devolver.add("dificil");
                break;
            case 4:
                System.out.println("Has elegido: Injugable");
                palabras = new String[] { "Floccinaucinihilipilificacion", "Pseudopseudohipoparatiroidismo",
                        "Antidisestablishmentarianism", "Honorificabilitudinitatibus",
                        "Incopyrightable", "Dermatoglifia", "Sesquipedalio",
                        "Triskaidekafobia", "Psiconeuroendocrinología",
                        "Electroencefalograficamente", "Hepaticocolangiocistostomia",
                        "Pseudopseudohipoparatiroidismo", "Subcompartimentalizacion",
                        "Triscaidecafobia", "Honorificabilitudinitatibus" };
                devolver.add("injugable");
                break;
        }

        // Añadir las palabras al ArrayList
        if (palabras != null) {
            for (String palabra : palabras) {
                devolver.add(palabra);
            }
        }

        return devolver;
    }

    public static void loadGame() {
        Path filePath = Paths.get("res/ahorcado.txt");
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
    }

    public static void newGame(ArrayList<String> difficulty, int MAX_INTENTOS) {
        Scanner scanner = new Scanner(System.in);
        String option = difficulty.get(0);
        int intentos = 0;
        int randomNumber = (int) (Math.random() * (difficulty.size() - 1)); //Número random para pasarlo al Array difficulty y elegir la palabra
        String word = difficulty.get(randomNumber + 1).toLowerCase(); //Obtiene la palabra aleatoria
        int longWord = word.length();
        ArrayList<String> lettersUser = new ArrayList<>();

        char[] wordState = new char[longWord]; //Estado actual de la palabra ___ por ejemplo o c_s_
        for (int i = 0; i < longWord; i++) {
            wordState[i] = '_'; //Se inicializa con guiones bajos
        }

        System.out.println("\n\n\nOpción seleccionada: " + option);
        do {
            System.out.println("Intentos restantes: " + (MAX_INTENTOS - intentos));
            System.out.println("Palabra: " + String.valueOf(wordState)); // Mostrar la palabra en progreso

            char letter;
            do {
                System.out.println("Introduce una letra: ");
                String input = scanner.next();

                if (input.length() == 1 && Character.isLetter(input.charAt(0))) { //Comprueba si es una letra y solo pone una
                    letter = Character.toLowerCase(input.charAt(0)); //Se asigna la letra en minúscula
                    break; //Sale del bucle si la letra es válida
                } else {
                    System.out.println("Por favor, introduce solo una letra.");
                }
            } while (true); //Repite hasta que se introduzca una letra válida

            intentos++;

            lettersUser.add(String.valueOf(letter));
            System.out.println("Letras introducidas: " + lettersUser);

            // Verificar si la letra está en la palabra
            boolean letterFound = false;
            for (int i = 0; i < longWord; i++) {
                if (word.charAt(i) == letter) {
                    wordState[i] = letter; // Reemplazar el guion bajo por la letra correcta
                    letterFound = true;
                }
            }

            // Verificar si se ha adivinado la palabra completa
            if (String.valueOf(wordState).equals(word)) {
                System.out.println("¡Felicidades! Has adivinado la palabra: " + word);
                break;
            }

            if (intentos == MAX_INTENTOS) {
                System.out.println("Juego terminado. La palabra era: " + word);
                break;
            }

        } while (intentos != MAX_INTENTOS);

        System.out.println("Juego terminado");
    }

    public static void saveGame(String difficulty, String palabra, int intentos, String letras) {
        System.out.println("Has elegido: Guardar");

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("res/ahorcado.txt"));) {
            escritor.write("Dificultad: " + difficulty);
            escritor.newLine();
            escritor.write("Palabra: " + palabra);
            escritor.newLine();
            escritor.write("Intentos: " + intentos);
            escritor.newLine();
            escritor.write("Letras: " + letras);
            escritor.newLine();
            System.out.println("Escritura realizada.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        int opcion;
        opcion = menu();

        final int MAX_INTENTOS = 8; // Illegal modifier for parameter MAX_INTENTOS; only final is
                                    // per1mittedJava(67109220) static final int MAX_INTENTOS = 8

        switch (opcion) {
            case 1:
                System.out.println("Has elegido: Juego nuevo");
                newGame(setDifficulty(), MAX_INTENTOS);
                break;
            case 2:
                System.out.println("Has elegido: Juego cargado");
                break;
            case 3:
                System.out.println("Has elegido: Dificultad");
                break;
            case 4:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion incorrecta, vuelva a introducir una opcion");
                break;
        }
    }
}
