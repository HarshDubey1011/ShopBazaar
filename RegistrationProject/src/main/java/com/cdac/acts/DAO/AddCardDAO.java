package com.cdac.acts.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Properties;

public class AddCardDAO {
	Connection con = null;
	PreparedStatement pStmt = null;
	PreparedStatement pStmtCheckBal = null;
	ResultSet rs = null;
	ResultSet rsCheckBal = null;
	PreparedStatement pStmtUpdate = null;
	ResultSet rsUpdate=null;
	double balance = 0;
	
	public AddCardDAO() throws ClassNotFoundException, IOException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties dbProperties = new Properties();
		dbProperties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

		String dbUrl = dbProperties.getProperty("connection.url");
		String username = dbProperties.getProperty("connection.username");
		String pass = dbProperties.getProperty("connection.password");
		String validateCard = dbProperties.getProperty("sql.validateCard");
		String checkBalance = dbProperties.getProperty("sql.checkBalance");
		String updateBalance = dbProperties.getProperty("sql.updateBalance");
		con = DriverManager.getConnection(dbUrl,username,pass);
		pStmt = con.prepareStatement(validateCard);
		pStmtCheckBal = con.prepareStatement(checkBalance);
		pStmtUpdate=con.prepareStatement(updateBalance);
		
	}
	
	public boolean validateCard(int card_number,int cvv, LocalDate expiry) throws SQLException {
		
	pStmt.setInt(1, card_number);
	pStmt.setInt(2, cvv);
	pStmt.setString(3, expiry.toString());
	
	rs = pStmt.executeQuery();
	while(rs.next()) {
		int cardNumber = rs.getInt("card_number");
		int cvvNo = rs.getInt("cvv");
		String date = rs.getString("expiry");
		System.out.println("validateCard_cardNumber: "+card_number);
		System.out.println("validateCard_cvv: "+cvvNo);
		System.out.println("validateCard_date: "+date);
		
		if(cardNumber==card_number && cvv==cvvNo && expiry.toString().equals(date)) {
			System.out.println("Card Successfully validated");
			return true;
		}
	}
		return false;
	}
	
	public boolean checkBalance(int cvv,double bal) throws SQLException {
		pStmtCheckBal.setInt(1, cvv);
		rsCheckBal = pStmtCheckBal.executeQuery();
		
		while(rsCheckBal.next()) {
			balance = rsCheckBal.getDouble("balance");
			System.out.println("validate_card_balance: "+balance);
			
		}
		
		if(balance >=bal) {
			System.out.println("balance is correct");
			return true;
		}
		return false;
		
	}
	
	public void updateBalance(int cvv,double bal) throws SQLException {
		bal = balance - bal;
		pStmtUpdate.setDouble(1, bal);
		pStmtUpdate.setInt(2, cvv);
		pStmtUpdate.executeUpdate();
		System.out.println("balance updated successfully!");
		
	}
	
	
}

