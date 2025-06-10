package com.cdac.acts.Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cdac.acts.DAO.AddProductDAO;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			AddProductDAO apd = new AddProductDAO();
			PrintWriter out  = response.getWriter();
			String productName = request.getParameter("productName");
			String productDescription = request.getParameter("productDescription");
			int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			String productImageUrl = request.getParameter("productImageUrl");
			System.out.println(productName+" "+productDescription+" "+productPrice+" "+productImageUrl);
			boolean addCategory = apd.addProduct(productName, productDescription, productPrice, productImageUrl);
			System.out.println(addCategory);
			if(addCategory) {
				response.sendRedirect("/Category");
			}else {
				out.println("<h1 style='color:red'>Invalid Input or Password</h1>");
			}
		}catch(Exception e) {
			System.out.println("without dao"+e.getMessage());
		}
	}

}
