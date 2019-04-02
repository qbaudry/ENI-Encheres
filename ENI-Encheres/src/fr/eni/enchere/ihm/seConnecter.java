package fr.eni.enchere.ihm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.gestionenchere.BusinessException;

/**
 * Servlet implementation class seConnecter
 */
@WebServlet("/seConnecter")
public class seConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seConnecter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		if (request.getParameter("identifiant")!=null &&
			request.getParameter("motdepasse")!=null)
		{
			String identifiant = request.getParameter("identifiant");
			String mdp = request.getParameter("motdepasse");
			
			try
			{
				Utilisateur util = new Utilisateur();
				util = utilisateurManager.selectionnerUtilisateur(identifiant, mdp);
				
				
				if(util.getPseudo() == identifiant && util.getMotDePasse() == mdp)
				{
					
					
					HttpSession session = request.getSession();

			        session.setAttribute("identifiant", identifiant);
			        session.setAttribute("motdepasse", mdp);
			        
			        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
					rd.forward(request, response);
				}
				else
				{
					
			        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
					rd.forward(request, response);
				}
				
				
				
			} catch(BusinessException e)
			{
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
			
		} else {
			System.out.println("Formulaire non rempli entièrement");
		}
	}

}
