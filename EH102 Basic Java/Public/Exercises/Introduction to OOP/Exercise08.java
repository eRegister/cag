package oopintro;

class User3 {
	private String name;
	private int age;
	
	/**
	 * Constructs a new user given a name and age
	 * @param name the name
	 * @param age the age
	 */
	public User3(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * Gets the user's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the user's name
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the user's age
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets the user's age
	 * @param age the age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Returns a string representation of this object
	 */
	@Override
	public String toString() {
		return "User[" + name + "|" + age + "]";
	}

	/**
	 * Compares this user to another to see if they are equal
	 */
	@Override
	public boolean equals(Object obj) {
		User3 u = (User3)obj;
		return u.name.equals(name) && (u.age == age);
	}	
}

public class Exercise08 {
	public static void main(String[] args) {
		User3 u1 = new User3("Jim", 24);
		User3 u2 = new User3("Bob", 28);
		User3 u3 = new User3("Jim", 24);
		
		System.out.println("u1 == u3 -> " + (u1 == u3 ? "TRUE" : "FALSE"));
		System.out.println("u1.equals(u3) -> " + (u1.equals(u3) ? "TRUE" : "FALSE"));
		System.out.println("u1.equals(u2) -> " + (u1.equals(u2) ? "TRUE" : "FALSE"));
	}
}
