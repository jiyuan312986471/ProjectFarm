<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
	<jsp:param name="title" value="ProjectFarm"/>
</jsp:include>


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
					<label class="col-sm-1">Value</label>
					<label class="col-sm-1 col-sm-offset-3">Created: </label>
					<label class="col-sm-1">Value</label>
				</div>
			</div>
				
			<div class="form-group">
				<div class="row">
					<label class="col-sm-1 col-sm-offset-1">Description: </label>
					<label class="col-sm-9">Value</label>
				</div>
			</div>
			
			<div class="form-group">
				<div class="row">
					<label class="col-sm-1 col-sm-offset-1">Category: </label>
					<label class="col-sm-1">Value</label>
					<label class="col-sm-1 col-md-offset-3">Budget:</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control text-left" id="disabledInput" placeholder="Budget" value="" disabled>
							<div class="input-group-addon">.00</div>
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
			 		<div class="col-sm-offset-10"><button type="button" class="btn btn-default">upload</button></div>
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
					<label class="col-sm-1">Value</label>
				</div>
			</div>
			
			<div class="form-group">
				<div class="row">
					<label class="col-sm-3 col-sm-offset-1">Attractiveness: </label>
					<label class="col-sm-1">Value</label>
				</div>
			</div>
			
			<div class="form-group">
				<div class="row">
					<label class="col-sm-3 col-sm-offset-1">Number of Evaluations: </label>
					<label class="col-sm-1">Value</label>
				</div>
			</div>
		</div>
         
	    <div class="modal-footer">
	    	<button type="submit" class="btn btn-primary">Save</button>
	    </div>
      </div>
      
</div>

<jsp:include page="/include/Footer.jsp" />

<% } %>