<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management Page</title>
<style type = "text/css">
<%@ include file = "/css/ManagementItems.css"%>
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
			<form action="view/AddItem.jsp" method="POST">
				<button class="button-custom">Add New Product</button>
			</form>
			<form action="view/UpdateItem.jsp" method="POST">
				<button class="button-custom"> Update Product </button>
			</form>
			<form action="view/DeleteItem.jsp" method="POST">
				<button class="button-custom bottom-button"> Delete Product </button>
			</form>
			<p><%=esito%></p>
		</div>
	</div>
</div>
		
</body>
</html>