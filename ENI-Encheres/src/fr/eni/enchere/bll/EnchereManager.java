package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDAO;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	
	public EnchereManager() {
		this.enchereDAO=DAOFactory.getEnchereDAO();
	}

	public void save(Enchere enchere) throws BusinessException
	{
		this.enchereDAO.save(enchere);
	}
	
	public void delete(Enchere enchere) throws BusinessException {
		this.enchereDAO.delete(enchere);
	}
	
	public Enchere select(Utilisateur u,ArticleVendu art) throws BusinessException {
		return this.enchereDAO.select(u, art);
	}
	
	public List<Enchere> lister() throws BusinessException {
		return this.enchereDAO.lister();
	}
}