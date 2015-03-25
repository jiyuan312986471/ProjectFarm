package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddDocumentServlet")
public class AddDocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 3853825233701404837L;
	
	public AddDocumentServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// get document
		
		
		// turn to ProjectDetailsServlet
		req.getRequestDispatcher("/ProjectDetailsServlet").forward(req, resp);
	}

}
