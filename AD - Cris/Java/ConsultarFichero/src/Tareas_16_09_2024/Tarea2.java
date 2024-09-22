package Tareas_16_09_2024;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import static java.lang.Character.toLowerCase;
import java.util.HashMap;
import java.util.Map;

/***
 * 2. Búsqueda de carácteres
 * 
 * Haz un programa que dado un fichero y un carácter cuente el número de
 * ocurrencias de ese carácter en el fichero. Variante: dado un fichero
 * encuentre el carácter más usado.
 */
public class Tarea2 {
    public static void main(String[] args) {
        Path p = Path.of("res/lorem.txt");
        Scanner sc = new Scanner(System.in);

        try (var lector = new Scanner(p)) {

            Map<Character, Integer> contadorCaracteres = new HashMap<>();
            System.out.print("\nIntroduce una letra: ");
            char letra = sc.nextLine().charAt(0);
            int contador = 0;

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                System.out.println("\nLínea: " + linea);

                for (int i = 0; i < linea.length(); i++) {
                    char letraTXT = toLowerCase(linea.charAt(i));

                    if (letraTXT == toLowerCase(letra)) {
                        contador++;
                    }

                    contadorCaracteres.put(letraTXT, contadorCaracteres.getOrDefault(letraTXT, 0) + 1); //Es un mapa de arrays tipo key:valor. [0] = letra, [1] = conteo. Si no encuentra la letra se añade sino la suma.
                }
            }

            System.out.println("Conteo total de '" + letra + "': " + contador);

            char caracterMasUsado = ' ';
            int maxOcurrencias = 0;

            for (Map.Entry<Character, Integer> entry : contadorCaracteres.entrySet()) {
                if (entry.getValue() > maxOcurrencias) {
                    maxOcurrencias = entry.getValue();
                    caracterMasUsado = entry.getKey();
                }
            }

            System.out.println(
                    "El carácter más usado es '" + caracterMasUsado + "' con " + maxOcurrencias + " ocurrencias.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            sc.close();
        }
    }

}
