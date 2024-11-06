/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdteismvc;

import controlador.PaisCtrl;

/**
 *
 * @author luis.canal
 */
public class BdTeisMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PaisCtrl paisCtrl = new PaisCtrl();
        System.out.println("entra en main");
        paisCtrl.control();                 // Delegamos la gestion al controlador de Pais
    }
    
}
