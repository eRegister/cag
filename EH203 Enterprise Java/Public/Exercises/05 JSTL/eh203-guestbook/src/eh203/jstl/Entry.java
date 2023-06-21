package eh203.jstl;

import java.util.Date;

public class Entry {
	protected String name;
	protected String email;
	protected Date date;
	protected String comment;
	
	public Entry() {
	}
	
	public Entry(String name, String email, Date date, String comment) {
		this.name = name;
		this.email = email;
		this.date = date;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
