package edu.badpals;

import static java.lang.Thread.sleep;

public class CDemonio extends _____________________{

    public CDemonio(){
        _____________________(true); _____________________("demoniobip");
        _____________________();
    }//constructor de la clase, define el hilo como demonio, le pone nombre y lo lanza

    public void run(){
        char sonido = "\u0007";
        _____________________(true){
            try{
                System.out.println(sonido);
                sleep(1000);//cantidad de milisegundos
            }
            catch (InterruptedException e){}

        }//fijarse método run() está definido como un proceso sin fin, ciclo sin fin!!
        // Esto es correcto porque está definido como un demonio al finalizar todos los hilos para
        //los que presta el servicio la máquina virtual java finalizará este proceso.

    } //fin tarea o proceso que ejecuta el hilo
}//fin clase que define al hilo
