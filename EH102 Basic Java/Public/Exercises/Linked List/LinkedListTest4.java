package eh102.linkedlist;

public class LinkedListTest4 {
	public static void main(String[] args) {	
		LinkedList4<String> list = new LinkedList4<String>();
		list.add("Hello");
		list.add("World");
		list.add("EHSDI");
		
		LinkedListNode4<String> current = list.getFirst();
		while (current != null) {
			System.out.println(current.getData());
			
			current = current.getNext();
		}
	}
}
