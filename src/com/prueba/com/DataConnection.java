package com.prueba.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataConnection {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private Connection con = null;
	
	public DataConnection() {
		try {
			new org.sqlite.JDBC();
	        Class.forName("org.sqlite.JDBC");
	        String url = "jdbc:sqlite:C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\WebContent\\WEB-INF\\lib\\test.db";
	        con = DriverManager.getConnection(url);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getStackTrace().toString());
		}
 
	}
	
	public Connection getConnection() {
		return con;
	}
	
	public void closeConnection(Connection conn) {
		try { 
	        if (conn != null) 
	            conn.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	public void closeStatement(Statement stmt) {
		try { 
	        if (stmt != null) 
	            stmt.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	public void closeResultSet (ResultSet rs) {
		try { 
	        if (rs != null) 
	            rs.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	public void closeQuery (PreparedStatement query) {
		try { 
	        if (query != null) 
	            query.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
}
