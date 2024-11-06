/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdteis1;

import java.sql.*;

/**
 * @author luis.canal
 */
public class BdTeis1 {

    public static void main(String[] args) {
        //ModelPais mp = new ModelPais();

        HomeController hc = new HomeController();

        hc.showMenu();

        /*try {
            ConexionMySQL con = new ConexionMySQL();
            Connection connection = con.conectarMySQL();

            //ENSEÑAMOS LOS PAISES
            mp.showPaises(connection);

            //INSERTAMOS UN PAIS
            boolean success = mp.addPais(connection, "Finlandia", 5540720, "Helsinki", "Euro");
            System.out.println(success == true ? "Se ha insertado correctamente" : "No se ha insertado correctamente");
            mp.showPaises(connection);

            //ACTUALIZAMOS UN PAIS SEGUN SUS NECESIDADES (PAIS o MONEDAS o CAPITAL....)
            boolean success2 = mp.updatePais(connection, 31, "Australia", null, null, null);
            System.out.println(success2 == true ? "Se ha actualizado correctamente" : "No se ha actualizado correctamente");
            mp.showPaises(connection);

            //BORRAMOS Y ACTUALIZAMOS EL AUTOINCREMENT
            boolean success3 = mp.deletePais(connection, 30);
            System.out.println(success3 == true ? "Se ha borrado correctamente" : "No se ha borrado correctamente");
            mp.showPaises(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


}

