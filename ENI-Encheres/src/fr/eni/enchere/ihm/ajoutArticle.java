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
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ajoutArticle
 */
public class ajoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajoutArticle() {
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
			


			
			CategorieManager categorieManager = new CategorieManager();
			UtilisateurManager utilisateurManager = new UtilisateurManager();

			List<Categorie> listeCategories = new ArrayList<>();
			List<Integer> listeCodesErreur = new ArrayList<>();
			
			Utilisateur util = new Utilisateur();
			
			
			
			try {
				listeCategories = categorieManager.lister();
				request.setAttribute("categories", listeCategories);
				
				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				//request.setAttribute("debut", );
				request.setAttribute("rue", util.getRue());
    			request.setAttribute("codepostal", util.getCodePostal());
    			request.setAttribute("ville", util.getVille());

			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/AjoutArticle.jsp");
			rd.forward(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
