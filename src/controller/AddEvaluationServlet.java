package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evaluation;
import model.Project;
import model.db.EvaluationDB;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

@WebServlet("/AddEvaluationServlet")
public class AddEvaluationServlet extends HttpServlet {

	private static final long serialVersionUID = 8041168495481984051L;

	public AddEvaluationServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*****************************************
		 * 		   	   PREPARATION
		 *****************************************/
		// error msg
		String errorRiskLevel = "Please choose the risk level";
		String errorAttractiveness = "Please choose the attractiveness";
		
		// get proj acronym
		String acronym = req.getSession().getAttribute("projectTitle").toString();
		
		// get evaluation
		String risk = req.getParameter("riskLevel").toString();
		String attract = req.getParameter("attractiveness").toString();
		
		// get project
		Project proj = null;
		try {
			proj = ProjectDB.getProjectByAcronym(acronym);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// check input
		if ( risk.equals("Please choose...") ) {
			req.setAttribute("project", proj);
			req.getSession().setAttribute("messageError", errorRiskLevel);
			req.getRequestDispatcher("/page/Evaluate.jsp").forward(req, resp);
		}
		else if ( attract.equals("Please choose...") ) {
			req.setAttribute("project", proj);
			req.getSession().setAttribute("messageError", errorAttractiveness);
			req.getRequestDispatcher("/page/Evaluate.jsp").forward(req, resp);
		}
		
		
		/*****************************************
		 * 		   IF ALL GOES WELL
		 *****************************************/
		// remove error message
		req.removeAttribute("messageError");
		
		try {
			// get values in integer
			int riskLevel = Integer.parseInt(risk);
			int attractiveness = Integer.parseInt(attract);
			
			// generate evaluation
			Evaluation eva = new Evaluation();
			eva.setProject(proj);
			eva.setEvaluator( UserDB.getEvaluator(req.getSession().getAttribute("mail").toString()) );
			eva.setRiskLevel(riskLevel);
			eva.setAttractiveness(attractiveness);
			
			// store into DB
			EvaluationDB.add(eva);
		} catch (ClassNotFoundException | InvalidDataException | DatabaseAccessError | SQLException | NamingException e1) {
			e1.printStackTrace();
		}
		
		
		/*****************************************
		 * 		      REDIRECTION
		 *****************************************/
		resp.sendRedirect("AllProjectsServlet");
		
	}
}
