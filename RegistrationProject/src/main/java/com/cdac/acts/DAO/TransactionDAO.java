package com.cdac.acts.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import java.sql.Date;

public class TransactionDAO {
	Connection con = null;
	PreparedStatement pStmt = null;
	PreparedStatement pStmtGet = null;
	ResultSet rs = null;
	ResultSet rsGet = null;
	
	public TransactionDAO() throws ClassNotFoundException, IOException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties dbProperties = new Properties();
		dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

		String dbUrl = dbProperties.getProperty("connection.url");
		String username = dbProperties.getProperty("connection.username");
		String pass = dbProperties.getProperty("connection.password");
		String query = dbProperties.getProperty("sql.insertTrasaction");
		String queryGet = dbProperties.getProperty("sql.getTransaction");
		con = DriverManager.getConnection(dbUrl,username,pass);
		pStmt = con.prepareStatement(query);
		pStmtGet = con.prepareStatement(queryGet);
	}
	
	public boolean addTransaction(int user_id,double amount,String timeStamp, int card_number, String status) throws SQLException, ParseException {
		//Date newDate = getStringToDate(timeStamp);
		pStmt.setInt(1, user_id);
		pStmt.setDouble(2, amount);
		pStmt.setString(3, timeStamp);
		pStmt.setInt(4, card_number);
		pStmt.setString(5, status);
		pStmt.executeUpdate();
		
		return true;
	}
	
	public ResultSet getTransaction(int card_number) throws SQLException {
		pStmtGet.setInt(1, card_number);
		rsGet = pStmtGet.executeQuery();
		return rsGet;
	}
	
	public Date getStringToDate(String Date) throws ParseException {
			String dateString = "2023-10-01"; // Example date string
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	            // Parse the string into a java.util.Date
            Date parsedDate = (Date) format.parse(dateString);
            // Convert java.util.Date to java.sql.Date
            Date sqlDate = new Date(parsedDate.getTime());
            return sqlDate;
	        
	}
}

