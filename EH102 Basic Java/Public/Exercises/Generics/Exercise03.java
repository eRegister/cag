package generics;

/**
 * Simple pair of objects class
 * @param <K> type of the first object
 * @param <T> type of the second object
 */
class Pair<K, T> {
	protected K first;
	protected T second;
	
	/**
	 * Constructor
	 * @param first object
	 * @param second object
	 */
	public Pair(K first, T second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Gets the first object
	 * @return the first object
	 */
	public K getFirst() {
		return first;
	}
	
	/**
	 * Gets the second object
	 * @return the second object
	 */
	public T getSecond() {
		return second;
	}
}

public class Exercise03 {
	public static void main(String[] args) {
		Pair<String, Integer> p = new Pair<String, Integer>("Hello", 77);
		
		printPair(p);
	}
	
	/**
	 * Print out a pair
	 * @param p the pair to print
	 */
	public static void printPair(Pair<?, ?> p) {
		System.out.println(p.getFirst() + ", " + p.getSecond());
	}
}
