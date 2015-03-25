package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.exception.DatabaseAccessError;

public class UserDB {
	
	private static Map<String,User> users;
	private static String QUERY = "SELECT email,name,password,userType FROM user WHERE email = ?";
	
	static {
		users = new LinkedHashMap<String, User>();
		initializeUsersList();
	}

	private static void initializeUsersList() {
		users.put("john@acme.com",new Owner("john@acme.com","John Silver","123"));
		users.put("mary@acme.com",new Owner("mary@acme.com","Mary Moon","123"));
		users.put("paul@acme.com",new Owner("paul@acme.com","Paul McDonalds","123"));
		
		users.put("sarah@geek.com",new Evaluator("sarah@geek.com","Sarah Logan","456"));
		users.put("thibault@geek.com",new Evaluator("thibault@geek.com","Thibault Moulin","456"));
		users.put("george@geek.com",new Evaluator("george@geek.com","George Papalodeminus","456"));		
	}
	
	public static boolean checkLogin(String email,String password) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException{
		User u = getUser(email);
		if ( u == null ) {
			return false;
		}
		return u.getPassword().equals(password);
		
//		User u = users.get(login);
//		if(u == null) 
//			return false;
//		return u.getPassword().equals(password);
	}
	
	public static User getUser(String email) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException {
		Connection con = DBUtil.getConnection();

		try {
			User user = null;
			PreparedStatement stmt = con.prepareStatement(QUERY);
			stmt.setString(1, email);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				user = new User();
				user.setEmail(result.getString(1));
				user.setName(result.getString(2));
				user.setPassword(result.getString(3));
				user.setUserType(result.getInt(4));
			}

			result.close();
			stmt.close();

			return user;

		} finally {
			try {
				DBUtil.dropConnection(con);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}
		
//		User u = getOwner(email);
//		if(u == null) {
//			u = getEvaluator(email);
//		}
//		return u;
	}
	
	public static Owner getOwner(String email) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException{
		User u = getUser(email);
		if( u.getUserType() == 0 ) {
			Owner owner = new Owner();
			owner.setEmail(u.getEmail());
			owner.setName(u.getName());
			owner.setPassword(u.getPassword());
			owner.setUserType(u.getUserType());
			return owner;
		}
		else {
			return null;
		}
		
//		Connection con = DBUtil.getConnection();
//
//		try {
//			Owner owner = null;
//			PreparedStatement stmt = con.prepareStatement(QUERY);
//			stmt.setString(1, email);
//
//			ResultSet result = stmt.executeQuery();
//
//			if (result.next()) {
//				owner = new Owner();
//				owner.setEmail(result.getString(1));
//				owner.setName(result.getString(2));
//				owner.setPassword(result.getString(3));
//				owner.setUserType(result.getInt(4));
//			}
//
//			result.close();
//			stmt.close();
//
//			return owner;
//
//		} finally {
//			try {
//				DBUtil.dropConnection(con);
//			} catch (SQLException e) { /* ignored */
//				e.printStackTrace();
//			}
//		}
		
//		User u = users.get(email);
//		if(u == null || !(u instanceof Owner))
//			return null;
//		return (Owner) u;
	}
	
	public static Evaluator getEvaluator(String email) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException {
		User u = getUser(email);
		if( u.getUserType() == 1 ) {
			Evaluator eva = new Evaluator();
			eva.setEmail(u.getEmail());
			eva.setName(u.getName());
			eva.setPassword(u.getPassword());
			eva.setUserType(u.getUserType());
			return eva;
		}
		else {
			return null;
		}
		
//		Connection con = DBUtil.getConnection();
//
//		try {
//			Evaluator eva = null;
//			PreparedStatement stmt = con.prepareStatement(QUERY);
//			stmt.setString(1, email);
//
//			ResultSet result = stmt.executeQuery();
//
//			if (result.next()) {
//				eva = new Evaluator();
//				eva.setEmail(result.getString(1));
//				eva.setName(result.getString(2));
//				eva.setPassword(result.getString(3));
//				eva.setUserType(result.getInt(4));
//			}
//
//			result.close();
//			stmt.close();
//
//			return eva;
//
//		} finally {
//			try {
//				DBUtil.dropConnection(con);
//			} catch (SQLException e) { /* ignored */
//				e.printStackTrace();
//			}
//		}
		
//		User u = users.get(email);
//		if(u == null || !(u instanceof Evaluator))
//			return null;
//		return (Evaluator) u;		
	}
}
