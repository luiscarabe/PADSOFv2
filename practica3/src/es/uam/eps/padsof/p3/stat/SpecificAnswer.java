/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.stat;

import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;

import java.io.Serializable;
import java.util.*;

public class SpecificAnswer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Question question;
	private List<Option> answers;
	private double markOutWeight;
	private double markOut10;

	/**
	 * Constructor of SpecificAnswer
	 * 
	 * @param question
	 */
	public SpecificAnswer(Question question) {
		this.question = question;
		this.answers = new ArrayList<Option>();
		this.markOut10 = -1;
		this.markOutWeight = -1;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the answers
	 */
	public List<Option> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(List<Option> answers) {
		this.answers = answers;
	}

	/**
	 * @return the markOutWeight
	 */
	public double getMarkOutWeight() {
		return markOutWeight;
	}

	/**
	 * @param markOutWeight
	 *            the markOutWeight to set
	 */
	public void setMarkOutWeight(double markOutWeight) {
		this.markOutWeight = markOutWeight;
	}

	/**
	 * @return the markOut10
	 */
	public double getMarkOut10() {
		return markOut10;
	}

	/**
	 * @param markOut10
	 *            the markOut10 to set
	 */
	public void setMarkOut10(double markOut10) {
		this.markOut10 = markOut10;
	}

	/**
	 * Method to choose an option
	 * 
	 * @param o
	 * @return true if successful, false if not
	 */

	public boolean chooseAnswer(Option o) {
		if (this.answers.contains(o)) {
			return false;
		}
		this.answers.add(o);
		return true;
	}

	/**
	 * Method to delete an answer
	 * 
	 * @param o
	 */

	public void deleteAnswer(Option o) {
		if (this.answers.contains(o)) {
			this.answers.remove(o);
		}
	}

	/**
	 * Method to calculate the mark of a question
	 * 
	 * @return true if it has been successfully calculated, false if not
	 */

	public void calculateMark() {
		int flag = 0;
		if (this.answers.isEmpty()) {
			this.markOutWeight = 0;
			this.markOut10 = 0;
			return;
		}
		if (this.question instanceof OpenQuestion) {
			for (Option o : this.answers) {
				if (this.question.getSolution().contains(o)) {
					this.markOut10 = 10;
					this.markOutWeight = this.question.getWeight();
					return;
				}
			}
		}
		if (this.answers.size() != this.question.getSolution().size()) {
			this.markOutWeight = 0 - this.question.getExer().getPenalty();
			this.markOut10 = 0 - (this.question.getExer().getPenalty() / this.question.getExer().getWeight()) * 10;
			return;
		}
		for (Option o : this.answers) {
			if (!this.question.getSolution().contains(o)) {
				flag = 1;
			}
		}
		if (flag == 1) {
			this.markOutWeight = 0 - this.question.getExer().getPenalty();
			this.markOut10 = 0 - (this.question.getExer().getPenalty() / this.question.getExer().getWeight()) * 10;

			return;
		} else {
			this.markOut10 = 10;
			this.markOutWeight = this.question.getWeight();
			return;
		}
	}

}