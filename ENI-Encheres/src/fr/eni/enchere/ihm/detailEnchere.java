package fr.eni.enchere.ihm;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class detailEnchere
 */
@WebServlet("/detailEnchere")
public class detailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public detailEnchere() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");

		ArticleVenduManager articleManager = new ArticleVenduManager();
		RetraitManager retraitManager = new RetraitManager();
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilManager = new UtilisateurManager();

		if(pseudo == null && mdp == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
		}
		else
		{
			
			String art = request.getParameter("no_article");

			if(art == null)
			{
				art = (String) session.getAttribute("id");

			}
			ArticleVendu article = new ArticleVendu();
			Retrait retrait = new Retrait();
			Utilisateur util = new Utilisateur();

			try {
				util = utilManager.selectionnerUtilisateur(pseudo, mdp);
				if(util.getPseudo() == null || util.getBanni()) {
					session.invalidate();
    				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
    				rd.forward(request, response);
				}
				session.setAttribute("credits", util.getCredit());
				article = articleManager.select(Integer.parseInt(art));		
				retrait = retraitManager.select(Integer.parseInt(art));

				Enchere enchere = new Enchere();
				enchere = enchereManager.selectMaxByArticle(article);
				request.setAttribute("enchere", enchere);
				request.setAttribute("formulaire", article);
				request.setAttribute("retrait", retrait);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				
				if(timestamp.after(article.getDate_fin_encheres()))
				{
					if(enchere != null)
					{
						if(util.getNoUtilisateur() == enchere.getEncherit().getNoUtilisateur())
						{
							request.setAttribute("succes", "Bravo, vous avez remporté l'enchère !");
							article.setPrix_vente(enchere.getMontant_enchere());
							articleManager.save(article);
						}
						else
						{
							request.setAttribute("echec", enchere.getEncherit().getPseudo()+" a gagné l'enchère !");
						}
					} else {
						request.setAttribute("echec", "Dommage, l'enchère s'est terminé sans aucune proposition !");
					}
				}

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/DetailsArticle.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");

		List<Integer> listeCodesErreur = new ArrayList<>();

		ArticleVenduManager articleManager = new ArticleVenduManager();
		RetraitManager retraitManager = new RetraitManager();
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilManager = new UtilisateurManager();

		String art = request.getParameter("id");

		ArticleVendu article = new ArticleVendu();
		Retrait retrait = new Retrait();
		Utilisateur util = new Utilisateur();

		lireValeurCredit(request, listeCodesErreur);

		if(listeCodesErreur.size()>0)
		{
			try {
				util = utilManager.selectionnerUtilisateur(pseudo, mdp);
				if(util.getPseudo() == null || util.getBanni()) {
					session.invalidate();
    				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
    				rd.forward(request, response);
				}
				article = articleManager.select(Integer.parseInt(art));		
				retrait = retraitManager.select(Integer.parseInt(art));
				request.setAttribute("formulaire", article);
				request.setAttribute("retrait", retrait);
				Enchere enchere = new Enchere();
				enchere = enchereManager.selectMaxByArticle(article);
				request.setAttribute("enchere", enchere);
				System.out.println("erreur : " + listeCodesErreur);
				request.setAttribute("listeCodesErreur", listeCodesErreur);	
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/DetailsArticle.jsp");
			rd.forward(request, response);
		}
		else
		{
			try {
				util = utilManager.selectionnerUtilisateur(pseudo, mdp);
				article = articleManager.select(Integer.parseInt(art));		
				retrait = retraitManager.select(Integer.parseInt(art));
				request.setAttribute("formulaire", article);
				request.setAttribute("retrait", retrait);
				Enchere enchere = new Enchere();
				enchere = enchereManager.selectMaxByArticle(article);
				if(enchere == null)
				{
					Enchere newEnchere = new Enchere();

					String value = request.getParameter("solde");
					newEnchere.setMontant_enchere(Integer.valueOf(value));

					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					newEnchere.setDateEnchere(timestamp);
					newEnchere.setEncherit(util);
					newEnchere.setConcerne(article);
					enchereManager.save(newEnchere);
					util.setCredit(util.getCredit() - Integer.valueOf(value));
					utilManager.UpdateUtilisateurCreditById(util);
					int credit = util.getCredit() - Integer.valueOf(value);
					session.setAttribute("credits", credit);
					request.setAttribute("enchere", enchere);

				}
				else
				{

					String value = request.getParameter("solde");
					enchere.setMontant_enchere(Integer.valueOf(value));
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					enchere.setDateEnchere(timestamp);
					enchere.setEncherit(util);
					enchereManager.save(enchere);
					util.setCredit(util.getCredit() - Integer.valueOf(value));
					utilManager.UpdateUtilisateurCreditById(util);
					int credit = util.getCredit() - Integer.valueOf(value);
					session.setAttribute("credits", credit);
					request.setAttribute("enchere", enchere);
					List<Enchere> lstenchere = new ArrayList();
					lstenchere = enchereManager.lister();
					for(Enchere ench: lstenchere)
					{
						if(ench.getConcerne().getNo_article() == article.getNo_article())
						{
							if(ench.getEncherit().getNoUtilisateur() != util.getNoUtilisateur())
							{
								ArrayList<Utilisateur> lstutil2 = new ArrayList();
								lstutil2 = utilManager.lister();
								for(Utilisateur util2: lstutil2)
								{
									if(util2.getNoUtilisateur() != util.getNoUtilisateur())
									{
										if(ench.getEncherit().getNoUtilisateur() == util2.getNoUtilisateur())
										{
											util2.setCredit(util2.getCredit() + ench.getMontant_enchere());
											utilManager.UpdateUtilisateurCreditById(util2);
											System.out.println(util2.getCredit());
											session.setAttribute("credits", util2.getCredit() + ench.getMontant_enchere());

										}
									}
								}
							}
						}
					}
				}
				session.setAttribute("id", art);
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setIntHeader("Refresh", 0);
			System.out.println("doPost");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/DetailsArticle.jsp");
			rd.forward(request, response);
		}
	}

	private void lireValeurCredit(HttpServletRequest request, List<Integer> listeCodesErreur) {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");

		String art = request.getParameter("id");

		ArticleVendu article = new ArticleVendu();
		Utilisateur util = new Utilisateur();

		ArticleVenduManager articleManager = new ArticleVenduManager();
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilManager = new UtilisateurManager();
		int value = Integer.valueOf(request.getParameter("solde"));

		try {
			util = utilManager.selectionnerUtilisateur(pseudo, mdp);
			article = articleManager.select(Integer.parseInt(art));	
			Enchere enchere = new Enchere();
			enchere = enchereManager.selectMaxByArticle(article);

			if(enchere == null)
			{
				if(value > util.getCredit() || value < article.getPrix_initial())
				{
					listeCodesErreur.add(CodesResultatServlets.PRIX_COMPRIS);
				}
			}
			else
			{
				if(value > util.getCredit() || value <= enchere.getMontant_enchere())
				{
					listeCodesErreur.add(CodesResultatServlets.PRIX_COMPRIS_DE_LENCHERE);
				}
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}