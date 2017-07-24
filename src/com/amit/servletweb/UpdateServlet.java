package com.amit.servletweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	
	public void init(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useSSL=false", "root", "keer");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
   

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				String email= req.getParameter("email");
		String password= req.getParameter("password");
		
		try {
			Statement st = con.createStatement();
			int result = st.executeUpdate("update user set password ='"+password+"' where email='"+email+"'");
			PrintWriter pw = resp.getWriter();
			if(result>0){
			pw.println("<h1> user updated</h1>");
			} else{
				pw.println("<h1>no value</h1>");
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
