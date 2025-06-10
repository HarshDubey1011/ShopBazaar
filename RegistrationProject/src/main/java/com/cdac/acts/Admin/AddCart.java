package com.cdac.acts.Admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdac.acts.POJO.Product;
import com.cdac.acts.cart.Cart;
import com.cdac.acts.cart.ShoppingCart;

/**
 * Servlet implementation class AddCard
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	if (session == null) {
    		response.sendRedirect("Form.html");
    		return;
    	}

    	int categoryId = Integer.parseInt(request.getParameter("categoryId"));
    	int productId = Integer.parseInt(request.getParameter("productId"));
    	int price = Integer.parseInt(request.getParameter("price"));

    	Product objProduct = new Product(categoryId, productId, 1, price);

    	Cart objCart = (Cart) session.getAttribute("cart");
    	if (objCart == null) {
    		objCart = new ShoppingCart();
    		session.setAttribute("cart", objCart);
    	}

    	objCart.addToCart(objProduct);

    	response.sendRedirect("ListCart");
    }


}
