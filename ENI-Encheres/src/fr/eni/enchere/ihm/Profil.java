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
List<Integer> listeCodesErreur = new ArrayList<>();
		
		lireParametreFormulaire(request, listeCodesErreur);
		validerEmailUtilisateur(request, listeCodesErreur);
		validerTelephoneUtilisateur(request, listeCodesErreur);
		validerCodePostalUtilisateur(request, listeCodesErreur);
		
		System.out.println("listeCodesErreur : " + listeCodesErreur);
		
		Utilisateur nouveauCompte = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"), 
				request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"),
				request.getParameter("rue"), request.getParameter("codepostal"), request.getParameter("ville"),
				request.getParameter("motdepasse"));
		
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("email");
		String tel = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String cp = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("motdepasse");
		
		if(listeCodesErreur.size()>0)
		{
			System.out.println("erreur : " + listeCodesErreur);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			request.setAttribute("formulaire", nouveauCompte);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
			rd.forward(request, response);
		}
		else
		{
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			try {
				utilisateurManager.UpdateUtilisateurById(nouveauCompte.getPseudo(), nouveauCompte.getNom(), nouveauCompte.getPrenom(), nouveauCompte.getEmail(), nouveauCompte.getTelephone(), nouveauCompte.getRue(), nouveauCompte.getCodePostal(), nouveauCompte.getVille(), nouveauCompte.getMotDePasse(), nouveauCompte.getNoUtilisateur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
				rd.forward(request, response);
				System.out.println("Utilisateur ajouté");
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				request.setAttribute("formulaire", nouveauCompte);
				System.out.println("Utilisateur non ajouté");
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
		if (pseudo == null || nom == null || prenom == null || email == null || telephone == null || rue == null
				|| codepostal == null || ville == null || motdepasse == null) {
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


