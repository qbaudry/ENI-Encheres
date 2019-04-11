package fr.eni.enchere.ihm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.jmx.snmp.Timestamp;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.BusinessException;

/**
 * Servlet implementation class listesEncheres
 */
@WebServlet("/listeEncheres")
public class listeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listeEncheres() {
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
		
		CategorieManager categorieManager = new CategorieManager();
		ArticleVenduManager articleManager = new ArticleVenduManager();
		UtilisateurManager utilManager = new UtilisateurManager();
		
		List<ArticleVendu> listeEncheres = new ArrayList<>();
		List<Categorie> listeCategories = new ArrayList<>();
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		try {
			Utilisateur util = utilManager.selectionnerUtilisateur(login, mdp);
			/*if(util.getPseudo() == null || util.getBanni()) {
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
				rd.forward(request, response);
			}*/
			session.setAttribute("credits", utilManager.selectionnerUtilisateur(login, mdp).getCredit());
			listeCategories = categorieManager.lister();
			request.setAttribute("categories", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		try {
			listeEncheres = articleManager.lister();
			
			request.setAttribute("articles", listeEncheres);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ListeEncheres.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
