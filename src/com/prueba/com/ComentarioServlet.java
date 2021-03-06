package com.prueba.com;

import java.io.IOException;
import java.sql.Connection;
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
 * ComentarioServlet.java
 * Servlet implementation class ComentarioServlet for inserting comentarios in database
 * @author  Guillermo
 * @since   2020-04-30
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String comm = "comentario";
		String comentario = request.getParameter(comm);
		
		DataConnection dataConnection = new DataConnection();
		Connection con = dataConnection.getConnection();
		
		
		try {
			
	        	String nick = (String) sesion.getAttribute("nick");
		        if(dataConnection.checkUser(nick)) {
		        	dataConnection.insertComentario(nick, comentario);
		            
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
