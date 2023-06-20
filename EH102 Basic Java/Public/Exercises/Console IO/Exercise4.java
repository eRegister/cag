package consoleio;

import java.io.*;

public class Exercise4 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);
			
		System.out.println("Power calculator:");
		
		System.out.print("Enter x:");
		String number1 = reader.readLine();
		int x = Integer.parseInt(number1);

		System.out.print("Enter y:");
		String number2 = reader.readLine();
		int y = Integer.parseInt(number2);
		
		System.out.println("Result of (x^y) " + Math.pow(x, y));
	}

}
