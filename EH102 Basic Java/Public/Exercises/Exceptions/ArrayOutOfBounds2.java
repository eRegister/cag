public class ArrayOutOfBounds2 {

	public static void main(String[] args) {
			
		int[] i = new int[4];
		
		try {
			i[4] = 12;
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("This array is not big enough.");
		}
	
	}
}
