
<style type = "text/css">
<%@ include file = "/css/FinalProject.css"%>
</style>

<jsp:include page="/view/Utility.jsp"></jsp:include>
<jsp:include page="/utilityJSP/Bootstrap.jsp"></jsp:include>	

	<%
		String nomewebApp = request.getContextPath();
		String formUrl = nomewebApp + "/LoginServlet";
		
		String nomewebApp2 = request.getContextPath();
		String formUrl2 = nomewebApp2 + "/RegisterServlet";
		String formUrl3 = nomewebApp2 + "/img/logo2.jpg";
	%>
 
	<ul>
         <li><a class="active" href="<%=request.getContextPath() + "/HomePageServlet"%>">Home</a></li>
         <li><a class="active" href="<%=request.getContextPath() + "/CatalogueServlet"%>">Catalogue</a></li>
         <li><a class="active" href="<%=formUrl%>">Login</a></li>
         <li><a class="active" href="<%=formUrl2%>">Registration</a></li>
   	</ul>

	<div class="titolo-background-color">
		<div class="row">	
			<div class="col-2"></div>	
			<div class="col-8 textCenter1"><h1 class="titoloHome">EXCEPTION-AL Cups and Mugs</h1></div>
			<div class="col-2"><img class="logoImg" src=<%=formUrl3%> alt="logo"/></div>
		</div>
	</div>
	
