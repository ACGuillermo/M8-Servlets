package com.prueba.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
 * Servlet implementation class CursosUser
 */
@WebServlet("/CursosUser")
public class CursosUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursosUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		
		DataConnection dataConnection = new DataConnection();
		Connection con = dataConnection.getConnection();
		
		Statement stmt = null; 
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement selectUser = null;
		PreparedStatement insertCompra = null;
		
		try {
			
        	String nick = (String) sesion.getAttribute("nick");
	        if(dataConnection.checkUser(nick)) {
	        	ResultSet userSet = dataConnection.getUser(nick);
	        	
	        	String userName = userSet.getString("nick");
	        	String userPassword = userSet.getString("pass");
	        	String userEmail = userSet.getString("email");
	        	
	        	
	        
	        	System.out.print(userPassword);
	            
	            String destination = "/jsp/cursosUser.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("name", userName);
				request.setAttribute("userPassword", userPassword);
				request.setAttribute("userEmail", userEmail);
				requestDispatcher.forward(request, response);
				
	        }else {
        	
        	String destination = "/jsp/login.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
			requestDispatcher.forward(request, response);
	        }
	        
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, e.toString());
		}finally {
			dataConnection.closeResultSet(rs);
			dataConnection.closeResultSet(rs2);
			dataConnection.closeStatement(stmt);
			dataConnection.closeQuery(selectUser);
			dataConnection.closeQuery(insertCompra);
			dataConnection.closeConnection(con);
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
