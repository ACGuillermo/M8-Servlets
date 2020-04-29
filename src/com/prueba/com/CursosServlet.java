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
 * Servlet implementation class CursosServlet
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
		        	insertCompra = con.prepareStatement("INSERT INTO compra (nick, productos, pago, grado) values (?,?,?,?)");
		        	
		        	insertCompra.setString(1, nick);
		        	insertCompra.setString(2, cursosString);
		        	insertCompra.setString(3, pago);
		        	insertCompra.setString(4, grado);
		        	
		        	insertCompra.executeUpdate();
		        	
		        	
		        	String sql = "SELECT * FROM compra";
			        stmt  = con.createStatement();
		            rs2    = stmt.executeQuery(sql);
		            
		            con.close();
		            
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
			e.printStackTrace();
		}finally { 
			dataConnection.closeResultSet(rs);
			dataConnection.closeResultSet(rs2);
			dataConnection.closeStatement(stmt);
			dataConnection.closeQuery(selectUser);
			dataConnection.closeQuery(insertCompra);
			dataConnection.closeConnection(con);
		}
	}

}
