package Tareas_16_09_2024;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static int menu() {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\n1. Nuevo juego\n2. Cargar juego\n3. Salir");
            System.out.print("Elige una opción: ");
            op = sc.nextInt();
        } while (op < 1 || op > 3);
        return op;
    }

    public static String chooseDifficulty() {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\nElige una dificultad:\n1. Fácil\n2. Medio\n3. Difícil\n4. Injugable"
                    + "\n5. No lo intentes, pero no lo intentes");
            System.out.print("Opción: ");
            op = sc.nextInt();
        } while (op < 1 || op > 5);

        switch (op) {
            case 1:
                return "fácil";
            case 2:
                return "medio";
            case 3:
                return "difícil";
            case 4:
                return "injugable";
            case 5:
                return "hardcore";
        }
        return "fácil"; // valor predeterminado
    }

    // Método para guardar el estado del juego
    public static void saveGame(Game game) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("res/savedGame.ser"))) {
            out.writeObject(game);
            System.out.println("Juego guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el juego: " + e.getMessage());
        }
    }

    // Método para cargar el estado del juego
    public static Game loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("res/savedGame.ser"))) {
            return (Game) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No hay un juego guardado. Por favor, inicia un nuevo juego.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el juego: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = null;
        boolean salir = false;
        String respuesta = "";
        do {
            do {
                System.out.print("\n\n¿Quieres recuperar una partida anterior? (s/n): ");
                respuesta = sc.next();
            } while (respuesta.length() != 1 || (respuesta.charAt(0) != 's' && respuesta.charAt(0) != 'n'));

            if (respuesta.equalsIgnoreCase("s")) {
                game = loadGame();
                if (game == null) {
                    System.out.println("No hay una partida guardada.");
                } else {
                    break; // Si se carga correctamente el juego, salimos del bucle
                }
            }

            int opcion = menu();

            switch (opcion) {
                case 1: // NUEVO JUEGO
                    String difficulty = chooseDifficulty();
                    game = Game.setDifficulty(difficulty);
                    break;
                case 2: // CARGAR JUEGO
                    game = loadGame();
                    if (game == null) {
                        System.out.println("No hay una partida guardada.");
                        continue; // Volver al menú si no se puede cargar un juego
                    }
                    break;
                case 3: // SALIR
                    System.out.println("Saliendo...");
                    salir = true;
                    break;
            }
        } while (!salir && game == null); // Si el juego no se carga o se inicia correctamente, repetir el menú

        // Si el jugador eligió salir, terminamos el programa
        if (salir) {
            return;
        }

        // Si el juego no ha terminado enseñamos el juego
        while (!game.isGameFinished()) {
            game.displayGameState(); // Enseñamos el display del juego: Letra, Intentos y _____

            System.out.print("Introduce una letra o escribe 'guardar' para guardar y salir: ");
            String input = sc.next().toLowerCase();

            if (input.equals("guardar")) {
                saveGame(game);
                System.out.println("Juego guardado. Saliendo...");
                break;
            }

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                char letter = input.charAt(0);
                game.processLetter(letter);
            } else {
                System.out.println("Entrada inválida, por favor introduce una letra.");
            }
        }

        if (game.isGameFinished()) {
            game.displayFinalResult();
            System.out.print("¿Quieres guardar la partida? (s/n): ");
            if (sc.next().equalsIgnoreCase("s")) {
                saveGame(game);
            }
        }
    }
}
