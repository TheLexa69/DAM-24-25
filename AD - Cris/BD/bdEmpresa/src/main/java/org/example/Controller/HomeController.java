package org.example.Controller;

import org.example.Model.Conexion;
import org.example.Model.SQLEmpresa;

import java.sql.Connection;

public class HomeController {

    public Conexion connection = new Conexion();
    public SQLEmpresa sqlEmpresa = new SQLEmpresa();

    //EXERCICIO 2.1
    //a) Fai un método para subir o salario aos empregados dun determinado departamento.
    // O método recibirá como parámetros a cantidade a aumentar e o nome do departamento.
    public void aumentarSalarios(){
        try{
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.incrementSalary(c, 500, 2);
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
    }

    public void nuevoDepartamento(){
        try{
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.newDepartament(c, 8, "'DIW'", 2221111);
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
    }

    public void borrarTrabajadorProyecto(){
        try{
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.delWorkerProject(c, 9900000, 2);
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
    }

    //2.2
    public void listarTrabajadoresCiudad(){
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.workersByCity(c, "Vigo");
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
    }



    public void listarTrabajadoresProyectos(){
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.showWorkersInProject(c);
            } else {
                System.err.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con la base de datos" + e.getMessage()
            );
        }
    }


}
