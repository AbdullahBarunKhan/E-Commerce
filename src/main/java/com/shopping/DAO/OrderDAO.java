package com.shopping.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.connection.DbCon;
import com.shopping.model.Order;
import com.shopping.model.Product;

public class OrderDAO {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDAO(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		
		boolean result = false;
		
		try {
			query = "Insert into orders (p_id, u_id, o_quantity, o_date) values (?,?,?,?)";
			
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			
			result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Order> UserOrder(int id) {
		List<Order> orders = new ArrayList<>();
		
		try {
			query = "Select * from orders where u_id=? order by orders.o_id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				ProductDAO productDao = new ProductDAO(this.con);
				int pId = rs.getInt("p_id");
				System.out.println("product id: " +  rs.getInt("p_id"));
				Product product = productDao.getSingleProduct(pId);
				
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setQuantity(rs.getInt("o_quantity"));
				order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				
				orders.add(order);
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return orders;
	}
	
	public void cancelOrder(int id) {
		
		try {
		query = "Delete from orders where o_id = ?";
		pst = this.con.prepareStatement(query);
		pst.setInt(1, id);
		pst.execute();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
