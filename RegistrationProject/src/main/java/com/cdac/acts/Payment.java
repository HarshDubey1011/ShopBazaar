package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdac.acts.DAO.AddCardDAO;
import com.cdac.acts.DAO.TransactionDAO;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(false);
			if(session==null) {
				response.sendRedirect("Form.html");
				return;
			}
			AddCardDAO acd = new AddCardDAO();
			TransactionDAO td  = new TransactionDAO();
			PrintWriter out = response.getWriter();
			
			int card_number = Integer.parseInt(request.getParameter("cardNumber"));
			int cvv = Integer.parseInt(request.getParameter("CVV"));
			LocalDate date = LocalDate.parse(request.getParameter("Expiry"));
			String name = request.getParameter("Name");
			double bal = (double) session.getAttribute("total");
			int userId = (int) session.getAttribute("userId");
			System.out.println("Payment: "+card_number);
			System.out.println("Payment: "+cvv);
			System.out.println("Payment: "+date);
			System.out.println("Payment: "+bal);
			System.out.println("Payment: "+userId);
			boolean validCard = acd.validateCard(card_number, cvv, date);
			boolean sufficientBal = acd.checkBalance(cvv, bal);
			System.out.println("Payment: "+validCard);
			System.out.println("Payment: "+sufficientBal);
			
			if(validCard && sufficientBal) {
				acd.updateBalance(cvv, bal);
				td.addTransaction(userId, bal, date.toString(), card_number, "Success");
				//response.sendRedirect(request.getContextPath()+"/Category");
				response.sendRedirect(request.getContextPath()+"/TransactionResult.jsp?card_number="+card_number);
			}else {
				out.println("<h1>Card Number is invalid  or Balance is insufficient</h1>");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
