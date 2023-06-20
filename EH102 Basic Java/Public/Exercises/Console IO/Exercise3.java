package consoleio;

import java.io.*;

public class Exercise3 {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		System.out.print("Who is this: ");

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);
		String who = reader.readLine();

		System.out.println("Hello " + who);
	}

}
