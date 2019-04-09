package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;



public interface UtilisateurDAO {
	
	public void insert(Utilisateur util) throws BusinessException;
	public Utilisateur selectByUser(String pseudo, String mdp) throws BusinessException;
	public Utilisateur selectByPseudoAndMail(String pseudo, String email) throws BusinessException;
	public Utilisateur selectByID(int id) throws BusinessException;
	public void updateByID(Utilisateur util) throws BusinessException;
	public void updateCreditByID(Utilisateur util) throws BusinessException;
	public void deleteUser(int id) throws BusinessException;
	public List<Utilisateur> lister() throws BusinessException;
	boolean countPseudo(String pseudo) throws BusinessException;
}
