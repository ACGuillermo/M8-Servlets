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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CursosServlet
 */
@WebServlet("/CursosServlet")
public class CursosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursosServlet() {
        super();
        // TODO Auto-generated constructor stub
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
        
		
		
		//RECOGER DATOS POST
		
		//CHECKBOX
		String[] cursos = request.getParameterValues("cursos");
		String cursosString = String.join(",", cursos);
		System.out.println(cursosString);
		
		//RADIO
		String pago = request.getParameter("pagos");
		System.out.println(pago);
		
		//DROPDOWN
		String grado = request.getParameter("grado");
		System.out.println(grado);
		
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
	        
	        if(sesion.getAttribute("nick") != null) {
	        	String nick = (String) sesion.getAttribute("nick");
	        	//COMPROBAR USER EN BBDD
		        selectUser = (PreparedStatement) con.prepareStatement("select * from users where nick = ?");
		        
		        selectUser.setString(1, nick);
		        selectUser.execute();
		        rs = selectUser.getResultSet();
		        
		        Boolean usuarioExiste = rs.next();
		        
		        if(usuarioExiste) {
		        	insertCompra = (PreparedStatement) con.prepareStatement("INSERT INTO compra (nick, productos, pago, grado) values (?,?,?,?)");
		        	
		        	insertCompra.setString(1, nick);
		        	insertCompra.setString(2, cursosString);
		        	insertCompra.setString(3, pago);
		        	insertCompra.setString(4, grado);
		        	
		        	insertCompra.executeUpdate();
		        	
		        	
		        	String sql = "SELECT * FROM compra";
			        stmt  = con.createStatement();
		            rs2    = stmt.executeQuery(sql);
		            
		            // loop through the result set
		            while (rs2.next()) {
		                System.out.println(rs2.getString("productos") );
		            }
		            
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

}
