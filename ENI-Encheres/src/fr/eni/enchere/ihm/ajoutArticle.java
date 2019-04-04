package fr.eni.enchere.ihm;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class ajoutArticle
 */
public class ajoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajoutArticle() {
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
			CategorieManager categorieManager = new CategorieManager();
			UtilisateurManager utilisateurManager = new UtilisateurManager();

			List<Categorie> listeCategories = new ArrayList<>();
			List<Integer> listeCodesErreur = new ArrayList<>();

			Utilisateur util = new Utilisateur();

			try {
				listeCategories = categorieManager.lister();
				request.setAttribute("categories", listeCategories);

				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				request.setAttribute("utilisateur", util);

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
				String debut = yyyyMMdd.format(timestamp).toString()+"T"+HHmm.format(timestamp).toString();
				request.setAttribute("debut", debut);


			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/AjoutArticle.jsp");
			rd.forward(request, response); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UtilisateurManager utilisateurManager = new UtilisateurManager();
		ArticleVenduManager articleManager = new ArticleVenduManager();
		CategorieManager categManager = new CategorieManager();
		RetraitManager retraitManager = new RetraitManager();
		
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");

		Utilisateur util = new Utilisateur();


		try {
			util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
			String article = request.getParameter("article");
			String description = request.getParameter("description");
			int categorie = Integer.valueOf(request.getParameter("categorie"));
			String image = request.getParameter("image");
			int prix = Integer.valueOf(request.getParameter("prix"));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Timestamp debut = getTimestamp(format.parse(request.getParameter("debut")));
			System.out.println(debut);
			Timestamp fin = getTimestamp(format.parse(request.getParameter("fin")));
			String rue = (String) request.getParameter("rue");
			String cp = (String) request.getParameter("codepostal");
			String ville = (String) request.getParameter("ville");

			Categorie categ = new Categorie();
			categ = categManager.select(categorie);
			ArticleVendu artVendus = new ArticleVendu(article, description, debut, fin, prix, 0, util, categ, "test");

			articleManager.save(artVendus);

			
			Retrait retrait = new Retrait(artVendus.getNo_article(), util.getRue(), util.getCodePostal(), util.getVille());
			
			retraitManager.save(retrait);
		
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp");
			rd.forward(request, response);


		} catch (BusinessException | ParseException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", "Probl�me d'enregistrement !");
			e.printStackTrace();
		}
	}
	
	public static Timestamp getTimestamp(java.util.Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }

}
