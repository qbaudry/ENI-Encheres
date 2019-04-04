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
			System.out.println(request.getParameter("categ"));
			categ = categorieManager.select(Integer.parseInt((String)request.getParameter("categ")));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		List<ArticleVendu> listeEncheres = new ArrayList<>();
		try {
			listeEncheres = articleManager.selectByCategorie(categ);
			System.out.println(listeEncheres.size());
			request.setAttribute("articles", listeEncheres);
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/ajax/ajax_listEnchere.jsp");
		rd.forward(request, response);
	}
}
