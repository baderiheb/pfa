package connectionDB;

public class Formation {
    private int idfor;
    private String nom;
    private String type;
    private String niveau;
    private String nature;
    private String domaine;
    private String duree;
    private float tarif;

    public Formation(int idfor,String nom, String type, String niveau, String nature, String domaine, String duree, float tarif) {
        this.idfor = idfor;

        this.nom = nom;
        this.type = type;
        this.niveau = niveau;
        this.nature = nature;
        this.domaine = domaine;
        this.duree = duree;
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return ""+idfor; }

    public int getIdfor() {
        return idfor;
    }

    public void setIdfor(int idfor) {
        this.idfor = idfor;
    }

      public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }
}