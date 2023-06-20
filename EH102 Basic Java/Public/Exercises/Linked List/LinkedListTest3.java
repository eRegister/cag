package eh102.linkedlist;

public class LinkedListTest3 {
	public static void main(String[] args) {	
		LinkedList3 list = new LinkedList3();
		list.add("Hello");
		list.add(true);
		list.add(77);
		
		LinkedListNode2 current = list.getFirst();
		while (current != null) {
			System.out.println(current.getData());
			
			current = current.getNext();
		}
	}
}
