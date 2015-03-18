<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>


<div class="container">

	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
	
         <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">Project Details</h4>
         </div>
         
	     <div class="modal-body">
		 	<!-- <label for="inputTitle" class="col-sm-2 control-label">Title: </label> -->
		 	<label class="control-label">Project Evaluation</label>
			<div class="col-sm-4">
			</div>

			<label for="inputDescription" class="col-sm-2 control-label">Description: </label>
			<div class="col-sm-10">
				<textarea style="overflow:scroll; overflow-x:hidden;" class="form-control" id="inputDescription" placeholder="Project Description" rows="3" name="projectDescription"><%=request.getParameter("projectDescription")==null?"":request.getParameter("projectDescription")%></textarea>
			</div>
		</div>
	         
	    <hr>
	         
	    <div class="modal-body">
			<div class="form-group">
				<label for="inputCategory" class="col-sm-2 control-label">Category:</label>
				<div class="col-sm-3">
					<select class="form-control" id="inputCategory" name="projectCategory">
					</select>
				</div>
				<label for="inputBudget" class="col-sm-2 control-label">Budget:</label>
				<div class="col-sm-4">
					<div class="input-group">
						<input type="text" class="form-control text-right" id="inputBudget" placeholder="Budget" name="projectBudget" value="<%=request.getParameter("projectBudget")==null?"":request.getParameter("projectBudget")%>">
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
      </div>
      
</div>

<jsp:include page="/include/Footer.jsp" />