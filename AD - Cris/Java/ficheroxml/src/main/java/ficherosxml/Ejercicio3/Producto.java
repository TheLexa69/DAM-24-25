package ficherosxml.Ejercicio3;

import java.io.Serializable;

public class Producto implements Serializable {
    private int idProducto;
    private String descricion;
    private double precio;

    public Producto(int idProducto, String descricion, double precio) {
        this.idProducto = idProducto;
        this.descricion = descricion;
        this.precio = precio;
    }

    public int getIdProducto() {
        return this.idProducto;
    }

    public String getDescricion() {
        return this.descricion;
    }

    public double getPrecio() {
        return this.precio;
    }

    @Override
    public String toString(){
        return "Producto{id: " + this.idProducto + ", descripción: " + this.descricion + ", precio: " + this.precio + "}";
    }
}
