package ficherosxml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.List;

public class VisualizarXML {
    public static void main(String[] args) {
        try {
            // Cargamos el fichero
            File fichero = new File("personas.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(fichero);

            // Obtenemos la raiz
            Element rootElement = document.getRootElement();
            List<Element> personas = rootElement.getChildren("persona");

            // Recorremos los nodos persona
            System.out.println("\nContenido del archivo XML:");
            for (Element persona : personas) {
                String nombre = persona.getChildText("nombre");
                String edad = persona.getChildText("edad");

                System.out.println("Nombre: " + nombre);
                System.out.println("Edad: " + edad);
                System.out.println("------------------------");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
