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
<title>Product Search Page</title>
<style type = "text/css">
<%@ include file = "/css/ItemSearch.css"%>
</style>
</head>
<body class="bgHome">
	 <jsp:include page="/utilityJSP/PulsanteLogin.jsp"></jsp:include>

	<%
	String msg = (String) request.getAttribute(Costanti.MESSAGGIO_ESITO);
	String esito = msg != null ? msg : "";
	%>
		
<div class="searchBar">
	<form action="<%=request.getContextPath() + "/ItemSearchServlet"%>" method="POST">
		<input type="text" class="textBox" name="<%=Costanti.ITEM_NAME%>" placeholder=" Product name">
		<input type="text" class="textBox" name="<%=Costanti.ITEM_PRICE%>" placeholder=" 0.00&euro;">
		<button type="submit" class="button-29">Search Product</button>
	</form>
	<p class="errorMsg"><%=esito%></p>	
</div>	

	<%
	List<Item> items = (List<Item>)request.getAttribute(Costanti.ITEM_LIST);
	User u = (User)session.getAttribute(Costanti.USER_INFO);
	Map<Integer,String> imageMap = (Map<Integer,String>) request.getAttribute("costanteMappa");
	
	if(items != null && items.size() != 0){
		if(u.getProfile().getName().equals(Costanti.USER)){
	%>

	<div class="dimensioni">
	<%	
		for(Item i : items){
	%>	
		<div class="items">
		   <img class="imgSearch" alt="item image" src="<%=imageMap.get(i.getId())%>">
		   <p><%=i.getItemName()%><br><% if(i.getSupply() <= 10) { %> 
		   		<p class="lowsupply"> Only <%=i.getSupply()%> remaining </p> 
		   	<% } else if (i.getSupply() > 10) { %> 
		   		Only <%=i.getSupply()%> remaining
		   	<% } else {%>
		   	<% } %></p>
			<div class="row ">
			   <div class="col-6 "><div class="prezzoGrosso"><%=i.getPrice()%></div>&euro;</div>
			   <div class="col margineCarrello">
				   <form action="<%=request.getContextPath() + "/ShoppingCartServlet"%>" method="POST">
				   <input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.ADD_ITEM_TO_CART%>">
				   <input type="hidden" name="<%=Costanti.ITEM_ID%>" value="<%=i.getId()%>"> 
				   <button type="submit" class="btn1"><img class="imgCart" alt="" src="img/CartImg.jpg"> </button>   
				   </form>
			   </div>
		   </div>
		 </div>
	
	<%
		}
	%>
	<%		
			}else if(u.getProfile().getName().equals(Costanti.ADMIN)){
	%>
	
	<%	
		for(Item i : items){
	%>	
		<div class="dimensioni">
			  <div class="items">
			   <img class="imgSearch" alt="asdsad" src="<%=imageMap.get(i.getId())%>">
			   <p><%=i.getItemName()%><br><% if(i.getSupply() <= 10) { %> 
		   		<p class="lowsupply"> Only <%=i.getSupply()%> remaining </p> 
		   	<% } else if (i.getSupply() > 10) { %> 
		   		Only <%=i.getSupply()%> remaining
		   	<% } else {%>
		   	<% } %></p>
				   <div class="row">
				   <div class="col-6"><div class="prezzoGrosso"><%=i.getPrice()%></div>&euro;</div>
				   <div class="col margineCarrello">
					   <form action="<%=request.getContextPath() + "/ShoppingCartServlet"%>" method="POST">
					   <input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.ADD_ITEM_TO_CART%>">
					   <input type="hidden" name="<%=Costanti.ITEM_ID%>" value="<%=i.getId()%>"> 
					   <button type="submit" class="btn1"><img class="imgCart" alt="" src="img/CartImg.jpg"> </button>   
					   </form>
				   </div>
			   <div class="row">
				    <div class="col">
					     <form action="view/UpdateItem.jsp">
					     <input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.UPDATE_ITEM%>"> 
					     <input type="hidden" name="<%=Costanti.ITEM_ID%>" value="<%=i.getId()%>">
					     <input type="hidden" name="<%=Costanti.ITEM_NAME%>" value="<%=i.getItemName()%>">
					     <input type="hidden" name="<%=Costanti.ITEM_PRICE%>" value="<%=i.getPrice()%>">
					     <input type="hidden" name="<%=Costanti.ITEM_SUPPLY%>" value="<%=i.getSupply()%>">
					     <button class="button-30">Update product</button>
					     </form>
				     </div>
				    <div class="col">
					     <form action="view/DeleteItem.jsp">
					     <input type="hidden" name="<%=Costanti.OPERATION_TYPE%>" value="<%=Costanti.DELETE_ITEM%>"> 
					     <input type="hidden" name="<%=Costanti.ITEM_ID%>" value="<%=i.getId()%>">
					     <button class="button-30">Delete product</button>
					     </form>
				     </div>
				    </div>
				   </div>
			  </div> 
		 </div>
			
	<%
		}
	%>			
					
	<%
		}
	}	
	%>
	</div>
	
	<div class="staiGiu">
	<jsp:include page="/utilityJSP/Contatti.jsp"></jsp:include>
	</div>
</body>
</html>