package controlador;

import java.util.ArrayList;
import modelo.Pais;
import modelo.PaisBD;
import vista.Inputs;
import vista.PaisVista;

/**
 *
 * @author luis.canal
 */
public class PaisCtrl {

    PaisVista vista = new PaisVista();
    PaisBD paisBD = new PaisBD();

    public void control() {

        // Variables que se usarán varias veces en el programa
        int opc = 0;
        int id = 0;
        int cantidad = 0;
        String condicion = "";
        ArrayList<Pais> lista;
        char s_n;
        int campo = -1;
 

        do {
            // menu() muestra opciones principales
            opc = vista.menu();

            switch (opc) {
                case 1:             
                    /* Nuevo País. 
                          - pedimos datos directamente sobre el objeto
                          - pedimos confirmidad antes de grabar
                    */
                    Pais pais = new Pais();
                    pais.setNombre(Inputs.inputString("País: "));
                    pais.setCapital(Inputs.inputString("Capital: "));
                    pais.setHabitantes((int) Inputs.inputInt("Habitantes: "));
                    pais.setMoneda(Inputs.inputString("Moneda: "));
                    vista.mostrarPais(pais);
                    
                    s_n = vista.conformidad("Grabar país (S/N)?");
                    if (s_n == 'S'){
                        cantidad = paisBD.insertar(pais);
                        if (cantidad != 1) {
                            System.out.println("Problemas   ");
                        } else {
                            System.out.println("País grabado correctamente");
                        }
                    }
                    break;

                case 2:
                    /* Modificar País. 
                          - pedimos id del país a modificar
                          - Solicitamos la lectura 
                            (aunque solo viene uno, usamos una lista)
                          - tenemos un bucle para pedir los datos a modificar
                          - pedimos confirmidad antes de grabar
                    */                    
                    id = vista.pedirID();

                    condicion = " WHERE id = " + id;
                    lista = paisBD.leer(condicion);

                    if (lista.size() > 0) {
                        pais = (Pais) lista.get(0);
                        do {
                            vista.mostrarPais(pais);

                            campo = vista.campoModificar();

                            switch (campo) {
                                case 1:
                                    pais.setNombre(Inputs.inputString("País (" + pais.getNombre() + "): "));
                                    break;
                                case 2:
                                    pais.setCapital(Inputs.inputString("Capital (" + pais.getCapital() + "): "));
                                    break;
                                case 3:
                                    int habitant = Inputs.inputInt("Habitantes (" + pais.getHabitantes() + "): ");
                                    pais.setHabitantes((int) habitant);
                                    break;
                                case 4:
                                    pais.setMoneda(Inputs.inputString("Moneda (" + pais.getMoneda() + "): "));
                                    break;

                                case 8:
                                    pais = new Pais(pais.getId(), pais.getNombre(), pais.getCapital(), pais.getHabitantes(), pais.getMoneda());

                                    cantidad = paisBD.modificar(pais);
                                    if (cantidad != 1) {
                                        System.out.println("Problemas   ");
                                    } else {
                                        System.out.println("País modificado correctamente");
                                    }
                                    break;
                                case 9:
                                    break;
                                default:
                                    System.out.println("Opción no existe");
                            }
                        } while (campo < 8);

                    } else {
                        System.out.println("País no encontrado");
                    }
                    break;
                case 3:
                    /* Borrar País. 
                          - pedimos id del país a borrar
                          - Solicitamos la lectura 
                            (aunque solo viene uno, usamos una lista)
                          - Mostramos datos y pedimos conormidad para borrar
                    */                    
                    id = vista.pedirID();

                    condicion = " WHERE id = " + id;
                    lista = paisBD.leer(condicion);

                    if (lista.size() > 0) {
                        pais = (Pais) lista.get(0);

                        vista.mostrarPais(pais);
                        s_n = vista.conformidad("Borrar País?");

                        if (s_n == 'S') {
                            cantidad = paisBD.borrar(id);
                            if (cantidad != 1) {
                                System.out.println("Problemas   ");
                            } else {
                                System.out.println("País borrado correctamente");
                            }
                        }

                    } else {
                        System.out.println("País no encontrado");
                    }

                    break;
                case 4:
                    condicion = "";
                    lista = paisBD.leer(condicion);

                    if (lista.size() > 0) {
                        vista.listar(lista);
                    }

                    break;
                case 0:
                    System.out.println("Ha seleccionado abandonar el programa.");
                    break;
                default:
                    System.out.println("Opción no existe");
            }
        } while (opc != 0);
    }

}
