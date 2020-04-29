package com.prueba.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void closeConnection (Connection con) {
    	try { 
	        if (con != null) 
	            con.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, sqle.getStackTrace().toString());
	    }
    }
    
    private void closeStatement (Statement stmt) {
    	try { 
	        if (stmt != null) 
	            stmt.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, sqle.getStackTrace().toString());
	    }
    }
    
    private void closeResultSet (ResultSet rs) {
    	try { 
	        if (rs != null) 
	            rs.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, sqle.getStackTrace().toString());
	    }
    }
    
    private void closeQuery (PreparedStatement query) {
    	try { 
	        if (query != null) 
	            query.close(); 
	    } catch (SQLException sqle) {
	    	LOGGER.log(Level.SEVERE, sqle.getStackTrace().toString());
	    }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		
		Connection con = null; 
		Statement stmt = null; 
		ResultSet rs = null;
		PreparedStatement selectUser = null;
		
		try {
			new org.sqlite.JDBC();
	        Class.forName("org.sqlite.JDBC");
	        String url = "jdbc:sqlite:C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\WebContent\\WEB-INF\\lib\\test.db";
	        con = DriverManager.getConnection(url);
	        System.out.println("Opened database successfully");
	        
	      //COMPROBAR USER EN BBDD
	        selectUser = (PreparedStatement) con.prepareStatement("select * from users where nick = ? and pass = ?");
	        
	        selectUser.setString(1, nick);
	        selectUser.setString(2, password);
	        selectUser.execute();
	        rs = selectUser.getResultSet();
	        
	        Boolean usuarioExiste = rs.next();
	        
	        if(usuarioExiste && sesion.getAttribute("nick") == null) {
	        	sesion.setAttribute("nick", nick);
	        	String destination = "jsp/loginExito.jsp";
	        	response.sendRedirect(destination);
				
				con.close();
	        }else {
	        	String destination = "/jsp/usuarioNoExiste.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				requestDispatcher.forward(request, response);
				
				con.close();
	        }
	        
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally { 
			closeResultSet(rs);
			closeStatement(stmt);
			closeQuery(selectUser);
			closeConnection(con);
		}
	}

}
