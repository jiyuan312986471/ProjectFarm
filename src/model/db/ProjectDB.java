package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.NamingException;

import model.Owner;
import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {
	private static String QUERY_GET_PROJECTS = "SELECT acronym, description, fundingDurationDays, budget, created, emailOwner, category FROM project";
	private static String QUERY_GET_PROJ_BY_PK = "SELECT acronym, description, fundingDurationDays, budget, created, emailOwner, category FROM project WHERE acronym = ?";
	private static String QUERY_GET_PROJ_LIST_BY_MAIL = "SELECT acronym, description, fundingDurationDays, budget, created, emailOwner, category FROM project WHERE emailOwner = ?";
	private static String ADD = "INSERT into project (acronym, description, fundingDurationDays, budget, created, emailOwner, category) values (?,?,?,?,?,?,?)";
	
	
	// add project into DB
	public static void add(Project proj) throws DatabaseAccessError {
		
		Connection con = null;
		try {
			
			// connection
			con = DBUtil.getConnection();
			
			// get date
			Timestamp date = new Timestamp(System.currentTimeMillis());
			
			// statement
			PreparedStatement stmt = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, proj.getAcronym());
			stmt.setString(2, proj.getDescription());
			stmt.setInt(3, 100);
			stmt.setInt(4, proj.getBudget());
			stmt.setTimestamp(5, date);
			stmt.setString(6, proj.getOwner().getEmail());
			stmt.setString(7, proj.getCategory());

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
	
	
	// get project by PK
	public static Project getProjectByAcronym(String acronym) throws Exception {

		Connection con = DBUtil.getConnection();

		try {
			Project proj = null;
			PreparedStatement stmt = con.prepareStatement(QUERY_GET_PROJ_BY_PK);
			stmt.setString(1, acronym);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {

				// set acronym, description, budget, create date and category
				proj = new Project();
				proj.setAcronym(result.getString(1));
				proj.setDescription(result.getString(2));
				proj.setBudget(result.getInt(4));
				proj.setCreated(result.getTimestamp(5));
				proj.setCategory(result.getString(7));
				
				// set owner
				String emailOwner = result.getString(6);
				Owner owner = UserDB.getOwner(emailOwner);
				proj.setOwner(owner);
			}

			result.close();
			stmt.close();

			return proj;

		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
	}
	
	
	// get user's projects
	public static ArrayList<Project> getProjectsOfUser(String email) throws ClassNotFoundException, SQLException, NamingException, DatabaseAccessError {
		
		Connection con = DBUtil.getConnection();
		
		ArrayList<Project> listProj = new ArrayList<Project>();
		PreparedStatement stmt = con.prepareStatement(QUERY_GET_PROJ_LIST_BY_MAIL);
		stmt.setString(1, email);
		
		ResultSet result = stmt.executeQuery();
		
		while ( result.next() ) {
			// create project
			Project proj = new Project();
			proj.setAcronym(result.getString(1));
			proj.setDescription(result.getString(2));
			proj.setBudget(result.getInt(4));
			proj.setCreated(result.getTimestamp(5));
			proj.setCategory(result.getString(7));
			
			Owner owner = UserDB.getOwner(email);
			proj.setOwner(owner);
			
			// add project into list
			listProj.add(proj);
		}
		
		result.close();
		stmt.close();
		
		DBUtil.dropConnection(con);

		return listProj;		
	}
	
	
	// get all projects
	public static ArrayList<Project> getAllProjects() throws ClassNotFoundException, SQLException, NamingException, DatabaseAccessError {
		Connection con = DBUtil.getConnection();
		
		ArrayList<Project> listProj = new ArrayList<Project>();
		Statement stmt = con.createStatement();
		
		ResultSet result = stmt.executeQuery(QUERY_GET_PROJECTS);
		
		while ( result.next() ) {
			// create project
			Project proj = new Project();
			proj.setAcronym(result.getString(1));
			proj.setDescription(result.getString(2));
			proj.setBudget(result.getInt(4));
			proj.setCreated(result.getTimestamp(5));
			proj.setCategory(result.getString(7));
			
			Owner owner = UserDB.getOwner(result.getString(6));
			proj.setOwner(owner);
			
			// add project into list
			listProj.add(proj);
		}
		
		result.close();
		stmt.close();
		
		DBUtil.dropConnection(con);

		return listProj;		
	}
	
}
