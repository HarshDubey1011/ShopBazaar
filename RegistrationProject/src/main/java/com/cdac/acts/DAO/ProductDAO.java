package com.cdac.acts.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ProductDAO {
	Connection con = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	
	public ProductDAO() throws ClassNotFoundException, IOException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties dbProperties = new Properties();
		dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

		String dbUrl = dbProperties.getProperty("connection.url");
		String username = dbProperties.getProperty("connection.username");
		String pass = dbProperties.getProperty("connection.password");
		String query = dbProperties.getProperty("sql.query");
		con= DriverManager.getConnection(dbUrl,username,pass);
		
		pStmt = con.prepareStatement(query);
	}
	
	public ResultSet validProduct(int categoryId) throws SQLException {
		pStmt.setInt(1, categoryId);
		rs = pStmt.executeQuery();
		return rs;
	}
}
