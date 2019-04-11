package fr.eni.enchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class droitsCompte
 */
public class droitsCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public droitsCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur util = new Utilisateur();
			try {
				if(util==null || !util.isAdministrateur()) {
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
					rd.forward(request, response);
				}
				if(utilisateurManager.selectionnerUtilisateur(pseudo, mdp).isAdministrateur()) {
					util = utilisateurManager.selectionnerUtilisateur(request.getParameter("login"), request.getParameter("mdp"));
					utilisateurManager.adminUser(util);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
	}

}
