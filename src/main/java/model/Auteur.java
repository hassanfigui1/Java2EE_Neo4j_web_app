package model;

public class Auteur {
	private String nom,prenom,email,mdp;
	private int id;
	private String departement;
	Article article;

	public Auteur(String nom, String prenom, String email, String mdp,String departement) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.departement =departement; 
	}
	
	public Auteur(int id, Article article) {
		super();
		this.id = id;
		this.article = article;
	}

	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Auteur(String email, Article article) {
		super();
		this.email = email;
		this.article = article;
	}
	

	public Auteur(String email) {
		super();
		this.email = email;
	}


	@Override
	public String toString() {
		return "Auteur [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mdp=" + mdp + ", id=" + id
				+ ", departement=" + departement + "]";
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Auteur() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
