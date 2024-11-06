package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author luis.canal
 */
public class PaisBD {

    // Crea conexión y devueve un objeto CONNECTION
    public Connection conectar() {

        Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String jdbcUrl = "jdbc:mariadb://localhost:3306/teispaises";

            con = (Connection) DriverManager.getConnection(jdbcUrl, "root", "");

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return con;
    }

    public ArrayList<Pais> leer(String... param) {

        String condiciones = "";
        if (param.length > 0) {
            condiciones = param[0];
        }
        ResultSet rs = null;

        String sql = "SELECT * FROM paises " + condiciones;

        try {
            Connection con = conectar();
            Statement stm = con.createStatement();
            rs = stm.executeQuery(sql);
            con.close();                //SELECT COUNT(*) FROM information_schema.PROCESSLIST 
            // Cada vez abre una nueva conexión

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }

        // convertir RS en Lista<Pais>
        return convertirRS_Lista(rs);
      
    }

    public ArrayList<Pais> convertirRS_Lista(ResultSet rs) {
        ArrayList<Pais> lista = new ArrayList<>();
        try {
            while (rs.next()) {
                Pais pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNombre(rs.getString("nombre"));
                pais.setCapital(rs.getString("capital"));
                pais.setHabitantes(rs.getInt("habitantes"));
                pais.setMoneda(rs.getString("moneda"));

                lista.add(pais);
            }

        } catch (SQLException ex) {
            System.out.println("Error convirtiendo datos " + ex.getMessage());
        }
        return lista;

    }

    public int insertar(Pais pais) {
        int cantidad = 0;

        Statement stmt;
        String sql = "INSERT INTO paises (nombre, capital, habitantes, moneda) VALUES (";
        sql += "'" + pais.getNombre() + "',";
        sql += "'" + pais.getCapital() + "',";
        sql += pais.getHabitantes() + ",";
        sql += "'" + pais.getMoneda() + "')";

        try {
            Connection con = conectar();
            stmt = con.createStatement();

            cantidad = stmt.executeUpdate(sql);
            con.close();                //SELECT COUNT(*) FROM information_schema.PROCESSLIST 

        } catch (SQLException ex) {
            System.out.println("Error grabando pais " + ex.getMessage());
        }

        return cantidad;
    }

    public int modificar(Pais pais) {
        int cantidad = 0;

        Statement stmt;
        String sql = "UPDATE paises SET "
                + "nombre = '" + pais.getNombre() + "', "
                + "capital = '" + pais.getCapital() + "', "
                + "habitantes = " + pais.getHabitantes() + ", "
                + "moneda = '" + pais.getMoneda() + "' "
                + "WHERE id = " + pais.getId();

        try {
            Connection con = conectar();
            stmt = con.createStatement();

            cantidad = stmt.executeUpdate(sql);
            con.close();                //SELECT COUNT(*) FROM information_schema.PROCESSLIST 

        } catch (SQLException ex) {
            System.out.println("Error modificando pais " + ex.getMessage());
        }

        return cantidad;
    }

    public int borrar(int id) {
        int cantidad = 0;

        Statement stmt;
        String sql = "DELETE FROM paises "
                + " WHERE id = " + id;

        try {
            Connection con = conectar();
            stmt = con.createStatement();

            cantidad = stmt.executeUpdate(sql);
            con.close();                //SELECT COUNT(*) FROM information_schema.PROCESSLIST 

        } catch (SQLException ex) {
            System.out.println("Error borrando pais " + ex.getMessage());
        }

        return cantidad;
    }
}
