package eh203.sessions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class First
 */
public class Exercise01a extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Exercise01a() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		if (name == null)
			name = "";
		
		out.print("<html><body>");
		out.print("<h1>Sessions 1</h1>");
		out.print("<form method='post'>");
		out.print("<input type='text' name='name' value='" + name + "' />");
		out.print("<input type='submit' value='Save' />");
		out.print("</form>");
		out.print("<p><a href='exercise01b'>Second page</a></p>");
		out.print("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		session.setAttribute("name", name);

		doGet(request, response);
	}
}
