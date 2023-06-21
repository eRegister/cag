package eh203.login;

public class User {
	protected String login;
	protected String password;
	protected String name;
	
	public User() {
	}
	
	/**
	 * @param login
	 * @param password
	 * @param name
	 */
	public User(String login, String password, String name) {
		this.login = login;
		this.password = password;
		this.name = name;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
