 package connectionDB;



public class Formateur {

	private int id;
	private String nom ;
	private String prenomF ;
	private String adresse ; 
	private int cin ; 
	private int numTel ; 


	public void setId(int id) {
		this.id = id;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public void setPrenomF(String prenomF) {
		this.prenomF = prenomF;
	}

	public int getCin() {
		return cin;
	}

	public int getId() {
		return id;
	}

	public int getNumTel() {
		return numTel;
	}

	public String getAdresse() {
		return adresse;
	}



	public String getNom() {
		return nom;
	}

	public String getPrenomF() {
		return prenomF;
	}

	public Formateur(int id, String nom, String prenomF, String adresse, int cin, int numTel) {
		this.id = id;
		this.nom = nom;
		this.prenomF = prenomF;
		this.adresse = adresse;
		this.cin = cin;
		this.numTel = numTel;
	}

	@Override
	public String toString() {
		return ""+id;

	}

	public Formateur(String nom, String prenomF, String adresse, int cin, int numTel, String formation, String dateDB) {
		this.nom = nom;
		this.prenomF = prenomF;
		this.adresse = adresse;
		this.cin = cin;
		this.numTel = numTel;

	}

	public Formateur() {
	}
}

    

