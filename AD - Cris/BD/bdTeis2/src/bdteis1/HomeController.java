package bdteis1;

import java.sql.Connection;
import java.util.Scanner;

public class HomeController {
    ModelPais mp = new ModelPais();
    ConexionMySQL con = new ConexionMySQL();
    Connection connection = con.conectarMySQL();


    public void showMenu(){
        Scanner scanner = new Scanner(System.in);
        String opcion;
        try {

            do {
                System.out.println("\n--- Menú ---");
                System.out.println("1. Listar todos los países");
                System.out.println("2. Añadir un nuevo país");
                System.out.println("3. Actualizar el país");
                System.out.println("4. Borrar el país");
                System.out.println("5. Salir de la aplicación");
                System.out.print("Selecciona una opción: ");
                opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        mp.showPaises(connection);
                        break;
                    case "2":
                        mp.addPais(connection, "Finlandia", 5540720, "Helsinki", "Euro");
                        break;
                    case "3":
                        mp.updatePais(connection, 31, "Australia", null, null, null);
                        break;
                    case "4":
                        mp.deletePais(connection, 31);
                        break;
                    case "5":
                        System.out.println("Saliendo de la aplicación...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (!opcion.equals("5"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }//FIN SHOWMENU


}//FIN HOMECONTROLLER
