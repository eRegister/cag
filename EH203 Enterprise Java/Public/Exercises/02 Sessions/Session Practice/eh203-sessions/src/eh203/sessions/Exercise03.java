package eh203.sessions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();	
		
		String bgcolor = "#FFFFFF";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("bgcolor")) {
				bgcolor = cookie.getValue();
				break;
			}
		}
		
		String[] optNames = new String[]{ "White", "Pink", "Green" };
		String[] optVals = new String[]{ "#FFFFFF", "#FFDDDD", "#DDFFDD" };
		
		out.print("<html><body style='background-color: " + bgcolor + "'>");
		out.print("<h1>Cookies</h1>");
		out.print("<form method='post'>");
		out.print("Select theme: <select name='bgcolor'>");
		
		for (int i = 0; i < optNames.length; i++) {
			out.print("<option value='" + optVals[i] + "' "
					+ (bgcolor.equals(optVals[i]) ? "selected" : "") + ">"
					+ optNames[i] + "</option>");
		}
		
		out.print("</select>");
		out.print("<input type='submit' value='Set'>");
		out.print("</form>");
		out.print("</body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String bgcolor = request.getParameter("bgcolor");
		
		Cookie cookie = new Cookie("bgcolor", bgcolor);
		cookie.setMaxAge(10000); // seconds
		response.addCookie(cookie);

		response.sendRedirect("exercise03");
	}
}
