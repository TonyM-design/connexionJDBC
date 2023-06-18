package cours_exercices.exercices.JDBC.model;

public class Fournisseur {
 private int id;
 private int numeroFournisseur;
 private String nom;
 private String mail;
 private String adresse;
 
 
public Fournisseur(int id, int numeroFournisseur, String nom, String mail, String adresse) {
	super();
	this.id = id;
	this.numeroFournisseur = numeroFournisseur;
	this.nom = nom;
	this.mail = mail;
	this.adresse = adresse;
}

public Fournisseur() {
	super();
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNumeroFournisseur() {
	return numeroFournisseur;
}
public void setNumeroFournisseur(int numeroFournisseur) {
	this.numeroFournisseur = numeroFournisseur;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}

@Override
public String toString() {
	return "Fournisseur [id=" + id + ", numeroFournisseur=" + numeroFournisseur + ", nom=" + nom + ", mail=" + mail
			+ ", adresse=" + adresse + "]";
}


 
 
 
}
