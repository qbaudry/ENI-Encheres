package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.gestionenchere.BusinessException;



public interface UtilisateurDAO {
	
	public void insert(Utilisateur util) throws BusinessException;
	public Utilisateur selectByUser(String pseudo, String password) throws BusinessException;

}
