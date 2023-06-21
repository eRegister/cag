package eh203.gallery;

public class GalleryEntry {
	protected String image;
	protected String name;
	
	public GalleryEntry() {
	}
	
	/**
	 * @param image
	 * @param name
	 */
	public GalleryEntry(String image, String name) {
		this.image = image;
		this.name = name;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
