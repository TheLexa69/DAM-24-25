package ficherosxml.Ejercicio3;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class CrearXMLPedidos {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("Pedidos.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {

            List<Pedido> pedidos = (List<Pedido>) ois.readObject();

            // Creamos el elemento raíz <pedidos>
            Element rootElement = new Element("pedidos");
            Document doc = new Document(rootElement);

            // Recorremos la lista de pedidos
            for (Pedido pedido : pedidos) {
                // Creamos el elemento <pedido>
                Element pedidoElement = new Element("pedido");

                // Añadimos el id del pedido y nombre cliente
                Element idPedido = new Element("idPedido");
                idPedido.setText(String.valueOf(pedido.getIdPedido()));

                Element nombreCliente = new Element("nomeCliente");
                nombreCliente.setText(pedido.getNomeCliente());

                // Anadimos los elementos <idPedido> y <nomeCliente> al elemento <pedido>
                pedidoElement.addContent(idPedido);
                pedidoElement.addContent(nombreCliente);

                Element productsElement = new Element("productos");

                // Recorremos la lista de productos
                for (Producto producto : pedido.getProductos()) {
                    // Crear el elemento <producto> y sus hijos
                    Element productoElement = new Element("producto");

                    Element idProducto = new Element("idProducto");
                    idProducto.setText(String.valueOf(producto.getIdProducto()));

                    Element descripcion = new Element("descricion");
                    descripcion.setText(producto.getDescricion());

                    Element precio = new Element("idPrezo");
                    precio.setText(String.valueOf(producto.getPrecio()));

                    productoElement.addContent(idProducto);
                    productoElement.addContent(descripcion);
                    productoElement.addContent(precio);

                    // Añadimos <producto> a <productos>
                    productsElement.addContent(productoElement);
                }

                // Añadimos <productos> a <pedido>
                pedidoElement.addContent(productsElement);

                // Añadimos <pedido> a <pedidos> (rootElement)
                rootElement.addContent(pedidoElement);
            }
            // Guardar el documento XML en un archivo
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(doc, new FileOutputStream("Pedidos.xml"));

            System.out.println("Archivo 'Pedidos.xml' creado con éxito.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
