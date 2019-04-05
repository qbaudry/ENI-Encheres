package fr.eni.enchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;

@WebServlet("/Ajax_ListeEnchere")
public class Ajax_ListeEnchere extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ajax_ListeEnchere() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieManager categorieManager = new CategorieManager();
		ArticleVenduManager articleManager = new ArticleVenduManager();
		Categorie categ = null;
		try {

			categ = categorieManager.select(Integer.parseInt((String)request.getParameter("categ")));

		} catch (Exception e) {
			e.printStackTrace();
		} 
		ArrayList<ArticleVendu> listeEncheres = new ArrayList<>();
		ArrayList<ArticleVendu> listeEncherestemp = new ArrayList<>();
		try {
			if(Integer.parseInt((String)request.getParameter("categ"))!=0) {
				listeEncheres = (ArrayList<ArticleVendu>) articleManager.selectByCategorie(categ);
			}else {
				listeEncheres = (ArrayList<ArticleVendu>) articleManager.lister();
			}
			String filtre = (String)request.getParameter("filtre");
			System.out.println("|"+filtre+"|");
			if(filtre!=null && !filtre.isEmpty()) {
				listeEncherestemp = (ArrayList<ArticleVendu>) listeEncheres.clone();
				listeEncheres.clear();
				for(ArticleVendu art : listeEncherestemp) {
					if(art.getNom_article().contains(filtre)) {
						listeEncheres.add(art);
					}
				}
			}

			request.setAttribute("articles", listeEncheres);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}

		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/ajax/ajax_listEnchere.jsp");
		rd.forward(request, response);
	}
}
