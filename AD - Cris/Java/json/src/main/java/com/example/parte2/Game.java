package com.example.parte2;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

//implements serializable para poder ser escrita y leida en binario
public class Game implements Serializable {
    private String nombre;
    private String descripcion;
    private List<String> idiomas;
    private Map<String, String> comentarios;

    public Game(String nombre, String descripcion, List<String> idiomas, Map<String, String> comentarios) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idiomas = idiomas;
        this.comentarios = comentarios;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    @Override
    public String toString() {
        return "Game{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idiomas=" + idiomas +
                ", comentarios=" + comentarios +
                '}';
    }
}
