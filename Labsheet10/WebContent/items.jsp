<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
        <script src="Components/items.js"></script>
		<title>Items Management</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>Items Management</h1>
					<form id="formItem" name="formItem" method="POST" action="items.jsp">
						Item code: 
						<input 
							id="itemCode" 
							name="itemCode" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Item name: 
						<input 
							id="itemName"
							name="itemName" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Item price: 
						<input 
							id="itemPrice" 
							name="itemPrice" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						Item description: 
						<input 
							id="itemDesc" 
							name="itemDesc" 
							type="text" 
							class="form-control form-control-sm"
						><br>

						<input 
							id="btnSave" 
							name="btnSave" 
							type="button" 
							value="Save" 
							class="btn btn-primary"
						>

						<input type="hidden" name="hidItemIDSave" id="hidItemIDSave" value="">
					</form>
				
					<br>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divItemsGrid">
						<%
							Item item = new Item(); 
							out.print(item.readItems());
						%>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>