package EjemplosBinarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Persona implements Serializable {
   private static final long serialVersionUID = 1L;

   private String nombre;
   private String apellidos;
   private List<Mascota> mascotas = new ArrayList<Mascota>();

   public Persona(String nombre, String apellidos) {
       this.nombre=nombre;
       this.apellidos=apellidos;
   }

   public String getNombre() {
       return nombre;
   }

   public String getApellidos() {
       return apellidos;
   }

   public void addMascota(Mascota mascota) {
       mascotas.add(mascota);
   }

   public List<Mascota> getMascotas() {
       return Collections.unmodifiableList(mascotas);
   }

   @Override
   public String toString() {
       return "Persona "+nombre + " "+ apellidos; 
   }
}