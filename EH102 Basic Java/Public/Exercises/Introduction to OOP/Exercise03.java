package oopintro;

class A3 {
	protected String message = "Hello";
	
	/**
	 * Prints the message to the console
	 */
	public void hello()
	{
		System.out.println(message + " from A");
	}
}

class B3 extends A3 {
	protected String message = "Goodbye";
	
	public void hello()
	{
		super.hello();
		
		System.out.println(message + " from B");
	}
}

public class Exercise03 {
	public static void main(String[] args) {
		A3 a = new A3();
		B3 b = new B3();
		A3 c = b;

		a.hello();
		b.hello();
		c.hello();
	}

}
