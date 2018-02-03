package ct414;

public class MCQQuestion implements Question {

	private int number;
	private String detail;
	private String[] answerOptions;
	private int correctAnswer;
	private int selectedAnswer;
	
	public MCQQuestion(int number, String detail, String[] answerOptions, int correctAnswer){
		this.number = number;
		this.detail = detail;
		this.answerOptions = answerOptions;
		this.correctAnswer = correctAnswer;
		this.selectedAnswer = -1;
	}
	
	@Override
	public int getQuestionNumber() {
		return this.number;
	}

	@Override
	public String getQuestionDetail() {
		return this.detail;
	}

	@Override
	public String[] getAnswerOptions() {
		return this.answerOptions;
	}
	
	public int getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public int getSelectedAnswer() {
		return this.selectedAnswer;
	}
	
	public void setSelectedAnswer(int selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}

}
