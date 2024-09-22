package Tareas_16_09_2024;

/***
 * 5. Coches

Dado un listado de modelos de coche, se generará un listado agrupado para cada marca con sus modelos. 
Por ejemplo, si tenemos los siguientes modelos en un fichero llamado coches.txt:

Mazda 5

Seat Ateca

Citröen C1

Ferrari 458 Italia

Ford Focus

Seat Tarraco

Hyundai Tucson

Mazda CX-5

Honda Civic

Seat Ibiza

Hyundai Kona

Citröen C3 Aircross

Mazda 3

Ford Fiesta

Como resultado tendremos un fichero llamado marcas.txt con el siguiente contenido:

Citröen: C1, C3 Aircross

Ferrari: 458 Italia

Ford: Fiesta, Focus

Honda: Civic

Hyundai: Kona, Tucson

Mazda: 3, 5, CX-5

Seat: Ateca, Ibiza, Tarraco
 * 
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tarea5 {

    public static void main(String[] args) {
        Path p = Path.of("res/coches.txt");
        Path outputPath = Path.of("res/marcas.txt");
        Map<String, List<String>> mapBrand = new HashMap<>();

        try (var lector = new Scanner(p)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (linea.isEmpty()) { //La linea esta vacia  ?
                    continue; //Pasamos a la siguiente
                }
                String[] partes = linea.split(" ", 2); //Guardamos en un array partes[] Marca => modelo. EJ: Citroen => C3 Aircross
                String marca = partes[0];
                String modelo = partes[1];

                // Añadir el modelo a la lista correspondiente de la marca
                mapBrand.computeIfAbsent(marca, k -> new ArrayList<>()).add(modelo); //computeIfAbsent es una función que se encarga de anadir el modelo a la lista correspondiente de la marca
                //computeifAbsent hace que SI la LLAVE no existe, añade el modelo a la lista correspondiente de la marca
            }

            StringBuilder contenido = new StringBuilder();  // Definimos contenido como StringBuilder para volcar los datos de Map. Es el archivo de salida
            for (Map.Entry<String, List<String>> entry : mapBrand.entrySet()) {
                contenido.append(entry.getKey())
                         .append(": ")
                         .append(String.join(", ", entry.getValue()))
                         .append(System.lineSeparator());
            }

            Files.writeString(outputPath, contenido.toString(), StandardOpenOption.CREATE); //Generamos en la ruta de la salida el archivo (outputPath -> es la ruta) con el contenido con un .toString() y la tercera opcion StandardOpenOption.CREATE que permite sobreescribir el fichero  
            
            if(Files.exists(outputPath)){
                System.out.println("Lista guardada como marcas.txt");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
