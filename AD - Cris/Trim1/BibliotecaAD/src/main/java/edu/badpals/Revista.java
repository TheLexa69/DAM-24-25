package edu.badpals;

import java.io.Serializable;

public class Revista extends Publicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String numero = "";

    public Revista(){
        super();
    }

    public Revista(String ISBN, String titulo, String añoPublicacion, String numero) {
        super(ISBN, titulo, añoPublicacion);
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Revista:\n")
                .append("\tISBN: ").append(getISBN()).append("\n")
                .append("\ttítulo: ").append(getTitulo()).append("\n")
                .append("\taño: ").append(getAnoPublicacion()).append("\n")
                .append("\tnúmero: ").append(getNumero()).append("\n");
        return sb.toString();
    }
}
