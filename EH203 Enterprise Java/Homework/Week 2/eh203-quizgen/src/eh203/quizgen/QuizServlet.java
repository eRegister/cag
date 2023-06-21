package eh203.quizgen;

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

/**
 * Servlet for displaying and checking a quiz
 */
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String title;
	private ArrayList<QuizQuestion> questions = new ArrayList<QuizQuestion>();

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
			// Read quiz title
			String line = reader.readLine();
			title = line.substring(2);
			
			// Read the questions
			QuizQuestion qq = null;
			while ((line = reader.readLine()) != null) {
				if (line.charAt(0) == 'Q') {
					qq = new QuizQuestion(line.substring(2));
					questions.add(qq);
				}
				else {
					qq.addOption(line.substring(2));
					
					if (line.charAt(0) == 'A')
						qq.setCorrectOption(qq.getOptions().size() - 1);
				}
			}		
		} catch (IOException e) {
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("title", title);
		request.setAttribute("questions", questions);
		
		request.getRequestDispatcher("/quiz.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Read quiz answers from request
		int[] answers = new int[questions.size()];
		boolean complete = true;
		for (int i = 0; i < questions.size(); i++) {
			String ans = request.getParameter("a" + (i + 1));
			if (ans == null) {
				request.setAttribute("error", "Some answers missing");
				answers[i] = -1;
				complete = false;
			}
			else
				answers[i] = Integer.parseInt(ans) - 1;
		}
		
		// Calculate score
		if (complete) {
			int score = 0;
			for (int i = 0; i < questions.size(); i++) {
				if (questions.get(i).getCorrectOption() == answers[i])
					score++;
			}
			
			request.setAttribute("score", score);
		}
		
		doGet(request, response);
	}

}
