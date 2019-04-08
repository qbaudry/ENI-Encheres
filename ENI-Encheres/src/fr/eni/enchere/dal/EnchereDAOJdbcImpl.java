package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT = "insert into ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere) values(?,?,?,?)";
	private static final String UPDATE = "update ENCHERES set date_enchere=?,montant_enchere=? where no_article = ? AND no_utilisateur=?";
	private static final String DELETE = "delete from ENCHERES where no_article = ? AND no_utilisateur=?";
	private static final String SELECT = "select * from ENCHERES where no_article = ? AND no_utilisateur=?";
	private static final String SELECTBYUSER = "select * from ENCHERES where no_utilisateur=?";
	private static final String SELECTBYARTICLE = "select * from ENCHERES where no_article = ?";
	private static final String SELECTMAXBYARTICLE = "select MAX(montant_enchere) as [montant_enchere],date_enchere,no_utilisateur from ENCHERES where no_article = ? group by  ENCHERES.date_enchere,no_utilisateur ";
	private static final String LISTER = "select * from ENCHERES";
	private static UtilisateurDAO utilDAO = DAOFactory.getUtilisateurDAO();
	private static ArticleVenduDAO artDAO = DAOFactory.getArticleDAO();
	@Override
	public Enchere save(Enchere ench) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection();){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				if(this.select(ench.getEncherit(),ench.getConcerne()) == null){
					ResultSet rs;
					pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, ench.getEncherit().getNoUtilisateur());
					pstmt.setInt(2, ench.getConcerne().getNo_article());
					pstmt.setTimestamp(3,ench.getDateEnchere() );
					pstmt.setInt(4,ench.getMontant_enchere() );
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					rs.close();
					pstmt.close();
				}else {
					pstmt = cnx.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setTimestamp(1,ench.getDateEnchere() );
					pstmt.setInt(2,ench.getMontant_enchere() );
					pstmt.setInt(3, ench.getConcerne().getNo_article());
					pstmt.setInt(4, ench.getEncherit().getNoUtilisateur());
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
		return ench;

	}

	@Override
	public void delete(Enchere ench) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				if(this.select(ench.getEncherit(),ench.getConcerne()) == null){
					pstmt = cnx.prepareStatement(DELETE);
					pstmt.setInt(1, ench.getConcerne().getNo_article());
					pstmt.setInt(2, ench.getEncherit().getNoUtilisateur());
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
	public Enchere select(Utilisateur u,ArticleVendu art) throws BusinessException {
		Enchere ench = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT);
				pstmt.setInt(1, art.getNo_article());
				pstmt.setInt(2, u.getNoUtilisateur());
				rs = pstmt.executeQuery();
				if(rs.next()){
					if(rs.getTimestamp("date_enchere")!= null && rs.getInt("montant_enchere")!= 0) {
						ench = new Enchere(u,rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"),art);
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
		return ench;
	}

	@Override
	public List<Enchere> lister() throws BusinessException {
		ArrayList<Enchere> listEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(LISTER);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					ArticleVendu art = artDAO.select(rs.getInt("no_article"));
					Utilisateur u = utilDAO.selectByID(rs.getInt("no_utilisateur"));
					listEnchere.add(new Enchere(u,rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"),art));
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
		return listEnchere;
	}

	@Override
	public List<Enchere> selectByUser(Utilisateur u) throws BusinessException {
		ArrayList<Enchere> listEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECTBYUSER);
				pstmt.setInt(1, u.getNoUtilisateur());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					ArticleVendu art = artDAO.select(rs.getInt("no_article"));
					listEnchere.add(new Enchere(u,rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"),art));
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
		return listEnchere;
	}

	@Override
	public List<Enchere> selectByArticle(ArticleVendu art) throws BusinessException {
		ArrayList<Enchere> listEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECTBYARTICLE);
				pstmt.setInt(1, art.getNo_article());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Utilisateur u = utilDAO.selectByID(rs.getInt("no_utilisateur"));
					listEnchere.add(new Enchere(u,rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"),art));
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
		return listEnchere;
	}

	public Enchere selectMaxByArticle(ArticleVendu art) throws BusinessException {
		Enchere enchere = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECTMAXBYARTICLE);
				pstmt.setInt(1, art.getNo_article());
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					Utilisateur u = utilDAO.selectByID(rs.getInt("no_utilisateur"));
					enchere = new Enchere(u,rs.getTimestamp("date_enchere"),rs.getInt("montant_enchere"),art);
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
		return enchere;
	}



}
