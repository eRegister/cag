package eh203.gallery;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GalleryList
 */
public class GalleryList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> galleries = new ArrayList<String>();
		File dir = new File(getServletContext().getRealPath("/gallery"));
		for (String child : dir.list())
			galleries.add(child);
		
		request.setAttribute("galleries", galleries);
		
		request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
	}

}
