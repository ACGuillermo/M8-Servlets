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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession();
		String comm = "comentario";
		String comentario = request.getParameter(comm);
		
		DataConnection dataConnection = new DataConnection();
		Connection con = dataConnection.getConnection();
		
		Statement stmt = null; 
		ResultSet rs = null;
		ResultSet rs2 = null;
		PreparedStatement selectUser = null;
		PreparedStatement insertCompra = null;
		
		try {
			
	        if(sesion.getAttribute("nick") != null) {
	        	String nick = (String) sesion.getAttribute("nick");
	        	//COMPROBAR USER EN BBDD
		        selectUser = con.prepareStatement("select * from users where nick = ?");
		        
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
		doGet(request, response);
	}

}
