package fr.eni.enchere.bo;

import java.sql.Timestamp;

public class ArticleVendu {

	 	private int no_article;
	    private String nom_article;
	    private String description;
	    private Timestamp date_debut_encheres;
	    private Timestamp date_fin_encheres;
	    private int prix_initial;
	    private int prix_vente ;
	    private Utilisateur vendeur;
	    private Enchere concerne;
	    private Categorie categorieArticle;
	    private String photo;
	    private Boolean paye;
	    
	    public ArticleVendu() {
	    	
	    }
	    
		public ArticleVendu(int no_article, String nom_article, String description, Timestamp date_debut_encheres,
				Timestamp date_fin_encheres, int prix_initial, int prix_vente,
				Utilisateur vend, Enchere concerne, Categorie categorieArticle) {
			this.no_article = no_article;
			this.nom_article = nom_article;
			this.description = description;
			this.date_debut_encheres = date_debut_encheres;
			this.date_fin_encheres = date_fin_encheres;
			this.prix_initial = prix_initial;
			this.prix_vente = prix_vente;
			this.vendeur = vend;
			this.concerne = concerne;
			this.categorieArticle = categorieArticle;
		}
		
		public ArticleVendu(String nom_article, String description, Timestamp date_debut_encheres,
				Timestamp date_fin_encheres, int prix_initial, int prix_vente,
				Utilisateur vend, Categorie categorieArticle, String photo) {
			this.nom_article = nom_article;
			this.description = description;
			this.date_debut_encheres = date_debut_encheres;
			this.date_fin_encheres = date_fin_encheres;
			this.prix_initial = prix_initial;
			this.prix_vente = prix_vente;
			this.vendeur = vend;
			this.categorieArticle = categorieArticle;
			this.photo = photo;
		}

		public ArticleVendu(String nom_article, String description, int prix_initial) {
			this.nom_article = nom_article;
			this.description = description;
			this.prix_initial = prix_initial;

		}




		public int getNo_article() {
			return no_article;
		}

		public void setNo_article(int no_article) {
			this.no_article = no_article;
		}

		public String getNom_article() {
			return nom_article;
		}

		public void setNom_article(String nom_article) {
			this.nom_article = nom_article;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Timestamp getDate_debut_encheres() {
			return date_debut_encheres;
		}

		public void setDate_debut_encheres(Timestamp date_debut_encheres) {
			this.date_debut_encheres = date_debut_encheres;
		}

		public Timestamp getDate_fin_encheres() {
			return date_fin_encheres;
		}

		public void setDate_fin_encheres(Timestamp date_fin_encheres) {
			this.date_fin_encheres = date_fin_encheres;
		}

		public int getPrix_initial() {
			return prix_initial;
		}

		public void setPrix_initial(int prix_initial) {
			this.prix_initial = prix_initial;
		}

		public int getPrix_vente() {
			return prix_vente;
		}

		public void setPrix_vente(int prix_vente) {
			this.prix_vente = prix_vente;
		}

		public Enchere getConcerne() {
			return concerne;
		}

		public void setConcerne(Enchere concerne) {
			this.concerne = concerne;
		}

		public Categorie getCategorieArticle() {
			return categorieArticle;
		}

		public void setCategorieArticle(Categorie categorieArticle) {
			this.categorieArticle = categorieArticle;
		}

		public Utilisateur getVendeur() {
			return vendeur;
		}

		public void setVendeur(Utilisateur vendeur) {
			this.vendeur = vendeur;
		}

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public Boolean getPaye() {
			return paye;
		}

		public void setPaye(Boolean paye) {
			this.paye = paye;
		}
		
		
	    
	   
}
