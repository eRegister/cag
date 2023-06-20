package eh102.linkedlist;

public class LinkedListTest1 {
	public static void main(String[] args) {	
		LinkedListNode1 first = new LinkedListNode1();
		first.setData("Hello");
		
		LinkedListNode1 second = new LinkedListNode1();
		second.setData("World");		
		first.setNext(second);
		
		LinkedListNode1 third = new LinkedListNode1();
		third.setData("!!!");
		second.setNext(third);
		
		LinkedListNode1 current = first;
		while (current != null) {
			System.out.println(current.getData());
			
			current = current.getNext();
		}
	}
}
