package ficherosxml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LecturaXML {
    public static void main(String[] args) {

        try {
            File fichero = new File("personas.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(fichero);

            //Obtenemos la raiz
            Element root = document.getRootElement();
            //Obtenemos los nodos personas 
            List<Persona> personas = new ArrayList<>();
            List<Element> personasElements = root.getChildren("persona");

            //Recorremos los nodos persona
            for (Element personaElement : personasElements) {
                //Obtenemos el nombre y la edad
                String nombre = personaElement.getChildText("nombre");
                int edad = Integer.parseInt(personaElement.getChildText("edad"));
                //Creamos la persona
                Persona persona = new Persona(nombre, edad);
                personas.add(persona);
            }
            //Imprimimos las personas
            for (Persona persona : personas) {
                System.out.println(persona);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
