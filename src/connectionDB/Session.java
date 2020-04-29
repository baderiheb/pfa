package connectionDB;

import java.util.Vector;

public class Session {
    private int ids;
    private Vector <Formateur> forma ;
    private Formation idformation;
    private String nom;
    private String date;
    private float duree;
    private int numSalle ;
    private String dateFin ;
    private String commentaire ;

    public Session(int ids, Vector<Formateur> forma, Formation idformation, String nom, String date, float duree, int numSalle, String dateFin, String commentaire) {
        this.ids = ids;
        this.forma = forma;
        this.idformation = idformation;
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.numSalle = numSalle;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public Vector<Formateur> getForma() {
        return forma;
    }

    public void setForma(Vector<Formateur> forma) {
        this.forma = forma;
    }

    public Formation getIdformation() {
        return idformation;
    }

    public void setIdformation(Formation idformation) {
        this.idformation = idformation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getDuree() {
        return duree;
    }

    public void setDuree(float duree) {
        this.duree = duree;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}