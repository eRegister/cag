package generics;

import java.util.*;

public class Exercise04 {

	/**
	 * Create a string -> object map
	 */
	private static Map<String, Object> map = new HashMap<String, Object>();

	public static void main(String[] args) {
		// Insert some name value pairs
		map.put("thedate", new Date());
		map.put("anumber", 123);
		map.put("mystring", "test");
		map.put("mystring", "newvalue");
		
		// Access specific entry
		System.out.println(map.get("thedate"));
		
		// Loop through all entries
		for (Object o : map.values()) 
			System.out.println(o);
		
		// Loop through all keys
		for (String s : map.keySet())
			System.out.println(s + ": " + map.get(s));
	}

}
