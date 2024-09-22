//import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Ej311LectorTexto {
    public static void main(String[] args) {

        /*
         * try (BufferedReader lector = new BufferedReader(new
         * FileReader("res/palabras.txt"));) {
         * String linea;
         * while ((linea=lector.readLine()) != null)
         * System.out.println(linea);
         * } catch (IOException e) {
         * System.err.println(e.getMessage());
         * }
         */

        Path p = Path.of("res/palabras.txt");
        try (var lector = new Scanner(p);) {
            while (lector.hasNextLine())
                System.out.println(lector.nextLine());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
