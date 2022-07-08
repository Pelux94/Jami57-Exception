<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product Page</title>
<style type = "text/css">
<%@ include file = "/css/AddItem.css"%>
</style>
</head>
<body class="bgHome">	
    <jsp:include page="/utilityJSP/PulsanteLogin.jsp"></jsp:include>
	<%String msg= (String)request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito= msg != null ? msg : "";
	%>
<div class="container">
	<div class="testbox">
		<div class="child">
	<h4>Fill the following fields</h4>
	
	<form action="<%=request.getContextPath() + "/ItemManagementServlet"%>" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.ADD_ITEM%>"> <br>
		<input type="file" name="image"><br>
	<label class="label">Product name</label><br>
		<input type="text" name="<%=Costanti.ITEM_NAME%>" placeholder="Tazza Avengers"> <br>
	<label class="label">Price</label><br>
		<input type="text" name="<%=Costanti.ITEM_PRICE%>" placeholder="0.00&euro;"><br>
	<label class="label">Supply</label><br>
		<input type="number" name="<%=Costanti.ITEM_SUPPLY%>" placeholder="100"><br>
		<button>Add New Product</button>
	</form>
	
		<p><%=esito%></p>
	
		</div>
	</div>
</div>
	
</body>
</html>