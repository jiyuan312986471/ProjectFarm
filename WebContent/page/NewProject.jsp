<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.db.UserDB" %>
<%@ page import="model.Owner" %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="New Project Idea"/>
</jsp:include>

<div class="container">

	<div class="modal-content">
	
         <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">New Project Idea</h4>
         </div>
         
         <div class="modal-body">
         	<form class="form-horizontal">
			  <div class="form-group">
			    <label for="inputTitle" class="col-sm-2 control-label">Title: </label>
			    <div class="col-sm-4">
			      <input type="text" class="form-control" id="inputTitle" placeholder="Project Title">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputDescription" class="col-sm-2 control-label">Description: </label>
			    <div class="col-sm-10">
			    	<textarea name="description" style="overflow:scroll; overflow-x:hidden;" class="form-control" id="inputDescription" placeholder="Project Description" rows="3"></textarea>
			      <!-- <input type="text" class="form-control" id="inputDescription" placeholder="Project Description"> -->
			    </div>
			  </div>
			  
			  <!-- <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <div class="checkbox">
			        <label>
			          <input type="checkbox"> Remember me
			        </label>
			      </div>
			    </div>
			  </div> -->
			  
			</form>
         </div>
         
         <hr>
         
         <div class="modal-body">
         	<form class="form-horizontal">
			  <div class="form-group">
			    <label for="inputCategory" class="col-sm-2 control-label">Category: </label>
			    <div class="col-sm-4">
			      <input type="text" class="form-control" id="inputCategory" placeholder="Project Category">
			    </div>
			    <label for="inputBudget" class="col-sm-2 control-label">Budget (EUR) </label>
			    <div class="col-sm-4">
			      <input type="text" class="form-control" id="inputBudget" placeholder="Project Budget">
			    </div>
			  </div>
			</form>
         </div>
         
         <div class="modal-footer">
         	<button type="submit" class="btn btn-primary">Save</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Discard</button>
         </div>
      </div><!-- /.modal-content -->

	
	<!-- Panel -->
	<!-- <div class="panel panel-default">
  		<div class="panel-heading">
    		<h3 class="panel-title">New Project Idea</h3>
  		</div>
  		<div class="panel-body">
    		Panel content
  		</div>
  		<hr>
  		<div class="panel-body">
    		Panel content
  		</div>
	</div> -->

</div>

<jsp:include page="/include/Footer.jsp" />