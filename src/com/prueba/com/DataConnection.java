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

/**
* 
* DataConnection.java
* Class implementation for managing database connections and querys.
* @author  Guillermo
* @since   2020-04-30
*  
*/
public class DataConnection {
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement selectUser = null;
	private PreparedStatement insertCompra = null;
	private PreparedStatement insertUser = null;
	private PreparedStatement dropUser = null;
	
	/**
	* 
	* DataConnection
	* Class constructor
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public DataConnection() {
		try {
			new org.sqlite.JDBC();
	        Class.forName("org.sqlite.JDBC");
	        String url = "jdbc:sqlite:C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\WebContent\\WEB-INF\\lib\\test.db";
	        con = DriverManager.getConnection(url);
		}
		catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.toString());
		}
 
	}
	
	/**
	* 
	* getConnection
	* Method for getting database connection.
	* @return database connection.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public Connection getConnection() {
		return con;
	}
	
	/**
	* 
	* closeConnection
	* Method for closing database connection.
	* @param conn connection to be closed.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void closeConnection(Connection conn) {
		try { 
	        if (conn != null) 
	            conn.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	/**
	* 
	* closeStatement
	* Method for closing statement connection with database.
	* @param stmt statement to be closed.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void closeStatement(Statement stmt) {
		try { 
	        if (stmt != null) 
	            stmt.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	/**
	* 
	* closeResultSet
	* Method for closing resultset connection with database.
	* @param rs resultset to be closed.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void closeResultSet (ResultSet rs) {
		try { 
	        if (rs != null) 
	            rs.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	/**
	* 
	* closeQuery
	* Method for closing preparedStatement connection with database.
	* @param rs preparedStatement to be closed.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void closeQuery (PreparedStatement query) {
		try { 
	        if (query != null) 
	            query.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
	}
	
	/**
	* 
	* getUser
	* Method for retrieving user info from databse.
	* @param user user nick to search.
	* @return resultSet data from user.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public ResultSet getUser(String user) throws SQLException {
		selectUser = con.prepareStatement("select * from users where nick = ?");
        
        selectUser.setString(1, user);
        selectUser.execute();
        rs = selectUser.getResultSet();
		return rs;
	}
	
	/**
	* 
	* checkUser
	* Method for checking if user exist in database.
	* @param user user nick to check.
	* @return true if user already exist.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public boolean checkUser (String user) throws SQLException {
		selectUser = con.prepareStatement("select * from users where nick = ?");
        
        selectUser.setString(1, user);
        selectUser.execute();
        rs = selectUser.getResultSet();
        
        return rs.next();
	}
	
	/**
	* 
	* insertUser
	* Method for inserting user in database.
	* @param nombre nick of user to insert.
	* @param password password of user to insert.
	* @paran email email of user to insert.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void insertUser(String nombre, String password, String email) throws SQLException {
		insertUser = con.prepareStatement("insert into users (nick, pass, email) values(?, ?, ?)");
        
        
        insertUser.setString(1, nombre);
        insertUser.setString(2, password);
        insertUser.setString(3, email);
        
        insertUser.executeUpdate();
        LOGGER.log(Level.FINE, "User inserted");
        
	}
	
	/**
	* 
	* dropUser
	* Method for deleting user from database.
	* @param nombre nick of user to delete.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void dropUser (String nombre) throws SQLException {
		dropUser = con.prepareStatement("DELETE FROM users WHERE nick = ?");
		
		dropUser.setString(1, nombre);
		
		dropUser.executeUpdate();
	}
	
	/**
	* 
	* insertComentario
	* Method for inserting comment in database.
	* @param nick nick of user who made the comment.
	* @param comentario comment to insert.
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void insertComentario(String nick, String comentario) throws SQLException {
		insertCompra = con.prepareStatement("INSERT INTO comentarios (nick, comentario) values (?,?)");
    	
    	insertCompra.setString(1, nick);
    	insertCompra.setString(2, comentario);
    	
    	insertCompra.executeUpdate();
        
	}
	
	/**
	* 
	* insertCompra
	* Method for inserting compra in database.
	* @param nick nick of user who buyed.
	* @param cursosString cursos selected.
	* @param pago payment method selected.
	* @param grado difficulty selected
	* @author  Guillermo
	* @since   2020-04-30
	*  
	*/
	public void insertCompra (String nick, String cursosString, String pago, String grado) throws SQLException {
		insertCompra = con.prepareStatement("INSERT INTO compra (nick, productos, pago, grado) values (?,?,?,?)");
    	
    	insertCompra.setString(1, nick);
    	insertCompra.setString(2, cursosString);
    	insertCompra.setString(3, pago);
    	insertCompra.setString(4, grado);
    	
    	insertCompra.executeUpdate();
        
	}
}
