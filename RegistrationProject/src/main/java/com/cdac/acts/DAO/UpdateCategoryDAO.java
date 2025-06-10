package com.cdac.acts.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UpdateCategoryDAO {
	Connection con = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	
	public UpdateCategoryDAO() throws ClassNotFoundException, IOException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties dbProperties = new Properties();
		dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

		String dbUrl = dbProperties.getProperty("connection.url");
		String username = dbProperties.getProperty("connection.username");
		String pass = dbProperties.getProperty("connection.password");
		String query = dbProperties.getProperty("sql.updateCategory");
		con = DriverManager.getConnection(dbUrl,username,pass);
		pStmt = con.prepareStatement(query);
	}
	
	public boolean updateCategory(int id, String categoryName, String categoryDescription, String categoryImageUrl) throws SQLException {
		pStmt.setString(1, categoryName);
		pStmt.setString(2, categoryDescription);
		pStmt.setString(3, categoryImageUrl);
		pStmt.setInt(4, id);
		pStmt.executeUpdate();
		return true;
		
	}
	
	public ResultSet getCategoryById(int categoryId) throws SQLException {
	    String query = "SELECT * FROM category WHERE categoryId = ?";
	    PreparedStatement stmt = con.prepareStatement(query);
	    stmt.setInt(1, categoryId);
	    rs = stmt.executeQuery(); 
	    return rs;
	}

}
