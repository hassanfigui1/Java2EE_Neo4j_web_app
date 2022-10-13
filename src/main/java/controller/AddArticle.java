package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Manager;
import model.Article;
import model.Auteur;
import model.Keyword;

public class AddArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = "neo4j";
	private String password = "neo4jneo4j";
    Manager manager = new Manager("neo4j://localhost:7687",username,password);

    public AddArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		if(userSession.getAttribute("userSession")!=null) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("Login").forward(request, response);
		}
		Auteur a = new Auteur();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("art_sub")!=null) {
			String title = request.getParameter("title");
			String abbreviation = request.getParameter("abbreviation");
			String motCles = request.getParameter("keywords");
			ArrayList<String> keywords = new ArrayList<String>();
			String [] keyWords = motCles.split(",");
			for(String str: keyWords) {
				keywords.add(str);
			}
			HttpSession userSession = request.getSession();
			if(userSession.getAttribute("userSession")!=null) {
				Keyword keyword = new Keyword(keywords);
				Article article = new Article(title,abbreviation,keyword);
				String email = (String)userSession.getAttribute("userSession");
				System.out.println(email);
				Auteur user = manager.getAuteur(email);
				Auteur auteur = new Auteur(user.getId(),article);
				manager.addArticle(auteur);
			}
			request.getRequestDispatcher("Login").forward(request, response);
		}
	
	}

}
