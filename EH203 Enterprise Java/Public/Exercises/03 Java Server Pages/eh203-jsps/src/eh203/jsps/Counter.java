package eh203.jsps;

/**
 * Simple thread-safe counter class
 */
public class Counter {
	private static int count;
	
	/**
	 * Increments and returns the current counter value
	 * @return the incremented counter value
	 */
	public static synchronized int getCount() {
		return (++count);
	}
}
