package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	private static final String INSERT = "insert into RETRAITS(no_article,rue,code_postal,ville) values(?,?,?,?)";
	private static final String UPDATE = "update RETRAITS set rue=?,code_postal=?,ville=? where no_article = ?";
	private static final String DELETE = "delete from RETRAITS where no_article = ?";
	private static final String SELECT = "select * from RETRAITS where no_article = ?";
	private static final String LISTER = "select * from RETRAITS";
	@Override
	public void save(Retrait r) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				if(this.select(r.getNoArticle())==null){
					pstmt = cnx.prepareStatement(INSERT);
					pstmt.setInt(1, r.getNoArticle());
					pstmt.setString(2, r.getRue());
					pstmt.setString(3, r.getCode_postal());
					pstmt.setString(4, r.getVille());
					pstmt.executeUpdate();
					pstmt.close();
				}else {
					pstmt = cnx.prepareStatement(UPDATE);
					pstmt.setString(1, r.getRue());
					pstmt.setString(2, r.getCode_postal());
					pstmt.setString(3, r.getVille());
					pstmt.setInt(4, r.getNoArticle());
					pstmt.executeUpdate();
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

	@Override
	public void delete(Retrait r) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(r.getNoArticle()!=0){
					pstmt = cnx.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, r.getNoArticle());
					pstmt.executeUpdate();
					// TODO A SUPPRIMER rs = pstmt.getGeneratedKeys();
					// TODO A SUPPRIMER rs.close();
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

	@Override
	public Retrait select(int id) throws BusinessException {
		Retrait ret = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				boolean premiereLigne = true;
				while(rs.next())
				{
					if(premiereLigne)
					{
						ret = new Retrait(rs.getInt("no_article"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"));
						premiereLigne=false;
					}
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
		return ret;
	}

	@Override
	public List<Retrait> lister() throws BusinessException {
		ArrayList<Retrait> listRetrait = new ArrayList<Retrait>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(LISTER);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						listRetrait.add( new Retrait(rs.getInt("no_article"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville")));
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
		return listRetrait;
	}
}

