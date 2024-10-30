/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdteis1;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author luis.canal
 */
public class BdTeis1 {
    private ConexionMySQL SQL = new ConexionMySQL();

    public static void main(String[] args) {
        try {

            // Establish the connection
            String url = "jdbc:mysql://localhost:3306/paises";
            Connection conexion = DriverManager.getConnection( url,"root", "root");

            // Create a statement
            Statement s = conexion.createStatement();

            // Execute the query
            ResultSet rs = s.executeQuery("SELECT * FROM paises"); // Corrected table name

            // Process the result set
            while (rs.next()) {
                System.out.println("nombre_pais: " + rs.getString("nombre_pais") + ", habitantes: " +
                        rs.getString("habitantes") + " " + rs.getString("capital"));
            }

            // Close the resources
            rs.close();
            s.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*private void conexion() {
        try (Connection conn = SQL.conectarMySQL()) {
            if (conn != null) {
                System.out.println("Connected to the database.");
                // You can add your SQL operations here
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}

