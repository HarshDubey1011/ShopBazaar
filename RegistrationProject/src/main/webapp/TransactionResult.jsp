<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.cdac.acts.DAO.TransactionDAO,java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transaction Details</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 40px;
        }

        h3 {
            text-align: center;
            color: #333;
        }

        table {
            width: 60%;
            margin: auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
            text-align: center;
        }

        td:first-child {
            font-weight: bold;
            background-color: #f9f9f9;
        }

        p {
            text-align: center;
            color: red;
            font-size: 1.2em;
        }
    </style>
</head>
<body>
    <%
        int card_number = Integer.parseInt(request.getParameter("card_number"));
        TransactionDAO td = new TransactionDAO();
        ResultSet rs = td.getTransaction(card_number);

        int txn_id = 0;
        int user_id = 0;
        double amount = 0;
        String timeStamp = "";
        int card_no = 0;
        String status = "";
        
        if (rs.next()) {
            txn_id = rs.getInt("txn_id");
            user_id = rs.getInt("user_id");
            amount = rs.getDouble("amount");
            timeStamp = rs.getDate("timestamp").toString();
            card_no = rs.getInt("card_number");
            status = rs.getString("status");
    %>
        <h3>Transaction Details</h3>
        <table>
            <tr><td>Transaction ID</td><td><%= txn_id %></td></tr>
            <tr><td>User ID</td><td><%= user_id %></td></tr>
            <tr><td>Amount</td><td>₹<%= amount %></td></tr>
            <tr><td>Timestamp</td><td><%= timeStamp %></td></tr>
            <tr><td>Card Number</td><td><%= card_no %></td></tr>
            <tr><td>Status</td><td><%= status %></td></tr>
        </table>
    <%
        } else {
    %>
        <p>No transaction found for card number: <%= card_number %></p>
    <%
        }
    %>
</body>
</html>
