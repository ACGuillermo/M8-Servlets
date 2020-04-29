package com.prueba.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComentarioServlet
 */
@WebServlet("/ComentarioServlet")
public class ComentarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComentarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		String comentario = request.getParameter("comentario");
		
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
	        System.out.println("Opened database successfully");
	        
	      //COMPROBAR USER EN BBDD
	        selectUser = (PreparedStatement) con.prepareStatement("select * from users where nick = ?");
	        
	        selectUser.setString(1, nick);
	        selectUser.execute();
	        rs = selectUser.getResultSet();
	        
	        Boolean usuarioExiste = rs.next();
	        
	        if(usuarioExiste) {
	        	
	        	insertCompra = (PreparedStatement) con.prepareStatement("INSERT INTO comentarios (nick, comentario) values (?,?)");
	        	
	        	insertCompra.setString(1, nick);
	        	insertCompra.setString(2, comentario);
	        	
	        	insertCompra.executeUpdate();
	        	
	        	String sql = "SELECT * FROM comentarios";
		        stmt  = con.createStatement();
	            rs2    = stmt.executeQuery(sql);
	            
	            // loop through the result set
	            while (rs2.next()) {
	                System.out.println(rs2.getString("comentario") );
	            }
	            
	            con.close();
	            
	            String destination = "/jsp/comentarioCorrecto.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("nick", nick);
				request.setAttribute("comentario", comentario);
				requestDispatcher.forward(request, response);
	        }else {
	        	con.close();
	        	
	        	String destination = "/jsp/usuarioNoExiste.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				requestDispatcher.forward(request, response);
	        }
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally { 
			try { 
		        if (rs != null) 
		            rs.close(); 
		    } catch (SQLException sqle) {
		    	System.out.println(sqle);
		    }
			try { 
		        if (rs2 != null) 
		            rs2.close(); 
		    } catch (SQLException sqle) {
		    	System.out.println(sqle);
		    }
		    try { 
		        if (stmt != null) 
		            stmt.close(); 
		    } catch (SQLException sqle) {
		    	System.out.println(sqle);
		    }
		    try { 
		        if (selectUser != null) 
		        	selectUser.close(); 
		    } catch (SQLException sqle) {
		    	System.out.println(sqle);
		    }
		    try { 
		        if (insertCompra != null) 
		        	insertCompra.close(); 
		    } catch (SQLException sqle) {
		    	System.out.println(sqle);
		    }
		    try { 
		        if (con != null) 
		            con.close(); 
		    } catch (SQLException sqle)  {
		    	System.out.println(sqle);
		    }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
