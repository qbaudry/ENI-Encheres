package fr.eni.enchere.bll;

import java.util.ArrayList;
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
	
	public void UpdateUtilisateurById(Utilisateur util) throws BusinessException {
		this.utilDAO.updateByID(util);
	}
	
	public void UpdateUtilisateurCreditById(Utilisateur util) throws BusinessException {
		 this.utilDAO.updateCreditByID(util);
	}
	
	public void deleteUser(int id) throws BusinessException {
		this.utilDAO.deleteUser(id);
	}
	
	public ArrayList<Utilisateur> lister() throws BusinessException{
		return (ArrayList<Utilisateur>) this.utilDAO.lister();
	}
	public boolean countPseudo(String pseudo) throws BusinessException {
		return this.utilDAO.countPseudo(pseudo);
	}
	
}