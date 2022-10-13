package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.Manager;
import model.Auteur;

public class GetRecommendation extends HttpServlet {
	private String username = "neo4j";
	private String password = "neo4jneo4j";
	private static final long serialVersionUID = 1L;
    Manager manager = new Manager("neo4j://localhost:7687",username,password);
    
    public GetRecommendation() {
        super();
        
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userSession") !=null){
			String email = (String)session.getAttribute("userSession");
			Auteur user = manager.getAuteur(email);
			System.out.println("Les informations de user online !! ***********");
			System.out.println(user.getNom() +"\t" +user.getPrenom());
			ArrayList<Auteur> Recommendation = manager.RecommendMe(user);
			request.setAttribute("Recommendation", Recommendation);
			request.setAttribute("user", user);
			for(Auteur auteur:Recommendation) {
				System.out.println("Nom : "+auteur.getNom());
				System.out.println("prenom :"+auteur.getPrenom());
				System.out.println("Article title :"+auteur.getArticle().getTitle());
				System.out.println("Article abbreviation :"+auteur.getArticle().getAbbreviation());
			}
			request.getRequestDispatcher("RecommendMeArticles.jsp").forward(request, response);
		}else {
			response.sendRedirect("Login");
		}

	}

}
