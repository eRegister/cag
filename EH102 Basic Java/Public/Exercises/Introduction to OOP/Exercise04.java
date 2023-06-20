package oopintro;

class A4 {
	protected String message;
	
	/**
	 * Constructs A from a message
	 * @param message the message
	 */
	A4(String message) {
		this.message = message;
	}
	
	/**
	 * Prints the message to the console
	 */
	public void hello()
	{
		System.out.println(message + " from A");
	}
}

class B4 extends A4 {	
	B4()
	{
		// Need to pass a parameter to A's constructor
		super("Goodbye");
	}
	
	public void hello()
	{
		super.hello();
		
		System.out.println(message + " from B");
	}
}

public class Exercise04 {
	public static void main(String[] args) {
		A4 a = new A4("Hello");
		B4 b = new B4();
		A4 c = b;

		a.hello();
		b.hello();
		c.hello();
	}

}
