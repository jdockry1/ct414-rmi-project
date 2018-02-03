package ct414;

import java.util.Random;

public class ClientSession {
	
	private static final Random RANDOM = new Random();
	
	private int accessToken;
	private int studentID;

	public ClientSession(int studentID) {
		this.accessToken = RANDOM.nextInt();
		this.studentID = studentID;
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
}
