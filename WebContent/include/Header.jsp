<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.Owner" %>
<%@ page import="model.Evaluator" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<title><%= request.getParameter("title") %></title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>


<body>
	<nav class="navbar navbar-default" role="navigation">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="<%= request.getContextPath()%>/index.jsp"><%= request.getParameter("title") %></a>
	    </div>
		
	    <div>
	      	<% if(session.getAttribute("name") == null) { %>
			<form class="navbar-form form-inline pull-right"  action="<%= request.getContextPath()%>/LoginServlet">
	    		<input type="text" placeholder="User name" name="username">
	    		<input type="password" placeholder="Password" name="password">
	    		<input type="hidden" name="pageSuccess"  value='<%= request.getParameter("page")%>'/>
	    		<button type="submit" class="btn">Sign in</button>
			</form>
			<% } else { %>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
		          <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		          	<span class="glyphicon glyphicon-user"></span> <%= session.getAttribute("name") %>
		          </a>
		          <ul class="dropdown-menu">
		          	<li>
		          		<% if( UserDB.getUser(request.getParameter("username")) instanceof Owner ) { %>
		          			<a href="#">My Projects</a>
		          		<% } else if ( UserDB.getUser(request.getParameter("username")) instanceof Evaluator ) { %>
		          			<a href="#">List Projects</a>
		          		<% } %>
		          	</li>
		            <li><a href="<%= request.getContextPath()%>/LogoutServlet">Logout</a></li>
		          </ul>
		        </li>
	        </ul>			
			<% } %>
		</div>	    
	  </div>
	</nav>
	
	<div class="container">
	
	<!-- container, body and HTML tags are still opened -->