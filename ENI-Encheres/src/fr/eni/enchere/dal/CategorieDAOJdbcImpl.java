
package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	private static final String INSERT = "insert into CATEGORIES values(?)";
	private static final String UPDATE = "update CATEGORIES set libelle=? where no_categorie = ?";
	private static final String DELETE = "delete from CATEGORIES where no_categorie = ?";
	private static final String SELECT = "select * from CATEGORIES where no_categorie = ?";
	private static final String LISTER = "select * from CATEGORIES"; 
	public void save(Categorie c) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(c.getNoCategorie()==0){
					pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, c.getLibelle());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if(rs.next()){
						c.setNoCategorie(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				}else {
					pstmt = cnx.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, c.getLibelle());
					pstmt.setInt(2, c.getNoCategorie());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					rs.close();
					pstmt.close();
				}
				
				cnx.commit();
			}catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
	}
	public void delete(Categorie c) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(c.getNoCategorie()!=0){
					pstmt = cnx.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, c.getNoCategorie());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					rs.close();
					pstmt.close();
				}
				
				cnx.commit();
			}catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
	}	
	public Categorie select(int id) throws BusinessException {
		Categorie c = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(id !=0){
					pstmt = cnx.prepareStatement(SELECT);
					pstmt.setInt(1, id);
					rs = pstmt.executeQuery();
					boolean premiereLigne = true;
					while(rs.next())
					{
						if(premiereLigne)
						{
							c = new Categorie(rs.getInt("no_categorie"),rs.getString("libelle"));
							premiereLigne=false;
						}
					}
					rs.close();
					pstmt.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
		return c;
	}
	public List<Categorie> lister() throws BusinessException{
		ArrayList<Categorie> listCateg = new ArrayList<Categorie>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(LISTER);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						listCateg.add(new Categorie(rs.getInt("no_categorie"),rs.getString("libelle")));
					}
					rs.close();
					pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
		return listCateg;
	}
}
