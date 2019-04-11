package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class supprimerCompte
 */
public class supprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public supprimerCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");



		if(pseudo == null && mdp == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
		}
		else
		{
			//Méthode d'accès bdd
			UtilisateurManager utilisateurManager = new UtilisateurManager();

			

			Utilisateur util = new Utilisateur();
			try {
				
				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				
				utilisateurManager.deleteUser(util.getNoUtilisateur());
				

				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
				rd.forward(request, response);


			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");



		if(pseudo == null && mdp == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
		}
		else
		{
			//Méthode d'accès bdd
			UtilisateurManager utilisateurManager = new UtilisateurManager();

			

			Utilisateur util = new Utilisateur();
			try {
				if(utilisateurManager.selectionnerUtilisateur(pseudo, mdp).isAdministrateur()) {
					util = utilisateurManager.selectionnerUtilisateur(request.getParameter("login"), request.getParameter("mdp"));
				
					utilisateurManager.deleteUser(util.getNoUtilisateur());
				}

			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

}
