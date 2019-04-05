package fr.eni.enchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bo.ArticleVendu;

/**
 * Servlet implementation class detailEnchere
 */
@WebServlet("/detailEnchere")
public class detailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("identifiant");
		String mdp = (String) session.getAttribute("motdepasse");
		ArticleVenduManager articleManager = new ArticleVenduManager();
		
		if(pseudo == null && mdp == null)
		{
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
		}
		else
		{
			String art = request.getParameter("no_article");
			ArticleVendu article = new ArticleVendu();
			
			try {
				
				article = articleManager.select(Integer.parseInt(art));				
				request.setAttribute("formulaire", article);
				
				
				
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/DetailsArticle.jsp");
			rd.forward(request, response);
		}
		
	}

}
