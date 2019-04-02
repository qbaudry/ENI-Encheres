package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.BusinessException;

public interface ArticleVenduDAO {

	public void save(ArticleVendu article) throws BusinessException;
	public void delete(ArticleVendu article) throws BusinessException;
	public ArticleVendu select(int id) throws BusinessException;
	public List<ArticleVendu> lister() throws BusinessException;

}
