package eh203.jstl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Guestbook1
 */
public class Guestbook5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Entry> entries = new ArrayList<Entry>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("entries", entries);
		
		request.getRequestDispatcher("/guestbook5.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String comment = request.getParameter("comment");
		Entry entry = new Entry(name, email, new Date(), comment);
		
		entries.add(entry);
		
		doGet(request, response);
	}

}
