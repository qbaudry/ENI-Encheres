package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;
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
		RetraitManager retManager = new RetraitManager();
		UtilisateurManager utilManager = new UtilisateurManager();
		EnchereManager enchereMngr = new EnchereManager();
		Retrait ret = retManager.select(article.getNo_article());		
		retManager.delete(ret);
		if(article.getConcerne() != null) {
			Utilisateur util = article.getConcerne().getEncherit();
			util.setCredit(util.getCredit()+article.getConcerne().getMontant_enchere());
			utilManager.UpdateUtilisateurCreditById(util);
		}
		ArrayList<Enchere> listEncheres = (ArrayList<Enchere>) enchereMngr.selectByArticle(article);
		if(!listEncheres.isEmpty()) {
			for(Enchere e : listEncheres) {
				enchereMngr.delete(e);
			}
		}
		this.articleDAO.delete(article);
	}

	public ArticleVendu select(int id) throws BusinessException {
		return this.articleDAO.select(id);
	}
	public List<ArticleVendu> selectByCategorie(Categorie categ) throws BusinessException {
		return articleDAO.selectByCategorie(categ);
	}
	public List<ArticleVendu> selectByVendeur(Utilisateur user) throws BusinessException {
		return articleDAO.selectByVendeur(user);
	}

	public List<ArticleVendu> lister() throws BusinessException {
		return this.articleDAO.lister();
	}
}