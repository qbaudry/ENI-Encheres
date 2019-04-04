package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.BusinessException;

public interface ArticleVenduDAO {

	public void save(ArticleVendu article) throws BusinessException;
	public void delete(ArticleVendu article) throws BusinessException;
	public ArticleVendu select(int id) throws BusinessException;
	public List<ArticleVendu> lister() throws BusinessException;
	public List<ArticleVendu> selectByCategorie(Categorie categ) throws BusinessException ;
	public List<ArticleVendu> selectByVendeur(Utilisateur usr) throws BusinessException ;

}
