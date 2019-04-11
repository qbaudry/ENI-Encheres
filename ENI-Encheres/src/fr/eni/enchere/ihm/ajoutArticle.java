package fr.eni.enchere.ihm;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.sun.xml.internal.txw2.Document;

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
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
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
			Utilisateur util = new Utilisateur();

			try {
				listeCategories = categorieManager.lister();
				request.setAttribute("categories", listeCategories);

				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				if(util.getPseudo() == null || util.getBanni()) {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
					rd.forward(request, response);
				}
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

		List<Integer> listeCodesErreur = new ArrayList<>();
		lireParametreFormulaire(request, listeCodesErreur);
		lirePrixNonNegatif(request, listeCodesErreur);
		lireJavaScript(request, listeCodesErreur);
		lireDatedeFin(request, listeCodesErreur);

		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");

		Utilisateur util = new Utilisateur();
		ArticleVendu art = new ArticleVendu(request.getParameter("article"), request.getParameter("description"), Integer.valueOf(request.getParameter("prix")));


		if(listeCodesErreur.size()>0)
		{
			System.out.println("erreur : " + listeCodesErreur);
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			request.setAttribute("formulaire", art);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/AjoutArticle.jsp");
			rd.forward(request, response);
		}
		else
		{
			try {

				//Recupere utilisateur et ses infos
				util = utilisateurManager.selectionnerUtilisateur(pseudo, mdp);
				if(util.getPseudo() == null || util.getBanni()) {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
					rd.forward(request, response);
				}
				session.setAttribute("credits", util.getCredit());

				//Recuere les infos de article
				String article = request.getParameter("article");
				String description = request.getParameter("description");
				int categorie = Integer.valueOf(request.getParameter("categorie"));
				int prix = Integer.valueOf(request.getParameter("prix"));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Timestamp debut = getTimestamp(format.parse(request.getParameter("debut")));
				Timestamp fin = getTimestamp(format.parse(request.getParameter("fin")));
				String rue = (String) request.getParameter("rue");
				String cp = (String) request.getParameter("codepostal");
				String ville = (String) request.getParameter("ville");


				//Gestion image
				String uploadPath = System.getProperty("user.home") + "\\git\\ENI-Encheres\\ENI-Encheres\\WebContent\\images";
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists())
					uploadDir.mkdir();

				System.out.println(uploadDir);
				String fileName = "";
				for (Part part : request.getParts()) {
					fileName = getFileName(part);

					part.write(uploadPath + File.separator + fileName);


				}
				System.out.println(fileName);
				System.out.println(uploadPath + File.separator + fileName);
				request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
				System.out.println(uploadPath);
				System.out.println("File " + fileName + " has uploaded successfully!");

				//Recupere categorie
				Categorie categ = new Categorie();
				categ = categManager.select(categorie);


				ArticleVendu artVendus = new ArticleVendu(article, description, debut, fin, prix, 0, util, categ, fileName);
				artVendus.setPaye(false);
				articleManager.save(artVendus);

				Retrait retrait = new Retrait(artVendus.getNo_article(), rue, cp, ville);
				retraitManager.save(retrait);

				RequestDispatcher rd = request.getRequestDispatcher("/listeEncheres");
				rd.forward(request, response);




			} catch (BusinessException | ParseException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", "Probleme d'enregistrement !");
				e.printStackTrace();
			}
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
		String prix = request.getParameter("prix");
		String debut = request.getParameter("debut");
		String fin = request.getParameter("fin");
		String rue = (String) request.getParameter("rue");
		String cp = (String) request.getParameter("codepostal");
		String ville = (String) request.getParameter("ville");

		if (article.equals("") || description.equals("") || prix.equals("") || rue.equals("") || cp.equals("")
				|| ville.equals("") || ville.equals("") || debut.equals("") || fin.equals("")) {
			listeCodesErreur.add(CodesResultatServlets.FORMULAIRE_AJOUT_SAISIE_OBLIGATOIRE);
		}
	}

	private String getFileName(Part part) {
		
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
			{
				System.out.println(content);
				String image = content.substring(content.indexOf("=") + 2, content.length() - 1);
				return image;
			}

		}
		//return "newfile.file";
		return "newfile.file";
	}


}
