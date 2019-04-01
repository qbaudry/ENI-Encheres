package fr.eni.enchere.dal;

public abstract class DAOFactory {
	
	public static EnchereDAO getListeCourseDAO()
	{
		return new EnchereDAOJdbcImpl();
	}
}
	