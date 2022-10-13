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

public class ModifyInfoAuthor extends HttpServlet {
	private String username = "neo4j";
	private String password = "neo4jneo4j";
	private static final long serialVersionUID = 1L;
    Manager manager = new Manager("neo4j://localhost:7687",username,password);
    public ModifyInfoAuthor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userSession") !=null){
			String email = (String)session.getAttribute("userSession");
			Auteur user = manager.getAuteur(email);
			request.setAttribute("user", user);
			request.getRequestDispatcher("ModifierAuthor.jsp").forward(request, response);
		}else {
			response.sendRedirect("Login");
	}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("act_upd")!=null) {
			Auteur author = new Auteur();
			author.setPrenom(request.getParameter("prenom"));
			author.setId(Integer.parseInt(request.getParameter("id")));
			author.setNom(request.getParameter("nom"));
			author.setMdp(request.getParameter("mdp"));
			author.setEmail(request.getParameter("email"));
			author.setDepartement(request.getParameter("departement"));
			System.out.println("********************** : \t "+author.getId());
			manager.updateAuthor(author);
			HttpSession session = request.getSession();
			session.invalidate();
			doGet(request,response);
		}
	}

}
