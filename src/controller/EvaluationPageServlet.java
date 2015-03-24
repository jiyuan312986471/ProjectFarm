package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.db.ProjectDB;

@WebServlet("/EvaluationPageServlet")
public class EvaluationPageServlet extends HttpServlet {

	private static final long serialVersionUID = 3904354408714250812L;
	
	public EvaluationPageServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get project info from DB
		Project proj = null;
		String projTitle = req.getParameter("acronym");
		try {
			proj = ProjectDB.getProjectByAcronym(projTitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// save project into request
		req.setAttribute("project", proj);
					
		// Turn to Page ProjectDetails
		req.getRequestDispatcher("/page/Evaluate.jsp").forward(req, resp);
		
	}

}
