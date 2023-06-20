package oopintro;

class User2 {
	private String name;
	private int age;
	
	/**
	 * Constructs a new user given a name and age
	 * @param name the name
	 * @param age the age
	 */
	public User2(String name, int age) {
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
}

public class Exercise07 {
	public static void main(String[] args) {
		User2 u1 = new User2("Jim", 24);
		User2 u2 = new User2("Bob", 28);
		User2 u3 = new User2("Ann", 20);
		
		System.out.println(u1);
		System.out.println(u2);
		
		String msg = "Hello " + u3;
		System.out.println(msg);
	}
}
