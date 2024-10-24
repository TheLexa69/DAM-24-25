package edu.badpals;

import java.io.*;
import java.util.*;

public class Biblioteca {
    private static String RUTA_LIBROS ="src/main/resources/libros.bin";
    private static String RUTA_REVISTAS ="src/main/resources/revistas.bin";

    private Set<Publicacion> libros;
    private Set<Publicacion> revistas;
    private Set<Publicacion> publicaciones;

    public Biblioteca() {
        this.libros = leerPublicaciones(RUTA_LIBROS);
        this.revistas = leerPublicaciones(RUTA_REVISTAS);
        this.publicaciones = new HashSet<>();

        publicaciones.addAll(libros);
        publicaciones.addAll(revistas);
    }

    public Set<Publicacion> getLibros() {
        return libros;
    }
    public Set<Publicacion> getRevistas() {
        return revistas;
    }
    public Set<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public Biblioteca addLibro(Publicacion libro) {
        if(libro instanceof Libro){
            libros.add(libro);
            publicaciones.add(libro);
        }
        return this;
    }

    public Biblioteca addRevista(Publicacion revista) {
        if(revista instanceof Revista){
            revistas.add(revista);
            publicaciones.add(revista);
        }
        return this;
    }

    public Biblioteca addPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        if (publicacion instanceof Revista){
            revistas.add(publicacion);
        } else if (publicacion instanceof Libro) {
            libros.add(publicacion);
        }
        return this;
    }

    public void guardarPublicacion(Publicacion publicacion){
        publicaciones.add(publicacion);

        if(publicacion instanceof Revista){
            guardar(revistas,RUTA_REVISTAS);
        } else if (publicacion instanceof Libro) {
            guardar(libros,RUTA_LIBROS);
        }else{
            return;
        }
        }

    public void guardarPublicaciones(){
        for(Publicacion publicacion : this.publicaciones){
            guardarPublicacion(publicacion);
        }
    }

    private void guardar(Set<Publicacion> publicaciones, String ruta){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(ruta)))){
                oos.writeObject(publicaciones);

        }catch (IOException e){
            System.out.println("Error al guardar publicaciones.");
            e.printStackTrace();
        }
    }

    public Set<Publicacion> leerPublicaciones(String ruta) {
        Set<Publicacion> publicacionesLeidas = new HashSet<>();

        File archivo = new File(ruta);
        if (!archivo.exists() || archivo.length() == 0) {
            return publicacionesLeidas;
        }

        try (FileInputStream fis = new FileInputStream(ruta);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                publicacionesLeidas = (Set<Publicacion>) ois.readObject();
            }

        } catch (IOException e) {
            System.out.println("Error al leer publicacion en: " + ruta);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer publicacion en: " + ruta);
            e.printStackTrace();
        }
        return publicacionesLeidas;
    }

    public void actualizarPublicacion(Publicacion publicacionActualizada){
        publicaciones.remove(publicacionActualizada);
        publicaciones.add(publicacionActualizada);

        if(publicacionActualizada instanceof Libro){
            libros.remove(publicacionActualizada);
            libros.add(publicacionActualizada);
        } else if (publicacionActualizada instanceof Revista) {
            revistas.remove(publicacionActualizada);
            revistas.add(publicacionActualizada);
        }
    }
}
