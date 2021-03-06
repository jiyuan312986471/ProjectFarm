package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
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
	
	
	// Servlet service
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//String previousURL = req.getHeader("referer");
		
		
		// error message setting
		String messageError = "Invalid Username or Password";
		
		try {
			if ( UserDB.checkLogin(username, password) ) {
				req.getSession().setAttribute("mail", UserDB.getUser(username).getEmail());
				req.getSession().setAttribute("name", UserDB.getUser(username).getName());
				req.getSession().setAttribute("User", UserDB.getUser(username));
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				//resp.sendRedirect(previousURL);
			}
			else {
				req.getSession().setAttribute("messageError", messageError);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		} catch (DatabaseAccessError e1) {
			req.getSession().setAttribute("messageError", messageError);
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			req.getSession().removeAttribute("messageError");
		}
	 }

}
