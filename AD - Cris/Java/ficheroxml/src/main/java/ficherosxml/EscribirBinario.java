package ficherosxml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EscribirBinario {
    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();

        personas.add(new Persona("Juan", 25));
        personas.add(new Persona("Pedro", 30));
        personas.add(new Persona("Maria", 40));

        try (FileOutputStream fos = new FileOutputStream("personas.bin"); 
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Persona p : personas) {
                oos.writeUTF(p.getNombre());
                oos.writeInt(p.getEdad());
            }

            System.out.println("Se ha creado el fichero personas.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }     

    }
}
