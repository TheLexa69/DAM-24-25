import java.io.File;

public class Ej21ConsultarFichero {
    public static void main(String[] args) throws Exception {
        try {
            File fichero = new File("res/palabras.txt");
            if (fichero.exists()) {
                System.out.printf("Nombre del archivo:%s%n", fichero.getName());
                System.out.printf("Ruta %s", fichero.getPath());
                System.out.printf("Ruta absoluta %s%n", fichero.getAbsolutePath());
                System.out.printf("Se puede escribir %s%n", fichero.canWrite());
                System.out.printf("Se puede leer %s%n", fichero.canRead());
                System.out.printf("Tamaño " + fichero.length());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
