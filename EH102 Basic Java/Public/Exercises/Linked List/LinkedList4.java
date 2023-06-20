package eh102.linkedlist;

public class LinkedList4<T> {
	protected LinkedListNode4<T> first;
	protected LinkedListNode4<T> last;
	
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
	}
	
	/**
	 * Gets the first node in this list
	 * @return the first node
	 */
	public LinkedListNode4<T> getFirst() {
		return first;
	}
}
