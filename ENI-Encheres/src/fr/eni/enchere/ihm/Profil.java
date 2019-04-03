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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
        String mdp = (String) session.getAttribute("motdepasse");
        
        
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
