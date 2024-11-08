package org.example.Model;

public class Pelicula {

    public enum Tematica {
        Accion, Aventura, Ciencia_Ficcion, Romance, Terror;
    }

    private String titulo;
    private String actor;
    private String guion;
    private Boolean disponible;
    private int identificador;
    private Tematica tematica;

    public Pelicula(){}

    public Pelicula(int identificador, String titulo, String actor, String guion, Boolean disponible, Tematica tematica) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.actor = actor;
        this.guion = guion;
        this.disponible = disponible;
        this.tematica = tematica;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getActor() {
        return actor;
    }

    public String getGuion() {
        return guion;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public Tematica getTematica() {
        return tematica;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "identificador='" + identificador + '\'' +
                ", titulo='" + titulo + '\'' +
                ", actor='" + actor + '\'' +
                ", guion='" + guion + '\'' +
                ", disponible=" + disponible +
                ", tematica=" + tematica +
                '}';
    }
}