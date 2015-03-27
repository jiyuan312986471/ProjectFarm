package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Document;
import model.Project;
import model.db.DocumentDB;
import model.db.EvaluationDB;
import model.db.ProjectDB;

@WebServlet("/ProjectDetailsServlet")
public class ProjectDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = 5940852084599075901L;
	
	private static String msgErrorLogin = "Please login";
	
	public ProjectDetailsServlet() {
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
			// referers
			String refAddProjectIdea = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/page/AddProjectIdea.jsp";
			String refMyProjects = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/MyProjectsServlet";
			String refAllProjects = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/AllProjectsServlet";
			String refAddEvaluation = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/EvaluationPageServlet";
			String refAddDocument = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/ProjectDetailsServlet";
			String refAddDocument2 = "http://" + req.getServerName() + ":8080" + req.getContextPath() + "/AddDocumentServlet";
			
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
				ArrayList<Document> listDoc = null;
				
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
						
						// prepare documents
						listDoc = DocumentDB.getDocsOfProject(projTitle);
						
						// prepare evaluations
						riskLevel = EvaluationDB.getAvgRiskLvl(projTitle);
						attractiveness = EvaluationDB.getAvgAttract(projTitle);
						nbOfEva = EvaluationDB.getNbOfEva(projTitle);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else if ( ref.equals(refAddEvaluation) || ref.equals(refAddDocument) || ref.equals(refAddDocument2) ) {
					// referer: AddEvaluation & AddDocument
					try {
						// prepare project
						String projTitle = req.getAttribute("projectTitle").toString();
						proj = ProjectDB.getProjectByAcronym(projTitle);
						
						// prepare documents
						listDoc = DocumentDB.getDocsOfProject(projTitle);
						
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
				req.setAttribute("listDoc", listDoc);
				req.setAttribute("riskLevel", riskLevel);
				req.setAttribute("attractiveness", attractiveness);
				req.setAttribute("nbOfEva", nbOfEva);
				
				// Turn to Page ProjectDetails
				req.getRequestDispatcher("/page/ProjectDetails.jsp").forward(req, resp);
			}// end finally
		}
	}// service
}
