package consoleio;

import java.io.*;

public class Exercise6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);
		String input;
		do {
			System.out.print("Enter: ");
			input = reader.readLine();
			
			System.out.println("You entered: " + input);
		}
		while (!input.toLowerCase().equals("q"));
		
		System.out.println("THE END");
	}

}
