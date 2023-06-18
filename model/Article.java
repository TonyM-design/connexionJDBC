package cours_exercices.exercices.JDBC.model;

public class Article {

	private int id;
	private int numeroArticle;
	private String nom;
	private Boolean status;
	private String description;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroArticle() {
		return numeroArticle;
	}
	public void setNumeroArticle(int numeroArticle) {
		this.numeroArticle = numeroArticle;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Article(int id, int numeroArticle, String nom, Boolean status, String description) {
		super();
		this.id = id;
		this.numeroArticle = numeroArticle;
		this.nom = nom;
		this.status = status;
		this.description = description;
	}
	
	public Article(int id,  String nom, Boolean status, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.status = status;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", numeroArticle=" + numeroArticle + ", nom=" + nom + ", status=" + status
				+ ", description=" + description + "]";
	}
	
	


	
	
	
}
