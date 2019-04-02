package fr.eni.enchere.dal;

public abstract class DAOFactory {
	
//	public static EnchereDAO getEnchereDAO();
//	
//		return new EnchereDAOJdbcImpl();
//	}


	/*public static EnchereDAO getEnchereDAO()
	{
		
	}*/
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJdbcImpl();	}
	
	public static CategorieDAO getCategorieDAO()
	{
		return new CategorieDAOJdbcImpl();	
	}

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
}
	