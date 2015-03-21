package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/MyProjectsServlet")
public class MyProjectsServlet extends HttpServlet {

	private static final long serialVersionUID = 8546268582055981926L;
	
	public MyProjectsServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get user
		String mail = req.getSession().getAttribute("mail").toString();
		
		// get all projects belong to the user
		ArrayList<Project> listProj = null;
		try {
			listProj = ProjectDB.getProjectsOfUser(mail);
		} catch (ClassNotFoundException | SQLException | NamingException
				| DatabaseAccessError e) {
			e.printStackTrace();
		}
		
		// set list into request
		req.setAttribute("listProj", listProj);
		
		// Turn to Page MyProjects
		req.getRequestDispatcher("/page/MyProjects.jsp").forward(req, resp);
	}

}
