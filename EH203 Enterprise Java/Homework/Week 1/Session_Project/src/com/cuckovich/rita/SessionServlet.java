package com.cuckovich.rita;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionServlet
 */
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("INSIDE SessionServlet");
		
		// Request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        
        
        // Response
        
        Cookie cookie1 = new Cookie("firstName", firstName);
        Cookie cookie2 = new Cookie("lastName", lastName);
        
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        out.println("<html>");
        out.println("<head>");
        out.println("<title>SessionServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet SessionServlet at " + request.getContextPath () + "</h1>");
        out.println("<p>Welcome " + firstName + " " + lastName + "</p>");
        
        out.println("</body>");
        out.println("</html>");

        out.close(); 
        
        System.out.println("END of SessionServlet");
	}

}
