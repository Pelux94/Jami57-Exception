<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@page import="com.azienda.catalogoProdotti.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="prova">
<head>
<meta charset="ISO-8859-1">
<title>Private Profile Page</title>
<style type = "text/css">
<%@ include file = "/css/User.css"%>
</style>
</head>
<body class="bgHome">
	 <jsp:include page="/utilityJSP/PulsanteLogin.jsp"></jsp:include>
	 
	<%
		User u = (User)session.getAttribute(Costanti.USER_INFO);				
	%>
	
	<div class="container dimensioni2">
	<h1 class="center">TOP 3 CUPS AND MUGS</h1>
	<h3 class="center">We missed you <%=u.getUsername()%>!</h3>
		<div class="row">
  
   <div class="col-4"><h3 class="center">Programmer_Cup</h3>
    <p class="textDesc">Sipping a drink during an iteration, what more could you ask from life?</p>
    <img class="tazze alt1" src="img/CodeMug.png">
   </div>
   
   <div class="col-4"><h3 class="center">Spyro's Cup</h3>
    <p class="textDesc">Fans of Spyro the Dragon need to check out this amazing Spyro Molded Mug. With the detailed sculpt and vibrant colors, this seems like it should go on a display shelf instead of being used as a coffee mug.</p>
    <img class="tazze alt2" src="img/Spyro-Molded-Coffee-Mug.jpg">
   </div>
   
   <div class="col-4"><h3 class="center">Halo's John Cup</h3>
    <p class="textDesc">"Welcome home, John." No doubt, Cortana would have liked to offer John a cup of coffee afterwards.</p>
    <img class="tazze alt3" src="img/Halo.jpg">
   </div>
   
  </div>
	</div>

	<jsp:include page="/utilityJSP/Contatti.jsp"></jsp:include>
	
	
</body>		


</html>