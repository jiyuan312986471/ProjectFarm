<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Project" %>
<%@ page import="model.db.EvaluationDB" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>

<%  Project proj = (Project) request.getAttribute("project"); %>
<%  boolean isEvaExists = EvaluationDB.isEvaExists(proj.getAcronym(), session.getAttribute("mail").toString()); %>

<div class="container">

	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
	
		<div class="modal-header">
	    	<h3 class="modal-title" id="myModalLabel">Project Details</h3>
	    </div>
			 
		<div class="modal-body">
			<div class="form-group">
				<div class="row">
					<label class="col-sm-2"><h4>Project Evaluation</h4></label>
				</div>
			</div>
			 	
			<div class="form-group">
				<div class="row">
					<label class="col-sm-1 col-sm-offset-1">Acronym: </label>
					<label class="col-sm-3"><%= proj.getAcronym() %></label>
					<label class="col-sm-1 col-sm-offset-1">Created: </label>
					<label class="col-sm-3"><%= proj.getCreated().toGMTString() %></label>
				</div>
			</div>
					
			<div class="form-group">
				<div class="row">
					<label class="col-sm-1 col-sm-offset-1">Description: </label>
					<label class="col-sm-9" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%= proj.getDescription() %></label>
				</div>
			</div>
				
			<div class="form-group">
				<div class="row">
					<label class="col-sm-1 col-sm-offset-1">Category: </label>
					<label class="col-sm-1"><%= proj.getCategory() %></label>
					<label class="col-sm-1 col-md-offset-3">Budget:</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control text-right" id="disabledInput" placeholder="Budget" value="<%= proj.toStringBudget() %>" disabled>
							<div class="input-group-addon">,00</div>
							<div class="input-group-addon">€</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			         
		<hr>
			         
		<div class="modal-body">
			<div class="form-group">
				<div class="row">
			 		<label class="col-sm-2"><h4>Documents</h4></label>
			 		<input type="file" class="btn btn-default col-sm-offset-8" name="documentUpload">
			 	</div>
		 	</div>
		 	
			<div class="form-group">
				<div class="row">
					<label class="col-sm-6 col-sm-offset-1"><u>Document1 Name</u></label>
				</div>
			</div>
				
			<div class="form-group">
				<div class="row">
					<label class="col-sm-6 col-sm-offset-1"><u>Document2 Name</u></label>
				</div>
			</div>
		</div>
			
		<hr>
		
		<% if ( !isEvaExists ) { %>
			<form action="<%= request.getContextPath()%>/AddEvaluationServlet">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<label class="col-sm-2"><h4>Statistics</h4></label>
						</div>
					</div>
						
					<div class="form-group">
						<div class="row">
							<label for="inputRiskLevel" class="col-sm-2 col-sm-offset-1">Risk Level: </label>
							<div class="col-sm-2">
								<select class="form-control" id="inputRiskLevel" name="riskLevel">
									<option selected="selected">Please choose...</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
							
							<label for="inputAttractiveness" class="col-sm-2 col-sm-offset-1">Attractiveness: </label>
							<div class="col-sm-2">
								<select class="form-control" id="inputAttractiveness" name="attractiveness">
									<option selected="selected">Please choose...</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			         
			    <div class="modal-footer">
			    	<input type="submit" class="btn btn-primary" value="Save">
			    </div>
			</form>
		<% } else { %>
			<form action="<%= request.getContextPath()%>/AllProjectsServlet">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<div class="col-sm-12 text-center"><h3>You have already evaluated this project.</h3></div>
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

<jsp:include page="/include/Footer.jsp" />

<%  request.getSession().setAttribute("projectTitle", proj.getAcronym()); %>

<%  } %>