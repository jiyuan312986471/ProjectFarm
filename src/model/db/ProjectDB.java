package model.db;

//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {

	private static String ADD = "INSERT into project (acronym, description, fundingDurationDays, budget, created, emailOwner, category) values (?,?,?,?,?,?,?)";
	
	public static void add(Project proj) throws DatabaseAccessError {
		
		Connection con = null;
		try {
			
			// connection
			con = DBUtil.getConnection();
			
			// get date
			java.sql.Date date = new Date( new java.util.Date().getTime() );
			
			// statement
			PreparedStatement stmt = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, proj.getAcronym());
			stmt.setString(2, proj.getDescription());
			stmt.setInt(3, 0);
			stmt.setInt(4, proj.getBudget());
			stmt.setDate(5, date);
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
	
}
