<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Puppy Sweeties - pet products online shop</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/templatemo.css">
    <link rel="stylesheet" href="assets/css/custom.css">

    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
    <link rel="stylesheet" href="assets/css/fontawesome.min.css">
    
    
    
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">    
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap5.min.css">
    
	
	<meta charset="iso-8859-1">
	

</head>

<body>
    <!-- Start Top Nav -->
    <nav class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block" id="templatemo_nav_top">
        <div class="container text-light">
            <div class="w-100 d-flex justify-content-between">
                <div>
                    <i class="fa fa-envelope mx-2"></i>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="mailto:info@company.com">puppysweeties@company.com</a>
                    <i class="fa fa-phone mx-2"></i>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="tel:+95-940-260-2046">+95-940-260-2046</a>
                </div>
                <div>
                    <a class="text-light" href="https://fb.com/templatemo" target="_blank" rel="sponsored"><i class="fab fa-facebook-f fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://www.instagram.com/" target="_blank"><i class="fab fa-instagram fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://twitter.com/" target="_blank"><i class="fab fa-twitter fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://www.linkedin.com/" target="_blank"><i class="fab fa-linkedin fa-sm fa-fw"></i></a>
                </div>
            </div>
        </div>
    </nav>
    <!-- Close Top Nav -->


     <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-light shadow">
        <div class="container d-flex justify-content-between align-items-center">

            <a class="navbar-brand text-success logo h1 align-self-center" href="index.jsp">
                Puppy Sweeties
            </a>

            <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            
            <c:url var="showMembersLink" value="PetController">
					<c:param name="mode" value="LIST"></c:param>
					
			</c:url>
			
			<c:url var="showAdminsLink" value="user">
					<c:param name="mode" value="ALL"></c:param>
					
			</c:url>
			
			<c:url var="showProductssLink" value="product">
					<c:param name="mode" value="LIST"></c:param>
					
			</c:url>

            <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="templatemo_main_nav">
                <div class="flex-fill">
                    <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="AdminPage.jsp">Home</a>
                        </li>
                        
                        <li class="nav-item">
                            <a class="nav-link" href="${showProductssLink }">Product</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${showAdminsLink }">Admin List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${showMembersLink }">Member List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp">Logout</a>
                        </li>
                    </ul>
                </div>
               
            </div>

        </div>
    </nav>
    <!-- Close Header -->

    <!-- Modal -->
    <div class="modal fade bg-white" id="templatemo_search" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="w-100 pt-1 mb-5 text-right">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="" method="get" class="modal-content modal-body border-0 p-0">
                <div class="input-group mb-2">
                    <input type="text" class="form-control" id="inputModalSearch" name="q" placeholder="Search ...">
                    <button type="submit" class="input-group-text bg-success text-light">
                        <i class="fa fa-fw fa-search text-white"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>



    <!-- Start Banner Hero -->
    
   <div class="container">
    <form action="product" method="post" enctype="multipart/form-data">
    <input type="hidden" name="mode" value="UPDATE" />
    
    <input type="hidden" name="id" value="${product.id}" />
        <h2 class="mb-3 text-center">Update Product Information</h2>
        
        
        
        <div class="mb-3">
            <label for="productName" class="form-label">*Product Name</label>
            <input type="text" value="${product.productName}" id="productName" name="productName" placeholder="Enter Product Name" class="form-control" required="required" autofocus>
        </div>
         <div class="mb-3">
            <label for="qty" class="form-label">*Quantity</label>
            <input type="number" value="${product.qty}" id="qty" name="qty" placeholder="Enter Quantity" class="form-control" required="required" autofocus>
        </div>
         <div class="mb-3">
            <label for="description" class="form-label">*Description</label>
            <textarea id="description" value="${product.description}" name="description" placeholder="Enter Description" class="form-control" required="required" autofocus></textarea>
        </div>
         <div class="mb-3">
            <label for="sellingprice" class="form-label">*Selling Price</label>
            <input type="number" value="${product.sellingprice}" id="sellingprice" name="sellingprice" placeholder="Enter Selling Price" class="form-control" required="required" autofocus>
        </div>
        
        <c:choose>
        	<c:when test="${product.discount }">
         
         <div class="mb-3">
            <label for="discount" class="form-label">*Is Discount ? (Y) / (N)</label>
            <div class="col-sm-6">
            	<div class="row">
            		<div class="col-sm-4">
            			<label class="radio-inline">
            				<input type="radio" class="discount"  id="discount" value="true" checked="checked">Yes
            			</label>
            		</div>
            		<div class="col-sm-4">
            			<label class="radio-inline">
            				<input type="radio" class="discount" id="discount" value="false" >No
            			</label>
            		</div>
            	</div>
            </div>
        </div>
        </c:when>
        <c:otherwise>
        	<div class="mb-3">
            <label for="discount" class="form-label">*Is Discount (Y) / (N)</label>
            <div class="col-sm-6">
            	<div class="row">
            		<div class="col-sm-4">
            			<label class="radio-inline">
            				<input type="radio" id="discount"  name="discount" value="true" >Yes
            			</label>
            		</div>
            		<div class="col-sm-4">
            			<label class="radio-inline">
            				<input type="radio" id="discount" name="discount" value="false"  >No
            			</label>
            		</div>
            	</div>
            </div>
        </div>
          </c:otherwise>
        </c:choose>
        
        
        <div class="mb-3">
            <label for="imageName" class="form-label">*Input Product Image</label>
            <input type="file" id="imageName" name="imageName">
            
            
        </div>
        
         <div class="mb-3">
            <label for="brandName" class="form-label">*Enter Brand Name</label>
            <input type="text" value="${product.brandName}" id="brandName" name="brandName" placeholder="Enter Brand Name" class="form-control" required="required" autofocus>
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
   
   
    <!-- End Banner Hero -->


    <!-- Start Footer -->
    <footer class="bg-dark" id="tempaltemo_footer">
        <div class="container">
            <div class="row">

                <div class="col-md-4 pt-5">
                    <h2 class="h2 text-success border-bottom pb-3 border-light logo">Puppy Sweeties Shop</h2>
                    <ul class="list-unstyled text-light footer-link-list">
                        <li>
                            <i class="fas fa-map-marker-alt fa-fw"></i>
                            73 St.,Bet: St.120 and St. 121,MDY
                        </li>
                        <li>
                            <i class="fa fa-phone fa-fw"></i>
                            <a class="text-decoration-none" href="tel:010-020-0340">+95-940-260-2046</a>
                        </li>
                        <li>
                            <i class="fa fa-envelope fa-fw"></i>
                            <a class="text-decoration-none" href="mailto:info@company.com">puppysweeties@company.com</a>
                        </li>
                    </ul>
                </div>


            </div>

            <div class="row text-light mb-4">
                <div class="col-12 mb-3">
                    <div class="w-100 my-3 border-top border-light"></div>
                </div>
                <div class="col-auto me-auto">
                    <ul class="list-inline text-left footer-icons">
                        <li class="list-inline-item border border-light rounded-circle text-center">
                            <a class="text-light text-decoration-none" target="_blank" href="http://facebook.com/"><i class="fab fa-facebook-f fa-lg fa-fw"></i></a>
                        </li>
                        <li class="list-inline-item border border-light rounded-circle text-center">
                            <a class="text-light text-decoration-none" target="_blank" href="https://www.instagram.com/"><i class="fab fa-instagram fa-lg fa-fw"></i></a>
                        </li>
                        <li class="list-inline-item border border-light rounded-circle text-center">
                            <a class="text-light text-decoration-none" target="_blank" href="https://twitter.com/"><i class="fab fa-twitter fa-lg fa-fw"></i></a>
                        </li>
                        <li class="list-inline-item border border-light rounded-circle text-center">
                            <a class="text-light text-decoration-none" target="_blank" href="https://www.linkedin.com/"><i class="fab fa-linkedin fa-lg fa-fw"></i></a>
                        </li>
                    </ul>
                </div>
                
            </div>
        </div>

        <div class="w-100 bg-black py-3">
            <div class="container">
                <div class="row pt-2">
                    <div class="col-12">
                        <p class="text-left text-light">
                            Copyright &copy; 2022 Host Myanmar Mandalay 
                            | java Servlet Project Submitted by Chit Su Hlaing</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </footer>
    <!-- End Footer -->

    <!-- Start Script -->
    <script src="assets/js/jquery-1.11.0.min.js"></script>
    <script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/templatemo.js"></script>
    <script src="assets/js/custom.js"></script>
    
    
    <!--  Java Script -->	
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
	
	<script src="js/scripts.js"></script>
	<script>
			$(document).ready(function (){
				$('#result').DataTable();
			});
	</script>
	<!-- End of Java Script -->
    
    <!-- End Script -->
</body>

</html>