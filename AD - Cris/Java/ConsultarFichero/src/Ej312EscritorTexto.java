import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ej312EscritorTexto {
    public static void main(String[] args) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("res/numeros.txt"));) {
            for (int i = 1; i <= 10; i++) {
                escritor.write("Línea número " + i);
                escritor.newLine();
            }
            System.out.println("Escritura realizada.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}