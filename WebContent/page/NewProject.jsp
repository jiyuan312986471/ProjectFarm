<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.Owner" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="New Project Idea"/>
</jsp:include>

<div class="container">

	<div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">New Project Idea</h3>
  		</div>
  		<div class="panel-body">
    		Panel content
  		</div>
	</div>

</div>

<jsp:include page="/include/Footer.jsp" />