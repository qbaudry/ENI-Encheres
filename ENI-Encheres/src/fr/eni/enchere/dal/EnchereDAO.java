package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public interface EnchereDAO {
	public void save(Enchere ench) throws BusinessException;
	public void delete(Enchere ench) throws BusinessException;
	public Enchere select(Utilisateur u,ArticleVendu art) throws BusinessException;
	public List<Enchere> selectByUser(Utilisateur u) throws BusinessException;
	public List<Enchere> selectByArticle(ArticleVendu art) throws BusinessException;
	public Enchere selectMaxByArticle(ArticleVendu art) throws BusinessException;
	public List<Enchere> lister() throws BusinessException;
}
