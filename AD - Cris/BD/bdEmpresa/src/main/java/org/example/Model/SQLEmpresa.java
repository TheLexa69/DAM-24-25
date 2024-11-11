package org.example.Model;

import java.sql.*;

public class SQLEmpresa {

    //EXERCICIO 2.1
    //a) Fai un método para subir o salario aos empregados dun determinado departamento.
    // O método recibirá como parámetros a cantidade a aumentar e o nome do departamento.
    public boolean incrementSalary(Connection con, float salary, int departamento) {
        //HECHO CON STATEMENT
        //FALTA COMPROBACION DE SALARIO NEGATIVO!!!!! Y DE DEPARTAMENTO QUE EXISTA!!!!!
        try{
            String query = "UPDATE empregado SET salario = (salario + " + salary + ") WHERE Num_departamento_pertenece = + " + departamento + ";";
            Statement statement = con.createStatement();

            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Se han actualizado: " + rowsAffected + " filas!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error en incrementSalary() en SQLEmpresa.java" + e);
            //throw new RuntimeException(e);
        }
        return false;
    }

    //EXERCICIO 2.1
    //b) Fai un método para inserir un novo departamento. O método recibirá como parámetros o número, nome do departamento e o nss do empregado director.
    // A data do comezo do director do departamento será a data do sistema.
    public boolean newDepartament(Connection con, int numDepartamento, String nomeDepartamento, int NSS) {
        //HECHO CON STATEMENT
        //FALTA COMPROBACIONES
        try {
            String query = "INSERT INTO departamento VALUES ("+ numDepartamento +", " + nomeDepartamento + ", " + NSS + ", now());";
            Statement statement = con.createStatement();

            System.out.println(query);
            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Se ha insertado: " + rowsAffected + " fila!");
            return true;

        } catch (Exception e){
            System.out.println("Error en newDepartamento() en SQLEmpresa.java" + e);
        }

        return false;
    }

    //EXERCICIO 2.1
    //c) Fai un método para borrar un empregado dun proxecto. O método recibirá como parámetros o nss do empregado e o número do proxecto.
    public boolean delWorkerProject(Connection con, int NSS, int numDepartamento) {
        //HECHO CON STATEMENT
        //FALTA COMPROBACIONES
        try {
            String query = "delete from empregado_proxecto where NSS_Empregado = " + NSS + " and Num_proxecto = "+ numDepartamento +";";
            Statement statement = con.createStatement();

            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Se ha borrado: " + rowsAffected + " fila!");
            return true;

        } catch (Exception e){
            System.out.println("Error en newDepartamento() en SQLEmpresa.java" + e);
        }

        return false;
    }



    public void showWorkersInProject(Connection con) {

        String query = "SELECT * FROM empregado_proxecto";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String idEmpleado = resultSet.getString("NSS_Empregado");
                int num_proxecto = resultSet.getInt("Num_Proxecto");
                int horas_semanales = resultSet.getInt("Horas_Semanais");


                System.out.println(
                        "ID: " + idEmpleado + "\n" +
                                "Número de proyecto" + num_proxecto + "\n" +
                                "Número de proyecto" + horas_semanales + "\n"
                );
            }
        } catch (Exception e) {
            System.out.println("Error en showWorkProject() en SQLEmpresa.java" + e);
            throw new RuntimeException(e);
        }
    }

}
