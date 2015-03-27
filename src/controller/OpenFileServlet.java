package controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OpenFileServlet")
public class OpenFileServlet extends HttpServlet {

	private static final long serialVersionUID = -223204855660282607L;
	
	private static String msgErrorLogin = "Please login";
	
	private DataInputStream in;
	private ServletOutputStream out;
	
	public OpenFileServlet() {
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
			// set path
			String acronym = req.getParameter("acronym").toString();
			String fileName = req.getParameter("name").toString();
			String path = acronym + "\\" + fileName;
			String realPath = "E:\\Lernen\\ESIGELEC\\2eme annee\\J2EE_Fuckner\\JEECourse\\ProjectFarm\\WebContent\\documents\\upload\\" + path;
			
			// create file
			File file = new File(realPath);
			
			// output stream
			out = resp.getOutputStream();
			
			// settings
			resp.setContentType("application/pdf");
			resp.setContentLength( (int)file.length() );
			resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
			resp.setHeader("Pragma", "No-cache");
			resp.setHeader("Cache-Control", "No-cache");	
			resp.setDateHeader("Expires", 0);
			
			// write content into the file
			byte[] bytes = new byte[1024];
			in = new DataInputStream(new FileInputStream(file));
			int length = 0;
			while ((in != null) && ((length = in.read(bytes)) != -1)) {
				out.write(bytes, 0, length);
			}
			out.close();
			
			// display
			resp.flushBuffer();
		}
	}

}
