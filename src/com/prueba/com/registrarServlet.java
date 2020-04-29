package com.prueba.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class registrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void closeConnection (Connection con) {
    	try { 
	        if (con != null) 
	            con.close(); 
	    } catch (SQLException sqle) {
	    	System.out.println(sqle);
	    }
    }
    
    private void closeStatement (Statement stmt) {
    	try { 
	        if (stmt != null) 
	            stmt.close(); 
	    } catch (SQLException sqle) {
	    	System.out.println(sqle);
	    }
    }
    
    private void closeResultSet (ResultSet rs) {
    	try { 
	        if (rs != null) 
	            rs.close(); 
	    } catch (SQLException sqle) {
	    	System.out.println(sqle);
	    }
    }
    
    private void closeQuery (PreparedStatement query) {
    	try { 
	        if (query != null) 
	            query.close(); 
	    } catch (SQLException sqle) {
	    	System.out.println(sqle);
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
		
		
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern patternEmail = Pattern.compile(regexEmail);
		Matcher matcherEmail = patternEmail.matcher(email);
		
		String regexNombre = "^[a-zA-Z0-9]{1,10}$";
		Pattern patternNombre = Pattern.compile(regexNombre);
		Matcher matcherNombre = patternNombre.matcher(nombre);
		
		String regexPassword = "^.{8,}$";
		Pattern patternPassword = Pattern.compile(regexPassword);
		Matcher matcherPassword = patternPassword.matcher(password);
		
		PrintWriter writer = response.getWriter();
		if(matcherEmail.matches() && matcherNombre.matches() && matcherPassword.matches()) {
			
			Connection con = null; 
			Statement stmt = null; 
			ResultSet rs = null;
			ResultSet rs2 = null;
			PreparedStatement selectUser = null;
			PreparedStatement insertUser = null;
			
			try {
				  
		        new org.sqlite.JDBC();
		        Class.forName("org.sqlite.JDBC");
		        String url = "jdbc:sqlite:C:\\Users\\Guillermo\\Desktop\\WorkStation\\M8-Servlets\\WebContent\\WEB-INF\\lib\\test.db";
		        con = DriverManager.getConnection(url);
		        System.out.println("Opened database successfully");
		        
		        //COMPROBAR USER EN BBDD
		        selectUser = (PreparedStatement) con.prepareStatement("select * from users where nick = ?");
		        
		        selectUser.setString(1, nombre);
		        selectUser.execute();
		        rs2 = selectUser.getResultSet();
		        Boolean usuarioExiste = rs2.next();
		        if(usuarioExiste) {
		        	con.close();
		        	System.out.println("Usuario ya existe!");
		        	String destination = "/jsp/error.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					requestDispatcher.forward(request, response);
		        }else {
		        	insertUser = (PreparedStatement) con.prepareStatement("insert into users (nick, pass, email) values(?, ?, ?)");
			        
			        
			        insertUser.setString(1, nombre);
			        insertUser.setString(2, password);
			        insertUser.setString(3, email);
			        
			        insertUser.executeUpdate();
			        System.out.println("Insertado correctamente, EN PRINCIPIO.");
			        String sql = "SELECT * FROM users";
			        stmt  = con.createStatement();
		            rs    = stmt.executeQuery(sql);
		            
		            // loop through the result set
		            while (rs.next()) {
		                System.out.println(rs.getString("nick") );
		            }
		            
		            
		            
		            con.close();
		            
					String destination = "/jsp/correcto.jsp";
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
					request.setAttribute("name", nombre);
					request.setAttribute("email", email);
					request.setAttribute("password", password);
					requestDispatcher.forward(request, response);
					
					
			        

		        }
		        
		        
			}catch(Exception e){
				e.printStackTrace();
			}finally { 
				closeResultSet(rs);
				closeResultSet(rs2);
				closeStatement(stmt);
				closeQuery(selectUser);
				closeQuery(insertUser);
				closeConnection(con);
			}
			
			
			//response.sendRedirect(request.getContextPath() + "/jsp/correcto.jsp");
		}else {
			String destination = "/jsp/error.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
			requestDispatcher.forward(request, response);
			
		}
		
	}

}
