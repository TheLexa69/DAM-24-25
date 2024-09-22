import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ej24ListarDirectorio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        Path dir = Path.of(nombre);
        System.out.println("Ficheros del directorio " + dir);
        if (Files.isDirectory(dir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir);) {
                for (Path fichero : stream) {
                    System.out.println(fichero.getFileName());
                }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }
        } else {
            System.err.println(dir.toString() + " no es un directorio");
        }

        sc.close();
    }

}
