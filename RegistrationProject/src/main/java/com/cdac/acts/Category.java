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

import com.cdac.acts.DAO.CategoryDAO;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		CategoryDAO cad = new CategoryDAO();
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; }");
		out.println("table { width: 80%; margin: auto; border-collapse: collapse; background: white; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
		out.println("th, td { padding: 12px 15px; border: 1px solid #ddd; text-align: center; }");
		out.println("th { background-color: #4CAF50; color: white; }");
		out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
		out.println("img { border-radius: 8px; }");
		out.println(".edit-btn, .delete-btn { padding: 8px 14px; border: none; border-radius: 5px; font-weight: bold; cursor: pointer; }");
		out.println(".edit-btn { background-color: #2196F3; color: white; }");
		out.println(".edit-btn:hover { background-color: #0b7dda; }");
		out.println(".delete-btn { background-color: #f44336; color: white; }");
		out.println(".delete-btn:hover { background-color: #d32f2f; }");
		out.println("</style>");

		out.println("</head>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Description</th>");
		out.println("<th>Image</th>");
		out.println("<th>Update</th>");
		out.println("<th>Delete</th>");
		out.println("</tr>");
			ResultSet rs = cad.isValid();
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td><a href='Products?categoryId=" + rs.getInt("categoryId") + "'>" + rs.getString("categoryName") + "</a></td>");
				out.println("<td>"+rs.getString("categoryDescription")+ "</td>");
				out.println("<td><img src='Images/" + rs.getString("categoryImageUrl") + "' height='60px' width='60px' /></td>");
				out.println("<td><a href='UpdateCategory?categoryId=" + rs.getInt("categoryId") + "'><button class='edit-btn'>Edit</button></a></td>");
				out.println("<td><a href='DeleteCategory?categoryId=" + rs.getInt("categoryId") + "'><button class='delete-btn'>Delete</button></a></td>");

				out.println("</tr>");
			}
			
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage()+"Jar or Bin file not found!");
			e.printStackTrace();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
