package com.cdac.acts.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdac.acts.POJO.Product;

/**
 * Servlet implementation class ListCart
 */
@WebServlet("/ListCart")
public class ListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("Form.html");
			return;
		}
		
		PrintWriter out = response.getWriter();
		Cart objCart = (Cart)session.getAttribute("cart");
		if(objCart == null) {
			out.println("Cart empty");
		}else {
			try {
				Iterator<Product> iter = objCart.getAllItems();
				out.println("<html>");
				out.println("<head>");
				out.println("<style>");
				out.println("body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 20px; }");
				out.println("h3 { text-align: center; color: #333; }");
				out.println("table { width: 80%; margin: 20px auto; border-collapse: collapse; background-color: white; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
				out.println("th, td { padding: 12px 15px; text-align: center; border: 1px solid #ddd; }");
				out.println("th { background-color: #4CAF50; color: white; }");
				out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
				out.println("a { display: inline-block; margin: 10px 5px; padding: 10px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; }");
				out.println("a:hover { background-color: #45a049; }");
				out.println(".total { text-align: center; font-size: 1.2em; color: #333; }");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3>Welcome, " + session.getAttribute("userName") + "</h3>");
				out.println("<div style='text-align:center;'>");
				out.println("<a href='Logout'>Logout</a>");
				
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<th>CatId</th>");
				out.println("<th>ProductId</th>");
				out.println("<th>Price</th>");
				out.println("<tr>");
				double total = 0.0;
				while(iter.hasNext()) {
					Product objProduct = iter.next();
					out.println("<tr>");
					out.println("<td>" +
					objProduct.getCategoryId()  +"</td>");
					out.println("<td>"+ objProduct.getProductId() + "</td>");
					out.println("<td>" + objProduct.getPrice()+ "</td>");
					out.println("</tr>");
					total+=objProduct.getPrice();
					
				}
				out.println("</table>");
				out.println("<div class='total'><h3>Total: " + total + "</h3></div>");
				out.println("<a href='Category'>Continue Shopping</a>");
				out.println("<a href='Payment.html'>Payment</a>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
				
				session.setAttribute("total", total);
			}catch(CartException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
