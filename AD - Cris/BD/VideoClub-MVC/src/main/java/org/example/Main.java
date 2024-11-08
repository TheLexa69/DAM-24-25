package org.example;

import org.example.Controller.HomeController;
import org.example.View.Messages;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        HomeController hc = new HomeController();
        Messages messages = new Messages();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        do {
            messages.showMenu();
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    hc.listarPeliculas(); //LISTAR LAS PELICULAS
                    break;
                case "2":
                    hc.listarPelicula(); //DATOS DE UNA PELICULA A PARTIR DE UN ID
                    break;
                case "3":
                    hc.borrarPelicula(); //BORRAR UNA PELICULA
                    break;
                case "4":
                    hc.modificarPelicula(); //ACTUALIZAR LA PELICULA
                    break;
                case "5":
                    //instituto.exportarXml();
                    break;
                case "6":
                    //instituto.salir();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!opcion.equals("6")); //-- -- SALE SI ES IGUAL A 6


    }
}