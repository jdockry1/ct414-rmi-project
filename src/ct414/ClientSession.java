package ct414;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ClientSession {
	
	private static final Random RANDOM = new Random();
	
	private int accessToken;
	private int studentID;
	private Date date;

	public ClientSession(int studentID) {
		this.accessToken = RANDOM.nextInt();
		this.studentID = studentID;
		
		Calendar calender = Calendar.getInstance();
        long time = calender.getTimeInMillis(); // Time in milliseconds
		this.date = new Date(time + (5 * 60000));
	}
	
	public int getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(int accessToken) {
		this.accessToken = accessToken;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
