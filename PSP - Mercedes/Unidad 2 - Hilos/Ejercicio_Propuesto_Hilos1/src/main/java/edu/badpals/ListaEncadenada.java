package edu.badpals;

import java.util.LinkedList;

public class ListaEncadenada {
    private LinkedList<Persona> lista = new LinkedList<>();

    public synchronized void insertar(Persona persona) {
        // Inserción ordenada alfabéticamente
        int i = 0;
        while (i < lista.size() && lista.get(i).getCadena().compareTo(persona.getCadena()) < 0) {
            i++;
        }
        lista.add(i, persona);
    }

    public LinkedList<Persona> getLista() {
        return lista;
    }
}