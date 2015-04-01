package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.naming.NamingException;

import model.Category;
import model.db.exception.DatabaseAccessError;

public class CategoryDB {
	
	private static Map<String,Category> categories;
//	private static String QUERY = "SELECT description FROM category WHERE description = ?";
	
	static {
		categories = new LinkedHashMap<>();
		initializeCategoryList();
	}
	
	public static LinkedList<Category> getCategories() throws DatabaseAccessError {
		return new LinkedList<Category>(categories.values());
	}
	
//	public static Category getCategory(String description) throws ClassNotFoundException, SQLException, NamingException {
//		Connection con = DBUtil.getConnection();
//
//		try {
//			Category cat = null;
//			PreparedStatement stmt = con.prepareStatement(QUERY);
//			stmt.setString(1, description);
//
//			ResultSet result = stmt.executeQuery();
//
//			if (result.next()) {
//				cat = new Category();
//				cat.setDescription(result.getString("description"));
//			}
//
//			result.close();
//			stmt.close();
//
//			return cat;
//
//		} finally {
//			try {
//				DBUtil.dropConnection(con);
//			} catch (SQLException e) { /* ignored */
//				e.printStackTrace();
//			}
//		}
//	}

	private static void initializeCategoryList() {
		categories.put("Apps",new Category("Apps"));
		categories.put("Robotics",new Category("Robotics"));
		categories.put("Information Systems",new Category("Information Systems"));
		categories.put("Hardware",new Category("Hardware"));
	}

}
