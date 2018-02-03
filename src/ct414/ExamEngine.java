
package ct414;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ExamEngine implements ExamServer {
	
	private List<Course> courses;
	private List<Student> students;
	private List<MCQAssessment> assessments;
	private List<ClientSession> clientSessions;

    // Constructor is required
    public ExamEngine(List<Course> courses, List<Student> students, List<MCQAssessment> assessments) {
        super();
		this.courses = courses;
		this.students = students;
		this.assessments = assessments;
		this.clientSessions = new ArrayList<ClientSession>();
    }

    // Implement the methods defined in the ExamServer interface...
    // Return an access token that allows access to the server for some time period
    public int login(int studentid, String password) throws 
                UnauthorizedAccess, RemoteException {
    	String error = "<html>The id entered is invalid</html>";
    	
    	for(Student student : students) {
    		if(student.getId() == studentid) {
    			error = "<html>The password entered is invalid</html>";
    			
    			if(student.getPassword().equals(password)) {
        			ClientSession cl = new ClientSession(student.getId());
        	    	clientSessions.add(cl);
        	    	
        	    	return cl.getAccessToken();
        		}
    		}
    	}
    	
    	throw new UnauthorizedAccess(error);
    }

    // Return a summary list of Assessments currently available for this studentid
    public List<String> getAvailableSummary(int token, int studentid) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
    	List<String> summaries = new ArrayList<String>();
    	
    	validateClientSession(token);
    	
    	for(Assessment assessment : assessments)
			summaries.add(assessment.getInformation());
    	
    	if(summaries.isEmpty())
    		throw new NoMatchingAssessment("No available assessments");
    	
    	return summaries;
    }

    // Return an Assessment object associated with a particular course code
    public Assessment getAssessment(int token, int studentid, String courseCode) throws
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
    	validateClientSession(token);
    	
    	for(Assessment assessment : assessments)
			if(((MCQAssessment) assessment).getCourse().getCourseCode().equals(courseCode))
				return assessment;
    	
        throw new NoMatchingAssessment("Assessment not available");
    }

    // Submit a completed assessment
    public void submitAssessment(int token, int studentid, Assessment completed) throws 
                UnauthorizedAccess, NoMatchingAssessment, RemoteException {
    	validateClientSession(token);
    	
    	for(MCQAssessment assessment : assessments)
    		if(assessment.getAssociatedID() == completed.getAssociatedID()) {
    			//assessment = (MCQAssessment) completed;
    			for(int i = 0; i < completed.getQuestions().size(); i++) {
    				try {
						assessment.selectAnswer(i, ((MCQQuestion) completed.getQuestion(i)).getSelectedAnswer());
					} catch (InvalidQuestionNumber | InvalidOptionNumber e) {
						e.printStackTrace();
					}
    			}
    			
    			return;
    		}
    	
    	throw new NoMatchingAssessment("Assessment not available");
    }
    
    public boolean validateClientSession(int accessToken) throws UnauthorizedAccess {
    	for(ClientSession clientSession : clientSessions) {
    		if(clientSession.getAccessToken() == accessToken)
    			return true;
    	}
    	
    	throw new UnauthorizedAccess("Client Session no longer valid!");
    }

    public static void main(String[] args) {
        //if (System.getSecurityManager() == null) {
            //System.setSecurityManager(new SecurityManager());
        //}
        try {
        	List<Course> courses = new ArrayList<Course>();;
        	List<Student> students = new ArrayList<Student>();;
        	List<MCQAssessment> assessments = new ArrayList<MCQAssessment>();;
            
            // Create Courses
            Course c1 = new Course("Computer Science & IT", "BCT");
            System.out.println(c1);
            courses.add(c1);
            
            // Create Students
            Student st1 = new Student("John", "Sandman", 123456789, "JSandman", c1);
            System.out.println(st1);
            students.add(st1);
            
            // Create Assignments
            String[] ans = {"Yes", "No", "All of the above", "None of the above"};
            List<Question> qs = new ArrayList<Question>();
            qs.add(new MCQQuestion(1, "This is question 1?", ans, 0));
            qs.add(new MCQQuestion(2, "This is question 2?", ans, 0));
            qs.add(new MCQQuestion(3, "This is question 3?", ans, 0));
            qs.add(new MCQQuestion(4, "This is question 4?", ans, 0));
            Calendar calender = Calendar.getInstance();
            long time = calender.getTimeInMillis(); // Time in milliseconds
            Date date = new Date(time + (10 * 60000)); // 10 minutes from now
            MCQAssessment a1 = new MCQAssessment("Assessment Title", c1, date, qs, 1111);
            System.out.println(a1.getInformation());
            assessments.add(a1);

            String name = "ExamServer";
            ExamEngine engine = new ExamEngine(courses, students, assessments);
            ExamServer stub = (ExamServer) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ExamEngine bound");
            
            
        } catch (Exception e) {
            System.err.println("ExamEngine exception:");
            e.printStackTrace();
        }
    }
}
