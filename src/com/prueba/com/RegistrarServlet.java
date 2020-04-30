package com.prueba.com;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class registrarServlet
 */
@WebServlet("/registrarServlet")
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Rgx rgx = new Rgx();
		
		if(rgx.emailRegex(email) && rgx.nombreRegex(nombre) && rgx.passwordRegex(password)) {
			
			DataConnection dataConnection = new DataConnection();
			Connection con = dataConnection.getConnection();
			Statement stmt = null; 
			ResultSet rs = null;
			ResultSet rs2 = null;
			PreparedStatement selectUser = null;
			PreparedStatement insertUser = null;
			
			try {
		        //COMPROBAR USER EN BBDD
		        if(dataConnection.checkUser(nombre)) {
		        	con.close();
		        	LOGGER.log(Level.FINE, "Usuario existe");
		        	String destination = "/jsp/error.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					requestDispatcher.forward(request, response);
		        }else {
		        	dataConnection.insertUser(nombre, password, email);
					String destination = "/jsp/correcto.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					request.setAttribute("name", nombre);
					request.setAttribute("email", email);
					request.setAttribute("password", password);
					requestDispatcher.forward(request, response);
					
					
			        

		        }
		        
		        
			}catch(Exception e){
				LOGGER.log(Level.SEVERE, e.toString());
			}finally { 
				dataConnection.closeResultSet(rs);
				dataConnection.closeResultSet(rs2);
				dataConnection.closeStatement(stmt);
				dataConnection.closeQuery(selectUser);
				dataConnection.closeQuery(insertUser);
				dataConnection.closeConnection(con);
			}
			
		}else {
			String destination = "/jsp/error.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
			requestDispatcher.forward(request, response);
			
		}
		
	}

}
