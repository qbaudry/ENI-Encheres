package fr.eni.enchere.ihm;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

/**
 * Servlet implementation class editionArticle
 */
public class editionArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editionArticle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");

		ArticleVenduManager articleManager = new ArticleVenduManager();
		RetraitManager retraitManager = new RetraitManager();
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilManager = new UtilisateurManager();

		if(pseudo == null && mdp == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
		}
		else
		{
			String art = request.getParameter("no_article");

			if(art == null)
			{
				art = (String) session.getAttribute("id");

			}
			ArticleVendu article = new ArticleVendu();
			Retrait retrait = new Retrait();
			Utilisateur util = new Utilisateur();

			CategorieManager categorieManager = new CategorieManager();
			
			List<Categorie> listeCategories = new ArrayList<>();
			
			try {
				util = utilManager.selectionnerUtilisateur(pseudo, mdp);
				session.setAttribute("credits", util.getCredit());
				article = articleManager.select(Integer.parseInt(art));		
				retrait = retraitManager.select(Integer.parseInt(art));
				listeCategories = categorieManager.lister();
				request.setAttribute("categories", listeCategories);
				
				//Date de d�but de l'ench�re
				Timestamp timestamp = article.getDate_debut_encheres();
				SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
				String debut = yyyyMMdd.format(timestamp).toString()+"T"+HHmm.format(timestamp).toString();
				request.setAttribute("datedebut", debut);
				
				//Date de fin de l'ench�re
				Timestamp timestampFin = article.getDate_fin_encheres();
				SimpleDateFormat yyyyMMddFin = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat HHmmFin = new SimpleDateFormat("HH:mm");
				String fin = yyyyMMddFin.format(timestampFin).toString()+"T"+HHmmFin.format(timestampFin).toString();
				request.setAttribute("datefin", fin);
				
				Enchere enchere = new Enchere();
				enchere = enchereManager.selectMaxByArticle(article);
				request.setAttribute("enchere", enchere);
				request.setAttribute("article", article);
				request.setAttribute("retrait", retrait);				

				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("id", art);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/EditionArticle.jsp");
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

		List<Categorie> listeCategories = new ArrayList<>();
		List<Integer> listeCodesErreur = new ArrayList<>();
		lireParametreFormulaire(request, listeCodesErreur);
		lirePrixNonNegatif(request, listeCodesErreur);
		lireJavaScript(request, listeCodesErreur);
		lireDatedeFin(request, listeCodesErreur);
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");
		String art = request.getParameter("no_article");

		if(art == null)
		{
			art = (String) session.getAttribute("id");

		}
		
		Utilisateur util = new Utilisateur();
		ArticleVendu article = new ArticleVendu();
		Retrait retrait = new Retrait();
		
		if(listeCodesErreur.size()>0)
		{
			System.out.println("erreur : " + listeCodesErreur);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			request.setAttribute("formulaire", article);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/EditionArticle.jsp");
			rd.forward(request, response);
		}
		else
		{
			try {
				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				session.setAttribute("credits", util.getCredit());
				article = articleManager.select(Integer.parseInt(art));		
				retrait = retraitManager.select(Integer.parseInt(art));
				listeCategories = categManager.lister();
				request.setAttribute("categories", listeCategories);
				String Nomarticle = request.getParameter("article");
				String description = request.getParameter("description");
				int categorie = Integer.valueOf(request.getParameter("categorie"));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Timestamp debut = getTimestamp(format.parse(request.getParameter("debut")));
				Timestamp fin = getTimestamp(format.parse(request.getParameter("fin")));
				String rue = (String) request.getParameter("rue");
				String cp = (String) request.getParameter("codepostal");
				String ville = (String) request.getParameter("ville");
				Categorie categ = new Categorie();
				categ = categManager.select(categorie);
				
				//Set des valeurs
				article.setNom_article(Nomarticle);
				article.setDescription(description);
				article.setDate_debut_encheres(debut);
				article.setDate_fin_encheres(fin);
				article.setCategorieArticle(categ);
				retrait.setCode_postal(cp);
				retrait.setRue(rue);
				retrait.setVille(ville);
				
				//Enregistrement BDD
				articleManager.save(article);
				retraitManager.save(retrait);
				
			} catch (BusinessException | ParseException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "Probl�me d'enregistrement !");
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/ListeEncheres.jsp");
			rd.forward(request, response);
			
		}
	}
	
	private void lireDatedeFin(HttpServletRequest request, List<Integer> listeCodesErreur) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		try {
			Timestamp debut = getTimestamp(format.parse(request.getParameter("debut")));
			Timestamp fin = getTimestamp(format.parse(request.getParameter("fin")));
			if(fin.before(debut))
			{
				listeCodesErreur.add(CodesResultatServlets.DATE_INCOHERANTE);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void lireJavaScript(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String rue = (String) request.getParameter("rue");
		String cp = (String) request.getParameter("codepostal");
		String ville = (String) request.getParameter("ville");
		if(article.contains("<") || description.contains("<") || rue.contains("<") || cp.contains("<") || ville.contains("<"))
		{
			listeCodesErreur.add(CodesResultatServlets.EMPECHER_JAVASCRIPT);
		}
	}

	private void lirePrixNonNegatif(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String prix = request.getParameter("prix");
		if(prix.startsWith("-"))
		{
			listeCodesErreur.add(CodesResultatServlets.PRIX_NON_NEGATIF);
		}
	}

	public static Timestamp getTimestamp(java.util.Date date) {
		return date == null ? null : new java.sql.Timestamp(date.getTime());
	}

	private void lireParametreFormulaire(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String rue = (String) request.getParameter("rue");
		String cp = (String) request.getParameter("codepostal");
		String ville = (String) request.getParameter("ville");
		
		if (article.equals("") || description.equals("") || rue.equals("") || cp.equals("")
				|| ville.equals("") || ville.equals("") || debut.equals("") || fin.equals("")) {
			listeCodesErreur.add(CodesResultatServlets.FORMULAIRE_AJOUT_SAISIE_OBLIGATOIRE);
		}
	}
}
