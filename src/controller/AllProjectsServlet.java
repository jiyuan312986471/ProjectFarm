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

@WebServlet("/AllProjectsServlet")
public class AllProjectsServlet extends HttpServlet {

	private static final long serialVersionUID = 8859370956703894061L;
	
	private static String msgErrorLogin = "Please login";

	public AllProjectsServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ( req.getSession().getAttribute("mail") == null ) {
			req.getSession().setAttribute("messageError", msgErrorLogin);
			resp.sendRedirect("index.jsp");
		}
		else {
			// get all projects
			ArrayList<Project> listProj = null;
			try {
				listProj = ProjectDB.getAllProjects();
			} catch (ClassNotFoundException | SQLException | NamingException | DatabaseAccessError e) {
				e.printStackTrace();
			}
	
			// set list into request
			req.setAttribute("listProj", listProj);
	
			// Turn to Page MyProjects
			req.getRequestDispatcher("/page/AllProjects.jsp").forward(req, resp);
		}
	}

}
