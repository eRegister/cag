package interfaces;

/**
 * Interface with a variable and a method
 */
interface A {
	double PI = 3.14;
	public void foo();
}

/**
 * B extends A and adds a method
 */
interface B extends A {
	public void bar();
}

interface C {
	public void hello();
}

/**
 * Class which implements B and C
 */
class D implements B, C {
	public void foo() {
		System.out.println(PI);		
	}
	public void bar() {		
	}
	public void hello() {		
	}
}

public class Exercise02 {
	public static void main(String[] args) {
		D d = new D();
		d.foo();
	}
}
