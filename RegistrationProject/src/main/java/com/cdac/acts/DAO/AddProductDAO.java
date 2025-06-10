package com.cdac.acts.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AddProductDAO {
	Connection con = null;
	PreparedStatement pStmt = null;
	PreparedStatement pStmtId = null;
	ResultSet rs = null;
	
	public AddProductDAO() throws ClassNotFoundException, IOException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties dbProperties = new Properties();
		dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

		String dbUrl = dbProperties.getProperty("connection.url");
		String username = dbProperties.getProperty("connection.username");
		String pass = dbProperties.getProperty("connection.password");
		String query = dbProperties.getProperty("sql.AddProduct");
		String queryId = dbProperties.getProperty("sql.getCategoryID");
		con = DriverManager.getConnection(dbUrl,username,pass);
		pStmt = con.prepareStatement(query);
		pStmtId = con.prepareStatement(queryId);
		System.out.println("Connection established");
	}
	
	public boolean addProduct(String productName,String productDescription,int productPrice,String productImageUrl) throws SQLException {
		pStmtId.setString(1, productImageUrl);
		rs = pStmtId.executeQuery();
		if(rs.next()) {
			int categoryId = rs.getInt("categoryId");
			
			pStmt.setString(1, productName);
			pStmt.setString(2, productDescription);
			pStmt.setInt(3, productPrice);
			pStmt.setString(4, productImageUrl);
			pStmt.setInt(5, categoryId);
			pStmt.executeUpdate();
			System.out.println("IN DAO"+productName+" "+productDescription+" "+productPrice+" "+productImageUrl);
			return true;
		}else {
			return false;
		}
		
		
	}
}
