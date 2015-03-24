<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Project" %>
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
         
         <div class="modal-body">
         	<form action="<%= request.getContextPath()%>/ProjectDetailsServlet" method="get">
				<table class="table table-bordered">
				
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
				  		<tr>
				  			<td>
				  				<a href="javascript:" onclick="getAcronym()"><%= proj.getAcronym() %></a>
				  				<input type="hidden" id="acronym" name="acronym">
				  			</td>
				      		<td><%= proj.getCategory() %></td>
				      		<td><%= proj.getFundingDuration() %></td>
				      		<td><%= proj.toStringBudget() %>,00</td>
				      		<td class="warning" style="color:black;">4,7</td>
				      		<td class="warning" style="color:black;">1,5</td>
				      		<td>42</td>
				  		</tr>
				  	<% } %>
				    <tr>
				      <td><a href="ProjectDetails.jsp">DIY Autonomous Car</a></td>
				      <td>Robotics</td>
				      <td>100</td>
				      <td>500.000,00</td>
				      <td class="warning" style="color:black;">4,7</td>
				      <td class="warning" style="color:black;">1,5</td>
				      <td>42</td>
				    </tr>
				    <tr>
				      <td><a href="ProjectDetails.jsp">Drone Delivery</a></td>
				      <td>Robotics</td>
				      <td>55</td>
				      <td>800.000,00</td>
				      <td class="danger" style="color:black;">3</td>
				      <td class="danger" style="color:black;">3</td>
				      <td>34</td>
				    </tr>
				    <tr>
				      <td><a href="ProjectDetails.jsp">DIY Smart Home</a></td>
				      <td>App</td>
				      <td>88</td>
				      <td>200.000,00</td>
				      <td class="success" style="color:black;">1.8</td>
				      <td class="danger" style="color:black;">3.6</td>
				      <td>50</td>
				    </tr>
				  </tbody>
				  
				</table>
			</form>
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="/include/Footer.jsp" />

<% } %>

<script type="text/javascript">
	// javascript part, to get acronym
	function getAcronym() {
		var currentTd = event.target.parentElement;
		var acro = currentTd.innerText;
		document.getElementById("acronym").value = acro;
		document.forms[0].submit();
	}
</script>