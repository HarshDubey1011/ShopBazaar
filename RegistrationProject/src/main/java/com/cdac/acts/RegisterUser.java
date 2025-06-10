package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.acts.DAO.RegisterationDAO;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			RegisterationDAO rd = new RegisterationDAO();
			boolean isValidUser = rd.isValidUser(userName, password);
			if(isValidUser) {
				//out.println("<h1 style='color:green;'>Welcome to Shop Bazaar shopping site</h1>");
				request.getRequestDispatcher("Authenticate").forward(request, response);
			}else {
				out.println("<h1 style='color:red'>Invalid Input or Password</h1>");
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
