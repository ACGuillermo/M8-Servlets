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
 * CursosServlet.java
 * Servlet implementation class CursosServlet for inserting cursos selected in database
 * @author  Guillermo
 * @since   2020-04-30
 */
@WebServlet("/CursosServlet")
public class CursosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursosServlet() {
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
		
		//CHECKBOX
		String[] cursos = request.getParameterValues("cursos");
		String cursosString = String.join(",", cursos);
		
		//RADIO
		String pago = request.getParameter("pagos");
		
		//DROPDOWN
		String grado = request.getParameter("grado");
		
		
		DataConnection dataConnection = new DataConnection();
		Connection con = dataConnection.getConnection();
		try {
	        
	        if(sesion.getAttribute("nick") != null) {
	        	String nick = (String) sesion.getAttribute("nick");
		        if(dataConnection.checkUser(nick)) {
		        	dataConnection.insertCompra(nick, cursosString, pago, grado);
		            
		            String destination = "/jsp/cursoCorrecto.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					request.setAttribute("nick", nick);
					request.setAttribute("producto", cursosString);
					request.setAttribute("grado", grado);
					request.setAttribute("pago", pago);
					requestDispatcher.forward(request, response);
					
		        }else {
		        	
		        	String destination = "/jsp/usuarioNoExiste.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					requestDispatcher.forward(request, response);
					
					con.close();
		        }
			}else {
				String destination = "/jsp/usuarioNoExiste.jsp";
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
				requestDispatcher.forward(request, response);
				
				con.close();
			}
	        
	        
	        
	        
	        
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, e.toString());
		}finally { 
			dataConnection.closeConnection(con);
		}
	}

}
