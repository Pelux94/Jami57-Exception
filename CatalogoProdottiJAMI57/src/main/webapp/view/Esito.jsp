<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Purchases Page</title>
<style type = "text/css">
<%@ include file = "/css/FinalProject.css"%>
</style>
</head>
<body class="bgHome">
<jsp:include page="/utilityJSP/Pulsante.jsp"></jsp:include>

<%String msg= (String)request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito= msg != null ? msg : "";
	%>

<jsp:include page="/view/URLUtil.jsp"></jsp:include>


	<img class="imgEsito" alt="cup" src="img/mug1.png">
	<img class="imgEsito2" alt="cup" src="img/mug2.png">
	<img class="imgEsito" alt="cup" src="img/mug3.png">
	<img class="imgEsito2" alt="cup" src="img/mug4.png">
	<img class="imgEsito" alt="cup" src="img/mug5.png">
	<div class="esitoText"><p><%=esito%></p></div>
	<img class="imgEsito2" alt="cup" src="img/mug6.png">
	<img class="imgEsito" alt="cup" src="img/mug7.png">
	<img class="imgEsito2" alt="cup" src="img/mug8.png">
	<img class="imgEsito" alt="cup" src="img/mug9.png">
	<img class="imgEsito2" alt="cup" src="img/mug10.png">

<div class="staiGiu2">
		 <jsp:include page="/utilityJSP/Contatti.jsp"></jsp:include>
	 </div> 


</body>
</html>