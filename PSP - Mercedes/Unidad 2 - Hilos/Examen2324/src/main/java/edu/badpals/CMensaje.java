package edu.badpals;

//CMensaje clase a la que pertenecerá el objeto a compartir por los hilos cooperantes
public class CMensaje {
    private String textoMensaje;
    private int numeroMensaje;
    private boolean disponible=false; //valor false cuando no hay ningun mensaje que
                        // mostrar, entonces solo se puede producir o generar uno nuevo
    public _____________________ void almacenar(int nmsj){
        //definimos aquí una sección crítica
        //el hilo productor al ejecutar el método almacenar, lo primero que hace es verificar
        //el valor de la bandera disponible
        while(disponible==_____________________){
            //el último mensaje producido aún no ha sido mostrado
            //el productor tiene que esperar, hay un mensaje sin mostrar, no se puede producir otro
            try{
                _____________________;
            } catch(InterruptedException e){}
        }
        numeroMensaje=nmsj;
        textoMensaje="Mensaje prueba";
        //simula aquí localizar el texto para mensaje correspondiente al nmensaje
        disponible=_____________________;//ya tenemos nuevo mensaje producido que mostrar
        _____________________;//aqui el hilo despierta a todos los hilos que están esperando desde wait()
    }//fin almacenar, fin método en donde se define seccióon crítica

    public _____________________ String obtener(){
        //segunda sección crítica definida en esta clase.
        //este método consume mensaje en objeto

        while(disponible==_____________________){
            //no hay mensaje que mostrar, el hilo consumidor tiene que esperar
            //no tiene permiso para seguir ejecutando sección crítica
            try{
                _____________________;
            } catch(InterruptedException e){}
        }

        String mensaje;

        mensaje = textoMensaje + "###" + numeroMensaje;

        disponible=_____________________;//no hay mensaje disponible para mostrar

        _____________________;//despierta a todos los hilos que están esperando desde wait() por el monitor del objeto

        return mensaje;
    }//fin sección crítica, despierta todos los hilos esperando por el monitor del objeto
    // y libera monitor del objeto
}// fin clase CMensaje
