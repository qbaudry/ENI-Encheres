package fr.eni.enchere.bo;

public class Categorie {

	private int noCategorie;
	private String libelle;
	private ArticleVendu categorieArticle;
	
	public Categorie(int noCategorie, String libelle, ArticleVendu categorieArticle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.categorieArticle = categorieArticle;
	}
	public Categorie(int noCategorie, String libelle) {
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	public Categorie() {
		
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public ArticleVendu getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(ArticleVendu categorieArticle) {
		this.categorieArticle = categorieArticle;
	}
	
	
	
	
	
}
