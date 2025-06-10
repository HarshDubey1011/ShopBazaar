package com.cdac.acts.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CategoryDAO {
	Connection con = null;
	PreparedStatement psStmt = null;
	ResultSet rs = null;
	public CategoryDAO() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties dbProperties = new Properties();
		dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

		String dbUrl = dbProperties.getProperty("connection.url");
		String username = dbProperties.getProperty("connection.username");
		String pass = dbProperties.getProperty("connection.password");
		String query = dbProperties.getProperty("connection.queryCategory");
		System.out.println(pass);
		System.out.println(query);
		con = DriverManager.getConnection(dbUrl,username,pass);
		System.out.println("Connection Established");
		psStmt = con.prepareStatement(query);
	
	}
	
	public ResultSet isValid() throws SQLException {
		rs = psStmt.executeQuery();
		return rs;
	}
}
