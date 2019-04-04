package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.ArticleVenduDAO;

public class ArticleVenduManager {
	private ArticleVenduDAO articleDAO;
	
	public ArticleVenduManager() {
		this.articleDAO=DAOFactory.getArticleDAO();
	}

	public void save(ArticleVendu article) throws BusinessException
	{
		this.articleDAO.save(article);
	}
	
	public void delete(ArticleVendu article) throws BusinessException {
		this.articleDAO.delete(article);
	}
	
	public ArticleVendu select(int id) throws BusinessException {
		return this.articleDAO.select(id);
	}
	public List<ArticleVendu> selectByCategorie(Categorie categ) throws BusinessException {
		return articleDAO.selectByCategorie(categ);
	}
	
	public List<ArticleVendu> lister() throws BusinessException {
		return this.articleDAO.lister();
	}
}