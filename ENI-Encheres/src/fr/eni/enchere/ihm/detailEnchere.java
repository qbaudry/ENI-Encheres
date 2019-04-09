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

			ArticleVendu article = new ArticleVendu();
			Retrait retrait = new Retrait();
			Utilisateur util = new Utilisateur();

			try {

				util = utilManager.selectionnerUtilisateur(pseudo, mdp);
				article = articleManager.select(Integer.parseInt(art));		
				retrait = retraitManager.select(Integer.parseInt(art));
				request.setAttribute("formulaire", article);
				request.setAttribute("retrait", retrait);
				Enchere enchere = new Enchere();
				enchere = enchereManager.select(article.getVendeur(), article);
				request.setAttribute("enchere", enchere);

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
		
		ArticleVenduManager articleManager = new ArticleVenduManager();
		RetraitManager retraitManager = new RetraitManager();
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilManager = new UtilisateurManager();

		String art = request.getParameter("id");

		ArticleVendu article = new ArticleVendu();
		Retrait retrait = new Retrait();
		Utilisateur util = new Utilisateur();

		try {

			util = utilManager.selectionnerUtilisateur(pseudo, mdp);
			article = articleManager.select(Integer.parseInt(art));		
			retrait = retraitManager.select(Integer.parseInt(art));
			request.setAttribute("formulaire", article);
			request.setAttribute("retrait", retrait);
			Enchere enchere = new Enchere();
			enchere = enchereManager.select(article.getVendeur(), article);
			request.setAttribute("enchere", enchere);
			String value = request.getParameter("solde");
			enchere.setMontant_enchere(Integer.valueOf(value));
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			enchere.setDateEnchere(timestamp);
			enchere.setEncherit(util);
			enchereManager.save(enchere);
			

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
