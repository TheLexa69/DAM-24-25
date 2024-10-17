package ficherosxml.Ejercicio3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CrearPedido {

    public static void main(String[] args) {
        Producto monitor = new Producto(100, "Monitor", 100.00);
        Producto rato = new Producto(101, "Rato", 10.00);
        Producto portatil = new Producto(102, "portatil", 600.00);
        Producto tablet = new Producto(103, "tablet", 400.00);
        Producto teclado = new Producto(104, "teclado", 200.00);



        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(new Pedido(1, "Pedro", List.of(monitor, rato)));
        pedidos.add(new Pedido(2, "Juan", List.of(portatil, tablet)));

        try (FileOutputStream fos = new FileOutputStream("Pedidos.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pedidos);
            System.out.println("Se ha creado el fichero Pedidos.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
