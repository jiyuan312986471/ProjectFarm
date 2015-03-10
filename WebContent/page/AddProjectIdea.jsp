<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.db.CategoryDB" %>
<%@ page import="model.Owner" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.LinkedList" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>

<% LinkedList<Category> listCategory = CategoryDB.getCategories(); %>

<div class="container">

	<div class="modal-content">
	
         <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">New Project Idea</h4>
         </div>
         
         <form class="form-horizontal" action="<%= request.getContextPath()%>/AddProjectIdeaServlet" method="post">
	         <div class="modal-body">
				  <div class="form-group">
				    <label for="inputTitle" class="col-sm-2 control-label">Title: </label>
				    <div class="col-sm-4">
				      <input type="text" class="form-control" id="inputTitle" placeholder="Project Title" name="projectTitle">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputDescription" class="col-sm-2 control-label">Description: </label>
				    <div class="col-sm-10">
				    	<textarea style="overflow:scroll; overflow-x:hidden;" class="form-control" id="inputDescription" placeholder="Project Description" rows="3" name="projectDescription"></textarea>
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
				    		<option><%= category.getDescription() %></option>
				    	<% } %>
						</select>
				    </div>
				    <label for="inputBudget" class="col-sm-2 control-label">Budget:</label>
				    <div class="col-sm-4">
						<div class="input-group">
						    <input type="text" class="form-control text-right" id="inputBudget" placeholder="Budget" name="projectBudget">
						    <div class="input-group-addon">.00</div>
						    <div class="input-group-addon">€</div>
						</div>
					</div>
				  </div>
	         </div>
         
	         <div class="modal-footer">
	         	<button type="submit" class="btn btn-primary">Save</button>
	            <button type="button" class="btn btn-default" data-dismiss="modal">Discard</button>
	         </div>
         </form>
      </div>
      
</div>

<jsp:include page="/include/Footer.jsp" />