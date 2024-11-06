/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import java.util.Scanner;

/**
 *
 * @author luis.canal
 */
public class Inputs {
    static Scanner sc = new Scanner(System.in);


    public static int inputInt(String mensaje) {

        int dato = 0;
        boolean ok = false;
        do {
            try {
                System.out.print(mensaje);
                dato = sc.nextInt();
                ok = true;
            } catch (java.util.InputMismatchException e) {
                ok = false;
                sc.useDelimiter("\n");
                sc.next();
            }
        } while (!ok);

        return dato;
    }

    public static String inputString(String mensaje) {
        String dato = "";
        sc.useDelimiter("\n");
        System.out.print(mensaje);
        dato = sc.next();

        return dato;
    }
    public static char inputChar(String mensaje){
        char c ;
        System.out.print(mensaje);
        

            c = sc.next().charAt(0);
    
        
        return c;
    }
    
}
