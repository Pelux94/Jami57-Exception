<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Product Page</title>
<style type = "text/css">
<%@ include file = "/css/DeleteItem.css"%>
</style>
</head>
<body class="bgHome">
	<jsp:include page="/utilityJSP/PulsanteLogin.jsp"></jsp:include>
	<%
	String msg = (String) request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito = msg != null ? msg : "";
	String id = (String) request.getParameter(Costanti.ITEM_ID);
	%>
	
<div class="container">
	<div class="testbox">
		<div class="child">
		
			<form action="<%=request.getContextPath() + "/ItemManagementServlet"%>" method="POST">
				<input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.DELETE_ITEM%>">
			<label class="label">Product ID</label><br> 
				<input type="number" name="<%=Costanti.ITEM_ID%>" placeholder="1" value="<%=id%>"><br>
				<button>Delete Product</button>
			</form>
	
			<p><%=esito%></p>
		
		</div>
	</div>
</div>

</body>
</html>