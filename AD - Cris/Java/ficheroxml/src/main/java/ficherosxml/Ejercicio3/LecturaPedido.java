package ficherosxml.Ejercicio3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;


public class LecturaPedido {
    public static void main(String[] args) {

        try (FileInputStream fis = new FileInputStream("Pedidos.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            List<Pedido> pedidos = (List<Pedido>) ois.readObject();

            //pedidos.forEach(System.out::println);
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
