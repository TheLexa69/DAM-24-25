package ficherosxml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LecturaBinarioEscrituraXML {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("personas.bin");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Element root = new Element("personas");
            Document doc = new Document(root);

            try {
                while (true) {
                    // Leemos el nombre y la edad de una persona
                    String nombre = ois.readUTF();
                    int edad = ois.readInt();

                    // Creamos un elemento persona con el nombre y la edad
                    Element personaElement = new Element("persona");

                    Element nombreElement = new Element("nombre");
                    nombreElement.setText(nombre);
                    Element edadElement = new Element("edad");
                    edadElement.setText(String.valueOf(edad));

                    // Anadimos el nombre y la edad al elemento persona
                    personaElement.addContent(nombreElement);
                    personaElement.addContent(edadElement);
                    // Anadimos el elemento persona al elemento root
                    root.addContent(personaElement);
                }
            } catch (IOException e) {
                System.out.println("aa");

                e.printStackTrace();
            }

            // Guardamos el documento XML en un archivo
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(doc, new FileOutputStream("personas.xml"));

            System.out.println("Archivo XML creado con éxito.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
