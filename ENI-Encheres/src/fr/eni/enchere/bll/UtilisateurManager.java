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
	
	public Utilisateur selectionnerUtilisateurByEmailPseudo(String pseudo, String email) throws BusinessException {
		return this.utilDAO.selectByPseudoAndMail(pseudo, email);
	}
	
	public Utilisateur selectionnerUtilisateurById(int id) throws BusinessException {
		return this.utilDAO.selectByID(id);
	}
	
	public Utilisateur UpdateUtilisateurById(String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String pwd, int id) throws BusinessException {
		return this.utilDAO.updateByID( pseudo,  nom,  prenom,  email,  telephone,  rue,  cp,  ville,  pwd,  id);
	}
	
	
}