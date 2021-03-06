package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

import model.Evaluation;
import model.db.exception.DatabaseAccessError;

public class EvaluationDB {
	
	private static String QUERY_SELECT_ONE = "SELECT acronymProj, riskLevel, atractiveness, emailEvaluator FROM evaluation WHERE acronymProj = ? AND emailEvaluator = ?";
	private static String QUERY_SELECT = "SELECT acronymProj, riskLevel, atractiveness, emailEvaluator FROM evaluation WHERE acronymProj = ?";
	private static String ADD = "INSERT INTO evaluation (acronymProj, riskLevel, atractiveness, emailEvaluator) values (?,?,?,?)";
	
	
	private static ArrayList<Evaluation> getEvaluations(String acronym) throws Exception {
		Connection con = DBUtil.getConnection();
		
		ArrayList<Evaluation> listEva = new ArrayList<Evaluation>();
		PreparedStatement stmt = con.prepareStatement(QUERY_SELECT);
		stmt.setString(1, acronym);
		
		ResultSet result = stmt.executeQuery();
		
		while ( result.next() ) {
			// create project
			Evaluation eva = new Evaluation();
			eva.setProject(ProjectDB.getProjectByAcronym(acronym));
			eva.setRiskLevel(result.getInt(2));
			eva.setAttractiveness(result.getInt(3));
			
			// add project into list
			listEva.add(eva);
		}
		
		result.close();
		stmt.close();
		
		DBUtil.dropConnection(con);
		
		return listEva;
	}
	
	
	public static Evaluation getEvaluation(String acronym, String email) throws Exception {
		Connection con = DBUtil.getConnection();
		
		try {
			Evaluation eva = null;
			PreparedStatement stmt = con.prepareStatement(QUERY_SELECT_ONE);
			stmt.setString(1, acronym);
			stmt.setString(2, email);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				// set evaluation
				eva = new Evaluation();
				eva.setProject(ProjectDB.getProjectByAcronym(acronym));
				eva.setRiskLevel(result.getInt(2));
				eva.setAttractiveness(result.getInt(3));
			}

			result.close();
			stmt.close();

			return eva;

		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
	}
	
	
	public static void add(Evaluation eva) throws DatabaseAccessError {
		Connection con = null;
		try {
			
			// connection
			con = DBUtil.getConnection();
			
			// statement
			PreparedStatement stmt = con.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, eva.getProject().getAcronym());
			stmt.setInt(2, eva.getRiskLevel());
			stmt.setInt(3, eva.getAttractiveness());
			stmt.setString(4, eva.getEvaluator().getEmail());

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
	
	
	public static float getAvgRiskLvl(String acronym) throws Exception {
		// get evaluations
		ArrayList<Evaluation> listEva = getEvaluations(acronym);
		
		// calculate the avg risk level
		float sum = 0;
		for ( Evaluation eva: listEva ) {
			sum = sum + eva.getRiskLevel();
		}
		
		if ( listEva.size() > 0 ) {
			return sum / (listEva.size());
		}
		else {
			return 0;
		}
	}
	
	
	public static float getAvgAttract(String acronym) throws Exception {
		// get evaluations
		ArrayList<Evaluation> listEva = getEvaluations(acronym);
				
		// calculate the avg risk level
		float sum = 0;
		for (Evaluation eva : listEva) {
			sum = sum + eva.getAttractiveness();
		}

		if ( listEva.size() > 0 ) {
			return sum / (listEva.size());
		}
		else {
			return 0;
		}
		
	}
	
	
	public static int getNbOfEva(String acronym) throws Exception {
		// get evaluations
		ArrayList<Evaluation> listEva = getEvaluations(acronym);
				
		// calculate the avg risk level
		return listEva.size();
	}
	
	
	public static boolean isEvaExists(String acronym, String email) throws Exception {
		Evaluation eva = getEvaluation(acronym, email);
		if ( eva == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
