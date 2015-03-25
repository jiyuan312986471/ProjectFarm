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
        	<h4 class="modal-title" id="myModalLabel">All Projects</h4>
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
				    <th><p></p><p class="text-center">Number of evaluations</p></th>
				    <th><p></p><p class="text-center">Action</p></th>
				</tr>
				</thead>
				<tbody>
				<% for ( Project proj: listProj ) { %>
					<tr>
				  		<td>
				  			<a href="<%= request.getContextPath()%>/ProjectDetailsServlet?acronym=<%= proj.getAcronym() %>"><%= proj.getAcronym() %></a>
				  		</td>
				    	<td><%= proj.getCategory() %></td>
				    	<td><%= proj.getFundingDuration() %></td>
				    	<td><%= proj.toStringBudget() %>,00</td>
				    	<td><%= EvaluationDB.getNbOfEva(proj.getAcronym()) %></td>
				    	<td><a href="<%= request.getContextPath()%>/EvaluationPageServlet?acronym=<%= proj.getAcronym() %>">Evaluate</a></td>
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
							<div class="col-sm-12 text-center"><h3>There is no project yet.</h3></div>
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