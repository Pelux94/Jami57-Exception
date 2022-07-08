<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.azienda.catalogoProdotti.model.User"%>
<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<style type = "text/css">
<%@ include file = "/css/FinalProject.css"%>
</style>
</head>
<body class="bgHome">	
<jsp:include page="/utilityJSP/Pulsante.jsp"></jsp:include>

	<div class="container dimensioni2">
	
		<div class="row">
			<div class="col-5"><img class="tazze" src="img/CodeMug.png"></div>
			<div class="col-7 textCenter">Are you asking yourself why you should buy funny mugs? Because there is nothing better than a funny cup filled with sweet steaming coffee to start the day! In fact, there are thousands of funny mugs inspired by TV series, movies or simply with a witty design. The Jamy57 Exception staff did a thorough research to find the coolest funny mugs on the market.</div>
		</div>
		<div class="row">
			<div class="col-7 textCenter">
				If(you are a big drinker of coffee or tea and herbal teas) {We recommend you funny mugs in MUG format, the largest format} else if (you are looking for nice mugs to buy as gifts) {you just have to scroll through OurSQL database which contains the funniest cups found on the web} else { you can also opt for a beautiful personalized mug and make it a special gift by having a meaningful photo System.out.println(on it)}
			</div>
			<div class="col-5">
				<img class="tazze" src="img/CodersRunMug.png">
			</div>
		</div>
	</div>
		
<jsp:include page="/utilityJSP/Contatti.jsp"></jsp:include>

	</body>
</html>