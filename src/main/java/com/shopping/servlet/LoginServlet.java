package com.shopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.shopping.DAO.UserDAO;
import com.shopping.connection.DbCon;
import com.shopping.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		try(PrintWriter out = response.getWriter()){
		
			String email=request.getParameter("login-email");
			String pass =request.getParameter("login-pass");
			
			try {
				UserDAO udao=new UserDAO(DbCon.getConnection());
				User user=udao.userLogin(email, pass);
				
				if(user==null) {
					out.print("not login");
				}
				else {
					System.out.println("login");
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				//	System.out.println(request.getSession().getAttribute("auth"));
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
