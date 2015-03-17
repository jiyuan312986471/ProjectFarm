package listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.db.DBUtil;

/**
 * Prepare the testing environment
 */

public class EnvironmentPreparation implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent evt) {

//		try {
//
//			evt.getServletContext().log("ESIGELEC - Preparing environment");
//
//			Connection con = DBUtil.getConnection();
//
//			Statement stmt = con.createStatement();
//
//			stmt.executeUpdate("CREATE TABLE person "
//					+ "(id INTEGER NOT NULL AUTO_INCREMENT," + "ssn INTEGER,"
//					+ "name VARCHAR(255)," + "photo LONGBLOB,"
//					+ "PRIMARY KEY (id))");
//
//			con.close();
//
//			evt.getServletContext().log("ESIGELEC - Environment is OK");
//
//		} catch (SQLException e) {
//			// ignore. table already exists for MYSQL
//			if (e.getErrorCode() == 1050 && e.getSQLState().equals("42S01")) {
//				return;
//			}
//
//			System.out.println(e.getErrorCode());
//			System.out.println(e.getSQLState());
//
//			throw new RuntimeException("Error executing listener", e);
//		} catch (ClassNotFoundException | NamingException e) {
//			throw new RuntimeException("Error executing listener", e);
//		}
	}

}
