package oopintro;

class User {
	private String name;
	private int age;
	
	/**
	 * Constructs a new user given a name and age
	 * @param name the name
	 * @param age the age
	 */
	public User(String name, int age) {
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
}

public class Exercise06 {
	public static void main(String[] args) {
		User u1 = new User("Jim", 24);
		User u2 = new User("Bob", 28);
		User u3 = new User("Ann", 20);
		
		System.out.println(u1.getName() + "|" + u1.getAge());
		System.out.println(u2.getName() + "|" + u2.getAge());
		System.out.println(u3.getName() + "|" + u3.getAge());
	}
}
