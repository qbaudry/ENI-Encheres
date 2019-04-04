package fr.eni.enchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL = 10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC = 10001;

	/**
	 * Echec de la lecture d'un utilisateur
	 */
	public static final int LECTURE_UTILISATEUR_ECHEC = 10002;
	/**
	 * Utilisateur inexistant
	 */
	public static final int LECTURE_UTILISATEUR_INEXISTANT = 10003;
	
	public static final int SUPPRESSION_UTILISATEUR_ERREUR = 10004;
}












