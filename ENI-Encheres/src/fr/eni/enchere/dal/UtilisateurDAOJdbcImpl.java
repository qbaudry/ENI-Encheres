package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	
	private static final String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";



	@Override
	public void insert(Utilisateur util) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(util.getNoUtilisateur()==0)
				{
					pstmt = cnx.prepareStatement(INSERT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, util.getPseudo());
					pstmt.setString(2, util.getNom());
					pstmt.setString(3, util.getPrenom());
					pstmt.setString(4, util.getEmail());
					pstmt.setString(5, util.getTelephone());
					pstmt.setString(6, util.getRue());
					pstmt.setString(7, util.getCodePostal());
					pstmt.setString(8, util.getVille());
					pstmt.setString(9, util.getMotDePasse());
					pstmt.setInt(10, util.getCredit());
					pstmt.setBoolean(11, util.isAdministrateur());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if(rs.next())
					{
						util.setNoUtilisateur(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				}
				
				cnx.commit();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
		
	}

}











