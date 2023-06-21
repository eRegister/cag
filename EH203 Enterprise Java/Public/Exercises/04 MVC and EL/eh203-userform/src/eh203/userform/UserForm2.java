package eh203.userform;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exercise01
 */
public class UserForm2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User2 user = new User2("Bob Smith", 28, true);
		user.setEmailAddress("Home", "bobsmith@yahoo.com");
		user.setEmailAddress("Work", "bsmith@ehsdi.com");
		
		request.setAttribute("curUser", user);
		
		RequestDispatcher view = request.getRequestDispatcher("/userform2.jsp");
		view.forward(request, response);
	}
}
