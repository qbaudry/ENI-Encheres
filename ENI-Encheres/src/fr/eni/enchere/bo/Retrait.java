package fr.eni.enchere.bo;

public class Retrait {

	private ArticleVendu lieuRetrait;
	private String rue;
	private String code_postal;
	private String ville;
	
	public Retrait(ArticleVendu lieuRetrait, String rue, String code_postal, String ville) {
		super();
		this.lieuRetrait = lieuRetrait;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	public ArticleVendu getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(ArticleVendu lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
}
