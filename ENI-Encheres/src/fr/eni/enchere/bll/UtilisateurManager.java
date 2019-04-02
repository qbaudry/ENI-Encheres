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
		BusinessException businessException = new BusinessException();
		this.validerEmailUtilisateur(util.getEmail(), businessException);
		
		if(!businessException.hasErreurs())
		{
			this.utilDAO.insert(util);
		}
		else
		{
			throw businessException;
		}
	}
	
	private void validerEmailUtilisateur(String email, BusinessException businessException) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (pat.matcher(email).matches()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_INSCRIPTION);
		} 
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
	
	
}