package ct414;

import java.io.Serializable;

public class Student implements Serializable {

	private String firstName;
	private String lastName;
	private int id;
	private String password;

	public Student(String firstName, String lastName, int id, String password, Course course) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return firstName + " " + lastName + " (" + id + ")";
	}
}
