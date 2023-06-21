package eh203.userform;

import java.util.HashMap;
import java.util.Map;

public class User2 {
	protected String name;
	protected int age;
	protected boolean admin;
	
	protected HashMap<String, String> emailAddresses = new HashMap<String, String>(); 
	
	public User2() {
		
	}
	
	public User2(String name, int age, boolean admin) {
		this.name = name;
		this.age = age;
		this.admin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public void setEmailAddress(String type, String address) {
		emailAddresses.put(type, address);
	}
	
	public Map<String, String> getEmailAddresses() {
		return emailAddresses;
	}
}
