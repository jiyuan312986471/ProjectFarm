<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% session.invalidate(); %>
<jsp:forward page="<%= request.getParameter(\"pageSuccess\")%>"></jsp:forward>