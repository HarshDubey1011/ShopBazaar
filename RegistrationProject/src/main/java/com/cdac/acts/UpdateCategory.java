package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.acts.DAO.UpdateCategoryDAO;

/**
 * Servlet implementation class UpdateCategory
 */
@WebServlet("/UpdateCategory")
public class UpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		try {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			UpdateCategoryDAO dao = new UpdateCategoryDAO();
			ResultSet rs = dao.getCategoryById(categoryId);
		   
			
			PrintWriter out = response.getWriter();
			if(rs.next()) {
				 	String name = rs.getString("categoryName");
				    String desc = rs.getString("categoryDescription");
				    String imageUrl = rs.getString("categoryImageUrl");
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("  <meta charset=\"UTF-8\">");
				out.println("  <title>Update Category</title>");
				out.println("  <style>");
				out.println("    body {");
				out.println("      font-family: Arial, sans-serif;");
				out.println("      background: #f5f7fa;");
				out.println("      display: flex;");
				out.println("      justify-content: center;");
				out.println("      align-items: center;");
				out.println("      height: 100vh;");
				out.println("    }");
				out.println("");
				out.println("    form {");
				out.println("      background: #ffffff;");
				out.println("      padding: 30px 40px;");
				out.println("      border-radius: 10px;");
				out.println("      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);");
				out.println("      width: 350px;");
				out.println("    }");
				out.println("");
				out.println("    form label {");
				out.println("      display: block;");
				out.println("      margin-bottom: 6px;");
				out.println("      font-weight: bold;");
				out.println("      color: #333;");
				out.println("    }");
				out.println("");
				out.println("    form input[type=\"text\"] {");
				out.println("      width: 100%;");
				out.println("      padding: 8px 10px;");
				out.println("      margin-bottom: 15px;");
				out.println("      border: 1px solid #ccc;");
				out.println("      border-radius: 5px;");
				out.println("      box-sizing: border-box;");
				out.println("    }");
				out.println("");
				out.println("    form input[type=\"submit\"] {");
				out.println("      width: 100%;");
				out.println("      padding: 10px;");
				out.println("      background-color: #4CAF50;");
				out.println("      color: white;");
				out.println("      border: none;");
				out.println("      border-radius: 5px;");
				out.println("      font-size: 16px;");
				out.println("      cursor: pointer;");
				out.println("      transition: background-color 0.3s ease;");
				out.println("    }");
				out.println("");
				out.println("    form input[type=\"submit\"]:hover {");
				out.println("      background-color: #45a049;");
				out.println("    }");
				out.println("  </style>");
				out.println("</head>");
				out.println("<body>");
			    out.println("<form action='UpdateCategory' method='POST'>");
			    out.println("<input type='hidden' name='categoryId' value='" + categoryId + "' />");
			    out.println("<label for='categoryName'>Category Name:</label>");
			    out.println("<input type='text' name='categoryName' value='" + name + "' required /><br>");
			    out.println("<label for='categoryDescription'>Category Description:</label>");
			    out.println("<input type='text' name='categoryDescription' value='" + desc + "' required /><br>");
			    out.println("<label for='categoryImageUrl'>Category Image URL:</label>");
			    out.println("<input type='text' name='categoryImageUrl' value='" + imageUrl + "' required /><br>");
			    out.println("<input type='submit' value='Update Category' />");
			    out.println("</form>");
				out.println("</body>");
				out.println("</html>");
		}else {
			 out.println("<h3>No category found!</h3>");
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UpdateCategoryDAO ud = new UpdateCategoryDAO();

			String tempId = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");
			String categoryDescription = request.getParameter("categoryDescription");
			String categoryImageUrl = request.getParameter("categoryImageUrl");

			int categoryId = Integer.parseInt(tempId);

			boolean updated = ud.updateCategory(categoryId, categoryName, categoryDescription, categoryImageUrl);
			if (updated) {
				// FIX HERE TOO:
				response.sendRedirect(request.getContextPath() + "/Category");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<h3 style='color:red'>Update failed!</h3>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
