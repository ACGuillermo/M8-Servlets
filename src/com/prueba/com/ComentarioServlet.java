package com.prueba.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ComentarioServlet
 */
@WebServlet("/ComentarioServlet")
public class ComentarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComentarioServlet() {
        super();
    }
    
    private void closeConnection (Connection con) {
    	try { 
	        if (con != null) 
	            con.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
    }
    
    private void closeStatement (Statement stmt) {
    	try { 
	        if (stmt != null) 
	            stmt.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
    }
    
    private void closeResultSet (ResultSet rs) {
    	try { 
	        if (rs != null) 
	            rs.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
    }
    
    private void closeQuery (PreparedStatement query) {
    	try { 
	        if (query != null) 
	            query.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, Arrays.toString(sqle.getStackTrace()));
	    }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String comm = "comentario";
		String comentario = request.getParameter(comm);
		
		Connection con = null; 
		Statement stmt = null; 
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement selectUser = null;
		PreparedStatement insertCompra = null;
		
		try {
			new org.sqlite.JDBC();
	        Class.forName("org.sqlite.JDBC");
	        String url = "jdbc:sqlite:C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\WebContent\\WEB-INF\\lib\\test.db";
	        con = DriverManager.getConnection(url);
	        LOGGER.log(Level.FINE, "DB openned");

	        if(sesion.getAttribute("nick") != null) {
	        	String nick = (String) sesion.getAttribute("nick");
	        	//COMPROBAR USER EN BBDD
		        selectUser = (PreparedStatement) con.prepareStatement("select * from users where nick = ?");
		        
		        selectUser.setString(1, nick);
		        selectUser.execute();
		        rs = selectUser.getResultSet();
		        
		        Boolean usuarioExiste = rs.next();
		        
		        if(usuarioExiste) {
		        	insertCompra = con.prepareStatement("INSERT INTO comentarios (nick, comentario) values (?,?)");
		        	
		        	insertCompra.setString(1, nick);
		        	insertCompra.setString(2, comentario);
		        	
		        	insertCompra.executeUpdate();
		            
		            con.close();
		            
		            String destination = "/jsp/comentarioCorrecto.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					request.setAttribute("name", nick);
					request.setAttribute(comm, comentario);
					requestDispatcher.forward(request, response);
					
		        }else {
	        	
	        	String destination = "/jsp/usuarioNoExiste.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				requestDispatcher.forward(request, response);
	        }
	        }
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
			closeResultSet(rs2);
			closeStatement(stmt);
			closeQuery(selectUser);
			closeQuery(insertCompra);
			closeConnection(con);
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
