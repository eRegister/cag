package eh102.linkedlist;

public class LinkedListNode2 {
	private Object data;
	private LinkedListNode2 next;

	/**
	 * Gets the data item for this node 
	 * @return the data item
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data item for this node
	 * @param data the data item
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * Gets the next node in the list, or null if this is the last node 
	 * @return the next node
	 */
	public LinkedListNode2 getNext() {
		return next;
	}

	/**
	 * Sets the next node in the list
	 * @param next the next node
	 */
	public void setNext(LinkedListNode2 next) {
		this.next = next;
	}
}
