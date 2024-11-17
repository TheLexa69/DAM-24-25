package org.example.View;

public class Messages {

    //HOMECONTROLLER
    public void msgErrorConnection(Exception e) {
        System.err.println("Error al conectar con la base de datos " + e.getMessage());
    }

    //SQLMETADATOS
    public void rsVisuFila(String NSS, String nomeCompleto, String Localidade, double Salario){
        System.out.println("NSS: " + NSS);
        System.out.println("Nome completo: " + nomeCompleto);
        System.out.println("Localidade: " + Localidade);
        System.out.println("Salario: " + Salario + "\n");
    }
}
