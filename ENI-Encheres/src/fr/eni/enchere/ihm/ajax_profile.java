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
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/ajax_profil")
public class ajax_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajax_profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("identifiant");
        String mdp = (String) session.getAttribute("motdepasse");
        
        if(login == null && mdp == null)
        {
        	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
        }
        else
        {
        	UtilisateurManager utilisateurManager = new UtilisateurManager();
            
            Utilisateur util = new Utilisateur();
    		try {
    			util = utilisateurManager.selectionnerUtilisateur(login, mdp);
    			if(util.getPseudo() == null || util.getBanni()) {
    				session.invalidate();
    				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
    				rd.forward(request, response);
    			}
    			request.setAttribute("formulaire", util);
    			
    			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
    			rd.forward(request, response);
    		} catch (BusinessException e) {
    			e.printStackTrace();
    		}
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		String loginUser = (String) session.getAttribute("identifiant");
        String mdpUser = (String) session.getAttribute("motdepasse");
        Utilisateur user = new Utilisateur();
        try {
			user = utilisateurManager.selectionnerUtilisateur(loginUser, mdpUser);
			if(user.getPseudo() == null || user.getBanni()) {
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("admin", user.isAdministrateur());
			}
		} catch (BusinessException e1) {
			e1.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
		}
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		System.out.println("listeCodesErreur : " + listeCodesErreur);
		
		
		String login = (String) request.getParameter("pseudo");
	    String mdp = (String) request.getParameter("mdp");
	    
	    Utilisateur util = new Utilisateur();
		try {
			util = utilisateurManager.selectionnerUtilisateur(login, mdp);
			request.setAttribute("util", util);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/ajax/ajax_profilUtilisateur.jsp");
		rd.forward(request, response);
	}
}


