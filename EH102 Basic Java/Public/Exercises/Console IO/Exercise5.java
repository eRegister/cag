package consoleio;

import java.io.*;

public class Exercise5 {

	static String[] values = new String[3];
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);
			
		System.out.println("Word length calc:");
		
		System.out.print("Enter word 1:");
		values[0] = reader.readLine();
		System.out.print("Enter word 2:");
		values[1] = reader.readLine();
		System.out.print("Enter word 3:");
		values[2] = reader.readLine();
		
		int length = 0;
		for (int i = 0; i < values.length; i++)
			length += values[i].length();
		
		System.out.println("Total length: " + length);
	}

}
