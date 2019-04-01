package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.gestionlistescourses.BusinessException;
import fr.eni.javaee.gestionlistescourses.bo.ListeCourse;
import fr.eni.javaee.gestionlistescourses.dal.CodesResultatDAL;
import fr.eni.javaee.gestionlistescourses.dal.ConnectionProvider;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_ALL = "SELECT * FROM :table";
	
	@Override
	public Object selectObjectByID(String table, int id) {
		return null;
	}

	@Override
	public <T> List<T> ListerTable(String table,T typeObject) {
		List<Object> liste = new ArrayList<Object>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			pstmt.setString(1, table);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				liste.add(new ListeCourse(rs.getInt("id"), rs.getString("nom")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_LISTES_ECHEC);
			throw businessException;
		}
		return listesCourse;
	}
	

}
