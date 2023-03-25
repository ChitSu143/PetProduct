<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
<title>Pet Shop</title>
<meta charset="iso-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<!--[if IE 6]><link href="css/ie6.css" rel="stylesheet" type="text/css"><![endif]-->
<!--[if IE 7]><link href="css/ie7.css" rel="stylesheet" type="text/css"><![endif]-->
</head>
<body>
	
	<c:url var="PetController" value="pet">
		<c:param name="mode" value="LIST"></c:param>
	</c:url>

<div id="header"> <a href="#" id="logo"><img src="images/logo.gif" width="200" height="114" alt=""></a>
  <ul class="navigation">
    <li class="active"><a href="index.jsp">Home</a></li>
    <li><a href="petmart.jsp">PetMart</a></li>
    <li><a href="Login.jsp">Login</a></li>
    <li><a href="Register.jsp">SignUp</a></li>
    <li><a href="PetController">PetMember</a></li>
    <li><a href="contact.jsp">Contact Us</a></li>
  </ul>
</div>

<div id="body">


<!-- Page content-->
	<div class="container mt-5">
		<table id="result" class="table table-striped" style="color: green;">
			<thead>
				<tr>
					<th>ID</th>
					<th>Pet Name</th>
					<th>Pet Age</th>
					<th>Owner Name</th>
					<th>Owner Age</th>
					<th>Purchased Status</th>
					<th>Action</th>
					
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList }">
				<c:url var="updateLink" value="PetController">
					<c:param name="mode" value="LOAD"></c:param>
					<c:param name="id" value="${result.id}"></c:param>
					
				</c:url>
				<c:url var="deleteLink" value="PetController">
					<c:param name="mode" value="DELETE"></c:param>
					<c:param name="id" value="${result.id}"></c:param>
					
				</c:url>
									
					<tr>
						<td> <c:out value="${result.id }"></c:out> </td>
						<td> <c:out value="${result.petName }"></c:out> </td>
						<td> <c:out value="${result.petAge }"></c:out> </td>
						<td> <c:out value="${result.ownerName }"></c:out> </td>
						<td> <c:out value="${result.ownerAge }"></c:out> </td>
						
						<td> 
						
						<c:choose>
							<c:when test="${result.purchasedStatus }">
								<c:out value="*Purchased"></c:out>
							</c:when>
							<c:otherwise>
								<c:out value="-"></c:out>
							</c:otherwise>
						</c:choose>
						</td>
						<td> 
							<a href="${updateLink }" class="btn btn-primary">Edit</a>
							<a href="${deleteLink }" id="delete" class="btn btn-danger" onclick="return confirm('Are you sure to delete this result?');">Delete</a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>ID</th>
					<th>Pet Name</th>
					<th>Pet Age</th>
					<th>Owner Name</th>
					<th>Owner Age</th>
					<th>Purchased Status</th>
					<th>Action</th>
					
					
				</tr>
			</tfoot>
		</table>
		
	</div>







</div>
<!-- Footer Java Script -->
	<c:import url="common/footer.html"></c:import>
	
<div id="footer">
  <div class="section">
    <ul>
      <li> <img src="images/friendly-pets.jpg" width="240" height="186" alt="">
        <h2><a href="#">Friendly Pets</a></h2>
        <p> Lorem ipsum dolor sit amet, consectetuer adepiscing elit, sed diam nonummy nib. <a class="more" href="#">Read More</a> </p>
      </li>
      <li> <img src="images/pet-lover2.jpg" width="240" height="186" alt="">
        <h2><a href="#">How dangerous are they</a></h2>
        <p> Lorem ipsum dolor sit amet, cons ectetuer adepis cing, sed diam euis. <a class="more" href="#">Read More</a> </p>
      </li>
      <li> <img src="images/healthy-dog.jpg" width="240" height="186" alt="">
        <h2><a href="#">Keep them healthy</a></h2>
        <p> Lorem ipsum dolor sit amet, consectetuer adepiscing elit, sed diam nonu mmy. <a class="more" href="#">Read More</a> </p>
      </li>
      <li>
        <h2><a href="#">Love...love...love...pets</a></h2>
        <p> Lorem ipsum dolor sit amet, consectetuer adepiscing elit, sed diameusim. <a class="more" href="#">Read More</a> </p>
        <img src="images/pet-lover.jpg" width="240" height="186" alt=""> </li>
    </ul>
  </div>
  <div id="footnote">
    <div class="section">Copyright &copy; 2012 <a href="#">Company Name</a> All rights reserved | Website Template By <a target="_blank" href="http://www.freewebsitetemplates.com/">freewebsitetemplates.com</a></div>
  </div>
</div>
<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<script>
			$(document).ready(function (){
				$('#result').DataTable();
			});
	</script>
</body>
</html>
