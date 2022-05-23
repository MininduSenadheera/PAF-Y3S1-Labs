package model;

import java.sql.*;

public class Item {
	
	// common method to connect to DB
	private Connection connect() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//DB connection details
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items_management","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public String insertItem(String code, String name, String price, String desc) {
		String output = "";
	 
		try	{
			Connection conn = connect(); 
			
			if (conn == null) {	
				return "Error while connecting to the database for inserting."; 
			}
		
			// create a prepared statement
			String query = " INSERT INTO items (`itemCode`,`itemName`,`itemPrice`,`itemDesc`)" + " values (?, ?, ?, ?)"; 
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			// binding values
			preparedStatement.setString(1, code); 
			preparedStatement.setString(2, name); 
			preparedStatement.setDouble(3, Double.parseDouble(price)); 
			preparedStatement.setString(4, desc);
			
			// execute the statement
			preparedStatement.execute(); 
			conn.close();
			
			output = "Inserted successfully"; 
		}
		catch (Exception e) {
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readItems() {
		String output = ""; 
		try {
		
			Connection conn = connect(); 
			
			if (conn == null) {
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the HTML table to be displayed
			output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" + "<th>Item Price</th>" + "<th>Item Description</th>" + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "SELECT * FROM items"; 
			
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			// iterate through the rows in the result set
			while (resultSet.next()) {
				String itemID = Integer.toString(resultSet.getInt("itemID"));
				String itemCode = resultSet.getString("itemCode");
				String itemName = resultSet.getString("itemName");
				String itemPrice = Double.toString(resultSet.getDouble("itemPrice")); 
				String itemDesc = resultSet.getString("itemDesc");
				
				// Add into the HTML table
				output += "<tr><td>" + itemCode + "</td>"; 
				output += "<td>" + itemName + "</td>"; 
				output += "<td>" + itemPrice + "</td>"; 
				output += "<td>" + itemDesc + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + itemID + "'>" 
						+ "</form></td></tr>";
			}
			
			conn.close();
			
			// Complete the HTML table
			output += "</table>";
			
		} catch (Exception e) {
			output = "Error while reading the items."; 
			System.err.println(e.getMessage());
		}
		
		return output;
	}
			
	
	public String updateItem(String ID, String code, String name, String price, String desc) {
		String output = ""; 
		try {
			Connection conn = connect(); 
			
			if (conn == null) {
				return "Error while connecting to the database for updating."; 
			}
			
			// create a prepared statement
			String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDesc=? WHERE itemID=?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			
			// binding values
			preparedStatement.setString(1, code); 
			preparedStatement.setString(2, name); 
			preparedStatement.setDouble(3, Double.parseDouble(price)); 
			preparedStatement.setString(4, desc); 
			preparedStatement.setInt(5, Integer.parseInt(ID));
			
			// execute the statement
			preparedStatement.execute(); 
			conn.close();
			
			output = "Updated successfully"; 
		} catch (Exception e) {
			output = "Error while updating the item."; 
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteItem(String itemID) {
		String output = ""; 
		try {
			Connection conn = connect(); 
			if (conn == null) {
				return "Error while connecting to the database for deleting."; 
			} 
			
			// create a prepared statement
			String query = "DELETE FROM items WHERE itemID=?";
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			// binding values
			preparedStatement.setInt(1, Integer.parseInt(itemID));
			// execute the statement
			preparedStatement.execute(); 
			
			conn.close();
			output = "Deleted successfully"; 
		}catch (Exception e) {
			output = "Error while deleting the item."; 
			System.err.println(e.getMessage());
		} 
		
		return output;
	}
}
