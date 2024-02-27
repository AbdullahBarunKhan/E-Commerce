<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.shopping.model.*"%>	
<%@page import="java.util.*" %>
	<%
      User auth=(User) request.getSession().getAttribute("auth");
    	if(auth!=null) {
    		request.setAttribute("auth",auth); 
    		response.sendRedirect("index.jsp");
    	}
    	
    	ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart-list");
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
<%@ include file="include/navbar.jsp" %>

	<div class="container ">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">User Login</div>
			<div class="card-body">
				<form action="user-login" method="post">

					<div class="form-group">
						<label>Email Address</label> <input type="email"
							class="form-control" name="login-email" required="required"
							placeholder="Enter your email">
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="login-pass" required="required"
							placeholder="Enter password">
					</div>
					
					<div class="text-center">
					<button class="btn btn-primary" type="submit">Login</button>
					</div>

				</form>
			</div>
		</div>
	</div>

 	<%@include file="include/footer.jsp"%>
</body>
</html>