<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@page import="com.azienda.catalogoProdotti.model.User"%>
<style type = "text/css">
<%@ include file = "/css/FinalProject.css"%>
</style>
<jsp:include page="/view/Utility.jsp"></jsp:include>
<jsp:include page="/utilityJSP/Bootstrap.jsp"></jsp:include>	

	<%
		String nomewebApp = request.getContextPath();
		String formUrl = nomewebApp + "/LoginServlet";
		
		String nomewebApp2 = request.getContextPath();
		String formUrl2 = nomewebApp2 + "/RegisterServlet";
		String formUrl3 = nomewebApp2 + "/img/logo2.jpg";
	%>
<%
		User u = (User)session.getAttribute(Costanti.USER_INFO);				
	%>
	<ul>
         <li><a class="active" href="<%=request.getContextPath() + "/HomePageServlet"%>">Home</a></li>
         <li><a class="active" href="<%=request.getContextPath() + "/ItemSearchServlet"%>">Search Item</a></li>
         <li><a class="active" href="<%=request.getContextPath() + "/ShoppingCartServlet"%>">ShoppingCart</a></li>
         
         
         <%	
		if (u.getProfile().getName().equals(Costanti.ADMIN)){				
	%>	
	<li><a class="active" href="<%=request.getContextPath() + "/UserListServlet"%>">User List</a></li>
	<li><a class="active" href="<%=request.getContextPath() + "/ItemManagementServlet"%>">Manage Items</a></li>
	
	<% 
		}
	%>
         <li><a class="active" href="<%=request.getContextPath() + "/LogoutServlet"%>">Logout</a></li>
   	</ul>
	<div class="titolo-background-color">
		<div class="row">	
			<div class="col-2"></div>	
			<div class="col-8 textCenter1"><h1 class="titoloHome">EXCEPTION-AL Cups and Mugs</h1></div>
			<div class="col-2"><img class="logoImg" src=<%=formUrl3%> alt="logo"/></div>
		</div>
	</div>