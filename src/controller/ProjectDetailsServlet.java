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
			// prepare project
			String projTitle = null;
			try {
				projTitle = req.getParameter("acronym").toString();
			} catch (NullPointerException e) {
				try {
					projTitle = req.getParameter("projectTitle").toString();
				} catch (NullPointerException e1) {
					try {
						projTitle = req.getAttribute("projectTitle").toString();
					} catch (NullPointerException e2) {
						try {
							projTitle = req.getSession().getAttribute("acronym").toString();
						} catch (Exception e3) {
							e3.printStackTrace();
						}
					}
				}
			}
			
			// get info from DB
			Project proj = null;
			float riskLevel = 0, attractiveness = 0;
			int nbOfEva = 0;
			ArrayList<Document> listDoc = null;
			
			try {
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
				
			// save info into request
			req.setAttribute("project", proj);
			req.getSession().setAttribute("acronym", proj.getAcronym());
			if (listDoc != null)
				req.setAttribute("listDoc", listDoc);
			req.setAttribute("riskLevel", riskLevel);
			req.setAttribute("attractiveness", attractiveness);
			req.setAttribute("nbOfEva", nbOfEva);

			// Turn to Page ProjectDetails
			req.getRequestDispatcher("/page/ProjectDetails.jsp").forward(req, resp);
			
		}// end else
	}// service
}
