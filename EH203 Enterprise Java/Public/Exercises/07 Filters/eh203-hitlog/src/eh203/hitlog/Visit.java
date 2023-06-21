package eh203.hitlog;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class to represent a visit to the website
 */
public class Visit {
	protected static ArrayList<Visit> visits = new ArrayList<Visit>();
	
	protected Date date;
	protected String url;
	protected String remoteAddr;
	protected long timeTaken;
	
	/**
	 * Constructs a new visit object
	 * @param date
	 * @param url
	 * @param remoteAddr
	 */
	public Visit(Date date, String url, String remoteAddr, long timeTaken) {
		this.date = date;
		this.url = url;
		this.remoteAddr = remoteAddr;
		this.timeTaken = timeTaken;
	}

	/**
	 * Gets the date and time of the visit
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the URL requested
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Gets the remote address
	 * @return the remote address
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}

	/**
	 * Gets the time taken to handle the request
	 * @return the time taken
	 */
	public long getTimeTaken() {
		return timeTaken;
	}
	
	/**
	 * Records a visit
 	 * @param visit the visit to record
	 */
	public static void record(Visit visit) {
		visits.add(visit);
	}
	
	/**
	 * Gets the array of recorded visits
	 * @return the recorded visits
	 */
	public static ArrayList<Visit> getRecordedVisits() {
		return visits;
	}
}
