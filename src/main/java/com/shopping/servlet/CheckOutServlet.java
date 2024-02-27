package com.shopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.shopping.DAO.OrderDAO;
import com.shopping.connection.DbCon;
import com.shopping.model.Cart;
import com.shopping.model.Order;
import com.shopping.model.User;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            
            User auth = (User) request.getSession().getAttribute("auth");
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            
            if(auth!= null && cart_list!=null) {
            	for(Cart c : cart_list) {
            		Order order = new Order();
            		order.setId(c.getId());
            		order.setUid(auth.getId());
            		order.setDate(formatter.format(date));
            	//	order.setOrderId(c.getId());
            		order.setQuantity(c.getQuantity());
            		
            		//instantiating DAO class
            		OrderDAO oDao = new OrderDAO(DbCon.getConnection());
            		//Calling insert to DB method
            		boolean result = oDao.insertOrder(order);
            		
            		if(!result) break;
            	}
            	
            	cart_list.clear();
            	response.sendRedirect("orders.jsp");
            	
            }
            else {
            	if(auth==null) response.sendRedirect("login.jsp");
            	response.sendRedirect("cart.jsp");
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
