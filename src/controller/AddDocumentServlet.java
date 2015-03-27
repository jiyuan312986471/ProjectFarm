package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Document;
import model.Project;
import model.db.DocumentDB;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/AddDocumentServlet")
public class AddDocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 3853825233701404837L;
	private String rootPath = "E:\\Lernen\\ESIGELEC\\2eme annee\\J2EE_Fuckner\\JEECourse\\ProjectFarm\\WebContent\\documents";
	private String uploadPath = "\\upload\\";
	private String tempPath = "\\uploadtmp\\";
	private int sizeMax = 10;
	
	private String msgErrorSize = "The file should be smaller than " + sizeMax + "MB.";
	private static String msgErrorType = "Only PDF file allowed.";
	private static String msgErrorDB = "File already exists.";
	private static String msgErrorLogin = "Please login";
	
	public AddDocumentServlet() {
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
			/***********************************************
			 * 				FILE STORAGE
			 ***********************************************/
			String acronym = req.getSession().getAttribute("acronym").toString();
			req.getSession().removeAttribute("acronym");
			
			String path = uploadPath + acronym + "\\";
			 
	        // create folder
	        if ( !new File( rootPath + path ).isDirectory() ) {  
	            new File( rootPath + path ).mkdirs();  
	        }  
	        if ( !new File( rootPath + tempPath ).isDirectory() ) {  
	            new File( rootPath + tempPath ).mkdirs();  
	        }  
	  
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        
	        // buffer
	        factory.setSizeThreshold( 5 * 1024 );
	        
	        // repository
	        factory.setRepository( new File( rootPath + tempPath ) );
	          
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        
	        // set max size: 10MB
	        upload.setSizeMax( sizeMax * 1024 * 1024 );
	          
	        String filePath = null;  
	        boolean validFile = false;
	        try {  
	            List<FileItem> items = upload.parseRequest(req);
	            for ( FileItem item : items ) {  
	                // get file name including path
	                String fileName = item.getName();
	                
	                // get file type
	                int i = fileName.lastIndexOf(".");
	                String type = fileName.substring( i + 1 ).toLowerCase();
	                
	                // check file type
	                validFile = type.equals("pdf");
	                if ( validFile ) {
	                	// path
	                	filePath = rootPath + path + fileName;
	                    
	                    // store in disk
	                    item.write( new File(filePath) );
	                }
	                else
	                	req.getSession().setAttribute("messageError", msgErrorType);
	            }
	        } catch (Exception e) {
	            req.getSession().setAttribute("messageError", msgErrorSize);
	        }
	        
	        
	        /***********************************************
	         * 				IF ALL GOES WELL
			 * 				   MAP IN DB
			 ***********************************************/
	        if ( validFile ) {
		        // remove error msg
		        req.getSession().removeAttribute("messageError");
		        
		        try {
		        	// get project
		            Project proj = null;
					proj = ProjectDB.getProjectByAcronym(acronym);
					
					 // generate doc
			        Document doc = new Document();
			        doc.setDocumentPath(filePath);
			        doc.setAdded(new Timestamp(System.currentTimeMillis()));
					doc.setProject(proj);
					
					// store into DB
					DocumentDB.add(doc);
				} catch (DatabaseAccessError e) {
					req.getSession().setAttribute("messageError", msgErrorDB);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
		    req.setAttribute("projectTitle", acronym);
	        
	        
	        /***********************************************
			 * 				 REDIRECTION
			 ***********************************************/
			req.getRequestDispatcher("/ProjectDetailsServlet").forward(req, resp);
		}
	}

}
