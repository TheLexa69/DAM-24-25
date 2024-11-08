package org.example.Controller;

import org.example.Model.Conexion;
import org.example.Model.Pelicula;
import org.example.Model.SQLPeliculas;
import org.example.View.Messages;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class HomeController {
    public Conexion connection = new Conexion();
    public SQLPeliculas sqlpeliculas = new SQLPeliculas();
    Scanner scanner = new Scanner(System.in);
    Messages messages = new Messages();

    /*
     *
     *
     */
    public void listarPeliculas() throws SQLException {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlpeliculas.showAllFilms(c);
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
    }

    public void listarPelicula() {
        int cntdPeliculas = 0;
        int slcPelicula;

        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                cntdPeliculas = sqlpeliculas.cntFilms(c);

                do {
                    System.out.print("Dime el ID de la película de la que quieres información (o escribe 'cancelar' para salir): ");

                    // Verificar si la entrada es un número o "cancelar"
                    if (scanner.hasNextInt()) {
                        slcPelicula = scanner.nextInt();
                        if (slcPelicula > 0 && slcPelicula <= cntdPeliculas) {
                            sqlpeliculas.showFilm(c, slcPelicula);
                        } else {
                            System.out.println("ID no válido. Por favor, inténtalo de nuevo.");
                        }
                    } else {
                        String input = scanner.next();
                        if (input.equalsIgnoreCase("cancelar")) {
                            System.out.println("Cancelando la búsqueda de información.");
                            return; // Salir del método
                        } else {
                            System.out.println("Entrada no válida. Por favor, introduce un número o 'cancelar'.");
                        }
                    }
                } while (true);

            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void borrarPelicula() {
        boolean salir = false;

        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                int cntdPeliculas = sqlpeliculas.cntFilms(c);
                int slcPelicula;

                do {
                    System.out.print("Dime el ID de la película que quieres borrar (o escribe 'cancelar' para salir): ");

                    // Verificar si la entrada es un número o "cancelar"
                    if (scanner.hasNextInt()) {
                        slcPelicula = scanner.nextInt();
                        if (slcPelicula > 0 && slcPelicula <= cntdPeliculas) {
                            sqlpeliculas.delFilm(c, slcPelicula);
                            System.out.println("Película borrada con éxito.");
                            salir = true; // Salir del bucle después de borrar
                        } else {
                            System.out.println("ID no válido. Por favor, inténtalo de nuevo.");
                        }
                    } else {
                        String input = scanner.next();
                        if (input.equalsIgnoreCase("cancelar")) {
                            System.out.println("Cancelando la operación de borrado.");
                            return; // Salir del método
                        } else {
                            System.out.println("Entrada no válida. Por favor, introduce un número o 'cancelar'.");
                        }
                    }
                } while (!salir);

            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public void modificarPelicula() {
        int cntdPeliculas = 0;

        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                cntdPeliculas = sqlpeliculas.cntFilms(c);

                while (true) {
                    System.out.print("Dime el ID de la película que quieres actualizar (o 0 para salir): ");
                    int slcPelicula = scanner.nextInt();

                    if (slcPelicula == 0) {
                        System.out.println("Saliendo del proceso de actualización.");
                        return; // Salir del método
                    }

                    if (slcPelicula > 0 && slcPelicula <= cntdPeliculas) {
                        // Variables para los campos a actualizar
                        String titulo = null, actor = null, guion = null;
                        Pelicula.Tematica tematica = null;
                        Boolean disponible = null;

                        // Menú de opciones para actualizar
                        messages.showMenuPeliculasUpdate();

                        while (true) {
                            System.out.print("Selecciona una opción: ");
                            int opcion = scanner.nextInt();

                            switch (opcion) {
                                case 1:
                                    System.out.print("Por favor, ingrese el nuevo título de la película (o escriba 'cancelar' para cancelar): ");
                                    String inputTitulo = scanner.next();
                                    if (inputTitulo.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización del título.");
                                    } else {
                                        titulo = inputTitulo;
                                    }
                                    break;

                                case 2:
                                    System.out.print("Por favor, ingrese el nuevo actor de la película (o escriba 'cancelar' para cancelar): ");
                                    String inputActor = scanner.next();
                                    if (inputActor.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización del actor.");
                                    } else {
                                        actor = inputActor;
                                    }
                                    break;

                                case 3:
                                    System.out.print("Por favor, ingrese la nueva temática de la película (o escriba 'cancelar' para cancelar): ");
                                    String inputTematica = scanner.next();
                                    if (inputTematica.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización de la temática.");
                                    } else {
                                        try {
                                            tematica = Pelicula.Tematica.valueOf(inputTematica);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Error: temática no válida. Por favor, inténtalo de nuevo.");
                                            continue; // Volver a pedir la opción
                                        }
                                    }
                                    break;

                                case 4:
                                    System.out.print("Por favor, ingrese el nuevo guion de la película (o escriba 'cancelar' para cancelar): ");
                                    String inputGuion = scanner.next();
                                    if (inputGuion.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización del guion.");
                                    } else {
                                        guion = inputGuion;
                                    }
                                    break;

                                case 5:
                                    System.out.print("¿Está disponible la película? (true/false) (o escriba 'cancelar' para cancelar): ");
                                    String inputDisponible = scanner.next();
                                    if (inputDisponible.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización de la disponibilidad.");
                                    } else {
                                        try {
                                            disponible = Boolean.valueOf(inputDisponible);
                                        } catch (Exception e) {
                                            System.out.println("Entrada no válida. Por favor, introduce 'true' o 'false'.");
                                            continue; // Volver a pedir la opción
                                        }
                                    }
                                    break;

                                case 6:
                                    // Actualizar todos los campos con opción de cancelar
                                    scanner.nextLine();
                                    System.out.print("Por favor, ingrese el nuevo título de la película (o escriba 'cancelar' para cancelar): ");
                                    inputTitulo = scanner.nextLine();
                                    if (inputTitulo.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización del título.");
                                    } else {
                                        titulo = inputTitulo;
                                    }

                                    System.out.print("Por favor, ingrese el nuevo actor de la película (o escriba 'cancelar' para cancelar): ");
                                    inputActor = scanner.nextLine();
                                    if (inputActor.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización del actor.");
                                    } else {
                                        actor = inputActor;
                                    }

                                    System.out.print("Por favor, ingrese la nueva temática de la película (o escriba 'cancelar' para cancelar): ");
                                    inputTematica = scanner.nextLine();
                                    if (inputTematica.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización de la temática.");
                                    } else {
                                        try {
                                            tematica = Pelicula.Tematica.valueOf(inputTematica);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Error: temática no válida. Por favor, inténtalo de nuevo.");
                                            continue; // Volver a pedir la opción
                                        }
                                    }

                                    System.out.print("¿Está disponible la película? (true/false) (o escriba 'cancelar' para cancelar): ");
                                    inputDisponible = scanner.nextLine();
                                    if (inputDisponible.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización de la disponibilidad.");
                                    } else {
                                        try {
                                            disponible = Boolean.valueOf(inputDisponible);
                                        } catch (Exception e) {
                                            System.out.println("Entrada no válida. Por favor, introduce 'true' o 'false'.");
                                            continue; // Volver a pedir la opción
                                        }
                                    }

                                    System.out.print("Por favor, ingrese el nuevo guion de la película (o escriba 'cancelar' para cancelar): ");
                                    inputGuion = scanner.nextLine();
                                    if (inputGuion.equalsIgnoreCase("cancelar")) {
                                        System.out.println("Cancelando la actualización del guion.");
                                    } else {
                                        guion = inputGuion;
                                    }
                                    //scanner.next();



                                    break;

                                case 0:
                                    System.out.println("Saliendo de la actualización.");
                                    return; // Salir del método
                                default:
                                    System.out.println("Opción no válida. Por favor, inténtalo de nuevo.");
                                    continue; // Volver a mostrar el menú
                            }

                            // Si se ha seleccionado al menos un campo, realizar la actualización
                            if (titulo != null || actor != null || tematica != null || guion != null || disponible != null) {
                                sqlpeliculas.updateFilm(c, slcPelicula, titulo, actor, tematica, guion, disponible);
                                System.out.println("Película actualizada con éxito.");

                                // Preguntar si desea actualizar otra película
                                System.out.print("¿Deseas actualizar otra película? (sí/no): ");
                                String respuesta = scanner.nextLine();
                                if (respuesta.equalsIgnoreCase("no")) {
                                    System.out.println("Saliendo del proceso de actualización.");
                                    return; // Salir del método
                                } else if (!respuesta.equalsIgnoreCase("sí") && !respuesta.equalsIgnoreCase("si")) {
                                    System.out.println("Respuesta no válida. Saliendo del proceso.");
                                    return; // Salir del método
                                }
                                break; // Volver al inicio del bucle para pedir otro ID
                            } else {
                                System.out.println("No se ha realizado ningún cambio.");
                            }
                        }
                    } else {
                        System.out.println("ID no válido. Por favor, inténtalo de nuevo.");
                    }
                }
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public boolean nuevaPelicula(){
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlpeliculas.insertFilm(c);
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
        return false;
    }

}
