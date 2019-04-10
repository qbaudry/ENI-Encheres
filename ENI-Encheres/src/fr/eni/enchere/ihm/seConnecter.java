package fr.eni.enchere.ihm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

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
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		 Boolean souvenir = false;
		if (cookies != null) {
		 for (Cookie cookie : cookies) {
		   if (cookie.getName().equals("sesouvenir") && cookie.getValue().equals("true")) {
			   souvenir = true;
				request.setAttribute("sesouvenir",true);
		   }
		  }
		 for (Cookie cookie : cookies) {
			   if (cookie.getName().equals("sesouvenirPseudo") && souvenir) {
					request.setAttribute("sesouvenir",true);
					request.setAttribute("pseudo",cookie.getValue());
			   }else if(cookie.getName().equals("sesouvenirMdp") && souvenir) {

					request.setAttribute("mdp",cookie.getValue());
			   }
			  }
		}
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
				
				if(util.getPseudo() == null || util.getMotDePasse() == null)
				{
					System.out.println(identifiant + " " + mdp);
					System.out.println(util.getPseudo() + " " + util.getMotDePasse());
					request.setAttribute("error", "Nom de compte ou mot de passe incorrect !");
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
					rd.forward(request, response);
				}
				else
				{
					if(util.getPseudo().equals(identifiant) && util.getMotDePasse().equals(mdp))
					{
						HttpSession session = request.getSession();
				        session.setAttribute("identifiant", util.getPseudo());
				        session.setAttribute("motdepasse", util.getMotDePasse());
				        session.setAttribute("credits", util.getCredit());
				        session.setAttribute("admin", util.isAdministrateur());
				        if(request.getParameter("sesouvenir") != null && request.getParameter("sesouvenir").equalsIgnoreCase("on")) {
				        	Cookie sesouvenir = new Cookie("sesouvenir","true");
				        	sesouvenir.setMaxAge(2147483647);
				        	response.addCookie(sesouvenir);
				        	Cookie sesouvenirPseudo = new Cookie("sesouvenirPseudo", util.getPseudo());
				        	sesouvenirPseudo.setMaxAge(2147483647);
				        	response.addCookie(sesouvenirPseudo);
				        	Cookie sesouvenirMdp = new Cookie("sesouvenirMdp", util.getMotDePasse());
				        	sesouvenirMdp.setMaxAge(2147483647);
				        	response.addCookie(sesouvenirMdp);
				        }
				        else {
				        	Cookie sesouvenir = new Cookie("sesouvenir","");
				        	sesouvenir.setMaxAge(2147483647);
				        	response.addCookie(sesouvenir);
				        	Cookie sesouvenirPseudo = new Cookie("sesouvenirPseudo","");
				        	sesouvenirPseudo.setMaxAge(2147483647);
				        	response.addCookie(sesouvenirPseudo);
				        	Cookie sesouvenirMdp = new Cookie("sesouvenirMdp", "");
				        	sesouvenirMdp.setMaxAge(2147483647);
				        	response.addCookie(sesouvenirMdp);
				        }
				        request.setAttribute("congret", "Bienvenue " + util.getPseudo() + " !");
				        RequestDispatcher rd = request.getRequestDispatcher("/listeEncheres");
						rd.forward(request, response);
					}
					else
					{
						request.setAttribute("error", "Nom de compte ou mot de passe incorrect !");
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/SeConnecter.jsp");
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
			System.out.println("Formulaire non rempli enti�rement");
		}
	}
}
