<%@page import="com.azienda.catalogoProdotti.model.User"%>
<%@page import="java.util.List"%>
<%@page import="com.azienda.catalogoProdotti.utility.Costanti"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Userlist Page</title>
<style type = "text/css">
<%@ include file = "/css/UserList.css"%>
</style>
</head>
<body class="bgHome">
    <jsp:include page="/utilityJSP/PulsanteLogin.jsp"></jsp:include>

	<%
		List<User> u = (List<User>)request.getAttribute(Costanti.USER_LIST);
		if( u != null && u.size() != 0){
	%>
	
	<div class="container">
    	<table>
			<thead>
				<tr>
					<th class="col-4">USER ID</th>
					<th class="col-4">USERNAME</th>
					<th class="col-4">PROFILE</th>
				</tr>
			</thead>
    
    <%
		for(User users : u){
	%>	 
    	
			<tbody>
				<tr class="tr-hov">
					<td><%=users.getId()%></td>
					<td><%=users.getUsername()%></td>
					<td><%=users.getProfile().getName()%></td>
				</tr>

 	<%
		}
	%>
			</tbody>
			</table>
	</div>
	<%
		}
	%>
	
</body>
</html>