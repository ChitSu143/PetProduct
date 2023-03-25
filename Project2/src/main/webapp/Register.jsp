<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
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

<div class="container">
    <form action="PetController" method="post" "form-horizontal" role="form">
        <h2 class="mb-3 text-center">Pet Registration</h2>
        
        <input type="hidden" name="mode" value="CREATE" />  
        <div class="mb-3">
            <label for="petName" class="form-label">*Pet Name</label>
            <input type="text" id="petName" name="petName" placeholder="Enter Your Pet Name" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="petAge" class="form-label">*Pet Age</label>
            <input type="number" id="petAge" name="petAge" placeholder="Enter Your Pet Age" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="ownerName" class="form-label">*Owner Name</label>
            <input type="text" id="ownerName" name="ownerName" placeholder="Enter Your Owner Name" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="ownerAge" class="form-label">*Owner Age</label>
            <input type="number" id="ownerAge" name="ownerAge" placeholder="Enter Your Owner Age" class="form-control" required="required" autofocus>
        </div>
        
        <div class="mb-3">
            <label for="purchase" class="form-label">*Purchased Status (Y) / (N)</label>
            <div class="col-sm-6">
            	<div class="row">
            		<div class="col-sm-4">
            			<label class="radio-inline">
            				<input type="radio" id="purchasedStatus"  name="purchasedStatus" value="true" >Yes
            			</label>
            		</div>
            		<div class="col-sm-4">
            			<label class="radio-inline">
            				<input type="radio" id="purchasedStatus" name="purchasedStatus" value="false"  >No
            			</label>
            		</div>
            	</div>
            </div>
        </div>
        
        
        
         
        
        
        
        <div class="mb-3">
            <div class="col-sm-9 col-sm-offset-3">
                <span class="help-block">*Required fields</span>
            </div>
        </div>
        
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-danger">Reset</button>
    </form> <!-- /form -->
     
</div> <!-- ./container -->
  
</div>
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
</body>
</html>
