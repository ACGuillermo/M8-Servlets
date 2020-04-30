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
 * DatosUser.java
 * Servlet implementation class DatosUser for retrieving user data from database
 * @author  Guillermo
 * @since   2020-04-30
 */
@WebServlet("/DatosUser")
public class DatosUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatosUser() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		
		DataConnection dataConnection = new DataConnection();
		Connection con = dataConnection.getConnection();
		
		
		try {
			
        	String nick = (String) sesion.getAttribute("nick");
	        if(dataConnection.checkUser(nick)) {
	        	ResultSet userSet = dataConnection.getUser(nick);
	        	
	        	String userName = userSet.getString("nick");
	        	String userPassword = userSet.getString("pass");
	        	String userEmail = userSet.getString("email");
	        	
	            
	            String destination = "/jsp/datosUser.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("name", userName);
				request.setAttribute("userPassword", userPassword);
				request.setAttribute("userEmail", userEmail);
				requestDispatcher.forward(request, response);
				
				
	        }else {
        	
        	String destination = "/jsp/index.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
			requestDispatcher.forward(request, response);
	        }
	        
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, e.toString());
		}finally {
			dataConnection.closeConnection(con);
		 }
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
