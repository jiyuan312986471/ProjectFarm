package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.NamingException;

import model.Document;
import model.Project;
import model.db.exception.DatabaseAccessError;

public class DocumentDB {
	
	private static String ROOTPATH = "E:\\Lernen\\ESIGELEC\\2eme annee\\J2EE_Fuckner\\JEECourse\\ProjectFarm\\WebContent\\documents\\upload\\";
	
	private static String ADD = "INSERT INTO document (documentPath, added, projectAcronym) values (?,?,?)";
	private static String QUERY_GET_DOC = "SELECT documentPath, added, projectAcronym FROM document WHERE documentPath = ?";
	private static String QUERY_GET_DOCS_OF_PROJ = "SELECT documentPath, added, projectAcronym FROM document WHERE projectAcronym = ?";
	
	public static void add(Document doc) throws DatabaseAccessError {
		Connection con = null;
		try {
			
			// connection
			con = DBUtil.getConnection();
			
			// get date
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			// statement
			PreparedStatement stmt = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, doc.getDocumentPath());
			stmt.setTimestamp(2, date);
			stmt.setString(3, doc.getProject().getAcronym());

			int lines = stmt.executeUpdate();

			if (lines != 1)
				throw new DatabaseAccessError("Invalid quantity of returned lines");

			stmt.close();
		} catch (ClassNotFoundException e) {
			throw new DatabaseAccessError("Class not found", e);
		} catch (SQLException e) {
			throw new DatabaseAccessError("SQL exception", e);
		} catch (NamingException e) {
			throw new DatabaseAccessError("Naming exception", e);
		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
	}
	
	public static Document getDocByName(String docName, String acronym) throws Exception {
		// prepare doc path
		String docPath = ROOTPATH + acronym + "\\" + docName;
		
		// get doc
		Connection con = DBUtil.getConnection();

		try {
			Document doc = null;
			PreparedStatement stmt = con.prepareStatement(QUERY_GET_DOC);
			stmt.setString(1, docPath);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				// set documentPath, added
				doc = new Document();
				doc.setDocumentPath(docPath);
				doc.setAdded(result.getTimestamp(2));
				
				// set project
				Project proj = ProjectDB.getProjectByAcronym(acronym);
				doc.setProject(proj);
			}

			result.close();
			stmt.close();

			return doc;

		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<Document> getDocsOfProject(String acronym) throws Exception {
		Connection con = DBUtil.getConnection();
		
		ArrayList<Document> listDoc = new ArrayList<Document>();
		PreparedStatement stmt = con.prepareStatement(QUERY_GET_DOCS_OF_PROJ);
		stmt.setString(1, acronym);
		
		ResultSet result = stmt.executeQuery();
		
		while ( result.next() ) {
			// set documentPath, added
			Document doc = new Document();
			doc.setDocumentPath(result.getString(1));
			doc.setAdded(result.getTimestamp(2));
			
			// set project
			Project proj = ProjectDB.getProjectByAcronym(acronym);
			doc.setProject(proj);
			
			// add project into list
			listDoc.add(doc);
		}
		
		result.close();
		stmt.close();
		
		DBUtil.dropConnection(con);

		return listDoc;
	}

}
