package com.amit.servletweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet(urlPatterns="/addServlet",initParams={@WebInitParam(name="dburl",value="jdbc:mysql://localhost/mydb?useSSL=false"),
		@WebInitParam(name="dbuser",value="root"),@WebInitParam(name="dbpass",value="keer")})
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	
	public void init(ServletConfig config){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(config.getInitParameter("dburl"),config.getInitParameter("dbuser"),config.getInitParameter("dbpass"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
   

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstName= req.getParameter("firstName");
		String lastName= req.getParameter("lastName");
		String email= req.getParameter("email");
		String password= req.getParameter("password");
		
		try {
			Statement st = con.createStatement();
			int result = st.executeUpdate("insert into user values('"+firstName+"','"+lastName+"','"+email+"','"+password+"')");
			PrintWriter pw = resp.getWriter();
			if(result>0){
			pw.println("<h1> user created entered</h1>");
			} else{
				pw.println("<h1>no value/h1>");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	
	public void destory(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
