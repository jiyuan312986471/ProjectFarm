<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%  if(session.getAttribute("name") == null) {
		request.getSession().setAttribute("messageError", "Please Login!");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else { %>

<jsp:include page="/include/Header.jsp">
  <jsp:param name="title" value="Project Farm" />
  <jsp:param name="page" value="/index.jsp" />
</jsp:include>

<!-- container -->
<div class="container">
	<div class="modal-content" style="filter:alpha(Opacity=95); -moz-opacity:0.95; opacity:0.95; background-color:black; color:white;">
		 <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">My Projects</h4>
         </div>
         
         <div class="modal-body">
			<table class="table table-bordered">
			  <thead>
			    <tr>
			      <th>Acronym</th>
			      <th>Category</th>
			      <th>Number of incubation days</th>
			      <th>Budget</th>
			      <th>Risk Level</th>
			      <th>Attractiveness</th>
			      <th>Number of evaluators</th>
			    </tr>
			  </thead>
			  <tbody>
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
		</div>
	</div>
</div>

<!-- footer -->
<jsp:include page="/include/Footer.jsp" />

<% } %>