package eh203.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<User> users = new ArrayList<User>();
	
	/**
	 * Called when servlet is being initialized - overridden here to load
	 * quiz data from a file
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String dataFile = config.getInitParameter("dataFile");	
		ServletContext srvCtx = config.getServletContext();
		InputStream in = srvCtx.getResourceAsStream(dataFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		try {		
			// Read the user profiles
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split("\\|");
				users.add(new User(tokens[0], tokens[1], tokens[2]));
			}	
			reader.close();
		} catch (IOException e) {
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("logout") != null)
			request.getSession().invalidate();
		
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String redirect = request.getParameter("redirect");
		User authenticatedUser = null;
		
		for (User user : users) {
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				authenticatedUser = user;
				break;
			}
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("authenticatedUser", authenticatedUser);
		
		if (redirect != null && redirect.length() > 0)
			response.sendRedirect(redirect);
		else
			doGet(request, response);
	}

}
