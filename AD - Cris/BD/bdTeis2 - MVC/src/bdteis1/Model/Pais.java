package bdteis1.Model;

public class Pais {

    private String nombrePais, nombreCapital, moneda;
    private int numHabitantes, id;

    public Pais() {
    }

    public Pais(int id, String nombrePais, int numHabitantes, String nombreCapital, String moneda) {
        this.id = id;
        this.nombrePais = nombrePais;
        this.nombreCapital = nombreCapital;
        this.moneda = moneda;
        this.numHabitantes = numHabitantes;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getNombreCapital() {
        return nombreCapital;
    }

    public String getMoneda() {
        return moneda;
    }

    public int getNumHabitantes() {
        return numHabitantes;
    }

    public int getId(){
        return id;
    }

    public String toString(){
        return
                "\tID: " + getId() + "\n"
                + "\tNome: " + getNombrePais() + "\n"
                + "\tCapital: " + getNombreCapital() + "\n"
                + "\tMoneda: " + getMoneda() + "\n"
                + "\tNum Habitantes: " + getNumHabitantes() + "\n";
    }
}
