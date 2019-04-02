package fr.eni.enchere.dal;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;



public interface UtilisateurDAO {
	
	public void insert(Utilisateur util) throws BusinessException;
	public Utilisateur selectByUser(String pseudo, String password) throws BusinessException;

}
