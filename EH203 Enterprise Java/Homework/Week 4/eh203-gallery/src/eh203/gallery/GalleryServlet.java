package eh203.gallery;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class GalleryServlet
 */
public class GalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gallery = request.getParameter("name");
		ArrayList<GalleryEntry> images = new ArrayList<GalleryEntry>();
		
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".jpg");
			}
		};
		File dir = new File(getServletContext().getRealPath("/gallery/" + gallery));
		for (String child : dir.list(filter)) {
			
			// Generate thumbnail if it doesn't exist
			String imagePath = dir.getPath() + "/" + child;
			String thumbPath = dir.getPath() + "/thumbs/" + child;
			if (!new File(thumbPath).exists())
				generateThumbnail(imagePath, thumbPath);
			
			images.add(new GalleryEntry(child, child));
		}
		
		request.setAttribute("gallery", gallery);
		request.setAttribute("images", images);
		
		request.getRequestDispatcher("/WEB-INF/gallery.jsp").forward(request, response);
	}

	/**
	 * @param imagePath path of existing image to resize
	 * @param thumbPath path of thumbnail image to create
	 * @throws IOException
	 * @throws FileNotFoundException if image doesn't exist
	 */
	private void generateThumbnail(String imagePath, String thumbPath)
			throws IOException, FileNotFoundException {
		BufferedImage img = ImageIO.read(new File(imagePath));
		Image timg = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		BufferedImage bimg = new BufferedImage(timg.getWidth(null), timg.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bimg.getGraphics().drawImage(timg, 0, 0, null);	
		FileOutputStream out = new FileOutputStream(thumbPath);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bimg);
		out.close();
	}
}
