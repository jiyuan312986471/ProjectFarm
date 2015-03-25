package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.db.EvaluationDB;
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
		String refAllProjects = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/AllProjectsServlet";
		String refAddEvaluation = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/EvaluationPageServlet";
		
		// detect the referer page
		String referer = req.getHeader("referer");
		String ref = null;
		try {
			String[] str = referer.split("\\?");
			ref = str[0];
		} catch (Exception e) {
			ref = referer;
		} finally {
			
			
			// get info from DB
			Project proj = null;
			float riskLevel = 0, attractiveness = 0;
			int nbOfEva = 0;
			
			if ( ref.equals(refAddProjectIdea) ) {
				// referer: AddProjectIdea
				try {
					// prepare project
					String projTitle = req.getAttribute("projectTitle").toString();
					proj = ProjectDB.getProjectByAcronym(projTitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if ( ref.equals(refMyProjects) || ref.equals(refAllProjects) ) {
				// referer: MyProjects & AllProjects
				try {
					// prepare project
					String projTitle = req.getParameter("acronym");
					proj = ProjectDB.getProjectByAcronym(projTitle);
					
					// prepare evaluations
					riskLevel = EvaluationDB.getAvgRiskLvl(projTitle);
					attractiveness = EvaluationDB.getAvgAttract(projTitle);
					nbOfEva = EvaluationDB.getNbOfEva(projTitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if ( ref.equals(refAddEvaluation) ) {
				// referer: AddEvaluation
				try {
					// prepare project
					String projTitle = req.getAttribute("projectTitle").toString();
					System.out.println(projTitle);
					proj = ProjectDB.getProjectByAcronym(projTitle);
					System.out.println(proj);
					
					// prepare evaluations
					riskLevel = EvaluationDB.getAvgRiskLvl(projTitle);
					attractiveness = EvaluationDB.getAvgAttract(projTitle);
					nbOfEva = EvaluationDB.getNbOfEva(projTitle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			// save info into request
			req.setAttribute("project", proj);
			req.setAttribute("riskLevel", riskLevel);
			req.setAttribute("attractiveness", attractiveness);
			req.setAttribute("nbOfEva", nbOfEva);
			
			// Turn to Page ProjectDetails
			req.getRequestDispatcher("/page/ProjectDetails.jsp").forward(req, resp);
			
		}// end finally
	}

}
