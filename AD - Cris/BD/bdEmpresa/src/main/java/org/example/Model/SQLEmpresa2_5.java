package org.example.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLEmpresa2_5 {

    //EXERCICIO 2.1
    //a) Fai un método para subir o salario aos empregados dun determinado departamento.
    // O método recibirá como parámetros a cantidade a aumentar e o nome do departamento.
    public boolean incrementSalary(Connection con, float salary, int departamento) {
        //HECHO CON STATEMENT
        //FALTA COMPROBACION DE SALARIO NEGATIVO!!!!! Y DE DEPARTAMENTO QUE EXISTA!!!!!
        try {
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
            String query = "INSERT INTO departamento VALUES (" + numDepartamento + ", " + nomeDepartamento + ", " + NSS + ", now());";
            Statement statement = con.createStatement();

            System.out.println(query);
            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Se ha insertado: " + rowsAffected + " fila!");
            return true;

        } catch (Exception e) {
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
            String query = "delete from empregado_proxecto where NSS_Empregado = " + NSS + " and Num_proxecto = " + numDepartamento + ";";
            Statement statement = con.createStatement();

            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Se ha borrado: " + rowsAffected + " fila!");
            return true;

        } catch (Exception e) {
            System.out.println("Error en newDepartamento() en SQLEmpresa.java" + e);
        }

        return false;
    }

    //EXERCICIO 2.2
    //No programa anterior, engade un método para visualizar os nomes, apelidos, localidade, salario, data de nacemento,
    // nome do empregado xefe e o nome do departamento onde traballan, de aqueles empregados dunha determinada localidade.
    // O método recibirá por parámetro o nome da localidade. Para executar as sentenzas utilizarase a interface Statement e deberanse controlar os posibles erros.
    public boolean workersByCity(Connection con, String localidade) {
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

    //EXERCICIO 2.3
    /*Realiza un programa Java para establecer unha conexión co SXBD MySQL, acceda á base de datos BDEmpresa, implemente e chame os seguintes métodos.
     * Utiliza sentencias pre-compiladas (preparadas) e controla os posibles erros. Separa a chamada aos métodos da implementación deles en clases diferentes.
     * a) Fai un método para cambiar o departamento que controla un proxecto. O método recibirá como parámetros o nome do departamento e o nome do proxecto
     */
    public boolean updateDepartment(Connection con, String nomeDepartamento, String nomeProxecto) {
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
    public boolean insertProject(Connection con, Proxecto proxecto) {
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

    //EXERCICIO 2.4 //CONFIRMAR EN CLASE SI ESTA BIEN
    public List<Proxecto> getProjectsByDepartment(Connection con, String nomeDepartamento) {
        List<Proxecto> proxectos = new ArrayList<>();
        String query = "SELECT p.Num_proxecto, p.Nome_proxecto, p.Lugar, p.Num_departamento " +
                "FROM Proxecto p " +
                "JOIN Departamento d ON p.Num_departamento = d.Num_departamento " +
                "WHERE d.Nome_departamento = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nomeDepartamento);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int numProxecto = resultSet.getInt("Num_proxecto");
                String nomeProxecto = resultSet.getString("Nome_proxecto");
                String lugar = resultSet.getString("Lugar");
                int numDepartamento = resultSet.getInt("Num_departamento");

                Proxecto proxecto = new Proxecto(numProxecto, nomeProxecto, lugar, numDepartamento);
                proxectos.add(proxecto);
            }

        } catch (SQLException e) {
            System.err.println("Error ao consultar os proxectos: " + e.getMessage());
        }

        return proxectos;
    }

    //EXERCICIO 2.5
    //Crea un método que chame ao procedemento sp_cambioDomicilio. O método recibirá como parámetros o nss do empregado, a rúa, o número, o piso, o código postal e a localidade.
    //(A)
    public int updateWorker(Connection con, String _nssEmpregado, String _Rua, int _numeroRua, String _Piso, String _CP, String _Localidade) {

        String sql = "{CALL pr_cambio_domicilio(?, ?, ?, ?, ?, ?)}";
        int respuesta;
        try (CallableStatement callableStatement = con.prepareCall(sql)) {
            callableStatement.setString(1, _nssEmpregado);
            callableStatement.setString(2, _Rua);
            callableStatement.setInt(3, _numeroRua);
            callableStatement.setString(4, _Piso);
            callableStatement.setString(5, _CP);
            callableStatement.setString(6, _Localidade);

            respuesta = callableStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta = 0;
        }
        return respuesta;
    }

    //(B)
    public Proxecto getProjectData(Connection con, int numeroProyecto) {
        Proxecto proyecto = null;
        String sql = "{CALL pr_DatosProxectos(?, ?, ?, ?)}";
        try (CallableStatement callableStatement = con.prepareCall(sql)) {
            callableStatement.setInt(1, numeroProyecto);
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.INTEGER);

            callableStatement.execute();

            String nombre = callableStatement.getString(2);
            String lugar = callableStatement.getString(3);
            int numDepartamento = callableStatement.getInt(4);
            proyecto = new Proxecto(numeroProyecto, nombre, lugar, numDepartamento);

            System.out.println("PROYECTO \n" + proyecto.toString());

            return proyecto;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proyecto;
    }

    //(C)
    public void showDepartmentsWithProjects(Connection con, int numeroMinimoProxectos) {
        String sql = "{CALL pr_DepartControlaProxec(?)}";
        boolean resultadoDeSeleccion = false;

        try (CallableStatement callableStatement = con.prepareCall(sql)) {
            callableStatement.setInt(1, numeroMinimoProxectos);

            resultadoDeSeleccion = callableStatement.execute();            // Ejecutar el procedimiento

            if (resultadoDeSeleccion) {
                try (ResultSet resultSet = callableStatement.getResultSet()) {
                    System.out.println("Departamentos que controlan al menos " + numeroMinimoProxectos + " proyectos:");
                    while (resultSet.next()) {
                        int numDepartamento = resultSet.getInt("Num_Departamento");
                        String nombre = resultSet.getString("Nome_departamento");
                        int numeroProxectos = resultSet.getInt("NumeroProxectos");

                        System.out.println("Departamento ID: " + numDepartamento + ", Nombre: " + nombre + ", Proyectos: " + numeroProxectos);
                    }
                }
            } else {
                System.out.println("El procedimiento fue ejecutado, pero no devuelve resultados.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //(D)
    public void showNumberOfEmployeesByDepartment(Connection con, String nombreDepartamento) {
        String sql = "{? = CALL fn_nEmpDepart(?)}"; // Llamada a la función

        try (CallableStatement callableStatement = con.prepareCall(sql)) {
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.setString(2, nombreDepartamento);

            callableStatement.execute();

            int numeroEmpleados = callableStatement.getInt(1);
            System.out.println("Número de empleados en el departamento '" + nombreDepartamento + "': " + numeroEmpleados);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
