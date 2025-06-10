package com.cdac.acts.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.acts.DAO.AddCategoryDAO;

/**
 * Servlet implementation class AddCategory
 */
@WebServlet("/AddCategory")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			AddCategoryDAO acd = new AddCategoryDAO();
			PrintWriter out  = response.getWriter();
			String categoryName = request.getParameter("categoryName");
			String categoryDescription = request.getParameter("categoryDescription");
			String categoryImageUrl = request.getParameter("categoryImageUrl");
			
			boolean isValid = acd.addCategory(categoryName, categoryDescription, categoryImageUrl);
			if(isValid) {
				response.sendRedirect("Category");
			}else {
				out.println("<h1 style='color:red'>Invalid Input or Password</h1>");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
