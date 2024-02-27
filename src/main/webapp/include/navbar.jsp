<style>
.navbar{
background-color:  #FFBB00 ;
font-weight: bold;
/* font-size: 10rem; */
}
body{
background-color: #FDF6F6;
}
</style>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark " >
<div class="container" >
  <a class="navbar-brand" href="index.jsp">E-Commerce</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger p-1.2">${cart_list.size()}</span></a>
      </li>
      
      <%if(auth != null){%>
    	  <li class="nav-item">
          <a class="nav-link" href="orders.jsp">Orders</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="log-out">Logout</a>
        </li>
      <% }
      else{%>
    	  <li class="nav-item">
        <a class="nav-link " href="login.jsp">Login</a>
      </li>
      <% }%>
        
      
    </ul>
  </div>
 </div> 
</nav>