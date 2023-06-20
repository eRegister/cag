package eh102.linkedlist;

public class LinkedListNode4<T> {
	private T data;
	private LinkedListNode4<T> next;

	/**
	 * Gets the data item for this node 
	 * @return the data item
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data item for this node
	 * @param data the data item
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Gets the next node in the list, or null if this is the last node 
	 * @return the next node
	 */
	public LinkedListNode4<T> getNext() {
		return next;
	}

	/**
	 * Sets the next node in the list
	 * @param next the next node
	 */
	public void setNext(LinkedListNode4<T> next) {
		this.next = next;
	}
}
