package bdteis1.View;

import bdteis1.Model.Pais;

public class Messages {

    public void showMenu() {

        System.out.println("\n--- Menú ---");
        System.out.println("1. Listar todos los países");
        System.out.println("2. Añadir un nuevo país");
        System.out.println("3. Actualizar el país");
        System.out.println("4. Borrar el país");
        System.out.println("5. Exportar a XML");
        System.out.println("6. Salir de la aplicación");
        System.out.print("Selecciona una opción: ");

    }

    public String showAllPaises(int id, String nome, int numHab, String nomeCapital, String moneda) {
        Pais pais = new Pais(id, nome, numHab, nomeCapital, moneda);
        return pais.toString();
    }

}
