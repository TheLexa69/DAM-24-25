package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class JsonXmlTransformation {

    public static void main(String[] args) {
        try {
            // -- -- CREAR un objeto JSON
            ObjectMapper jsonMapper = new ObjectMapper();
            Cliente cliente = new Cliente("Juan Pérez", "Calle Falsa 123");
            String json = jsonMapper.writeValueAsString(cliente);
            System.out.println("JSON: " + json);

            // Guardar JSON en archivo
            File jsonFile = new File("cliente.json");
            jsonMapper.writeValue(jsonFile, cliente);
            System.out.println("JSON guardado en archivo: " + jsonFile.getAbsolutePath());


            /**-----------------------------**/

            // Convertir JSON a XML
            XmlMapper xmlMapper = new XmlMapper();
            String xmlFromJson = xmlMapper.writeValueAsString(cliente);
            System.out.println("XML desde JSON: " + xmlFromJson);

            // Guardar XML en archivo
            File xmlFile = new File("cliente.xml");
            xmlMapper.writeValue(xmlFile, cliente);
            System.out.println("XML guardado en archivo: " + xmlFile.getAbsolutePath());


            /**-----------------------------**/


            // Leer el XML y convertir a JSON
            Cliente clienteDesdeXml = xmlMapper.readValue(xmlFile, Cliente.class);
            String jsonDesdeXml = jsonMapper.writeValueAsString(clienteDesdeXml);
            System.out.println("JSON desde XML: " + jsonDesdeXml);

            // Guardar el JSON convertido desde XML en archivo
            File jsonDesdeXmlFile = new File("cliente_desde_xml.json");
            jsonMapper.writeValue(jsonDesdeXmlFile, clienteDesdeXml);
            System.out.println("JSON guardado desde XML en archivo: " + jsonDesdeXmlFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Cliente {
        private String nombre;
        private String direccion;

        public Cliente() { }

        public Cliente(String nombre, String direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
    }
}