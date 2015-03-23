package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllProjectsServlet")
public class AllProjectsServlet extends HttpServlet {

	private static final long serialVersionUID = 8859370956703894061L;
	
	public AllProjectsServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	// Servlet Service
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

}
