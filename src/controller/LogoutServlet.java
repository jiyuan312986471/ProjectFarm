package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -2970065148247146523L;
	
	public LogoutServlet() {
		super();
		}
	
	public void destroy() {
		super.destroy();
		}
	
	
	// Servlet Service
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect("index.jsp");
	}

}
