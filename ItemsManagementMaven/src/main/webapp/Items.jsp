<%@page import="com.PAF.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	if (request.getParameter("itemCode") != null) {	
		
		Item item = new Item();
		
		String code = request.getParameter("itemCode");
		String name = request.getParameter("itemName");
		String price = request.getParameter("itemPrice");
		String desc = request.getParameter("itemDesc");
		
		String statusMsg = item.insertItem(code, name, price, desc);
		
		session.setAttribute("statusMsg", statusMsg);
	}

	if (request.getParameter("itemID") != null) {
		Item item = new Item();
		String statusMsg = item.deleteItem(request.getParameter("itemID"));
		session.setAttribute("statusMsg", statusMsg);
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="views/bootstrap.min.css">
		<title>Items Management</title>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col">
					<h1>Items Management</h1>
					<form method="POST" action="Items.jsp">
						Item code: <input name="itemCode" type="text" class="form-control"><br>
						Item name: <input name="itemName" type="text" class="form-control"><br>
						Item price: <input name="itemPrice" type="text" class="form-control"><br>
						Item description: <input name="itemDesc" type="text" class="form-control"><br>
						<input name="btnSubmit" type="submit" value="Save" class="btn btn-primary">
					</form>
				
					<div class="alert alert-success">
						<% out.print(session.getAttribute("statusMsg")); %>
					</div>
					<br>
					<%
						Item item = new Item(); 
						out.print(item.readItems());
					%>
				</div>
			</div>
		</div>
	</body>
</html>