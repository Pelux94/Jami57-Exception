<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<style type = "text/css">
<%@ include file = "/css/Login.css"%>
</style>
<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
<link href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css" rel="stylesheet">
</head>
<body class="bgHome">
<jsp:include page="/utilityJSP/Pulsante.jsp"></jsp:include>

	<%
	String msg = (String) request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito = msg != null ? msg : "";
	%>

	<div class="testbox">
	  <h1>Login</h1>
	
	  <form action="<%=request.getContextPath() + "/LoginServlet"%>" method="POST">
		  <label id="icon" for="name"><i class="icon-user"></i></label>
		  <input type="text" name="<%=Costanti.INPUT_USERNAME%>" id="name" placeholder="Userame" required/><br>
		  <label id="icon" for="name"><i class="icon-shield"></i></label>
		  <input type="password" name="<%=Costanti.INPUT_PASSWORD%>" id="name" placeholder="Password" required/>
		
		  <br> <button type = "submit" class="button">Login</button>
		  <%if(esito.equals("Successful registration. Login into your new account")) { %>
		  <p class="ok"><%=esito%><img alt="ok" class="success" src="img/checked.png"></p>
		  <% } else { %>
		  <p class="registered"><%=esito%></p>
		  <% } %>
		   <img alt="image" src="./img/javaloginmug.gif">
		   <p style="margin-top: 70px">Register to our site if you already didn't!</p>
		   <a href="<%=request.getContextPath() + "/RegisterServlet"%>">Sign up here!</a>
	  </form>
	</div>
		
		<jsp:include page="/utilityJSP/Contatti.jsp"></jsp:include>
</body>
</html>