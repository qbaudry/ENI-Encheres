package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.gestionenchere.BusinessException;


public interface UtilisateurDAO {
	
	public void insert(Utilisateur util) throws BusinessException;

}
