package cours_exercices.exercices.JDBC.model;

public class Client {
private int id;
private int numeroClient;
private String nom;
private String prenom;
private String mail;
private String adresse;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNumeroClient() {
	return numeroClient;
}
public void setNumeroClient(int numeroClient) {
	this.numeroClient = numeroClient;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
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
public Client(int id, int numeroClient, String nom, String prenom, String mail, String adresse) {
	super();
	this.id = id;
	this.numeroClient = numeroClient;
	this.nom = nom;
	this.prenom = prenom;
	this.mail = mail;
	this.adresse = adresse;
}
public Client() {
	super();
}
@Override
public String toString() {
	return "Client [id=" + id + ", numeroClient=" + numeroClient + ", nom=" + nom + ", prenom=" + prenom
			+ ", mail=" + mail + ", adresse=" + adresse + "]";
}


}
