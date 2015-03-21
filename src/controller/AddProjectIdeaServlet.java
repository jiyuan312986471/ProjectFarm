package controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Document;
import model.Evaluation;
import model.Owner;
import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;


@WebServlet("/AddProjectIdeaServlet")
public class AddProjectIdeaServlet extends HttpServlet {

	private static final long serialVersionUID = 2025935986799381632L;
	private Project proj;

	public AddProjectIdeaServlet() {
		super();
		}
	
	public void destroy() {
		super.destroy();
		}
	
	
	
	// Servlet Service
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get values
		String projTitle = req.getParameter("projectTitle");
		String projDescription = req.getParameter("projectDescription");
		String projCategory = req.getParameter("projectCategory");
		String projBudget = req.getParameter("projectBudget");
		Owner projOwner = (Owner) req.getSession().getAttribute("User");
		
		// error message setting
		String errorBudget = "Invalid Budget";
		String errorTitle = "The project needs a title";
		String errorDescription = "The project needs a description";
		String errorAdd = "The project \"" + projTitle + "\" already exists.";
		
		proj = new Project();
		
        // title check
        if ( !proj.isValidProjectTitle(projTitle) ) {
        	req.getSession().setAttribute("messageError", errorTitle);
			req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
        }
        
        // description check
        else if ( !proj.isValidProjectDescription(projDescription) ) {
            req.getSession().setAttribute("messageError", errorDescription);
    		req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
        }
        
		// budget check
        else if ( !proj.isValidProjectBudget(projBudget) ) {
        	req.getSession().setAttribute("messageError", errorBudget);
			req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
        }
        
        
        
        /**************************************************
         * 				IF ALL GOES WELL
         **************************************************/
        // remove error message
        req.getSession().removeAttribute("messageError");
        
        // generate project
        int budget = Integer.parseUnsignedInt(projBudget);
		try {
			//proj = new Project(projTitle, projDescription, budget, projOwner, projCategory);
			proj.setAcronym(projTitle);
			proj.setDescription(projDescription);
			proj.setFundingDuration(100);
			proj.setBudget(budget);
			proj.setCreated(new Date());
			proj.setOwner(projOwner);
			proj.setCategory(projCategory);
			proj.setEvaluations(new LinkedList<Evaluation>());
			proj.setDocuments(new LinkedList<Document>());
		} catch (InvalidDataException e1) {
			e1.printStackTrace();
		}

        // save project to DB
        try {
			ProjectDB.add(proj);
		} catch (DatabaseAccessError e) {
			// if add failed
			req.getSession().setAttribute("messageError", errorAdd);
			req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
		}
        
        // remove parameters from request
        req.removeAttribute("projectTitle");
        req.removeAttribute("projectDescription");
        req.removeAttribute("projectCategory");
        req.removeAttribute("projectBudget");
        
        // save project into request
        req.setAttribute("projectTitle", proj.getAcronym());
        
		// turn to servlet ProjectDetailsServlet
        req.getRequestDispatcher("/ProjectDetailsServlet").forward(req, resp);
	}
	
}
