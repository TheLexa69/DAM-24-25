package modelo;

/**
 * @author luis.canal
 */
public class Pais {
    private int id;
    private String nombre;
    private String capital;
    private int habitantes;
    private String moneda;

    public Pais(int id, String nombre, String capital, int habitantes, String moneda) {
        this.id = id;
        this.nombre = nombre;
        this.capital = capital;
        this.habitantes = habitantes;
        this.moneda = moneda;
    }

    public Pais(String nombre, String capital, int habitantes, String moneda) {
        this.nombre = nombre;
        this.capital = capital;
        this.habitantes = habitantes;
        this.moneda = moneda;
    }

    public Pais() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return String.format("%4d%2s%-20s%-20s%-20s",id,"- ", nombre, capital, moneda);

      //  return "Pais{" + "id=" + id + ", nombre=" + nombre + ", capital=" + capital + ", habitantes=" + habitantes + ", moneda=" + moneda + '}';
    }
    
}
