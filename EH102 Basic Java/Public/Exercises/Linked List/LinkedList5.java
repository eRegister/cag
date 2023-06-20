package eh102.linkedlist;

public class LinkedList5<T> {
	protected LinkedListNode4<T> first;
	protected LinkedListNode4<T> last;
	protected int size = 0;
	
	/**
	 * Adds an object to the list
	 * @param obj
	 */
	public void add(T obj) {
		LinkedListNode4<T> node = new LinkedListNode4<T>();
		node.setData(obj);
		
		// If first node is null, list is currently empty
		if (first == null)
			first = node;	
		
		if (last != null)
			last.setNext(node);
		
		last = node;
		size++;
	}
	
	/**
	 * Gets the first node in this list
	 * @return the first node
	 */
	public LinkedListNode4<T> getFirst() {
		return first;
	}
	
	/**
	 * Gets the size of the list
	 * @return the size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Gets the element at the given position
	 * @param index the list position
 	 * @return the element
	 */
	public T get(int index) {
		LinkedListNode4<T> current = first;
		int count = 0;
		while (current != null) {
			if (count == index)
				return current.getData();
			current = current.getNext();
			count++;
		}
		return null;
	}
}
