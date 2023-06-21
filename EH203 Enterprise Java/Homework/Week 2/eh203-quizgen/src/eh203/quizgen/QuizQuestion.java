package eh203.quizgen;

import java.util.ArrayList;

/**
 * A single question in a quiz
 */
public class QuizQuestion {
	protected String question;
	protected ArrayList<String> options = new ArrayList<String>();
	protected int correctOption;
	
	/**
	 * Default constructor
	 */
	public QuizQuestion() {
	}
	
	/**
	 * Constructs a quiz question from a string
	 * @param question the question to ask
	 */
	public QuizQuestion(String question) {
		this.question = question;
	}

	/**
	 * Gets the question
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * Sets the question
	 * @param question the question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Gets the array of possible answers
	 * @return the possible answers
	 */
	public ArrayList<String> getOptions() {
		return options;
	}

	/**
	 * Adds an option to the array of possible answers
	 * @param option
	 */
	public void addOption(String option) {
		options.add(option);
	}

	/**
	 * Gets the correct answer
	 * @return the index of the correct option
	 */
	public int getCorrectOption() {
		return correctOption;
	}

	/**
	 * Sets the correct answer
	 * @param answer the index of the correct option
	 */
	public void setCorrectOption(int answer) {
		this.correctOption = answer;
	}
}
