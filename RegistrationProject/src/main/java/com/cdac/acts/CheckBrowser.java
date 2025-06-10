package com.cdac.acts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckBrowser
 */
@WebServlet("/CheckBrowser")
public class CheckBrowser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBrowser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userAgent = request.getHeader("user-agent");
		System.out.println("userAgent"+userAgent);
		PrintWriter out = response.getWriter();
		if(userAgent.indexOf("Firefox")!=-1) {
			out.println("You are using Mozilla Firefox");
		} else if(userAgent.indexOf("Edge")!=-1) {
			out.println("You are using Microsoft Edge");
		} else if(userAgent.indexOf("Chrome")!=-1) {
			out.println("You are using Chrome");
		} else {
			out.println("You are using a non standard browser");
		}
	}
}
