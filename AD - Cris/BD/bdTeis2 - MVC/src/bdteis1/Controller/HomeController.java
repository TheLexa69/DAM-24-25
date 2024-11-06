package bdteis1.Controller;

import bdteis1.Model.ConexionMySQL;
import bdteis1.Model.SQLPais;
import bdteis1.View.Messages;

import java.sql.Connection;
import java.util.Scanner;

public class HomeController {
    SQLPais mp = new SQLPais();
    ConexionMySQL con = new ConexionMySQL();
    Connection connection = con.conectarMySQL();
    Messages msg = new Messages();

    public void showMenu(){
        Scanner scanner = new Scanner(System.in);
        String opcion;
        try {

            do {
                //VIEW MESSAGES MENU()
                msg.showMenu();

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
                        mp.deletePais(connection, 30);
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
