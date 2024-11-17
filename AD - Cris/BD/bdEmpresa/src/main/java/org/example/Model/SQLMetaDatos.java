package org.example.Model;

import org.example.View.Messages;

import java.sql.*;

public class SQLMetaDatos {
    Messages messages = new Messages();

    // Exercicio 3.1: Información sobre o SXBD e a conexión
    public void mostrarInfoConexion(Connection con) {
        try {
            DatabaseMetaData metaDatos = con.getMetaData();
            System.out.println("Nome do SXBD: " + metaDatos.getDatabaseProductName());
            System.out.println("Versión do SXBD: " + metaDatos.getDatabaseProductVersion());
            System.out.println("Versión principal do SXBD: " + metaDatos.getDatabaseMajorVersion());
            System.out.println("Versión secundaria do SXBD: " + metaDatos.getDatabaseMinorVersion());
            System.out.println("Nome do conectador JDBC: " + metaDatos.getDriverName());
            System.out.println("Versión principal do conectador JDBC: " + metaDatos.getJDBCMajorVersion());
            System.out.println("Versión secundaria do conectador JDBC: " + metaDatos.getJDBCMinorVersion());
            System.out.println("Versión do conectador JDBC: " + metaDatos.getDriverVersion());
            System.out.println("URL da base de datos: " + metaDatos.getURL());
            System.out.println("Usuario conectado: " + metaDatos.getUserName());
            System.out.println("Base de datos de só lectura: " + metaDatos.isReadOnly());
        } catch (SQLException e) {
            System.err.println("Erro ao obter a información de metadatos: " + e.getMessage());
        }
    }

    // Exercicio 3.2.a: Información de todas as táboas
    public void mostrarInformacionTaboas(Connection con) {
        try {
            DatabaseMetaData metaDatos = con.getMetaData();
            ResultSet rs = metaDatos.getTables(null, null, "%", new String[] {"TABLE"});
            while (rs.next()) {
                System.out.println("Esquema: " + rs.getString("TABLE_SCHEM") +
                        ", Nome da táboa: " + rs.getString("TABLE_NAME") +
                        ", Tipo: " + rs.getString("TABLE_TYPE"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a información de táboas: " + e.getMessage());
        }
    }

    // Exercicio 3.2.b: Información de columnas
    public void mostrarInformacionColumnas(Connection con, String esquema, String taboa) {
        try {
            DatabaseMetaData metaDatos = con.getMetaData();
            ResultSet rs = metaDatos.getColumns(null, esquema, taboa, "%");
            while (rs.next()) {
                System.out.println("Nome da columna: " + rs.getString("COLUMN_NAME") +
                        ", Tipo de datos: " + rs.getString("TYPE_NAME") +
                        ", Tamaño: " + rs.getInt("COLUMN_SIZE") +
                        ", Admite nulos: " + (rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable ? "Sí" : "Non"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a información de columnas: " + e.getMessage());
        }
    }

    // Exercicio 3.2.c: Información de procedementos
    public void mostrarInformacionProcedementos(Connection con) {
        try{
            DatabaseMetaData metaDatos = con.getMetaData();
            ResultSet rs = metaDatos.getProcedures(null, null, "%");
            while (rs.next()) {
                System.out.println("Nome do procedemento: " + rs.getString("PROCEDURE_NAME") +
                        ", Esquema: " + rs.getString("PROCEDURE_SCHEM"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a información de procedementos: " + e.getMessage());
        }
    }

    // Exercicio 3.2.d: Información de claves primarias
    public void mostrarClavesPrimarias(Connection con, String esquema, String taboa) {
        try {
            DatabaseMetaData metaDatos = con.getMetaData();
            ResultSet rs = metaDatos.getPrimaryKeys(null, esquema, taboa);
            while (rs.next()) {
                System.out.println("Columna de clave primaria: " + rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a información de claves primarias: " + e.getMessage());
        }
    }

    // Exercicio 3.2.e: Información de claves foráneas
    public void mostrarClavesForaneas(Connection con, String esquema, String taboa) {
        try{
            DatabaseMetaData metaDatos = con.getMetaData();
            ResultSet rs = metaDatos.getImportedKeys(null, esquema, taboa);
            while (rs.next()) {
                System.out.println("Columna de clave foránea: " + rs.getString("FKCOLUMN_NAME") +
                        ", Táboa referenciada: " + rs.getString("PKTABLE_NAME") +
                        ", Columna referenciada: " + rs.getString("PKCOLUMN_NAME"));
            }
            if (!rs.next()){
                System.out.println("No contiene clave foraneas");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter a información de claves foráneas: " + e.getMessage());
        }
    }

}
