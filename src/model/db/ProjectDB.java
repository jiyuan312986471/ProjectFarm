package model.db;

//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {

	private static String ADD = "INSERT into project (acronym, description, category, budget) values (?,?,?)";
	
	public static void add(Project proj) throws DatabaseAccessError {
		
		Connection con = null;
		try {
			
			// connection
			con = DBUtil.getConnection();
			
			// statement
			PreparedStatement stmt = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(2, proj.getAcronym());
			stmt.setString(3, proj.getDescription());
			stmt.setString(8, proj.getCategory());
			stmt.setInt(5, proj.getBudget());

			int lines = stmt.executeUpdate();

			if (lines != 1)
				throw new DatabaseAccessError("Invalid quantity of returned lines");

//			ResultSet key = stmt.getGeneratedKeys();
//
//			if (key.next())
//				proc.setId(key.getInt(1));

			stmt.close();
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new DatabaseAccessError("General error", e);
		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
		
	}
	
//	private static Map<String, Project> projects;
//
//	static {
//		projects = new LinkedHashMap<String, Project>();
//	}
//
//	public static void saveProject(Project project) throws DatabaseAccessError {
//		projects.put(project.getAcronym(), project);
//	}
//
//	public static Project getProject(String acronym) throws DatabaseAccessError {
//		return projects.get(acronym);
//	}
//
//	public static List<Project> getProjectsOfOwner(Owner owner) throws DatabaseAccessError {
//
//		List<Project> projectsOfOwner = new LinkedList<Project>();
//
//		for (Project p : projects.values()) {
//			if (p.getOwner().equals(owner)) {
//				projectsOfOwner.add(p);
//			}
//		}
//		return projectsOfOwner;
//
//	}
//	
//	public static List<Project> getAllProjects() throws DatabaseAccessError {
//		return new LinkedList<Project>(projects.values());
//	}
	
}
