package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.CategorieDAO;

public class CategorieManager {
	private CategorieDAO categDAO;
	
	public CategorieManager() {
		this.categDAO=DAOFactory.getCategorieDAO();
	}

	public void save(Categorie categ) throws BusinessException
	{
		this.categDAO.save(categ);
	}
	
	public void delete(Categorie categ) throws BusinessException {
		this.categDAO.delete(categ);
	}
	
	public Categorie select(int id) throws BusinessException {
		return this.categDAO.select(id);
	}
	
	public List<Categorie> lister() throws BusinessException {
		return this.categDAO.lister();
	}
}