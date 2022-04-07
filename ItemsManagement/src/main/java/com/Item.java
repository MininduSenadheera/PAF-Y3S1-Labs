package com;

import java.sql.*;

public class Item {

    public Connection connect(){
    	
        //database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "items_management";
        String dbUsername = "root";
        String dbPassword = "";
        
        Connection conn = null;
        
        try {
        	//connecting the database
        	Class.forName(dbDriver);
        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        	
        	//if successfully connected this will be printed in the terminal
        	System.out.print("Database connected successfully");
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return conn;
    }
    
    
    //method to insert data
    public String insertItem(String code, String name, String price, String desc) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO items (itemCode,itemName,itemPrice,itemDesc) VALUES (?,?,?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, code);
        	preparedStatement.setString(2, name);
        	preparedStatement.setDouble(3, Double.parseDouble(price));
        	preparedStatement.setString(4, desc);
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	Output = "Item inserted successfully";
        	
    	} catch(Exception e) {
    		Output = "Failed to insert the item";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    
    //method to read data
    public String readItems() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM items";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table border='1'><tr><th>Item Code</th>" +"<th>Item Name</th><th>Item Price</th>"
    		+ "<th>Item Description</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";
        	
        	while(resultSet.next()) {
        		String itemID = Integer.toString(resultSet.getInt("itemID"));
        		String itemCode = resultSet.getString("itemCode");
        		String itemName = resultSet.getString("itemName");
        		String itemPrice = Double.toString(resultSet.getDouble("itemPrice"));
        		String itemDesc = resultSet.getString("itemDesc");
        		
        		// Add a row into the HTML table
        		Output += "<tr><td>" + itemCode + "</td>"; 
        		Output += "<td>" + itemName + "</td>"; 
        		Output += "<td>" + itemPrice + "</td>"; 
        		Output += "<td>" + itemDesc + "</td>";
        		
        		// buttons
        		Output += "<td><input name='btnUpdate' type='button' value='Update'></td>" + "<td><form method='post' action='items.jsp'>"
        				+ "<input name='btnRemove' type='submit' value='Remove'>"
        				+ "<input name='itemID' type='hidden' value='" + itemID + "'>"
        				+ "</form></td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the items";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
}
