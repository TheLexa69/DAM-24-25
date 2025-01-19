package edu.badpals;

public class Persona {
    private String cadena;
    private int longitud;
    private String iniciales;

    public Persona(String cadena) {
        this.cadena = cadena;
        this.longitud = cadena.length();
        this.iniciales = obtenerIniciales(cadena);
    }

    private String obtenerIniciales(String cadena) {
        String[] palabras = cadena.split(" ");
        StringBuilder iniciales = new StringBuilder();
        for (String palabra : palabras) {
            iniciales.append(palabra.charAt(0));
        }
        return iniciales.toString();
    }

    public String getCadena() {
        return cadena;
    }

    public int getLongitud() {
        return longitud;
    }

    public String getIniciales() {
        return iniciales;
    }
}