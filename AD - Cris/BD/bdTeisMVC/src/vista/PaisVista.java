/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import modelo.Pais;

/**
 *
 * @author luis.canal
 */
public class PaisVista {

    public int menu() {
        int opc = 0;
        do {
            System.out.println("MENU");
            System.out.println("1 - Crear País");
            System.out.println("2 - Modificar País");
            System.out.println("3 - Borrar País");
            System.out.println("4 - Listado de Países");
            System.out.println("0 - Salir");
            opc = Inputs.inputInt("Indicar opción: ");
        } while (opc > 4 || opc < 0);
        return opc;
    }

    public void mostrarPais(Pais pais) {
        System.out.println(" 1- País.......:" + pais.getNombre());
        System.out.println(" 2- Capital....:" + pais.getCapital());
        System.out.println(" 3- Habitantes.:" + pais.getHabitantes());
        System.out.println(" 4- Moneda.....:" + pais.getMoneda());

    }

    public int campoModificar() {
        System.out.println(" 8- Grabar");
        System.out.println(" 9- Salir");
        return Inputs.inputInt("Elemento a modificar: ");
    }

    // listado con formato, en el toString de Pais.java también está así
    public void listar(ArrayList<Pais> lista) {
        System.out.format("%6s %-20s%-20s%-20s", "Id  ", "País", "Capital", "Moneda");
        System.out.println("");
        System.out.format("%-6s %-20s%-20s%-20s","----  ", "---------------", "---------------", "-------------");
        System.out.println("");
        for (Pais pais : lista) {
            System.out.println(pais);
        }
    }

    // Lo vamos a usar en varios apartados del programa
    public int pedirID() {
        return Inputs.inputInt("Indicar ID del país: ");
    }

    /*
        haremos varias peticiones de conformidad... lo mejor un char
    */
    public char conformidad(String mensaje) {
        char c ;
        do{
            c = Character.toUpperCase(Inputs.inputChar(mensaje));
        } while(c != 'S' && c != 'N');
        return c;
    }
    
}
