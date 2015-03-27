<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Project" %>
<%@ page import="model.db.EvaluationDB" %>
<%@ page import="java.util.ArrayList" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
  <jsp:param name="title" value="Project Farm" />
  <jsp:param name="page" value="/index.jsp" />
</jsp:include>

<% ArrayList<Project> listProj = (ArrayList<Project>)request.getAttribute("listProj"); %>

<!-- container -->
<div class="container">
	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
		<div class="modal-header">
        	<h4 class="modal-title" id="myModalLabel">My Projects</h4>
		</div>
         
        <% if ( listProj.size() > 0 ) { %>
		<div class="modal-body">
			<table class="table table-bordered text-center">
				<thead>
					<tr>
				    	<th><p></p><p class="text-center">Acronym</p></th>
				    	<th><p></p><p class="text-center">Category</p></th>
				    	<th><p></p><p class="text-center">Number of incubation days</p></th>
				    	<th><p></p><p class="text-center">Budget</p></th>
				    	<th><p></p><p class="text-center">Risk Level</p></th>
				    	<th><p></p><p class="text-center">Attractiveness</p></th>
				    	<th><p></p><p class="text-center">Number of evaluators</p></th>
				    </tr>
				</thead>
				<tbody>
					<% for ( Project proj: listProj ) { %>
					<%
						float risk = EvaluationDB.getAvgRiskLvl(proj.getAcronym());
						float attract = EvaluationDB.getAvgAttract(proj.getAcronym());
					%>
				  		<tr>
				  			<td>
				  				<a href="<% session.removeAttribute("messageError"); %><%= request.getContextPath()%>/ProjectDetailsServlet?acronym=<%= proj.getAcronym() %>"><%= proj.getAcronym() %></a>
				  			</td>
				      		<td><%= proj.getCategory() %></td>
				      		<td><%= proj.getFundingDuration() %></td>
				      		<td><%= proj.toStringBudget() %>,00</td>
				      		
				      		<% if ( risk == 0) { %>
				      			<td>No Data</td>
				      		<% } else if ( risk < 2.3) { %>
				      			<td class="success" style="color:black;"><%= risk %></td>
				      		<% } else if ( risk < 3.7 ) { %>
				      			<td class="warning" style="color:black;"><%= risk %></td>
				      		<% } else { %>
				      			<td class="danger" style="color:black;"><%= risk %></td>
				      		<% } %>
				      		
				      		<% if ( attract == 0) { %>
				      			<td>No Data</td>
				      		<% } else if ( attract < 2.3) { %>
				      			<td class="danger" style="color:black;"><%= attract %></td>
				      		<% } else if ( attract < 3.7 ) { %>
				      			<td class="warning" style="color:black;"><%= attract %></td>
				      		<% } else { %>
				      			<td class="success" style="color:black;"><%= attract %></td>
				      		<% } %>
				      		
				      		<td><%= EvaluationDB.getNbOfEva(proj.getAcronym()) %></td>
				  		</tr>
				  	<% } %>
				  </tbody>
			</table>
		</div>
		<% } else { %>
			<form action="<%= request.getContextPath()%>/index.jsp">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12 text-center"><h3>You don't have any project yet.</h3></div>
						</div>
					</div>
				</div>
				         
				<div class="modal-footer">
				   	<input type="submit" class="btn btn-primary" value="Back">
				</div>
			</form>
		<% } %>
	</div>
</div>

<!-- footer -->
<jsp:include page="/include/Footer.jsp" />

<% } %>