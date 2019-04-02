package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.Properties;

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
 * Servlet implementation class mdpOublie
 */
public class mdpOublie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mdpOublie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ForgotPassword.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		if (request.getParameter("mail")!=null &&
			request.getParameter("pseudo")!=null)
		{
			String mail = request.getParameter("mail");
			String pseudo = request.getParameter("pseudo");
			
			try
			{
				Utilisateur util = new Utilisateur();
				util = utilisateurManager.selectionnerUtilisateurByEmailPseudo(pseudo, mail);
				
				if(util.getPseudo() == null || util.getEmail() == null)
				{
					request.setAttribute("error", "Pseudo ou mail incorrect !");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ForgotPassword.jsp");
					rd.forward(request, response);
				}
				else
				{
					if(util.getPseudo().equals(pseudo) && util.getEmail().equals(mail))
					{						
				        request.setAttribute("congret", "Votre mot de passe : " + util.getMotDePasse());
				        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ForgotPassword.jsp");
						rd.forward(request, response);
					}
					else
					{
						request.setAttribute("error", "Pseudo ou mail incorrect !");
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ForgotPassword.jsp");
						rd.forward(request, response);
					}
				}
				
				

				
				
				
			} catch(BusinessException e)
			{
				
				request.setAttribute("error", "Nom de compte ou mot de passe incorrect !");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
				rd.forward(request, response);
				
			}
			
		} else {
			System.out.println("Formulaire non rempli entièrement");
		}
	}

}
