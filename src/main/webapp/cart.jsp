<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>
<%@page import="com.shopping.connection.DbCon"%>
<%@page import="com.shopping.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.shopping.model.*"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

List<Cart> cartProduct = null;
double total_price = 0;
if (cart_list != null) {
	ProductDAO pd = new ProductDAO(DbCon.getConnection());
	cartProduct = pd.getCart(cart_list);
	total_price = pd.getTotalPrice(cart_list);
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shoppee</title>
<%@ include file="include/header.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@ include file="include/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>
				Total Price >> $<%=dcf.format(total_price)%>
			</h3>
			<a class="mx-3 btn btn-primary" href="check-out">Check Out</a>
		</div>
		<table class="table table-loght">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th>Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>$<%=dcf.format(c.getPrice())%></td>
					<td>
						<form
							action="order-now?id=<%=c.getId()%>&quantity=<%=c.getQuantity()%>"
							method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex jsutify-content-between w-50">
								<a class="btn btn-sm btn-decre"
									href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quanity" class="form-control w-50"
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-sm btn-incre"
									href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy</button>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger"
						href="remove-from-cart?id=<%=c.getId()%>">Remove</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>


	<%@include file="include/footer.jsp"%>
</body>
</html>