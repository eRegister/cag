package eh203.userform;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Exercise02
 */
public class UserForm5 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getUser(request);
		
		if (request.getParameter("edit") != null)
			request.getRequestDispatcher("/userform5_edit.jsp").forward(request, response);
		else
			request.getRequestDispatcher("/userform5_view.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("user_name");
		int age = Integer.parseInt(request.getParameter("user_age"));
		boolean admin = Boolean.parseBoolean(request.getParameter("user_admin"));
		String work = request.getParameter("user_work");
		String home = request.getParameter("user_home");
		
		User2 user = getUser(request);
		
		user.setName(name);
		user.setAge(age);
		user.setAdmin(admin);	
		user.setEmailAddress("Work", work);
		user.setEmailAddress("Home", home);
		
		doGet(request, response);
	}
	
	protected User2 getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User2 user = (User2)session.getAttribute("curUser");
		
		if (user == null) {
			user = new User2("Bob Smith", 28, true);
			user.setEmailAddress("Home", "bobsmith@yahoo.com");
			user.setEmailAddress("Work", "bsmith@ehsdi.com");
			
			session.setAttribute("curUser", user);
		}
		
		return user;
	}
}
