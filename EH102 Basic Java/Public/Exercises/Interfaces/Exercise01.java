package interfaces;

interface SimpleCalculator {
	public double add(double x, double y);
	public double sub(double x, double y);
	public double mul(double x, double y);
	public double div(double x, double y);
}

class MyCalculator implements SimpleCalculator {
	@Override
	public double add(double x, double y) {
		return x + y;
	}

	@Override
	public double div(double x, double y) {
		return x / y;
	}

	@Override
	public double mul(double x, double y) {
		return x * y;
	}

	@Override
	public double sub(double x, double y) {
		return x - y;
	}
}

public class Exercise01 {
	public static void main(String[] args) {
		MyCalculator calc = new MyCalculator();
		
		testSums(calc);
	}
	
	/**
	 * Tests an implementation of SimpleCalculator
	 * @param calc the calculator to test
	 */
	public static void testSums(SimpleCalculator calc) {
		System.out.println("3 + 4 = " + calc.add(3, 4));
		System.out.println("3 - 4 = " + calc.sub(3, 4));
		System.out.println("3 * 4 = " + calc.mul(3, 4));
		System.out.println("3 / 4 = " + calc.div(3, 4));
	}
}
