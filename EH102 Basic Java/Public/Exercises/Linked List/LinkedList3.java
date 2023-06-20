package eh102.linkedlist;

public class LinkedList3 {
	protected LinkedListNode2 first;
	protected LinkedListNode2 last;
	
	/**
	 * Adds an object to the list
	 * @param obj
	 */
	public void add(Object obj) {
		LinkedListNode2 node = new LinkedListNode2();
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
	public LinkedListNode2 getFirst() {
		return first;
	}
}
