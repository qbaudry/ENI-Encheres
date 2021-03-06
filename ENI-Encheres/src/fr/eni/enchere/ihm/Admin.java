package fr.eni.enchere.ihm;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;

@WebServlet("/AdminPage")
public class Admin extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("identifiant");
        String mdp = (String) session.getAttribute("motdepasse");
        ArrayList<Utilisateur> listUser = new ArrayList<Utilisateur>();
        List<Categorie> listCateg = new ArrayList<Categorie>();
        
		UtilisateurManager utilManager = new UtilisateurManager();
		Utilisateur util = null;
		try {
			util = utilManager.selectionnerUtilisateur(login,mdp);
			session.setAttribute("credits", util.getCredit());
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		if(util.getPseudo()==null || !util.isAdministrateur() || util.getBanni()) {
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
		} else {
			try {
				listUser = utilManager.lister();
				request.setAttribute("listUser", listUser);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			CategorieManager categManager = new CategorieManager();
			try {
				listCateg = categManager.lister();
				request.setAttribute("listCateg", listCateg);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/Admin.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		CategorieManager categorieManager = new CategorieManager();
		Categorie categ = new Categorie(request.getParameter("categorie"));
		
		try {
			categorieManager.save(categ);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}
}
