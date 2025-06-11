package com.cdac.acts.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

public class AuthenticateDAO {
	Connection con = null;
	PreparedStatement psStmt = null;
	PreparedStatement pStmt = null;
	ResultSet rsId = null;
	ResultSet rs = null;
	
	public AuthenticateDAO() throws IOException, SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties dbProperties = new Properties();
			dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

			String dbUrl = dbProperties.getProperty("connection.url");
			String username = dbProperties.getProperty("connection.username");
			String pass = dbProperties.getProperty("connection.password");
			String query = dbProperties.getProperty("connection.queryGet");
			String queryGet = dbProperties.getProperty("sql.getUserId");
			con = DriverManager.getConnection(dbUrl,username,pass);
			psStmt = con.prepareStatement(query);
			pStmt = con.prepareStatement(queryGet);
	}
	
	public boolean isValidUser(String userName, String password) throws SQLException {
		psStmt.setString(1, userName);
		psStmt.setString(2, password);
		rs = psStmt.executeQuery();
		boolean isValid = false;
		if(rs.next()) {
			isValid = true;
		}
		return isValid;
	}
	
	public int getUserId(String userName) throws SQLException {
		pStmt.setString(1, userName);
		rsId = pStmt.executeQuery();
		
		if(rsId.next()) {
			int userId = rs.getInt("id");
			return userId;
		}
		return -1;
		
	}
}
