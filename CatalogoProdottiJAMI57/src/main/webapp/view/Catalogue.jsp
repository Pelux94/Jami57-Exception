<%@page import="java.util.Map"%>
<%@page import="com.azienda.catalogoProdotti.model.User"%>
<%@page import="com.azienda.catalogoProdotti.model.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Catalogue Page</title>
<style type = "text/css">
<%@ include file = "/css/ItemSearch.css"%>
</style>
</head>
<body class="bgHome">
	 <jsp:include page="/utilityJSP/Pulsante.jsp"></jsp:include>

	<%
	String msg = (String) request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito = msg != null ? msg : "";
	%>
		
<div class="searchBar">
	<form action="<%=request.getContextPath() + "/CatalogueServlet"%>" method="POST">
		<input type="text" class="textBoxInvisibile" name="<%=Costanti.ITEM_NAME%>" placeholder=" Product name">
		<input type="text" class="textBoxInvisibile" name="<%=Costanti.ITEM_PRICE%>" placeholder=" 0.00&euro;">
		<button type="submit" class="button-31">Show Catalogue</button>
	</form>
	<p class="errorMsg"><%=esito%></p>	
</div>	

	<%
	List<Item> items = (List<Item>)request.getAttribute(Costanti.ITEM_LIST);
	User u = (User)session.getAttribute(Costanti.USER_INFO);
	Map<Integer,String> imageMap = (Map<Integer,String>) request.getAttribute("costanteMappa");
	
	if(items != null && items.size() != 0){
	%>

	<div class="dimensioni">
	<%	
		for(Item i : items){
	%>	
		<div class="items">
		   <img class="imgSearch" alt="item image" src="<%=imageMap.get(i.getId())%>">
		   <p class="itemNameList"><%=i.getItemName()%></p>
		 </div>
	
	<%
		}
	%>
	
	
	
					
	<%
		
	}	
	%>
	</div>
	
	<div class="staiGiu">
	<jsp:include page="/utilityJSP/Contatti.jsp"></jsp:include>
	</div>
</body>
</html>