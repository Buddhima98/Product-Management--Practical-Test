<%@page import="com.GBPracticalTest.model.productsServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>Product Management - GadgetBadget</title>
	
		<link href="myStyle.css" rel="stylesheet" />
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
		<script src="Components/jquery-3.5.0.min.js"></script>
		<script src="Components/Product.js"></script>

	</head>
	
	<body>
		<div class="container">
	
			<p class="font-weight-bold">
				<center>
					<h1><b>Product Management - GadgetBadget</b></h1>
				</center>
			</p>
			<br><br>
			
			<fieldset>
	
				<legend><b>Add Product Details</b></legend>
					<form id="PRODUCT" name="PRODUCT" class="border border-light p-5">
					
					
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Name:</label>
						    <input type="text" id="productName" class="form-control" name="productName" placeholder="Enter Product Name..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Price:</label>
						    <input type="text" id="productPrice" class="form-control" name="productPrice" placeholder="Enter Product Price..">						    
						</div>
						
						<div class="form-outline mb-4">
						    <label class="form-label" for="form6Example3" class="col-sm-2 col-form-label col-form-label-sm">Product Description:</label>
						    <input type="text" id="productDescription" class="form-control" name="productDescription" placeholder="Enter Product Description">						    
						</div>
												
						<br> 
						
						<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary btn-lg btn-block"> 
						<input type="hidden" id="hidProductIDSave" name="hidProductIDSave" value="">
					</form>
				
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>			
			</fieldset>
			
			<br> 
			
			<div class="container" id="ProductGrid">
				<fieldset>
					<legend><b>View Product Details</b></legend>
					<form method="post" action="product.jsp" class="table table-striped">
						<%
							productsServlet viewProducts = new productsServlet();
											out.print(viewProducts.readProducts());
						%>
					</form>
					<br>
				</fieldset>
			</div>
		</div>
	</body>
</html>



