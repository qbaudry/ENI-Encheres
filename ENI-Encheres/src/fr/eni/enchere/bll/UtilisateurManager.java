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
	
	

	public void ajouterUtilisateur(int noUtil, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) throws BusinessException
	{
		
		
		BusinessException businessException = new BusinessException();
		credit = 0;
		administrateur = false;
		Utilisateur util = new Utilisateur(noUtil ,pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		
		this.utilDAO.insert(util);
		
	}
	
}
