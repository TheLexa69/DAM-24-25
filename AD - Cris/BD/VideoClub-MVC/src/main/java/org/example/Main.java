package org.example;

import org.example.Controller.HomeController;
import org.example.View.Messages;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void clearCache(){
        System.out.println("Limpiando caché presione enter para seguir... ");
        scanner.nextLine();
    }

    public static void main(String[] args) throws SQLException {
        HomeController hc = new HomeController();
        Messages messages = new Messages();

        String opcion;

        do {
            messages.showMenu();
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    hc.listarPeliculas(); //LISTAR LAS PELICULAS
                    clearCache();
                    break;
                case "2":
                    hc.listarPelicula(); //DATOS DE UNA PELICULA A PARTIR DE UN ID
                    clearCache();
                    break;
                case "3":
                    hc.borrarPelicula(); //BORRAR UNA PELICULA
                    clearCache();
                    break;
                case "4":
                    hc.modificarPelicula(); //ACTUALIZAR LA PELICULA
                    clearCache();
                    break;
                case "5":
                    hc.insertarPelicula();
                    clearCache();
                    break;
                case "6":
                    System.out.println("Gracias por usar el sistema. Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    hc.cerrarConexion();
            }
        } while (!opcion.equals("6")); //-- -- SALE SI ES IGUAL A 6


    }
}