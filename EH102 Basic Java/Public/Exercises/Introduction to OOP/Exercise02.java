package oopintro;

class A2 {
	protected String message = "Hello";
	
	/**
	 * Prints the message to the console
	 */
	public void hello()
	{
		System.out.println(message + " from A");
	}
}

class B2 extends A2 { 
	public void hello()
	{
		System.out.println(message + " from B");
	}
}

public class Exercise02 {
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		A2 a = new A2();
		B2 b = new B2();
		A2 c = b;

		a.hello();
		b.hello();
		c.hello();
	}

}
