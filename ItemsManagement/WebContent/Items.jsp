<%@page import="com.Item"%>
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
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Items Management</title>
	</head>
	<body>
		<h1>Items Management</h1>
		<form method="POST" action="Items.jsp">
			Item code: <input name="itemCode" type="text" ><br>
			Item name: <input name="itemName" type="text" ><br>
			Item price: <input name="itemPrice" type="text" ><br>
			Item description: <input name="itemDesc" type="text" ><br>
			<input name="btnSubmit" type="submit" value="Save">
		</form>
		<br>
		<%
			out.print(session.getAttribute("statusMsg"));
		%>
		<%
			Item item = new Item(); 
			out.print(item.readItems());
		%>
	</body>
</html>