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
public class UserForm1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User1 user = new User1("Bob Smith", 28, true);
		
		request.setAttribute("curUser", user);
		
		RequestDispatcher view = request.getRequestDispatcher("/userform1.jsp");
		view.forward(request, response);
	}
}
