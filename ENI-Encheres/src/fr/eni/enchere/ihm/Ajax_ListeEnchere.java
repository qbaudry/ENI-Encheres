package fr.eni.enchere.ihm;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/Ajax_ListeEnchere")
public class Ajax_ListeEnchere extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ajax_ListeEnchere() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("identifiant");
        String mdp = (String) session.getAttribute("motdepasse");
        
		CategorieManager categorieManager = new CategorieManager();
		ArticleVenduManager articleManager = new ArticleVenduManager();
		UtilisateurManager utilManager = new UtilisateurManager();
		Utilisateur util = null;
		Timestamp actualTS = new Timestamp(new Date().getTime());
		try {
			util = utilManager.selectionnerUtilisateur((String)session.getAttribute("identifiant"),(String)session.getAttribute("motdepasse"));
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		Categorie categ = null;
		try {

			categ = categorieManager.select(Integer.parseInt((String)request.getParameter("categ")));

		} catch (Exception e) {
			e.printStackTrace();
		} 
		ArrayList<ArticleVendu> listeEncheres = new ArrayList<>();
		ArrayList<ArticleVendu> listeEncherestemp = new ArrayList<>();
		try {
			if(Integer.parseInt((String)request.getParameter("categ"))!=0 && util != null) {
				listeEncheres = (ArrayList<ArticleVendu>) articleManager.selectByCategorie(categ);
			}else {
				listeEncheres = (ArrayList<ArticleVendu>) articleManager.lister();
			}
			String filtre = (String)request.getParameter("filtre");

			if(filtre!=null && !filtre.isEmpty()) {
				listeEncherestemp = (ArrayList<ArticleVendu>) listeEncheres.clone();
				listeEncheres.clear();
				for(ArticleVendu art : listeEncherestemp) {
					if(art.getNom_article().contains(filtre)) {
						listeEncheres.add(art);
					}
				}
			}

			if(util!=null) {
				String achatOuVente = (String)request.getParameter("achat_vente");


				Boolean eOuvertes = Boolean.parseBoolean(request.getParameter("eOuvertes"));
				Boolean eEnCours = Boolean.parseBoolean(request.getParameter("eEnCours"));
				Boolean eFermees = Boolean.parseBoolean(request.getParameter("eFermees"));
				Boolean vEnCours = Boolean.parseBoolean(request.getParameter("vEnCours"));
				Boolean vNonDebutees = Boolean.parseBoolean(request.getParameter("vNonDebutees"));
				Boolean vTerminees = Boolean.parseBoolean(request.getParameter("vTerminees"));

				if(achatOuVente != null) {
					if(achatOuVente.equals("achat")) {
						listeEncherestemp = (ArrayList<ArticleVendu>) listeEncheres.clone();
						listeEncheres.clear();
						for(ArticleVendu art : listeEncherestemp) {

							if(art.getVendeur().getNoUtilisateur() != util.getNoUtilisateur()) {
								//							if((!eOuvertes && !eEnCours && !eFermees)||
								//									(eOuvertes && art.getDate_debut_encheres().before(actualTS) && art.getDate_fin_encheres().after(actualTS))||
								//									(eFermees && art.getDate_fin_encheres().before(actualTS))||
								//									(eEnCours && art.getDate_debut_encheres().before(actualTS) && art.getDate_fin_encheres().after(actualTS))
								//									) {
								listeEncheres.add(art);
								//							}
							}

						}
						//					for(ArticleVendu art : listeEncherestemp) {
						//						if(art.getDate_fin_encheres().after(actualTS) && art.getDate_debut_encheres().before(actualTS) ) {
						//							listeEncheres.add(art);
						//						}
						//					}

					}else {
						listeEncherestemp = (ArrayList<ArticleVendu>) listeEncheres.clone();
						listeEncheres.clear();
						for(ArticleVendu art : listeEncherestemp) {
							if(art.getVendeur().getNoUtilisateur() == util.getNoUtilisateur()) {
								if((!vEnCours && !vTerminees && !vNonDebutees)||
										(vEnCours && art.getDate_debut_encheres().before(actualTS) && art.getDate_fin_encheres().after(actualTS))||
										(vTerminees && art.getDate_fin_encheres().before(actualTS))||
										(vNonDebutees  && art.getDate_debut_encheres().after(actualTS))
										) {
									listeEncheres.add(art);
								}
							}
						}
					}
				}else {
					listeEncherestemp = (ArrayList<ArticleVendu>) listeEncheres.clone();
					listeEncheres.clear();
					for(ArticleVendu art : listeEncherestemp) {
						if(art.getDate_fin_encheres().after(actualTS) && art.getDate_debut_encheres().before(actualTS) ) {
							listeEncheres.add(art);
						}
					}
				}
			}
			request.setAttribute("articles", listeEncheres);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/ajax/ajax_listEnchere.jsp");
		rd.forward(request, response);
	}
}
