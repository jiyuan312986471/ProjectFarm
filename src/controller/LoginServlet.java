package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.UserDB;
import model.db.exception.DatabaseAccessError;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1731274764063357773L;
	
	public LoginServlet() {
		super();
		}
	
	public void destroy() {
		super.destroy();
		}
	
//	// get previous page relative URL
//	private String getPreviousRelativeURL(String url, HttpServletRequest req) {
//		String[] list = url.split(req.getContextPath().trim());
//		return list[list.length - 1];
//	}
	
	
	// Servlet service
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String previousURL = req.getHeader("referer");
		
		// error message setting
		String messageError = "Invalid Username or Password";
		
//		// get previous page relative URL
//		String previousRelativeURL = this.getPreviousRelativeURL(previousURL, req);
		
		try {
			if ( UserDB.checkLogin(username, password) ) {
				req.getSession().removeAttribute("messageError");
				req.getSession().setAttribute("mail", UserDB.getUser(username).getEmail());
				req.getSession().setAttribute("name", UserDB.getUser(username).getName());
				req.getSession().setAttribute("User", UserDB.getOwner(username));
			}
			else {
				req.getSession().setAttribute("messageError", messageError);
			}
		} catch (DatabaseAccessError e1) {
			req.getSession().setAttribute("messageError", messageError);
		} finally {
			//req.getRequestDispatcher(previousRelativeURL).forward(req, resp);
			resp.sendRedirect(previousURL);
		}
	 }

}
