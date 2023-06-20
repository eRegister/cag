package eh102.linkedlist;

public class LinkedListTest2 {
	public static void main(String[] args) {	
		LinkedListNode2 first = new LinkedListNode2();
		first.setData("Hello");
		
		LinkedListNode2 second = new LinkedListNode2();
		second.setData(true);		
		first.setNext(second);
		
		LinkedListNode2 third = new LinkedListNode2();
		third.setData(77);
		second.setNext(third);
		
		LinkedListNode2 current = first;
		while (current != null) {
			System.out.println(current.getData());
			
			current = current.getNext();
		}
	}
}
