package fr.eni.enchere.bll;

import java.util.regex.Pattern;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.bll.CodesResultatBLL;

public class UtilisateurManager {
	private UtilisateurDAO utilDAO;
	
	public UtilisateurManager() {
		this.utilDAO=DAOFactory.getUtilisateurDAO();
	}

	public void ajouterUtilisateur(Utilisateur util) throws BusinessException
	{
		this.utilDAO.insert(util);
	}
	
	public Utilisateur selectionnerUtilisateur(String pseudo, String mdp) throws BusinessException {
		return this.utilDAO.selectByUser(pseudo, mdp);
	}
	
	
}