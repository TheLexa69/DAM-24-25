package edu.badpals;

import java.io.Serializable;

public class Libro extends Publicacion implements Prestable,Serializable{
    private static final long serialVersionUID = 1L;
    private boolean prestado;

    public Libro() {
        super();
    }

    public Libro(String ISBN, String titulo, String añoPublicacion) {
        super(ISBN, titulo, añoPublicacion);
        prestado = false;
    }

    @Override
    public void presta() {
        prestado = true;
    }

    @Override
    public void devuelve() {
        prestado = false;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Libro:\n")
                .append("\tISBN: ").append(getISBN()).append("\n")
                .append("\ttítulo: ").append(getTitulo()).append("\n")
                .append("\taño: ").append(getAnoPublicacion()).append("\n")
                .append("\tprestable: ").append(estaPrestado()?"No":"Sí").append("\n");
        return sb.toString();
    }
}
