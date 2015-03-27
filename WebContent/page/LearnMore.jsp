<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
	<jsp:param name="page" value="/index.jsp" />
</jsp:include>

<div class="container">

	<div class="jumbotron" style="filter:alpha(Opacity=85); -moz-opacity:0.85; opacity:0.85; background-color:black; color:white;">
		<h1>Project Farm</h1>
		<p>
			<p>Simplified version of KickStarter project aiming at helping people to get funding for project ideas in several fields.</p></br></br>
			<a class="btn btn-primary btn-lg" href="<% session.removeAttribute("messageError"); %><%= request.getContextPath()%>/index.jsp" role="button">Back</a>
		</p> 
	</div>

</div>

<jsp:include page="/include/Footer.jsp" />