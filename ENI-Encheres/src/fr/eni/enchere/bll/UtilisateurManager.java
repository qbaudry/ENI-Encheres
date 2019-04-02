package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.gestionenchere.BusinessException;



public class UtilisateurManager {
	private UtilisateurDAO utilDAO;
	
	public UtilisateurManager() {
		this.utilDAO=DAOFactory.getUtilisateurDAO();
	}
	
	

	public void ajouterUtilisateur(Utilisateur util) throws BusinessException
	{
		BusinessException businessException = new BusinessException();
		this.utilDAO.insert(util);
	}
	
}
