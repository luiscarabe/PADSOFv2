/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.stat;

import java.util.*;
import java.io.Serializable;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.user.Student;

public class Answer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exercise exercise;
	private List<SpecificAnswer> specificAnswer;
	private Student student;
	private double markOutWeight;
	private double markOut10;
	
	/**
	 * Constructor of Answer
	 * @param exercise
	 * @param student
	 * @param nQues
	 */

	public Answer(Exercise exercise, Student student, int nQues) {
		this.exercise = exercise;
		this.specificAnswer = new ArrayList<SpecificAnswer>(nQues);
		this.student = student;
		this.markOut10 = -1;
		this.markOutWeight = -1;
	}


	/**
	 * @return the exercise
	 */
	public Exercise getExercise() {
		return exercise;
	}


	/**
	 * @param exercise the exercise to set
	 */
	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}


	/**
	 * @return the specificAnswer
	 */
	public List<SpecificAnswer> getSpecificAnswer() {
		return specificAnswer;
	}


	/**
	 * @param specificAnswer the specificAnswer to set
	 */
	public void setSpecificAnswer(List<SpecificAnswer> specificAnswer) {
		this.specificAnswer = specificAnswer;
	}


	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}


	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the markOutWeight
	 */
	public double getMarkOutWeight() {
		return markOutWeight;
	}


	/**
	 * @param markOutWeight the markOutWeight to set
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
	 * @param markOut10 the markOut10 to set
	 */
	public void setMarkOut10(double markOut10) {
		this.markOut10 = markOut10;
	}

	/* Methods */
	
	/**
	 * Method that informs if an answer is allowed to be shown
	 * @return true if it is false if not
	 */
	public boolean allowedToShow(){
		
		return this.getExercise().isAllowedToShow();
	}
	
	/**
	 * Method that calculates the average mark of a student at an exercise
	 * @return true if it has been calculated false if not
	 */
	public double calculateMark(){
		double aux = 0;
		double totalWeight = 0;
		
		if(this.specificAnswer.isEmpty()){
			return -1;
		}else {
			for(SpecificAnswer sa: this.specificAnswer){
				aux += sa.getMarkOutWeight();
				totalWeight += sa.getQuestion().getWeight();
			}
			this.markOutWeight = aux;
			if(this.markOutWeight < 0){
				this.markOutWeight = 0;
			}
			this.markOut10 = aux*10/totalWeight;
			return this.markOut10;
		}
	}

	
}
 