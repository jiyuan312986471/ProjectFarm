<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Project" %>
<%@ page import="model.db.UserDB" %>
<%@ page import="java.lang.*" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>

<%  Project proj = (Project) request.getAttribute("project"); %>
<%  float riskLvl = Float.parseFloat(request.getAttribute("riskLevel").toString()); %>
<%  float attract = Float.parseFloat(request.getAttribute("attractiveness").toString()); %>
<%  int nbOfEva = Integer.parseInt(request.getAttribute("nbOfEva").toString()); %>

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
			<form action="<%= request.getContextPath()%>/AddDocumentServlet">
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
					 		<label class="col-sm-2"><h4>Documents</h4></label>
					 		<input type="file" class="btn btn-default col-sm-offset-7 col-sm-2" name="documentUpload">
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
				
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<label class="col-sm-2"><h4>Statistics</h4></label>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Risk Level: </label>
							<% if ( riskLvl == 0) { %>
								<label class="col-sm-1">None</label>
							<% } else { %>
								<div class="progress col-sm-7">
	    							<div class="progress-bar" data-transitiongoal="<%= riskLvl * 20 %>"></div>
								</div>
								<!-- <label class="col-sm-1"><%= riskLvl %></label> -->
							<% } %>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Attractiveness: </label>
							<% if ( attract == 0) { %>
								<label class="col-sm-1">None</label>
							<% } else { %>
								<div class="progress col-sm-7">
	    							<div class="progress-bar" data-transitiongoal="<%= attract * 20 %>"></div>
								</div>
								<!-- <label class="col-sm-1"><%= attract %></label> -->
							<% } %>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Number of Evaluations: </label>
							<label class="col-sm-1"><%= nbOfEva %></label>
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
					 		<label class="col-sm-2"><h4>Documents</h4></label>
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
				
				<div class="modal-body">
					<div class="form-group">
						<div class="row">
							<label class="col-sm-2"><h4>Statistics</h4></label>
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
		    							<div class="progress-bar
		    							<% if ( riskLvl < 2.3) { %>
					      					progress-bar-success
					      				<% } else if ( riskLvl < 3.7 ) { %>
					      					progress-bar-warning
					      				<% } else { %>
					      					progress-bar-danger
					      				<% } %>
		    							" data-transitiongoal="<%= riskLvl * 20 %>"></div>
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
		    							<div class="progress-bar
		    							<% if ( attract < 2.3) { %>
					      					progress-bar-danger
					      				<% } else if ( attract < 3.7 ) { %>
					      					progress-bar-warning
					      				<% } else { %>
					      					progress-bar-success
					      				<% } %>
		    							" data-transitiongoal="<%= attract * 20 %>"></div>
									</div>
								</div>
							<% } %>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<label class="col-sm-3 col-sm-offset-1">Number of Evaluations: </label>
							<label class="col-sm-1"><%= nbOfEva %></label>
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

<%  //} %>

<jsp:include page="/include/Footer.jsp" />

<%  } %>

<script type="text/javascript">
$(document).ready(function() {
    $('.progress .progress-bar').progressbar({display_text: 'fill', use_percentage: false});
});
</script>