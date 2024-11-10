package org.example.View;

import org.example.Model.Pelicula;

public class Messages {

    public void showMenu(){
        System.out.println("===== MENU =====");
        System.out.println("1. Listado de películas");
        System.out.println("2. Pintado de datos de una película");
        System.out.println("3. Borrado de una película");
        System.out.println("4. Modificar una película");
        System.out.println("5. Crear nueva película");
        System.out.println("6. Saír");
        System.out.print("Seleccione una opción: ");
    }

    public void showMenuPeliculasUpdate(){
        System.out.println("¿Qué deseas actualizar?");
        System.out.println("1. Titulo");
        System.out.println("2. Actor");
        System.out.println("3. Temática");
        System.out.println("4. Guion");
        System.out.println("5. Disponibilidad");
        System.out.println("6. Actualizar todo");
        System.out.println("0. Salir");
    }

    public void showMenuTematica(){
        System.out.println("Seleccione la temática de la película:");
        System.out.println("1. Accion");
        System.out.println("2. Aventura");
        System.out.println("3. Ciencia Ficcion");
        System.out.println("4. Romance");
        System.out.println("5. Terror");
        System.out.print("Ingrese el número de la opción: ");
    }



    public String showAllPeliculas(int identificador, String titulo, String actor, String guion, Boolean disponible, Pelicula.Tematica tematica) {
        Pelicula pelicula = new Pelicula(identificador, titulo, actor, guion, disponible, tematica);
        return pelicula.toString();
    }
}
