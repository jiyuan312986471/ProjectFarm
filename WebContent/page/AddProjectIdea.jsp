<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.db.CategoryDB" %>
<%@ page import="model.Owner" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.LinkedList" %>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>

<% LinkedList<Category> listCategory = CategoryDB.getCategories(); %>

<div class="container">

	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
	
         <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">New Project Idea</h4>
         </div>
         
         <form class="form-horizontal" action="<%= request.getContextPath()%>/AddProjectIdeaServlet">
	         <div class="modal-body">
				  <div class="form-group">
				    <label for="inputTitle" class="col-sm-2 control-label">Title: </label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="inputTitle" placeholder="Project Title" name="projectTitle" value="<%= request.getParameter("projectTitle") == null ?"": request.getParameter("projectTitle") %>">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputDescription" class="col-sm-2 control-label">Description: </label>
				    <div class="col-sm-10">
				    	<textarea style="overflow:scroll; overflow-x:hidden;" class="form-control" id="inputDescription" placeholder="Project Description" rows="3" name="projectDescription"><%= request.getParameter("projectDescription")==null?"":request.getParameter("projectDescription")%></textarea>
				    </div>
				  </div>
	         </div>
	         
	         <hr>
	         
	         <div class="modal-body">
				  <div class="form-group">
				    <label for="inputCategory" class="col-sm-2 control-label">Category:</label>
				    <div class="col-sm-3">
				    	<select class="form-control" id="inputCategory" name="projectCategory">
				    	<% for(Category category: listCategory) { %>
				    		<option
				    			<% if( category.getDescription().trim().equals(request.getParameter("projectCategory")) ) { %>
				    				selected="selected"
				    			<% } %>				    		
				    		><%= category.getDescription() %></option>
				    	<% } %>
						</select>
				    </div>
				    <label for="inputBudget" class="col-sm-2 control-label">Budget:</label>
				    <div class="col-sm-4">
						<div class="input-group">
						    <input type="text" class="form-control text-right" id="inputBudget" placeholder="Budget" name="projectBudget" value="<%= request.getParameter("projectBudget") == null ?"": request.getParameter("projectBudget") %>">
						    <div class="input-group-addon">,00</div>
						    <div class="input-group-addon">€</div>
						</div>
					</div>
				  </div>
	         </div>
         
	         <div class="modal-footer">
	         	<button type="submit" class="btn btn-primary">Save</button>
	            <button type="reset" class="btn btn-default" data-dismiss="modal">Reset All</button>
	         </div>
         </form>
      </div>
      
</div>

<jsp:include page="/include/Footer.jsp" />

<% } %>