<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.Owner" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>


<div class="jumbotron">
	<h1>Project Farm</h1>
	<h2>Project ideas are seeds to change the world</h2></br>
	<p>Simplified version of KickStarter project aiming at helping people to get funding for project ideas in several fields.</p></br></br>
	<p>
		<% if ( UserDB.getUser(request.getParameter("username")) instanceof Owner ) { %>
			<a class="btn btn-primary btn-lg" href="#" role="button">New project idea</a>
		<% } else { %>
			<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
		<% } %>
	</p> 
</div>

<jsp:include page="/include/Footer.jsp" />