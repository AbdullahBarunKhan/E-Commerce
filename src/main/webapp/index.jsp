<%@page import="com.shopping.connection.DbCon"%>
<%@page import="com.shopping.model.*"%>
<%@page import="com.shopping.DAO.*"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
	
}	
	ProductDAO pd = new ProductDAO(DbCon.getConnection());
	List<Product> products = pd.getAllProducts();
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if(cart_list!=null){
		request.setAttribute("cart_list", cart_list);
	} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shoppee</title>
<%@ include file="include/header.jsp"%>
</head>
<body>
	<%@ include file="include/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
		
		<% 
			if( ! products.isEmpty()) {
				for(Product p : products){%>			
				<div class="col-md-3 my-3">
					<div class="card w-100	" style="width: 18rem;">
						<img class="card-img-top" src="product_images/<%= p.getImage()%>" alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%=p.getName() %></h5>
							<h6 class="price">Price:$<%=p.getPrice()%></h6>
							<h6 class="category">Category: <%=p.getCategory() %></h6>
							<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-primary">Add to Cart</a>
							<a href="order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-dark">Buy Now</a>
							</div>
							
						</div>
					</div>
				</div>
			<%	}
			}
		%>
		
			
		</div>
	</div>

	<%@include file="include/footer.jsp"%>
</body>
</html>