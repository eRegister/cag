package generics;

import java.util.ArrayList;

public class Exercise01 {
	public static void main(String[] args) {
		// Create strongly typed arrays
		ArrayList<String> strArray = new ArrayList<String>();
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		
		// The add method now checks the object type
		strArray.add("Hello");
		strArray.add("World");
		intArray.add(123);
		intArray.add(456);
		intArray.add(5, 678);
		
		// Get specific elements
		System.out.println(strArray.get(0));
		System.out.println(strArray.get(1));
		System.out.println(intArray.get(0));
		System.out.println(intArray.get(1));
		
		// Loop through
		for (String s : strArray) {
			System.out.println(s);
		}
	}

}
