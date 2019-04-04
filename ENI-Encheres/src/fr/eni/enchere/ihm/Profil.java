package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
 * Servlet implementation class Profil
 */
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
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
        	UtilisateurManager utilisateurManager = new UtilisateurManager();
            
            
            Utilisateur util = new Utilisateur();
    		try {
    			util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
    			request.setAttribute("pseudo", util.getPseudo());
    			request.setAttribute("nom", util.getNom());
    			request.setAttribute("prenom", util.getPrenom());
    			request.setAttribute("email", util.getEmail());
    			request.setAttribute("telephone", util.getTelephone());
    			request.setAttribute("rue", util.getRue());
    			request.setAttribute("codepostal", util.getCodePostal());
    			request.setAttribute("ville", util.getVille());
    			request.setAttribute("motdepasse", util.getMotDePasse());
    			request.setAttribute("credit", util.getCredit());
    			
    			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
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

		 UtilisateurManager utilisateurManager = new UtilisateurManager();
		 HttpSession session = request.getSession();
		 String pseudo = (String) session.getAttribute("identifiant");
	     String mdp = (String) session.getAttribute("motdepasse");
	        
	        Utilisateur util = new Utilisateur();
			try {
				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				String login = (String) request.getParameter("pseudo");
				String nom = (String) request.getParameter("nom");
				String prenom = (String) request.getParameter("prenom");
				String mail = (String) request.getParameter("email");
				String tel = (String) request.getParameter("telephone");
				String rue = (String) request.getParameter("rue");
				String cp = (String) request.getParameter("codepostal");
				String ville = (String) request.getParameter("ville");
				String pass = (String) request.getParameter("motdepasse");
				String credit = (String) request.getParameter("credit");
				
				
				Utilisateur utilUpdate = new Utilisateur(util.getNoUtilisateur() ,login, nom, prenom, mail, tel, rue, cp, ville, pass);
				utilisateurManager.UpdateUtilisateurById(utilUpdate);
				request.setAttribute("congret", "Enregistrement en cours...");
				session.setAttribute("identifiant", utilUpdate.getPseudo());
				session.setAttribute("motdepasse", utilUpdate.getMotDePasse());
				
				
				System.out.println("ok");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
				response.setIntHeader("Refresh", 2);
				rd.forward(request, response);
				
				
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "Problème d'enregistrement !");
				e.printStackTrace();
			}
	}
	
	
	

	}


