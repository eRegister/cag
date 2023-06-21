package eh203.hibernate.domain;

public class Supervisor extends Person {
	protected String jobTitle;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
}
