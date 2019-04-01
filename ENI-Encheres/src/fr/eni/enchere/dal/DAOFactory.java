package fr.eni.enchere.dal;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getListeCourseDAO()
	{
		return new UtilisateurDAOJdbcImpl();
	}
}
	