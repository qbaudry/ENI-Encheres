package fr.eni.enchere.bll;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.bll.CodesResultatBLL;

public class UtilisateurManager {
	private UtilisateurDAO utilDAO;

	public UtilisateurManager() {
		this.utilDAO=DAOFactory.getUtilisateurDAO();
	}

	public void ajouterUtilisateur(Utilisateur util) throws BusinessException
	{
		this.utilDAO.insert(util);
	}

	public Utilisateur selectionnerUtilisateur(String pseudo, String mdp) throws BusinessException {
		Utilisateur util = this.utilDAO.selectByUser(pseudo, mdp);
		ArticleVenduManager artManager = new ArticleVenduManager();
		List<ArticleVendu> listArt = artManager.selectByVendeur(util);
		if(!listArt.isEmpty()) {
			for(ArticleVendu art : listArt) {
				if(!art.getPaye() && art.getDate_fin_encheres().before(new Timestamp(System.currentTimeMillis()))) {
					System.out.println("article non payé et enchere terminée");
					if(art.getConcerne() != null) {
						Utilisateur vendeur = art.getVendeur();
						System.out.println(vendeur.getPseudo() + " se fait créditer " 
						+ art.getConcerne().getMontant_enchere() + " pour la vente de " + art.getNom_article() + 
						" à " + art.getConcerne().getEncherit().getPseudo());
						System.out.println("il avait " + vendeur.getCredit() + "il a maintenant " 
						+ vendeur.getCredit()+art.getConcerne().getMontant_enchere());
						vendeur.setCredit(vendeur.getCredit()+art.getConcerne().getMontant_enchere());
						this.UpdateUtilisateurCreditById(vendeur);
						
					}
					art.setPaye(true);
					artManager.save(art);
				}
			}
		}
		return util;
	}

	public Utilisateur selectionnerUtilisateurByEmailPseudo(String pseudo, String email) throws BusinessException {
		return this.utilDAO.selectByPseudoAndMail(pseudo, email);
	}

	public Utilisateur selectionnerUtilisateurById(int id) throws BusinessException {
		return this.utilDAO.selectByID(id);
	}

	public void UpdateUtilisateurById(Utilisateur util) throws BusinessException {
		this.utilDAO.updateByID(util);
	}

	public void UpdateUtilisateurCreditById(Utilisateur util) throws BusinessException {
		this.utilDAO.updateCreditByID(util);
	}

	public void deleteUser(int id) throws BusinessException {
		ArticleVenduManager artManager = new ArticleVenduManager();
		ArrayList<ArticleVendu> listArt = (ArrayList<ArticleVendu>) artManager.selectByVendeur(this.selectionnerUtilisateurById(id));
		if(!listArt.isEmpty()) {
			for(ArticleVendu art : listArt) {
				artManager.delete(art);
			}
		}
		this.utilDAO.deleteUser(id);
	}

	public ArrayList<Utilisateur> lister() throws BusinessException{
		return (ArrayList<Utilisateur>) this.utilDAO.lister();
	}
	public boolean countPseudo(String pseudo) throws BusinessException {
		return this.utilDAO.countPseudo(pseudo);
	}

}