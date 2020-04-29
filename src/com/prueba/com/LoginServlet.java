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
		
		HttpSession sesion = request.getSession();
		
		String nick = request.getParameter("nick");
		String password = request.getParameter("password");
		
		DataConnection dataConnection = new DataConnection();
		Connection con = dataConnection.getConnection();
		Statement stmt = null; 
		ResultSet rs = null;
		PreparedStatement selectUser = null;
		
		try {
	      //COMPROBAR USER EN BBDD
	        selectUser = con.prepareStatement("select * from users where nick = ? and pass = ?");
	        
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
			LOGGER.log(Level.SEVERE, e.toString());
		}finally { 
			dataConnection.closeResultSet(rs);
			dataConnection.closeStatement(stmt);
			dataConnection.closeQuery(selectUser);
			dataConnection.closeConnection(con);
		}
	}

}
