package com.cuckovich.rita;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet2
 */
public class SessionServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstName = "";
		String lastName = "";
		
		// Request
        String remove = request.getParameter("remove");
        
        if(remove.equals("yes")) {
			for (Cookie cookie : request.getCookies()) {
				  if (cookie.getName().equals("firstName")) {
					  firstName = cookie.getValue();
					  cookie.setMaxAge(0); // seconds
					  response.addCookie(cookie);
				  } else if(cookie.getName().equals("lastName")) {
					  lastName = cookie.getValue();
					  cookie.setMaxAge(0); // seconds
					  response.addCookie(cookie);
				  }
			}
        }
		
		HttpSession session = request.getSession();
		
		session.setAttribute("firstName", firstName);
		session.setAttribute("lastName", lastName);
		
		response.sendRedirect("SessionServlet3");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
