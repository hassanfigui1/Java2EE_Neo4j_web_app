package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Manager;
import model.Article;
import model.Auteur;
import model.Keyword;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = "neo4j";
	private String password = "neo4jneo4j";
	ArrayList<Auteur> authorConnected = new ArrayList<Auteur>();
    Manager manager = new Manager("neo4j://localhost:7687",username,password);
    ArrayList<Auteur> listAuteurs = manager.getAuthors();
    ArrayList<Auteur> ConnectedUser = new ArrayList<Auteur>();
    ArrayList<Article> userArticles = new ArrayList<Article>();

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userSession") !=null){
			request.setAttribute("userInfo", ConnectedUser);
			request.setAttribute("userArticles", userArticles);
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}if(session.getAttribute("userSession") ==null) {
			userArticles.removeAll(userArticles);
			ConnectedUser.removeAll(ConnectedUser);
			System.out.println("from get "+userArticles);
			System.out.println("After remove user"+ConnectedUser);

		}
			response.sendRedirect("Login.jsp");	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("frm_sub")!= null) {
			String email = request.getParameter("email");
			String mdp = request.getParameter("psw");
			//check if the user's login and password exist
			Auteur user =manager.getAuteur(email, mdp);
			if(user!=null) {
				System.out.println(user.toString());
				ConnectedUser.add(user);
				HttpSession session = request.getSession();
				for(Article article: manager.GetUserArticles(user)) {
					userArticles.add(article);
//					System.out.println(article);
				}

				session.setAttribute("userSession", email);
				doGet(request,response);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				String msgError = "Login or password is incorrect";
				request.setAttribute("msgError", msgError);
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
			doGet(request,response);
		}else if(request.getParameter("art_sub")!=null) {
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
				Auteur user = manager.getAuteur(email);
				Auteur auteur = new Auteur(user.getId(),article);
				manager.addArticle(auteur);
				userArticles.removeAll(userArticles);
				System.out.println("userArticles"+userArticles);
				for(Article articles: manager.GetUserArticles(user)) {
					userArticles.add(articles);
//					System.out.println(article);
				}
				doGet(request,response);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			doGet(request,response);
		}else if(request.getParameter("sub_delete")!=null) {
			int idDelete = Integer.parseInt(request.getParameter("deleteId"));
			manager.removeArticle(idDelete);
			userArticles.removeAll(userArticles);
			HttpSession userSession = request.getSession();
			String email = (String)userSession.getAttribute("userSession");
			Auteur user = manager.getAuteur(email);
			for(Article articles: manager.GetUserArticles(user)) {
				userArticles.add(articles);
			}
			doGet(request,response);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			doGet(request,response);
		}
	}
}
