package fr.eni.enchere.dal;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;



public interface UtilisateurDAO {
	
	public void insert(Utilisateur util) throws BusinessException;
	public Utilisateur selectByUser(String pseudo, String mdp) throws BusinessException;
	public Utilisateur selectByPseudoAndMail(String pseudo, String email) throws BusinessException;
	public Utilisateur selectByID(int id) throws BusinessException;
	public Utilisateur updateByID(String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String pwd, int id) throws BusinessException;
}
