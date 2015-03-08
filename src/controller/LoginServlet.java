package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
		
		try {
			if (UserDB.checkLogin(username, password)) {
				req.getSession().setAttribute("mail", UserDB.getUser(username).getEmail());
				req.getSession().setAttribute("name", UserDB.getUser(username).getName());
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid Username or Password");
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			}
		} catch (DatabaseAccessError e1) {
			JOptionPane.showMessageDialog(null, "Invalid Username or Password");
		}
	 }

}
