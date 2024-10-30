package bdteis1;

import java.sql.*;

public class ConexionMySQL {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "paises";
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE + "?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            //Class.forName(DRIVER);
            //conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String url = "jdbc:mysql://localhost:3306/mundo";
            Connection conexion = DriverManager.getConnection( url,"root", "root");
            return conexion;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}