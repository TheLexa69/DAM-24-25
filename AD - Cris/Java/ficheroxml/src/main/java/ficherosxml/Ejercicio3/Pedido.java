package ficherosxml.Ejercicio3;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {
    private int idPedido;
    private String nomeCliente;
    private List<Producto> productos;
    
    public Pedido(int idPedido, String nomeCliente, List<Producto> productos) {
        this.idPedido = idPedido;
        this.nomeCliente = nomeCliente;
        this.productos = productos;
    }

    public int getIdPedido() {
        return this.idPedido;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }

    @Override
    public String toString() {
        return "Pedido{id: " + this.idPedido + ", cliente: " + this.nomeCliente + ", productos: " + this.productos + "}";
    }
}
