package com.cdac.acts;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.io.PrintWriter;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Enumeration<String> headerNames = request.getHeaderNames();
		out.println("<html>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Value</th>");
		out.println("</tr>");
		
		while(headerNames.hasMoreElements()) {
			String nameHeader = headerNames.nextElement();
			String valueHeader = request.getHeader(nameHeader);
			out.println("<tr>");
			out.println("<td>" + nameHeader + "</td>");
			out.println("<td>"+valueHeader+"</td>");
			out.println("</tr>");
		}
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
