package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.acts.DAO.ProductDAO;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		ProductDAO pd = new ProductDAO();
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
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Description</th>");
		out.println("<th>Price</th>");
		out.println("<th>Image</th>");
		out.println("</tr>");
		
		
			String tempId = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(tempId);
			
			ResultSet rs = pd.validProduct(categoryId);
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getString("productName") + "</td>");
				out.println("<td>" + rs.getString("productDescription")+"</td>");
				out.println("<td>"+rs.getInt("productPrice") + "</td>");
				out.println("<td><img src='Images/" + rs.getString("ProductImageUrl") + "' height='100px' width='100px'/></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
 		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
