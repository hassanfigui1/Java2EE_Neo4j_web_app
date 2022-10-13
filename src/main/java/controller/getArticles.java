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

public class getArticles extends HttpServlet {
	private String username = "neo4j";
	private String password = "neo4jneo4j";

	private static final long serialVersionUID = 1L;
    Manager m = new Manager("neo4j://localhost:7687",username,password);
    ArrayList<Auteur> listAuteurs = m.getAuthors();
       
    public getArticles() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("userSession") == null) {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		request.setAttribute("list", listAuteurs);
		request.getRequestDispatcher("les_auteurs.jsp").forward(request, response);	
	}

}
