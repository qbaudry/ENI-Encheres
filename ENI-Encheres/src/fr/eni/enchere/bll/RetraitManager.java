package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;

public class RetraitManager {
private RetraitDAO retraitDAO;
	
	public RetraitManager() {
		this.retraitDAO=DAOFactory.getRetraitDAO();
	}

	public void save(Retrait retrait) throws BusinessException
	{
		this.retraitDAO.save(retrait);
	}
	
	public void delete(Retrait retrait) throws BusinessException {
		this.retraitDAO.delete(retrait);
	}
	


}
