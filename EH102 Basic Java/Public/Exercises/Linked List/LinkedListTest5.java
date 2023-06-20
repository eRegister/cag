package eh102.linkedlist;

public class LinkedListTest5 {
	public static void main(String[] args) {	
		LinkedList5<String> list = new LinkedList5<String>();
		list.add("Hello");
		list.add("World");
		list.add("EHSDI");
		
		for (int i = 0; i < list.size(); i++) {
			 System.out.println(list.get(i));
		}
	}
}
