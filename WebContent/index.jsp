<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.User" %>
<%@ page import="model.Owner" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>


<div class="container">

	<div class="jumbotron"  style="filter:alpha(Opacity=85); -moz-opacity:0.85; opacity:0.85; background-color:black; color:white;">
		<h1>Project Farm</h1>
		<h2>Project ideas are seeds to change the world</h2></br></br>
		<p>
			<% if ( session.getAttribute("mail") == null ) { %>
				<a class="btn btn-primary btn-lg" href="<%= request.getContextPath()%>/page/LearnMore.jsp" role="button">Learn more</a>
			<% } else if ( UserDB.getUser(session.getAttribute("mail").toString()).getUserType() == 0 ) { %>
				<a class="btn btn-primary btn-lg" href="<%= request.getContextPath()%>/page/AddProjectIdea.jsp" role="button">New project idea</a>
			<% } else { %>
				<a class="btn btn-primary btn-lg" href="<%= request.getContextPath()%>/page/LearnMore.jsp" role="button">Learn more</a>
			<% } %>
		</p> 
	</div>

</div>

<jsp:include page="/include/Footer.jsp" />