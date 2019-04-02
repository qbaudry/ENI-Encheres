package fr.eni.enchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.gestionenchere.BusinessException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/CreerCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		if (request.getParameter("pseudo")!=null &&
			request.getParameter("nom")!=null &&
			request.getParameter("prenom")!=null &&
			request.getParameter("email")!=null &&
			request.getParameter("telephone")!=null &&
			request.getParameter("rue")!=null &&
			request.getParameter("codepostal")!=null &&
			request.getParameter("ville")!=null &&
			request.getParameter("motdepasse")!=null &&
			request.getParameter("motdepasse2")!=null)
		{
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
			
			try
			{
				Utilisateur nouveauCompte = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codepostal, ville, motdepasse);
				utilisateurManager.ajouterUtilisateur(nouveauCompte);
			} catch(BusinessException e)
			{
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
		} else {
			System.out.println("Formulaire non rempli entièrement");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/CreerCompte.jsp");
		rd.forward(request, response);
	}

}