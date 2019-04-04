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
@WebServlet("/monProfil")
public class monProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public monProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

		List<Integer> listeCodesErreur = new ArrayList<>();
		
		lireParametreFormulaire(request, listeCodesErreur);
		validerEmailUtilisateur(request, listeCodesErreur);
		validerTelephoneUtilisateur(request, listeCodesErreur);
		validerCodePostalUtilisateur(request, listeCodesErreur);
		
		System.out.println("listeCodesErreur : " + listeCodesErreur);
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("identifiant");
	    String mdp = (String) session.getAttribute("motdepasse");
	    
	    Utilisateur util = new Utilisateur();
		try {
			util = utilisateurManager.selectionnerUtilisateur(login, mdp);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
	    
	    if(listeCodesErreur.size()>0)
		{
			System.out.println("erreur : " + listeCodesErreur);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			request.setAttribute("formulaire", util);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
			rd.forward(request, response);
		}
		else
		{
			try {
				Utilisateur modifCompte = new Utilisateur(util.getNoUtilisateur(), request.getParameter("pseudo"),
						request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"),
						request.getParameter("telephone"), request.getParameter("rue"), request.getParameter("codepostal"),
						request.getParameter("ville"), request.getParameter("motdepasse"));
				
				utilisateurManager.UpdateUtilisateurById(modifCompte);
				
				//request.setAttribute("congret", "Enregistrement en cours...");
				session.setAttribute("identifiant", modifCompte.getPseudo());
				session.setAttribute("motdepasse", modifCompte.getMotDePasse());
				
				RequestDispatcher rd = request.getRequestDispatcher("/listeEncheres");
				//response.setIntHeader("Refresh", 2);
				rd.forward(request, response);
				
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("formulaire", util);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
				rd.forward(request, response);
			}
		}
	}

	private void lireParametreFormulaire(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codepostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String motdepasse = request.getParameter("motdepasse");
		String credit = request.getParameter("credit");
		if (pseudo == null || nom == null || prenom == null || email == null || telephone == null || rue == null
				|| codepostal == null || ville == null || motdepasse == null || credit == null) {
			listeCodesErreur.add(CodesResultatServlets.FORMULAIRE_INSCIPTION_SAISIE_OBLIGATOIRE);
		}
	}
	
	private void validerEmailUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String email = request.getParameter("email");
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (!pat.matcher(email).matches()) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_INSCIPTION_SAISIE_OBLIGATOIRE);
		}
	}
	
	private void validerTelephoneUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String telephone = request.getParameter("telephone");
		String codepostalRegex = "^[0-9]{10}$";
		Pattern pat = Pattern.compile(codepostalRegex);
		if (!pat.matcher(telephone).matches()) {
			listeCodesErreur.add(CodesResultatServlets.TELEPHONE_INSCIPTION_SAISIE_OBLIGATOIRE);
		}
	}

	private void validerCodePostalUtilisateur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String codepostal = request.getParameter("codepostal");
		String codepostalRegex = "^[0-9]{5}$";
		Pattern pat = Pattern.compile(codepostalRegex);
		if (!pat.matcher(codepostal).matches()) {
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_INSCIPTION_SAISIE_OBLIGATOIRE);
		}
	}
}

