package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdac.acts.DAO.AuthenticateDAO;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
			if(userName.equals("dac") && password.equals("dac")) {
				out.println("<h1 style='color:green;'>Welcome to Shop Bazaar shopping site</h1>");
	
			}else {
				out.println("<h1 style='color:green;'>Invalid Username "+userName+" and Password "+ password+ "</h1>");
			}
		System.out.println("get");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			AuthenticateDAO adao = new AuthenticateDAO();
			boolean isValid = adao.isValidUser(userName, password);
			if(isValid) {
				//out.println("<h1 style='color:green;'>Welcome to Shop Bazaar shopping site</h1>");
				// Creating the session
				HttpSession session = request.getSession();
				session.setAttribute("userName",userName);
				response.sendRedirect(request.getContextPath()+"/Category");
			}else {
				out.println("<h1 style='color:red'>Invalid Input or Password</h1>");
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}

}