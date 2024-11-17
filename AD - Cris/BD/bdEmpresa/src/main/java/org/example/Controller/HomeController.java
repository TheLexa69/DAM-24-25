package org.example.Controller;

import org.example.Model.*;
import org.example.View.Messages;

import java.sql.Connection;
import java.util.List;

public class HomeController {

    public Conexion connection = new Conexion();
    public SQLEmpresa sqlEmpresa = new SQLEmpresa();
    public SQLResultSet sqlResultSet = new SQLResultSet();
    public SQLMetaDatos sqlMetaDatos = new SQLMetaDatos();
    public Messages messages = new Messages();

    //EXERCICIO 2.1
    //a) Fai un método para subir o salario aos empregados dun determinado departamento.
    // O método recibirá como parámetros a cantidade a aumentar e o nome do departamento.
    public void aumentarSalarios() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.incrementSalary(c, 500, 2);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    public void nuevoDepartamento() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.newDepartament(c, 8, "'DIW'", 2221111);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    public void borrarTrabajadorProyecto() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.delWorkerProject(c, 9900000, 2);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.2
    public void listarTrabajadoresCiudad() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.workersByCity(c, "Vigo");
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.3 (A)
    public void actualizarDepartamento() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.updateDepartment(c, "Persoal", "Portal");
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //(B)
    public void insertarProyecto() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();

                Proxecto p = new Proxecto(11, "PROXECTO B", "PONTEVEDRA", 3);
                sqlEmpresa.insertProject(c, p);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //(C)
    public void borrarProyecto() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.delProject(c, 11);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }


    //2.4
    public void obtenerProxectosPorDepartamento() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.delProject(c, 1);
                List<Proxecto> proxectos = sqlEmpresa.getProjectsByDepartment(c, "INFORMÁTICA");
                for (Proxecto proxecto : proxectos) {
                    System.out.println("Proxecto: " + proxecto.getNome_proxecto() + ", Lugar: " + proxecto.getLugar());
                }
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.5 (A)
    public void actualizarEmpleado() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();

                if (sqlEmpresa.updateWorker(c, "0010010", "Garcia Barbon", 36890, "2A", "36201", "Vigo") == 1) {
                    System.out.println("Se han actualizado los datos del trabajador correctamente");
                } else {
                    System.out.println("Error a la hora de actualizar los datos del trabajador.");
                }
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.5 (B)
    public void obtenerDatosProyecto() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.getProjectData(c, 1);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.5 (C)
    public void mostrarDepartamentosControlaProyectos() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.showDepartmentsWithProjects(c, 2);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.5 (D)
    public void mostrarNumeroEmpleadosEnDepartamento() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlEmpresa.showNumberOfEmployeesByDepartment(c, "INNOVACIÓN");
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //2.6 RESULT SET
    //(A)
    public void mostrarTiposRS() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlResultSet.mostrarTiposResultSet(c);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //(B)
    public void inserirObxectoProxecto() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                Proxecto novoProxecto = new Proxecto(101, "Novo Proxecto", "Vigo", 4);
                sqlResultSet.inserirProxecto(c, novoProxecto);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //(C)
    public void incrementarSalarios() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlResultSet.incrementarSalarioPorDepartamento(c, 1, 1000);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //(D)
    public void infoEmpleadoCntdProyectos() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlResultSet.visualizarEmpregadosPorProxectos(c, 1);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //SQLMetaDatos 3.1
    public void mostrarInfoConexion() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlMetaDatos.mostrarInfoConexion(c);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //SQLMetaDatos 3.2 (A)
    public void mostrarInfoTaboas() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlMetaDatos.mostrarInformacionTaboas(c);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //SQLMetaDatos 3.2 (B)
    public void mostrarInformacionColumnas() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlMetaDatos.mostrarInformacionColumnas(c, null, "empregado");
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //SQLMetaDatos 3.2 (C)
    public void mostrarInformacionProcedementos() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlMetaDatos.mostrarInformacionProcedementos(c);
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //SQLMetaDatos 3.2 (D)
    public void mostrarClavesPrimarias() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlMetaDatos.mostrarClavesPrimarias(c, null, "empregado");
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

    //SQLMetaDatos 3.2 (E)
    public void mostrarClavesForaneas() {
        try {
            if (connection != null) {
                Connection c = connection.conectarMySQL();
                sqlMetaDatos.mostrarClavesForaneas(c, null, "empregado");
            }
        } catch (Exception e) {
            messages.msgErrorConnection(e);
        }
    }

}
