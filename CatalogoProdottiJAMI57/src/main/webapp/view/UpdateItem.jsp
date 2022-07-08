<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product Page</title>
<style type = "text/css">
<%@ include file = "/css/UpdateItem.css"%>
</style>
</head>
<body class="bgHome">
	<jsp:include page="/utilityJSP/PulsanteLogin.jsp"></jsp:include>

	<%String msg= (String)request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito= msg != null ? msg : "";
	String id = (String) request.getParameter(Costanti.ITEM_ID);
	String name = (String) request.getParameter(Costanti.ITEM_NAME);
	String price = (String) request.getParameter(Costanti.ITEM_PRICE);
	String supply = (String) request.getParameter(Costanti.ITEM_SUPPLY);
	%>
	
	<div class="container">
		<div class="testbox">
			<div class="child">
				<h4>Fill the following fields</h4>	
				<form action="<%=request.getContextPath() + "/ItemManagementServlet"%>" method="POST">
					<input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.UPDATE_ITEM%>"><br>
				<label class="label">Product ID</label><br>
					<input type="number" name="<%=Costanti.ITEM_ID%>" placeholder = "333734" value="<%=id%>"><br>
				<label>Product name</label><br>
				<% if(name == null){ %>
				<input type="text" name="<%=Costanti.ITEM_NAME%>" placeholder="Tazza Avengers"> <br>
				<%} else if(name != null) { %>
					<input type="text" name="<%=Costanti.ITEM_NAME%>" placeholder="Tazza Avengers" value="<%=name%>"> <br>
					<%} %>
				<label>Price</label><br>
				<% if(price == null){ %>
				<input type="text" name="<%=Costanti.ITEM_PRICE%>" placeholder="0.00&euro;"><br>
				<%} else if(name != null) {%>
					<input type="text" name="<%=Costanti.ITEM_PRICE%>" placeholder="0.00&euro;" value="<%=price%>"><br>
					<%} %>
				<label>Supply</label><br>
					<input type="number" name="<%=Costanti.ITEM_SUPPLY%>" placeholder="100" value="<%=supply%>"><br>
					<button>Update Product</button>
				</form>		
				<p><%=esito%></p>
			</div>
		</div>
	</div>
</body>
</html>