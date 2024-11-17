package org.example.Model;

import org.example.View.Messages;

import java.sql.*;

public class SQLResultSet {

    public Messages messages = new Messages();
    //2.6 (A)
    public void mostrarTiposResultSet(Connection con) {
        try{
            DatabaseMetaData metaDatos = con.getMetaData();

            // Imprimimos los tipos de ResultSet soportados
            System.out.println("Tipos de ResultSet soportados:");
            System.out.println("ResultSet.TYPE_FORWARD_ONLY: " +
                    metaDatos.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
            System.out.println("ResultSet.TYPE_SCROLL_INSENSITIVE: " +
                    metaDatos.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
            System.out.println("ResultSet.TYPE_SCROLL_SENSITIVE: " +
                    metaDatos.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));

            // Imprimimos los tipos de concorrencia soportados
            System.out.println("Tipos de concorrencia soportados:");
            System.out.println("ResultSet.CONCUR_READ_ONLY: " +
                    metaDatos.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY));
            System.out.println("ResultSet.CONCUR_UPDATABLE: " +
                    metaDatos.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
        } catch (SQLException e) {
            System.err.println("Erro ao obter os metadatos da base de datos: " + e.getMessage());
        }
    }

    //2.6 (B)
    public boolean proxectoExiste(ResultSet rs, int numeroProxecto, String nomeProxecto) throws SQLException {
        while (rs.next()) {
            if (rs.getInt("Num_Proxecto") == numeroProxecto &&
                    rs.getString("Nome_proxecto").equalsIgnoreCase(nomeProxecto)) {
                return true;
            }
        }
        return false;
    }

    // Método que devolve true se o número de departamento existe na táboa departamento
    public boolean departamentoExiste(Connection con, int numeroDepartamento) {
        String consulta = "SELECT COUNT(*) FROM departamento WHERE Num_departamento = " + numeroDepartamento;
        try (
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(consulta)) {

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el número de departamento: " + e.getMessage());
        }
        return false;
    }

    // Método para inserir un novo proxecto na táboa proxecto
    public void inserirProxecto(Connection con, Proxecto proxecto) {
        try (Statement stmt = con.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {

            // Consultar todos os datos da táboa proxecto
            ResultSet rs = stmt.executeQuery("SELECT * FROM proxecto");

            // Verificar se o proxecto xa existe
            if (proxectoExiste(rs, proxecto.getNum_proxecto(), proxecto.getNome_proxecto())) {
                System.out.println("O proxecto xa existe.");
                return;
            }

            // Verificar se o departamento existe
            if (!departamentoExiste(con, proxecto.getNum_departamento())) {
                System.out.println("O número de departamento non existe.");
                return;
            }

            // Inserir o novo proxecto
            rs.moveToInsertRow();
            rs.updateInt("Num_proxecto", proxecto.getNum_proxecto());
            rs.updateString("Nome_proxecto", proxecto.getNome_proxecto());
            rs.updateString("Lugar", proxecto.getLugar());
            rs.updateInt("Num_departamento", proxecto.getNum_departamento());
            rs.insertRow();

            System.out.println("Proxecto inserido correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al inserir el proyecto: " + e.getMessage());
        }
    }

    //2.6 (C)
    /*
    * ResultSet Actualizable: O uso de TYPE_SCROLL_INSENSITIVE e CONCUR_UPDATABLE permite realizar actualizacións dinámicas directamente sobre o ResultSet.
    * Actualización de Salarios: O método percorre o ResultSet, incrementa o salario de cada empregado e utiliza updateRow() para confirmar a actualización na base de datos.
    * Verificación: O método verifica se hai rexistros usando rs.isBeforeFirst() para comprobar se o ResultSet contén datos
    * */
    public void incrementarSalarioPorDepartamento(Connection con, int numeroDepartamento, double incremento) {
        try (Statement stmt = con.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {

            // Consultar os empregados do departamento especificado
            ResultSet rs = stmt.executeQuery("SELECT * FROM empregado WHERE Num_departamento_pertenece = " + numeroDepartamento);

            // Verificar se existen empregados no departamento
            if (!rs.isBeforeFirst()) {
                System.out.println("Non se atoparon empregados no departamento especificado.");
                return;
            }

            // Actualizar os salarios
            while (rs.next()) {
                double salarioActual = rs.getDouble("salario");
                rs.updateDouble("salario", salarioActual + incremento);
                rs.updateRow(); // Confirmar a actualización no ResultSet
            }

            System.out.println("Os salarios foron incrementados correctamente para o departamento " + numeroDepartamento);

        } catch (SQLException e) {
            System.err.println("Erro ao actualizar os salarios: " + e.getMessage());
        }
    }

    //2.6 (D)
    public void visualizarEmpregadosPorProxectos(Connection con, int numeroProxectos) {
        String consulta = """
        SELECT e.NSS, CONCAT(e.Nome, ' ', e.Apellido_1, ' ', e.Apellido_2) AS nomeCompleto, e.Localidade, e.Salario
        FROM empregado e
        JOIN empregado_proxecto ep ON e.NSS = ep.NSS_Empregado
        GROUP BY e.NSS, e.Nome, e.Apellido_1, e.Localidade, e.Salario
        HAVING COUNT(ep.Num_proxecto) > ?
    """;

        try (PreparedStatement pstmt = con.prepareStatement(
                consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {

            // Configurar o parámetro da consulta
            pstmt.setInt(1, numeroProxectos);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) { //Si hay datos
                    System.out.println("Non se atoparon empregados con máis proxectos que " + numeroProxectos);
                    return;
                }


                if (rs.first()) { //Visualizamos la primera fila
                    visualizarFila(rs, "Primeira fila");
                }


                if (rs.last()) {//Visualizamos la última fila
                    visualizarFila(rs, "Última fila");
                }


                if (rs.getRow() > 2 && rs.relative(-2)) { //Visualizamos la antepenúltima fila
                    visualizarFila(rs, "Antepenúltima fila");
                } else {
                    System.out.println("Non hai suficientes filas para mostrar a antepenúltima.");
                }

                // Visualizar todas as filas en sentido inverso
                System.out.println("\nVisualización de todas as filas en sentido inverso:");
                // Mover ao final para empezar desde a última fila
                if (rs.last()) {
                    do {
                        visualizarFila(rs, null);
                    } while (rs.previous());
                }

            }
        } catch (SQLException e) {
            System.err.println("Erro ao executar a consulta: " + e.getMessage());
        }
    }

    // Método auxiliar para imprimir os datos dunha fila
    private void visualizarFila(ResultSet rs, String etiqueta) throws SQLException {
        if (etiqueta != null) {
            System.out.println("\n" + etiqueta + ":");
        }

        String nss =  rs.getString("NSS");
        String nome = rs.getString("nomeCompleto");
        String localidade = rs.getString("Localidade");
        double Salario = rs.getDouble("Salario");

        messages.rsVisuFila(nss, nome, localidade, Salario);

    }
}
