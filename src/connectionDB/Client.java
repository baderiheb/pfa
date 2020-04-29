package connectionDB;

public class Client {
    private int idC ;
    private String nomC ;
    private String prenomC;
    private String adresseC ;
    private int cinC;
    private int numtelC;

    public Client() {
    }

    public Client(int idC, String nomC, String prenomC, String adresseC, int cinC, int numtelC) {
        this.idC = idC;
        this.nomC = nomC;
        this.prenomC = prenomC;
        this.adresseC = adresseC;
        this.cinC = cinC;
        this.numtelC = numtelC;
    }

    public int getIdC() {
        return idC;
    }

    public String getNomC() {
        return nomC;
    }

    public String getPrenomC() {
        return prenomC;
    }

    public String getAdresseC() {
        return adresseC;
    }

    public int getCinC() {
        return cinC;
    }

    public int getNumtelC() {
        return numtelC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public void setPrenomC(String prenomC) {
        this.prenomC = prenomC;
    }

    public void setAdresseC(String adresseC) {
        this.adresseC = adresseC;
    }

    public void setCinC(int cinC) {
        this.cinC = cinC;
    }

    public void setNumtelC(int numtelC) {
        this.numtelC = numtelC;
    }
}
