package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.Manager;
import model.Auteur;

public class AddAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username = "neo4j";
	private String password = "neo4jneo4j";
    Manager manager = new Manager("neo4j://localhost:7687",username,password);
    public AddAuthor() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String text = "python,java,google,php, youtube";
//		String[] words=text.split(",");
//		ArrayList<String> keyword = new ArrayList<String>();
//		for(String str:words) {
//			keyword.add(str);
//		}
//		System.out.println("arrayList values");
//		for(String key:keyword) {
//			System.out.println(key);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("frm_sub")!=null) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");	
			String departement  = request.getParameter("departement");
			if(manager.CheckEmail(email)) {
				String msg ="This email exists, Try an other email";
				System.out.println(msg);
				request.setAttribute("msgError", msg);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else {
				Auteur a = new Auteur(nom, prenom, email, mdp,departement);
				manager.addAuthor(a);
				request.getRequestDispatcher("Login.jsp").forward(request, response);

			}

			
		}else {
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
	}

}
