package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CodesResultatDAL;





public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ACCOUNT_EXIST = "select count (*) AS count FROM UTILISATEURS WHERE pseudo = (?)";
	private static final String SELECT_ACCOUNT = "select * FROM UTILISATEURS WHERE pseudo = (?) AND mot_de_passe = (?) ;";
	private static final String SELECT_ACCOUNT_BY_EMAIL = "select * FROM UTILISATEURS WHERE pseudo = (?) AND email = (?) ;";
	private static final String SELECT_ACCOUNT_BY_ID = "select * FROM UTILISATEURS WHERE no_utilisateur = (?) ;";
	private static final String UPDATE_ACCOUNT ="update UTILISATEURS SET pseudo = (?), nom = (?), prenom = (?), email = (?), telephone = (?), rue = (?), code_postal =(?) , ville = (?), mot_de_passe = (?) where no_utilisateur = (?)";
	private static final String DELETE_ACCOUNT = "DELETE FROM UTILISATEURS WHERE no_utilisateur = (?);";
	private static final String LISTER = "SELECT * FROM UTILISATEURS";
	
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
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Utilisateur selectByUser(String pseudo, String mdp) throws BusinessException {
		Utilisateur util = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACCOUNT);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, mdp);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne=true;
			while(rs.next())
			{
				util.setNoUtilisateur(rs.getInt("no_utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				util.setMotDePasse(rs.getString("mot_de_passe"));
				util.setNom(rs.getString("nom"));
				util.setPrenom(rs.getString("prenom"));
				util.setEmail(rs.getString("email"));
				util.setTelephone(rs.getString("telephone"));
				util.setRue(rs.getString("rue"));
				util.setCodePostal(rs.getString("code_postal"));
				util.setVille(rs.getString("ville"));
				util.setCredit(rs.getInt("credit"));
				util.setAdministrateur(rs.getBoolean("administrateur"));
				

				premiereLigne=false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		/*
		if(util.getPseudo() == null || util.getMotDePasse() == null)
		{
			BusinessException businessException = new BusinessException();	
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		*/
		return util;
	}

	@Override
	public Utilisateur selectByPseudoAndMail(String pseudo, String email) throws BusinessException {
		Utilisateur util = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACCOUNT_BY_EMAIL);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne=true;
			while(rs.next())
			{
				util.setNoUtilisateur(rs.getInt("no_utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				util.setEmail(rs.getString("email"));
				util.setMotDePasse(rs.getString("mot_de_passe"));
				util.setNom(rs.getString("nom"));
				util.setPrenom(rs.getString("prenom"));
				util.setTelephone(rs.getString("telephone"));
				util.setRue(rs.getString("rue"));
				util.setCodePostal(rs.getString("code_postal"));
				util.setVille(rs.getString("ville"));
				util.setCredit(rs.getInt("credit"));
				util.setAdministrateur(rs.getBoolean("administrateur"));
				

				premiereLigne=false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		/*
		if(util.getPseudo() == null || util.getMotDePasse() == null)
		{
			BusinessException businessException = new BusinessException();	
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		*/
		return util;
	}

	@Override
	public Utilisateur selectByID(int id) throws BusinessException {
		Utilisateur util = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACCOUNT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne=true;
			while(rs.next())
			{
				util.setNoUtilisateur(rs.getInt("no_utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				util.setEmail(rs.getString("email"));
				util.setMotDePasse(rs.getString("mot_de_passe"));
				util.setNom(rs.getString("nom"));
				util.setPrenom(rs.getString("prenom"));
				util.setTelephone(rs.getString("telephone"));
				util.setRue(rs.getString("rue"));
				util.setCodePostal(rs.getString("code_postal"));
				util.setVille(rs.getString("ville"));
				util.setCredit(rs.getInt("credit"));
				util.setAdministrateur(rs.getBoolean("administrateur"));
				

				premiereLigne=false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		/*
		if(util.getPseudo() == null || util.getMotDePasse() == null)
		{
			BusinessException businessException = new BusinessException();	
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		*/
		return util;
	}


	@Override
	public void updateByID(Utilisateur util) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			try
			{
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				if(util.getNoUtilisateur()!=0)
				{
					pstmt = cnx.prepareStatement(UPDATE_ACCOUNT);
					pstmt.setString(1, util.getPseudo());
					pstmt.setString(2, util.getNom());
					pstmt.setString(3, util.getPrenom());
					pstmt.setString(4, util.getEmail());
					pstmt.setString(5, util.getTelephone());
					pstmt.setString(6, util.getRue());
					pstmt.setString(7, util.getCodePostal());
					pstmt.setString(8, util.getVille());
					pstmt.setString(9, util.getMotDePasse());
					pstmt.setInt(10, util.getNoUtilisateur());
					pstmt.executeUpdate();					
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
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void deleteUser(int id) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ACCOUNT);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public boolean countPseudo(String pseudo) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ACCOUNT_EXIST);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			int num = 0;
            while(rs.next()){
                num = (rs.getInt(1));
            }
            
			if (num == 0) {
				return true;
			} else {
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<Utilisateur> lister() throws BusinessException {
		ArrayList<Utilisateur> listUtil = new ArrayList<Utilisateur>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(LISTER);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Utilisateur util = new Utilisateur();
				util.setNoUtilisateur(rs.getInt("no_utilisateur"));
				util.setPseudo(rs.getString("pseudo"));
				util.setMotDePasse(rs.getString("mot_de_passe"));
				util.setNom(rs.getString("nom"));
				util.setPrenom(rs.getString("prenom"));
				util.setEmail(rs.getString("email"));
				util.setTelephone(rs.getString("telephone"));
				util.setRue(rs.getString("rue"));
				util.setCodePostal(rs.getString("code_postal"));
				util.setVille(rs.getString("ville"));
				util.setCredit(rs.getInt("credit"));
				util.setAdministrateur(rs.getBoolean("administrateur"));
				
				listUtil.add(util);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
		/*
		if(util.getPseudo() == null || util.getMotDePasse() == null)
		{
			BusinessException businessException = new BusinessException();	
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_INEXISTANT);
			throw businessException;
		}
		*/
		return listUtil;
	}
}