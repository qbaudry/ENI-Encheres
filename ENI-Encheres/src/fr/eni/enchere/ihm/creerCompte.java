package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class creerCompte
 */
@WebServlet("/creerCompte")
public class creerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public creerCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/CreerCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		List<Integer> listeCodesErreur = new ArrayList<>();

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codepostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String motdepasse = request.getParameter("motdepasse");
		String motdepasse2 = request.getParameter("motdepasse2");

		if (pseudo != null && nom != null && prenom != null && email != null && telephone != null && rue != null
				&& codepostal != null && ville != null && motdepasse != null && motdepasse2 != null) {

			try {
				Utilisateur nouveauCompte = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codepostal,
						ville, motdepasse);
				utilisateurManager.ajouterUtilisateur(nouveauCompte);
				//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
				//rd.forward(request, response);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		} else {
			System.out.println("Formulaire non rempli entièrement");
		}
	}
	
	//Verification confirmation mot de passe
	public static boolean isValidMdp(String motdepasse, String motdepasse2) {
		if (motdepasse == motdepasse2)
			return true;
		return false;
	}

}