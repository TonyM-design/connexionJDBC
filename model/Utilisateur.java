package cours_exercices.exercices.JDBC.model;

public class Utilisateur {	
	private int id;
	private int numeroEmploye;
	private String nom;
	private String prenom;
	private String mail;
	private String login;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroEmploye() {
		return numeroEmploye;
	}
	public void setNumeroEmploye(int numeroEmploye) {
		this.numeroEmploye = numeroEmploye;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Utilisateur(int id, int numeroEmploye, String nom, String prenom, String mail, String login,
			String password) {
		super();
		this.id = id;
		this.numeroEmploye = numeroEmploye;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.login = login;
		this.password = password;
	}
	public Utilisateur() {
		super();
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", numeroEmploye=" + numeroEmploye + ", nom=" + nom + ", prenom=" + prenom
				+ ", mail=" + mail + ", login=" + login + ", password=" + password + "]";
	}

	
	

}
