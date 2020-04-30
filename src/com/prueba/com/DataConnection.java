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
	private ResultSet rs = null;
	private PreparedStatement selectUser = null;
	private PreparedStatement insertCompra = null;
	private PreparedStatement insertUser = null;
	private PreparedStatement dropUser = null;
	
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
	
	public ResultSet getUser(String user) throws SQLException {
		selectUser = con.prepareStatement("select * from users where nick = ?");
        
        selectUser.setString(1, user);
        selectUser.execute();
        rs = selectUser.getResultSet();
		return rs;
	}
	
	public boolean checkUser (String user) throws SQLException {
		selectUser = con.prepareStatement("select * from users where nick = ?");
        
        selectUser.setString(1, user);
        selectUser.execute();
        rs = selectUser.getResultSet();
        
        return rs.next();
	}
	
	public void insertUser(String nombre, String password, String email) throws SQLException {
		insertUser = con.prepareStatement("insert into users (nick, pass, email) values(?, ?, ?)");
        
        
        insertUser.setString(1, nombre);
        insertUser.setString(2, password);
        insertUser.setString(3, email);
        
        insertUser.executeUpdate();
        LOGGER.log(Level.FINE, "User inserted");
        
	}
	
	public void dropUser (String nombre) throws SQLException {
		dropUser = con.prepareStatement("DELETE FROM users WHERE nick = ?");
		
		dropUser.setString(1, nombre);
		
		dropUser.executeUpdate();
	}
	
	public void insertComentario(String nick, String comentario) throws SQLException {
		insertCompra = con.prepareStatement("INSERT INTO comentarios (nick, comentario) values (?,?)");
    	
    	insertCompra.setString(1, nick);
    	insertCompra.setString(2, comentario);
    	
    	insertCompra.executeUpdate();
        
	}
	
	public void insertCompra (String nick, String cursosString, String pago, String grado) throws SQLException {
		insertCompra = con.prepareStatement("INSERT INTO compra (nick, productos, pago, grado) values (?,?,?,?)");
    	
    	insertCompra.setString(1, nick);
    	insertCompra.setString(2, cursosString);
    	insertCompra.setString(3, pago);
    	insertCompra.setString(4, grado);
    	
    	insertCompra.executeUpdate();
        
	}
}
