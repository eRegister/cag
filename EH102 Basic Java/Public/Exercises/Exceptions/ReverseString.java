public class ReverseString {

	public static void main(String[] args) {
		
		try{
			System.out.println(reverseStr("cat"));
			System.out.println(reverseStr(""));
		} catch(Exception e) {
			System.out.println("String to reverse is empty");
		} finally {
			System.out.println("Reversal program has completed");
		}
	}
	
	static String reverseStr(String s) throws Exception {
		
		if(s.length() == 0) {
			throw new Exception();
		}
		
		String reverseStr = "";
		for(int i=s.length()-1; i>=0; --i) {
			reverseStr += s.charAt(i);
		}
		
		return reverseStr; 
	}
}

