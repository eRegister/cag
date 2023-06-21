package eh203.sessions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class First
 */
public class Exercise02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Exercise02() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		HttpSession session = request.getSession();
		
		out.print("<html><body>");
		out.print("<h1>Sessions 2</h1>");
		out.print("<p>New: " + (session.isNew() ? "true" : "false") + "</p>");
		out.print("<p>Created: " + millisToString(session.getCreationTime()) + "</p>");
		out.print("<p>Accessed: " + millisToString(session.getLastAccessedTime()) + "</p>");
		out.print("<p>Timeout: " + session.getMaxInactiveInterval() + " seconds</p>");
		out.print("<p>ID: " + session.getId() + "</p>");
		out.print("<p><a href='exercise02'>Refresh</a></p>");
		out.print("<form method='post'><input type='submit' value='Invalidate'></form>");
		out.print("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate();

		doGet(request, response);
	}
	
	private String millisToString(long millis) {
		Date d = new Date(millis);
		return SimpleDateFormat.getTimeInstance().format(d);
	}
}
