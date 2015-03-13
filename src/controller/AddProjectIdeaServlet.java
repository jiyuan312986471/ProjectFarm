package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Owner;
import model.Project;
import model.db.CategoryDB;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;


@WebServlet("/AddProjectIdeaServlet")
public class AddProjectIdeaServlet extends HttpServlet {

	private static final long serialVersionUID = 2025935986799381632L;

	public AddProjectIdeaServlet() {
		super();
		}
	
	public void destroy() {
		super.destroy();
		}
	
	// project title check
	private boolean isValidProjectTitle(String projTitle) {
		if ( projTitle.equals("") || projTitle == null )
			return false;
		else
			return true;
	}
	
	// project description check
	private boolean isValidProjectDescription(String projDescription) {
		if ( projDescription.equals("") || projDescription == null )
			return false;
		else
			return true;
	}
	
	// project budget check
	private boolean isValidProjectBudget(String projBudget) {
		if ( projBudget.equals("") || projBudget == null ) {
			return false;
		} else {
			try {
				Integer.parseUnsignedInt(projBudget);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
	}
	
	// Servlet Service
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get values
		String projTitle = req.getParameter("projectTitle");
		String projDescription = req.getParameter("projectDescription");
		Category projCategory = CategoryDB.getCategory(req.getParameter("projectCategory"));
		String projBudget = req.getParameter("projectBudget");
		Owner projOwner = (Owner) req.getSession().getAttribute("User");
		
		// error message setting
		String errorBudget = "Invalid Budget";
		String errorTitle = "The project needs a title";
		String errorDescription = "The project needs a description";
		
        // title check
        if ( !isValidProjectTitle(projTitle) ) {
        	req.getSession().setAttribute("messageError", errorTitle);
			req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
        }
        
        // description check
        if ( !isValidProjectDescription(projDescription) ) {
            req.getSession().setAttribute("messageError", errorDescription);
    		req.getRequestDispatcher("/page/AddProjectIdea.jsp").forward(req, resp);
        }
        
		// budget check
        if ( !isValidProjectBudget(projBudget) ) {
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
        Project proj = null;
        try {
        	proj = new Project(projTitle, projDescription, budget, projOwner, projCategory);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // save project to DB
        try {
			ProjectDB.saveProject(proj);
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		// turn to page ProjectDetails.jsp
		resp.sendRedirect("/page/ProjectDetails.jsp");
	}
	
}
