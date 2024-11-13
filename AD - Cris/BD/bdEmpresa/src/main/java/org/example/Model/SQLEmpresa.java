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

    //EXERCICIO 2.2
    //No programa anterior, engade un método para visualizar os nomes, apelidos, localidade, salario, data de nacemento,
    // nome do empregado xefe e o nome do departamento onde traballan, de aqueles empregados dunha determinada localidade.
    // O método recibirá por parámetro o nome da localidade. Para executar as sentenzas utilizarase a interface Statement e deberanse controlar os posibles erros.
    public boolean workersByCity(Connection con, String localidade){
        String query = "SELECT e.Nome, e.Apellido_1, e.Apellido_2, e.Localidade, e.Salario, e.Data_nacemento, " +
                "e2.Nome AS Nome_Xefe, d.Nome_departamento " +
                "FROM Empregado e " +
                "LEFT JOIN Empregado e2 ON e.NSS_Supervisa = e2.NSS " +
                "JOIN Departamento d ON e.Num_departamento_pertenece = d.Num_departamento " +
                "WHERE e.Localidade = '" + localidade + "'";

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String nome = resultSet.getString("Nome");
                String apellido1 = resultSet.getString("Apellido_1");
                String apellido2 = resultSet.getString("Apellido_2");
                String localidadeResult = resultSet.getString("Localidade");
                float salario = resultSet.getFloat("Salario");
                String dataNacemento = resultSet.getString("Data_nacemento");
                String nomeXefe = resultSet.getString("Nome_Xefe");
                String nomeDepartamento = resultSet.getString("Nome_departamento");

                System.out.println("Nome: " + nome + " " + apellido1 + " " + apellido2);
                System.out.println("Localidade: " + localidadeResult);
                System.out.println("Salario: " + salario);
                System.out.println("Data de Nacemento: " + dataNacemento);
                System.out.println("Nome do Empregado Xefe: " + (nomeXefe != null ? nomeXefe : "N/A"));
                System.out.println("Departamento: " + nomeDepartamento);
                System.out.println("-----------------------------------");
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error ao executar a consulta: " + e.getMessage());
        }
        return false;
    }

    /*Realiza un programa Java para establecer unha conexión co SXBD MySQL, acceda á base de datos BDEmpresa, implemente e chame os seguintes métodos.
    * Utiliza sentencias pre-compiladas (preparadas) e controla os posibles erros. Separa a chamada aos métodos da implementación deles en clases diferentes.
    * a) Fai un método para cambiar o departamento que controla un proxecto. O método recibirá como parámetros o nome do departamento e o nome do proxecto
    */
    public boolean updateDepartment(Connection con, String nomeDepartamento, String nomeProxecto){
        String queryDepartamento = "SELECT * FROM Departamento WHERE Nome_departamento = ?";
        String queryProxecto = "UPDATE Proxecto SET Num_departamento = ? WHERE Nome_proxecto = ?";

        try {
            PreparedStatement stmtDepartamento = con.prepareStatement(queryDepartamento);
            stmtDepartamento.setString(1, nomeDepartamento);
            ResultSet resultSet = stmtDepartamento.executeQuery();

            if (resultSet.next()) {
                int numDepartamento = resultSet.getInt("Num_departamento");

                PreparedStatement stmtUpdate = con.prepareStatement(queryProxecto);
                stmtUpdate.setInt(1, numDepartamento);
                stmtUpdate.setString(2, nomeProxecto);

                int rowsUpdated = stmtUpdate.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Se han actualizado " + rowsUpdated + "fila");
                    System.out.println("Departamento cambiado exitosamente para el proyecto: " + nomeProxecto);
                } else {
                    System.out.println("No se encontró el proyecto: " + nomeProxecto);
                }
            } else {
                System.out.println("No se encontró el departamento: " + nomeDepartamento);
            }

        } catch (SQLException e) {
            System.err.println("Error al cambiar el departamento: " + e.getMessage());
        }
        return false;
    }

    //CONFIRMAR EN CLASE SI ESTA BIEN
    //b) Fai un método para inserir un novo proxecto. O método recibirá como parámetro un obxecto proxecto.
    // Crea a clase proxecto, cos métodos setter e getter, e coa mesma estrutura que a táboa proxecto.
    public boolean insertProject(Connection con, Proxecto proxecto){
        String query = "INSERT INTO proxecto (num_proxecto, nome_proxecto, lugar, num_departamento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, proxecto.getNum_proxecto());
            stmt.setString(2, proxecto.getNome_proxecto());
            stmt.setString(3, proxecto.getLugar());
            stmt.setInt(4, proxecto.getNum_departamento());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se ha insertado " + rowsInserted + " fila");
                System.out.println("Proxecto inserido exitosamente: " + proxecto.getNome_proxecto());
            } else {
                System.out.println("Non se puido inserir o proxecto.");
            }
        } catch (SQLException e) {
            System.err.println("Error ao inserir o proxecto: " + e.getMessage());
        }
        return false;
    }

    //CONFIRMAR EN CLASE SI ESTA BIEN
    // c) Fai un método para borrar un proxecto. O método recibirá como parámetros o número do proxecto.
    //  Tamén debes borrar a información da asignación dos empregados ao proxecto.
    public void delProject(Connection con, int numProxecto) {
        String deleteAsignacionQuery = "DELETE FROM Empregado_Proxecto WHERE Num_proxecto = ?";
        String deleteProxectoQuery = "DELETE FROM Proxecto WHERE Num_proxecto = ?";

        try {
            // Borramos las asignaciones de los empleados al proyecto
            try (PreparedStatement stmtAsignacion = con.prepareStatement(deleteAsignacionQuery)) {
                stmtAsignacion.setInt(1, numProxecto);
                int rowsDeletedAsignacion = stmtAsignacion.executeUpdate();
                System.out.println("Asignación de empregados eliminada: " + rowsDeletedAsignacion + " filas afectadas.");
            }

            // Borramos el proyecto
            try (PreparedStatement stmtProxecto = con.prepareStatement(deleteProxectoQuery)) {
                stmtProxecto.setInt(1, numProxecto);
                int rowsDeletedProxecto = stmtProxecto.executeUpdate();
                if (rowsDeletedProxecto > 0) {
                    System.out.println("Proxecto eliminado exitosamente: " + numProxecto);
                } else {
                    System.out.println("Non se puido eliminar o proxecto. Verifique se o proxecto existe.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error ao eliminar o proxecto: " + e.getMessage());
        }
    }







//    public void showWorkersInProject(Connection con) {
//
//        String query = "SELECT * FROM empregado_proxecto";
//        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                String idEmpleado = resultSet.getString("NSS_Empregado");
//                int num_proxecto = resultSet.getInt("Num_Proxecto");
//                int horas_semanales = resultSet.getInt("Horas_Semanais");
//
//
//                System.out.println(
//                        "ID: " + idEmpleado + "\n" +
//                                "Número de proyecto" + num_proxecto + "\n" +
//                                "Número de proyecto" + horas_semanales + "\n"
//                );
//            }
//        } catch (Exception e) {
//            System.out.println("Error en showWorkProject() en SQLEmpresa.java" + e);
//            throw new RuntimeException(e);
//        }
//    }

}
