package Tareas_16_09_2024;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    private String secretWord;
    private char[] wordState;
    private Set<Character> lettersUser; // Cambiado a Set para evitar letras repetidas
    private int errors;
    private final int MAX_ERRORS = 8;
    private String difficulty;

    // Constructor
    public Game(String secretWord, String difficulty) {
        this.secretWord = secretWord;
        this.wordState = new char[secretWord.length()];
        for (int i = 0; i < secretWord.length(); i++) {
            wordState[i] = '_';
        }
        this.lettersUser = new HashSet<>(); // Usamos un HashSet para almacenar las letras ingresadas
        this.errors = 0;
        this.difficulty = difficulty;
    }

    // Método para mostrar el estado del juego
    public void displayGameState() {
        System.out.println("\nPalabra: " + String.valueOf(wordState));
        System.out.println("Intentos restantes: " + (MAX_ERRORS - errors));
        System.out.println("Letras introducidas: " + lettersUser);
    }

    // Método para verificar si el juego ha terminado
    public boolean isGameFinished() {
        return errors >= MAX_ERRORS || String.valueOf(wordState).equals(secretWord);
    }

    // Método para procesar la letra introducida por el usuario
    public boolean processLetter(char letter) {
        if (lettersUser.contains(letter)) {
            // Si la letra ya fue ingresada, no la procesa de nuevo
            System.out.println("\nYa has ingresado esta letra.");
            return false;
        }

        lettersUser.add(letter); // Agrega la letra al array de letras ingresadas por el usuario
        boolean letterFound = false;

        // Reemplazar los guiones bajos por la letra correcta si está en la palabra
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                wordState[i] = letter;
                letterFound = true;
            }
        }

        if (!letterFound) {
            errors++; // Solo aumentar el contador si la letra no está en la palabra
        }

        return true;
    }

    // Método para mostrar el resultado final
    public void displayFinalResult() {
        if (String.valueOf(wordState).equals(secretWord)) {
            System.out.println("\n¡Felicidades! Has adivinado la palabra: " + secretWord);
        } else {
            System.out.println("\nJuego terminado. La palabra era: " + secretWord);
        }
    }

    // Método estático para elegir la dificultad y generar una palabra aleatoria
    public static Game setDifficulty(String difficulty) {
        String[] palabras = null;

        switch (difficulty.toLowerCase()) {
            case "fácil":
                palabras = new String[] { "Gato", "Perro", "Casa", "Sol" };
                break;
            case "medio":
                palabras = new String[] { "Computadora", "Elefante", "Montaña" };
                break;
            case "difícil":
                palabras = new String[] { "Anticonstitucional", "Esternocleidomastoideo", "Electroencefalografía" };
                break;
            case "injugable":
                palabras = new String[] { "Floccinaucinihilipilificacion", "Pseudopseudohipoparatiroidismo" };
                break;
            case "hardcore":
                palabras = new String[] { "Hipopotomonstrosesquipedaliofobia",
                        "Pneumonoultramicroscopicsilicovolcanoconiosis" };
                break;
        }

        String secretWord = palabras[(int) (Math.random() * palabras.length)].toLowerCase();
        return new Game(secretWord, difficulty);
    }
}
