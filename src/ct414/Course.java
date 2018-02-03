package ct414;

import java.io.Serializable;

public class Course implements Serializable{
	
	private String courseName;
	private String courseCode;
	
	public Course(String courseName, String courseCode) {
		this.courseName = courseName;
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String toString() {
		return this.courseName + " (" + this.courseCode + ")";
	}

}
