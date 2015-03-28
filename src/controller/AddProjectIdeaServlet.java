package controller;

import java.io.IOException;
import java.sql.Timestamp;
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
import model.User;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;


@WebServlet("/AddProjectIdeaServlet")
public class AddProjectIdeaServlet extends HttpServlet {

	private static final long serialVersionUID = 2025935986799381632L;
	
	private static String msgErrorLogin = "Please login";
	
	private Project proj;

	public AddProjectIdeaServlet() {
		super();
		}
	
	public void destroy() {
		super.destroy();
		}
	
	// Servlet Service
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ( req.getSession().getAttribute("mail") == null ) {
			req.getSession().setAttribute("messageError", msgErrorLogin);
			resp.sendRedirect("index.jsp");
		}
		else {
			// get values
			String projTitle = req.getParameter("projectTitle").toString();
			String projDescription = req.getParameter("projectDescription").toString();
			String projCategory = req.getParameter("projectCategory").toString();
			String projBudget = req.getParameter("projectBudget").toString();
			
			// get owner
			User u = (User) req.getSession().getAttribute("User");
			Owner projOwner = new Owner();
			projOwner.setEmail(u.getEmail());
			projOwner.setName(u.getName());
			projOwner.setPassword(u.getPassword());
			projOwner.setUserType(u.getUserType());
			
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
				req.getSession().removeAttribute("messageError");
	        }
	        
	        // description check
	        else if ( !proj.isValidProjectDescription(projDescription) ) {
	            req.getSession().setAttribute("messageError", errorDescription);
	    		req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
	    		req.getSession().removeAttribute("messageError");
	        }
	        
			// budget check
	        else if ( !proj.isValidProjectBudget(projBudget) ) {
	        	req.getSession().setAttribute("messageError", errorBudget);
				req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
				req.getSession().removeAttribute("messageError");
	        }
	        
	        
	        /**************************************************
	         * 				IF ALL GOES WELL
	         **************************************************/
	        // generate project
	        int budget = Integer.parseUnsignedInt(projBudget);
			proj.setAcronym(projTitle);
			proj.setDescription(projDescription);
			proj.setBudget(budget);
			proj.setCreated(new Timestamp(System.currentTimeMillis()));
			proj.setOwner(projOwner);
			proj.setCategory(projCategory);
			proj.setEvaluations(new LinkedList<Evaluation>());
			proj.setDocuments(new LinkedList<Document>());
	
	        // save project to DB
	        try {
				ProjectDB.add(proj);
			} catch (DatabaseAccessError e) {
				// if add failed
				req.getSession().setAttribute("messageError", errorAdd);
				req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
				req.getSession().removeAttribute("messageError");
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
	        req.getSession().removeAttribute("messageError");
		}
	}
	
}
