package bdteis1.Model;

import java.sql.*;

public class ConexionMySQL {

    private static final String DATABASE = "paises";
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";



    public Connection conectarMySQL() {
        Connection conn = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void cerrarMySQL(Connection conn) throws SQLException {
        conn.close();
    }
}