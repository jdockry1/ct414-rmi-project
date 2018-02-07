package ct414;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MCQAssessment implements Assessment {
	
	private String title;
	private Course course;
	private Date closingDate;
	private List<Question> questions = new ArrayList<Question>();
	private int id;
	
	public MCQAssessment(String title, Course course, Date closingDate, List<Question> questions, int id){
		this.title = title;
		this.course = course;
		this.closingDate = closingDate;
		this.questions = questions;
		this.id = id;
	}

	@Override
	public String getInformation() {
		return this.title + ", Course: " + course.getCourseCode();
	}

	@Override
	public Date getClosingDate() {
		return this.closingDate;
	}

	@Override
	public List<Question> getQuestions() {
		return this.questions;
	}

	@Override
	public Question getQuestion(int questionNumber) throws InvalidQuestionNumber {
		return this.questions.get(questionNumber);
	}

	@Override
	public void selectAnswer(int questionNumber, int optionNumber) throws InvalidQuestionNumber, InvalidOptionNumber {
		((MCQQuestion) questions.get(questionNumber)).setSelectedAnswer(optionNumber);
	}

	@Override
	public int getSelectedAnswer(int questionNumber) {
		return ((MCQQuestion) questions.get(questionNumber)).getSelectedAnswer();
	}

	@Override
	public int getAssociatedID() {
		return id;
	}
	
	public Course getCourse(){
		return course;
	}
	
	public String getTitle() {
		return this.title;
	}

}