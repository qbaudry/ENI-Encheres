package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_acheteur,no_vendeur,no_categorie) values (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "update ARTICLES_VENDUS set nom_article=?, description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,no_acheteur=?,no_vendeur?,no_categorie=? where no_article = ?";
	private static final String DELETE = "delete from ARTICLES_VENDUS where no_article = ?";
	private static final String SELECT = "select * from ARTICLES_VENDUS where no_article = ?";
	private static final String LISTER = "select * from ARTICLES_VENDUS";
	private CategorieDAO catDAO = DAOFactory.getCategorieDAO();
	private UtilisateurDAO utilDAO = DAOFactory.getUtilisateurDAO();
	private RetraitDAO retDAO = DAOFactory.getRetraitDAO();
	
	@Override
	public void save(ArticleVendu article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(article.getNo_article()==0){
					pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getNom_article());
					pstmt.setString(2, article.getDescription());
					pstmt.setTimestamp(3, article.getDate_debut_encheres());
					pstmt.setTimestamp(4, article.getDate_fin_encheres());
					pstmt.setInt(5, article.getPrix_initial());
					pstmt.setInt(6, article.getPrix_vente());
					pstmt.setInt(7, article.getAcheteur().getNoUtilisateur());
					pstmt.setInt(8, article.getVendeur().getNoUtilisateur());
					pstmt.setInt(9, article.getCategorieArticle().getNoCategorie());
					pstmt.executeUpdate();
					rs = pstmt.getGeneratedKeys();
					if(rs.next()){
						article.setNo_article(rs.getInt(1));
					}
					rs.close();
					pstmt.close();
				}else {
					pstmt = cnx.prepareStatement(UPDATE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, article.getNom_article());
					pstmt.setString(2, article.getDescription());
					pstmt.setTimestamp(3, article.getDate_debut_encheres());
					pstmt.setTimestamp(4, article.getDate_fin_encheres());
					pstmt.setInt(5, article.getPrix_initial());
					pstmt.setInt(6, article.getPrix_vente());
					pstmt.setInt(7, article.getAcheteur().getNoUtilisateur());
					pstmt.setInt(8, article.getVendeur().getNoUtilisateur());
					pstmt.setInt(9, article.getCategorieArticle().getNoCategorie());
					pstmt.setInt(10, article.getNo_article());
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

	@Override
	public void delete(ArticleVendu article) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				if(article.getNo_article()!=0){
					pstmt = cnx.prepareStatement(DELETE, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setInt(1, article.getNo_article());
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

	@Override
	public ArticleVendu select(int id) throws BusinessException {
		ArticleVendu article = null;
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
							Categorie categorie = catDAO.select(rs.getInt("no_categorie"));
							Retrait ret = retDAO.select(rs.getInt("retrait"));
							Utilisateur acheteur = utilDAO.selectByID(rs.getInt("no_acheteur"));
							Utilisateur vendeur = utilDAO.selectByID(rs.getInt("no_vendeur"));
							article = new ArticleVendu();
							article.setNo_article(rs.getInt("no_article"));
							article.setNom_article(rs.getString("nom_article"));
							article.setDescription(rs.getString("description"));
							article.setDate_debut_encheres(rs.getTimestamp("date_debut_encheres"));
							article.setDate_fin_encheres(rs.getTimestamp("date_fin_encheres"));
							article.setPrix_initial(rs.getInt("prix_initial"));
							article.setPrix_vente(rs.getInt("prix_vente"));
							article.setAcheteur(acheteur);
							article.setVendeur(vendeur);
							article.setLieuRetrait(ret);
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
		return article;
	}

	@Override
	public List<ArticleVendu> lister() throws BusinessException {
		ArrayList<ArticleVendu> listArticleVendu = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection()){
			try{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
					pstmt = cnx.prepareStatement(LISTER);
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						ArticleVendu article = null;
						Categorie categorie = catDAO.select(rs.getInt("no_categorie"));
						Retrait ret = retDAO.select(rs.getInt("retrait"));
						Utilisateur acheteur = utilDAO.selectByID(rs.getInt("no_acheteur"));
						Utilisateur vendeur = utilDAO.selectByID(rs.getInt("no_vendeur"));
						article = new ArticleVendu();
						article.setNo_article(rs.getInt("no_article"));
						article.setNom_article(rs.getString("nom_article"));
						article.setDescription(rs.getString("description"));
						article.setDate_debut_encheres(rs.getTimestamp("date_debut_encheres"));
						article.setDate_fin_encheres(rs.getTimestamp("date_fin_encheres"));
						article.setPrix_initial(rs.getInt("prix_initial"));
						article.setPrix_vente(rs.getInt("prix_vente"));
						article.setAcheteur(acheteur);
						article.setVendeur(vendeur);
						article.setLieuRetrait(ret);
						article.setCategorieArticle(categorie);
						
						listArticleVendu.add(article);
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
		return listArticleVendu;
	}

}
