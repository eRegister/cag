package oopintro;

class A1 {
	protected String message = "Hello";
	
	/**
	 * Prints the message to the console
	 */
	public void hello()
	{
		System.out.println(message);
	}
}

class B1 extends A1 {
}

public class Exercise01 {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		A1 a = new A1();
		B1 b = new B1();

		// B1 inherits the hello method from A1
		a.hello();
		b.hello();
	}

}
