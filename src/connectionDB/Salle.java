package connectionDB;

public class Salle {
private int numSalle ;
private int capaciteSalle ;
private String etat ;

    public Salle(int numSalle, int capaciteSalle, String etat) {
        this.numSalle = numSalle;
        this.capaciteSalle = capaciteSalle;
        this.etat = etat;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }

    public int getCapaciteSalle() {
        return capaciteSalle;
    }

    public void setCapaciteSalle(int capaciteSalle) {
        this.capaciteSalle = capaciteSalle;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
