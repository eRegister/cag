public class GoodHobbies {
	
	public static void main(String[] args) {
		String myHobby = args[0];
		
		GoodHobbies goodHobbies = new GoodHobbies();
	
		try {
			
			goodHobbies.checkHobby(myHobby);
			
		} catch(BadHobbyException e) {
			System.out.println("I don't like "+myHobby);
		}
	}
	
	class BadHobbyException extends Exception {
		
	}
	
	void checkHobby(String hobby) throws BadHobbyException {

		if(!hobby.equals("boxing") && !hobby.equals("salsa")) {
			throw new BadHobbyException();
		}
		
		System.out.println("I like " + hobby);
	}
}

