package fr.eni.enchere.dal;

public abstract class DAOFactory {
	
	public static EnchereDAO getEnchereDAO()
	{
		return new EnchereDAOJdbcImpl();

	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();	}
}
	