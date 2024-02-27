package com.shopping.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.shopping.model.*;

public class ProductDAO {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDAO(Connection con) {
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products  = new ArrayList<Product>();
		
		try {
			query="Select * from products;";
		    pst=this.con.prepareStatement(query);
			rs =pst.executeQuery();
			while(rs.next()) {
				Product product=new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
				
				products.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public List<Cart> getCart(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {
				for(Cart c: cartList) {
					query="Select * from products where id = ?;";
					pst= this.con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					while(rs.next()){
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*c.getQuantity());
						row.setQuantity(c.getQuantity());
						products.add(row);
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return products;
	}
	
	public double getTotalPrice(ArrayList<Cart> cartList) {
		double price=0;
		
		try {
			if(!cartList.isEmpty()) {
				for(Cart c : cartList) {
					query = "Select price from products where id=?;";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					
					if(rs.next()) {
						price+= c.getQuantity() * rs.getDouble("price");
					}
				}
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return price;
	}
	
	public Product getSingleProduct(int id) {
		
		Product product = null;
		
		try {
			System.out.println("Inside try for getSingleProduct");
			query = "Select * from products where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				System.out.println("Inside While");
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setCategory(rs.getString("category"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setImage(rs.getString("image"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return product;
	}
	
}
