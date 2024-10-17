package ficherosxml.Ejercicio2;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContarElementos {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: ContarElementos <fichero>");
            return;
        }

        try {
            File fichero = new File(args[0]);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(fichero);

            // Obtenemos la raiz
            Element root = document.getRootElement();

            Map<String, Integer> conteoEtiquetas  = new HashMap<>();
            
            contarEtiquetas(root, conteoEtiquetas);

            System.out.println("Conteo de etiquetas:");
            for (Map.Entry<String, Integer> entry : conteoEtiquetas.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            System.out.println("Número de elementos: " + root.getChildren().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursivamente contar las etiquetas en el elemento y sus hijos y 
     * guardar el conteo en el mapa conteoEtiquetas.
     * 
     * @param elemento elemento del que se van a contar las etiquetas
     * @param conteoEtiquetas mapa en el que se va a guardar el conteo de etiquetas
     */
    private static void contarEtiquetas(Element elemento, Map<String, Integer> conteoEtiquetas) {
        String nombreEtiqueta = elemento.getName();

        // Incrementar el contador de la etiqueta en el mapa
        conteoEtiquetas.put(nombreEtiqueta, conteoEtiquetas.getOrDefault(nombreEtiqueta, 0) + 1);

        // Recursivamente contar las etiquetas de los hijos del elemento
        List<Element> hijos = elemento.getChildren();
        for (Element hijo : hijos) {
            contarEtiquetas(hijo, conteoEtiquetas);
        }
    }
}
