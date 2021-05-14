package com.GBPracticalTest.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class productsServlet {
	private Connection connect() 
	{ 
		Connection con = null;
		try
		{   
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/productsservice", "root", "1234"); 
			} 
		catch (Exception e)
		{e.printStackTrace();}
		return con; 
	}
	public String insertProducts(String name, String price, String description) 
	{
		String output = ""; 
		try {
			Connection con = connect(); 
			if (con == null)
			{return"Error while connecting to the database for inserting."; }
			
			String query = " insert into practicaltestproducts  (productID,productName,productPrice,productDescription)"+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price)); 
			preparedStmt.setString(4, description);
			
			preparedStmt.execute();
			con.close(); 
			
			 String newProduct = readProducts(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}"; 
		}
		catch (Exception e)
		{ 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the product.\"}";
			System.err.println(e.getMessage()); 
			} 
		return output; 
		
	}
	public String readProducts() {
		String output = "";
		
		try {
			
			Connection con = connect(); 
			if (con == null) 
			{return"Error while connecting to the database for reading."; } 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Product Name</th>" +
			      
			         "<th>Product Price</th>" +  
					 "<th>Product Description</th>" +
			         "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from practicaltestproducts";
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			while (rs.next()) 
			{ 
				String productID = Integer.toString(rs.getInt("productID")); 
				String productName = rs.getString("productName"); 
				String productPrice = Double.toString(rs.getDouble("productPrice")); 
				String productDescription = rs.getString("productDescription"); 
				
				// Add into the html table
				//output += "<tr><td>" + productID + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + productPrice + "</td>"; 
				output += "<td>" + productDescription + "</td>"; 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-productID='" + productID + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-productID='"
						 + productID + "'>" + "</td></tr>";
				}
			con.close(); 
			
			output += "</table>"; 
		}
			
	    catch (Exception e)
		{
	    	output = "Error while reading the products."; 
	    	System.err.println(e.getMessage()); 
		}
		
		return output; 
	} 
	
	public String updateProducts(String ID , String name, String price, String description)
	{
		String output = ""; 
		try 
		{
			Connection con = connect(); 
			if (con == null) 
			{return"Error while connecting to the database for updating."; } 
			// create a prepared statement
			
			String query = "UPDATE practicaltestproducts SET productName=?,productPrice=?,productDescription=? WHERE productID=?"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setString(1, name); 
			preparedStmt.setDouble(2, Double.parseDouble(price)); 
			preparedStmt.setString(3, description); 
			preparedStmt.setInt(4, Integer.parseInt(ID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close();
			 String newProduct = readProducts(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
		} 
		catch (Exception e)
		{
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the product.\"}"; 
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deleteProduct(String productID)
	{ 
		String output = ""; 
		try{ Connection con = connect(); 
		if (con == null) 
		{return"Error while connecting to the database for deleting."; }
		
		// create a prepared statement
		String query = "delete from practicaltestproducts where productID=?"; 
		
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, Integer.parseInt(productID)); 
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newProduct = readProducts(); 
		output = "{\"status\":\"success\", \"data\": \"" + newProduct + "\"}";
		}
		catch (Exception e) 
		{ 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting ....\"}"; 
			System.err.println(e.getMessage());
			System.out.println("++++++++++++++++++++++++++++++++Error" +e);
			
		} 
		return output; 
		
	}
	
}
