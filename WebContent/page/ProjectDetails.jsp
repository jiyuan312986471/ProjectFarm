<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Project" %>
<%@ page import="model.Document" %>
<%@ page import="model.Evaluation" %>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.db.EvaluationDB" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.util.ArrayList" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>

<%  Project proj = (Project) request.getAttribute("project"); %>
<%  ArrayList<Document> listDoc = (ArrayList<Document>) request.getAttribute("listDoc"); %>
<%  float riskLvl = Float.parseFloat(request.getAttribute("riskLevel").toString()); %>
<%  float attract = Float.parseFloat(request.getAttribute("attractiveness").toString()); %>
<%  int nbOfEva = Integer.parseInt(request.getAttribute("nbOfEva").toString()); %>
<%  boolean isEvaExists = EvaluationDB.isEvaExists(proj.getAcronym(), session.getAttribute("mail").toString()); %>

<%
	float riskLvlUser = 0, attractUser = 0;
	if ( isEvaExists ){
		Evaluation eva = EvaluationDB.getEvaluation(proj.getAcronym(), session.getAttribute("mail").toString());
		riskLvlUser = eva.getRiskLevel();
		attractUser = eva.getAttractiveness();
	}
%>

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
						<label class="col-sm-1 col-sm-offset-2">Created: </label>
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
						<label class="col-sm-1 col-md-offset-4">Budget:</label>
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
		
		<% if ( UserDB.getUser(session.getAttribute("mail").toString()).getUserType() == 0 ) { %>
			<form action="<%= request.getContextPath()%>/AddDocumentServlet" enctype="multipart/form-data" method="post">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
					 		<label class="col-sm-2"><h4>Documents</h4></label>
					 		<input type="file" class="btn btn-default" name="documentUpload">
					 		<label class="col-sm-2" style="color:red;">Only PDF file allowed</label>
					 	</div>
				 	</div>
				 	<% if ( listDoc != null && listDoc.size() > 0 ) { %>
					 	<% for ( Document doc: listDoc) { %>
					 		<% String[] split = doc.getDocumentPath().split("\\\\"); %>
					 		<% String docName = split[split.length - 1]; %>
							<div class="form-group">
								<div class="row">
									<a href="<%= request.getContextPath()%>/OpenFileServlet?name=<%= docName %>&acronym=<%= proj.getAcronym() %>" class="col-sm-6 col-sm-offset-1"><%= docName %></a>
								</div>
							</div>
						<% } %>
					<% } %>
				</div>
				
				<hr>
				
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<label class="col-sm-2"><h4>Statistics</h4></label>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Number of Evaluations: </label>
							<label class="col-sm-1"><%= nbOfEva %></label>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Risk Level: </label>
							<% if ( riskLvl == 0) { %>
								<label class="col-sm-1">None</label>
							<% } else { %>
								<div class="col-sm-7">
									<div class="progress">
		    							<div 
		    								class="progress-bar
			    							<% if ( riskLvl < 2.3) { %>
						      					progress-bar-success
						      				<% } else if ( riskLvl < 3.7 ) { %>
						      					progress-bar-warning
						      				<% } else { %>
						      					progress-bar-danger
						      				<% } %>"
						      				
			    							data-transitiongoal="
			    							<% if ( riskLvl != 1) { %>
			    								<%= (riskLvl - 1) * 25 %>
			    							<% } else { %>
			    								<%= 1 %>
			    							<% } %>"
			    							style="min-width: 2em;">
		    							</div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Attractiveness: </label>
							<% if ( attract == 0) { %>
								<label class="col-sm-1">None</label>
							<% } else { %>
								<div class="col-sm-7">
									<div class="progress">
		    							<div 
		    								class="progress-bar
			    							<% if ( attract < 2.3) { %>
						      					progress-bar-danger
						      				<% } else if ( attract < 3.7 ) { %>
						      					progress-bar-warning
						      				<% } else { %>
						      					progress-bar-success
						      				<% } %>"
						      				
			    							data-transitiongoal="
			    							<% if ( attract != 1) { %>
			    								<%= (attract - 1) * 25 %>
			    							<% } else { %>
			    								<%= 1 %>
			    							<% } %>"
			    							style="min-width: 2em;">
		    							</div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
				</div>
		         
			    <div class="modal-footer">
			    	<input type="submit" class="btn btn-primary" value="Save">
			    </div>
		    </form>
	    <% } else { %>
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
					 		<label class="col-sm-2"><h4>Documents</h4></label>
					 	</div>
				 	</div>
				 	<% if ( listDoc != null && listDoc.size() > 0 ) { %>
						<% for ( Document doc: listDoc) { %>
					 		<% String[] split = doc.getDocumentPath().split("\\\\"); %>
					 		<% String docName = split[split.length - 1]; %>
							<div class="form-group">
								<div class="row">
									<a href="<% session.removeAttribute("messageError"); %><%= request.getContextPath()%>/OpenFileServlet?name=<%= docName %>&acronym=<%= proj.getAcronym() %>" class="col-sm-6 col-sm-offset-1"><%= docName %></a>
								</div>
							</div>
						<% } %>
					<% } %>
				</div>
				
				<hr>
				
			<form action="<%= request.getContextPath()%>/AddEvaluationServlet" method="post">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<label class="col-sm-2"><h4>Statistics</h4></label>	
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Number of Evaluations: </label>
							<label class="col-sm-1"><%= nbOfEva %></label>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Risk Level: </label>
							<% if ( riskLvl == 0) { %>
								<label class="col-sm-1">None</label>
							<% } else { %>
								<div class="col-sm-7">
									<div class="progress">
		    							<div class="progress">
		    							<div 
		    								class="progress-bar
			    							<% if ( riskLvl < 2.3) { %>
						      					progress-bar-success
						      				<% } else if ( riskLvl < 3.7 ) { %>
						      					progress-bar-warning
						      				<% } else { %>
						      					progress-bar-danger
						      				<% } %>"
						      				
			    							data-transitiongoal="
			    							<% if ( riskLvl != 1) { %>
			    								<%= (riskLvl - 1) * 25 %>
			    							<% } else { %>
			    								<%= 1 %>
			    							<% } %>"
			    							style="min-width: 2em;">
		    							</div>
									</div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Attractiveness: </label>
							<% if ( attract == 0) { %>
								<label class="col-sm-1">None</label>
							<% } else { %>
								<div class="col-sm-7">
									<div class="progress">
		    							<div 
		    								class="progress-bar
			    							<% if ( attract < 2.3) { %>
						      					progress-bar-danger
						      				<% } else if ( attract < 3.7 ) { %>
						      					progress-bar-warning
						      				<% } else { %>
						      					progress-bar-success
						      				<% } %>"
						      				
			    							data-transitiongoal="
			    							<% if ( attract != 1) { %>
			    								<%= (attract - 1) * 25 %>
			    							<% } else { %>
			    								<%= 1 %>
			    							<% } %>"
			    							style="min-width: 2em;">
		    							</div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
				</div>
				
				<hr>
				
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<label class="col-sm-2"><h4>Your Evaluation</h4></label>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label for="inputRiskLevel" class="col-sm-3 col-sm-offset-1">Risk Level: </label>
							<% if ( riskLvlUser == 0 ) { %>
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
							<% } else { %>
								<div class="col-sm-7">
									<div class="progress">
		    							<div class="progress">
		    							<div 
		    								class="progress-bar
			    							<% if ( riskLvlUser < 2.3) { %>
						      					progress-bar-success
						      				<% } else if ( riskLvlUser < 3.7 ) { %>
						      					progress-bar-warning
						      				<% } else { %>
						      					progress-bar-danger
						      				<% } %>"
						      				
			    							data-transitiongoal="
			    							<% if ( riskLvlUser != 1) { %>
			    								<%= (riskLvlUser - 1) * 25 %>
			    							<% } else { %>
			    								<%= 1 %>
			    							<% } %>"
			    							style="min-width: 2em;">
		    							</div>
									</div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label for="inputAttractiveness" class="col-sm-3 col-sm-offset-1">Attractiveness: </label>
							<% if ( attractUser == 0 ) { %>
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
							<% } else { %>
								<div class="col-sm-7">
									<div class="progress">
		    							<div 
		    								class="progress-bar
			    							<% if ( attractUser < 2.3) { %>
						      					progress-bar-danger
						      				<% } else if ( attractUser < 3.7 ) { %>
						      					progress-bar-warning
						      				<% } else { %>
						      					progress-bar-success
						      				<% } %>"
						      				
			    							data-transitiongoal="
			    							<% if ( attractUser != 1) { %>
			    								<%= (attractUser - 1) * 25 %>
			    							<% } else { %>
			    								<%= 1 %>
			    							<% } %>"
			    							style="min-width: 2em;">
		    							</div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
				</div>
		         
			    <div class="modal-footer">
			    	<input type="submit" class="btn btn-primary" value="Save">
			    </div>
		    </form>
	    <% } %>
	    
      </div>
      
</div>

<jsp:include page="/include/Footer.jsp" />

<%  request.getSession().setAttribute("projectTitle", proj.getAcronym()); %>

<%  } %>

<script type="text/javascript">
$(document).ready(function() {
    $('.progress .progress-bar').progressbar({ display_text: 'fill' });
}); 
</script>