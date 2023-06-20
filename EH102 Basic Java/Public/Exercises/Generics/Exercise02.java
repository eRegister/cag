package generics;

/**
 * Old-style version, takes any object type
 */
class EqualityTester1 {
	public boolean test(Object o1, Object o2) {
		return o1.equals(o2);
	}
}

/**
 * Generic method version
 */
class EqualityTester2 {
	public <T> boolean test(T o1, T o2) {
		return o1.equals(o2);
	}
}

/**
 * Generic class version
 * @param <T> The type of objects to be tested
 */
class EqualityTester3<T> {
	public boolean test(T o1, T o2) {
		return o1.equals(o2);
	}
}

public class Exercise02 {
	public static void main(String[] args) {
		EqualityTester1 tester1 = new EqualityTester1();
		boolean result1 = tester1.test("Hello", "Hello");
		System.out.println(result1 ? "Equal" : "Not equal");
		
		EqualityTester2 tester2 = new EqualityTester2();
		boolean result2 = tester2.test("Hello", "Hello");
		System.out.println(result2 ? "Equal" : "Not equal");
		
		EqualityTester3<String> tester3 = new EqualityTester3<String>();
		boolean result3 = tester3.test("Hello", "Hello");
		System.out.println(result3 ? "Equal" : "Not equal");
	}
}
