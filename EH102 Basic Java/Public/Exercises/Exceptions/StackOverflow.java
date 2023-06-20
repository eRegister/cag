public class StackOverflow {

	public static void main(String[] args) {

		System.out.println("hello from main");
		
		myMethod();
	}
	
	static void myMethod() {
		System.out.println("hello from my method.");
		
		try{
			// do stuff
			System.out.println("inside try.");
			
			myMethod();
			
		} catch(Error e) {
			// do exception handling
			System.out.println("inside catch.");
			System.out.println("&&&&&&&&&&&&&&&&&&&&  BEFORE MEMORY EXCEPTION IS FATAL");
			System.out.println(e.toString());
		}
	}
}
