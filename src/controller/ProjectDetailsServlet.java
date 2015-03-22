package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.db.ProjectDB;

@WebServlet("/ProjectDetailsServlet")
public class ProjectDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 5940852084599075901L;
	
	public ProjectDetailsServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// referers
		String refAddProjectIdea = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/page/AddProjectIdea.jsp";
		String refMyProjects = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/MyProjectsServlet";
		
		// detect the referer page
		String referer = req.getHeader("referer");
		String ref = null;
		try {
			String[] str = referer.split("?");
			ref = str[0];
		} catch (Exception e) {
			ref = referer;
		} finally {
			
			// get project info from DB
			Project proj = null;
			if ( ref.equals(refAddProjectIdea) ) {
				// referer: AddProjectIdea
				try {
					// prepare proj title
					String projTitle = req.getAttribute("projectTitle").toString();
					proj = ProjectDB.getProjectByAcronym(projTitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if ( ref.equals(refMyProjects) ) {
				// referer: MyProjects
				try {
					// prepare proj title
					String projTitle = req.getParameter("acronym");
					proj = ProjectDB.getProjectByAcronym(projTitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// save project into request
			req.setAttribute("project", proj);
			
			// Turn to Page ProjectDetails
			req.getRequestDispatcher("/page/ProjectDetails.jsp").forward(req, resp);
		}
	}

}
