/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.exercise;

import java.util.*;
import java.io.Serializable;
import java.time.*;
import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.CourseElement;
import es.uam.eps.padsof.p3.stat.ExerciseStat;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;
import es.uam.eps.padsof.p3.user.Student;


public class Exercise extends CourseElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double weight;
	private double penalty;
	private int numQues;
	private boolean randomness;
	private ExerciseStat stats;
	private List<Question> questions;
	private LocalDate expDate;
	private LocalDate startDate;
 	private List<Answer> answers;
	
 	/**
 	 * Constructor of Exercise
 	 * @param title
 	 * @param desc
 	 * @param hidden
 	 * @param course
 	 */
 	
	public Exercise(String title, String desc,boolean hidden, Course course) {
		super(title, desc, hidden, course);
		this.questions = new ArrayList<Question>();
		this.answers = new ArrayList<Answer>();
		this.randomness = false;
		this.startDate = null;
		this.expDate = null;
		this.numQues = 0;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the penalty
	 */
	public double getPenalty() {
		return penalty;
	}

	/**
	 * @param penalty the penalty to set
	 */
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return the numQues
	 */
	public int getNumQues() {
		return numQues;
	}

	/**
	 * @param numQues the numQues to set
	 */
	public void setNumQues(int numQues) {
		this.numQues = numQues;
	}

	/**
	 * @return the randomness
	 */
	public boolean isRandomness() {
		return randomness;
	}

	/**
	 * @param randomness the randomness to set
	 */
	public void setRandomness(boolean randomness) {
		this.randomness = randomness;
	}

	/**
	 * @return the stats
	 */
	public ExerciseStat getStats() {
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public void setStats(ExerciseStat stats) {
		this.stats = stats;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * @return the expDate
	 */
	public LocalDate getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate the expDate to set
	 */
	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 	
	 * @return
	 */
	public List<Answer> getAnswers() {
		return answers;
	}
	
	/**
	 * 
	 * @param answers
	 */

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	/* Methods */
	
	
	/**
	 * Method that informs the professor if an exercise is still modifiable
	 * @return true if there is any student that has taken the exercise, false if not
	 */
	public boolean isModifiable(){
		if(this.getAnswers().isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Method that adds a question to an exercise
	 * @param q question to add
	 * @return true if successful, false if not
	 */
	public boolean addQuestion(Question q){
		for(Question aux: this.questions){
			if(aux.equals(q)){
				return false;
			}
		}
		this.questions.add(q);
		q.setExer(this);
		this.numQues++;
		return true;
	}
	
	
	/**
	 * Method that allows professors to delete a question
	 * @param q: Question
	 * @return true if the question has been deleted, false if not
	 */
	public boolean deleteQuestion(Question q){
		if(q == null){
			return false;
		}
		if(this.getQuestions().contains(q)){
			this.getQuestions().remove(q);
			this.numQues--;
			return true;
		}
		return false;
	}
	
	/**
	 * Method that allows professors to randomly select the order of question
	 * @return true if shuffles the questions false if not
	 */
	public boolean randomizeOrder(){
		if(this.randomness == true){
			Collections.shuffle(this.questions);
			return true;
		}
		return false;
	}
	
	/**
	 * Method that checks if a date is valid
	 * @param d date to check
	 * @return true if it is valid false if not
	 */
	public boolean isValidDate(LocalDate d){
		LocalDate now = LocalDate.now();
		
		if(d.isBefore(now)){
			return false;
		}
		return true;
	}
	
	/**
	 * Method that checks if a professor can add a start date to an exercise
	 * @param d date to add
	 * @return true if it is successfully added false if not
	 */
	public boolean addStartDate(LocalDate d){
		if(this.isAllowedToShow() == true){
			return false;
		}
	    if (this.isModifiable() == false){
	        return false;
	    }
		if(this.isValidDate(d)){
			if(this.expDate != null){
				if(this.expDate.isBefore(d)){
					return false;
				}
			}
			this.setStartDate(d);
			return true;
		}
		return false;
	}
	
	/**
	 * Method that checks if a professor can add an expiration date to an exercise
	 * @param d date to add
	 * @return true if it is successfully added false if not
	 */
	public boolean addExpirationDate(LocalDate d){
		if(this.isAllowedToShow() == true){
			return false;
		}
	    if(this.isModifiable()){
	        if(this.isValidDate(d)){
			    if(this.startDate != null && d.isBefore(this.startDate)){
				    return false;
		    	}

		    	this.setExpDate(d);
			}
	    }
		if(this.isValidDate(d)){

			if(this.startDate != null && d.isBefore(this.startDate)){
				return false;
			}
			if(this.expDate != null){
				if(d.isBefore(this.expDate)){
					return false;
				}
			}
			this.setExpDate(d);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method that informs if the answers of an exercises are allowed to be shown
	 * @return true if it is false if not
	 */
	public boolean isAllowedToShow(){
		LocalDate now;
		
		now = LocalDate.now();
		
		if(this.getExpDate() == null){
			return false;
		}
		
		return now.isAfter(this.getExpDate());
	}
	
	/**
	 * Method that notifies students if the exercise is available to take
	 * @return true if it is false if not
	 */
	public boolean canTakeExercise(Student s){
		LocalDate now = LocalDate.now();
		
		if(this.isTakenExerciseByStudent(s) == true){
			return false;
		}
		if(now.equals(this.startDate) || now.equals(this.expDate)){
			if(this.isHidden() == true){
				this.setHidden(false);
			}
			return true;
		}
		if(now.isAfter(this.startDate) && now.isBefore(this.expDate)){
			if(this.isHidden() == true){
				this.setHidden(false);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Method that informs a student if he/she has taken the exercise
	 * @param s student that has taken the exercise (or not)
	 * @return true if the exercise has been taken by the student
	 */
	public boolean isTakenExerciseByStudent(Student s){
		
		for(Answer aux: this.answers){
			if(aux.getStudent().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that allows a student to take an exercise
	 * @param s student that takes the exercise
	 * @return Answer if it has been created or null if not
	 */
	public Answer takeExercise(Student s){
		Answer ans;
		int nQues;
		int i;
		
		if(this.isTakenExerciseByStudent(s)){
			return null;
		}
		
		nQues = this.getQuestions().size();
		
		ans = new Answer(this, s, nQues);
				
		for(i = 0; i < nQues; i++){
			SpecificAnswer a = new SpecificAnswer(this.questions.get(i));
			ans.getSpecificAnswer().add(a);
		}
		
		this.answers.add(ans);
		s.getAnswers().add(ans);
		
		return ans;
	}
	
	/**
	 * Method that the student calls before sending his/her answers to cancel them.
	 * @param a answer associated to a the exercise to cancel
	 */
	public boolean cancelExercise(Answer a){
		if(a == null){
			return false;
		}
		if(this.getAnswers().contains(a)){
			a.getStudent().getAnswers().remove(a);
			a.getExercise().getAnswers().remove(a);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Method that search the answer to an exercise of a student
	 * @param s the student of the answer to search
	 * @return Answer of the student to the exercise or null if he/she hasn't answered the execise
	 */
	public Answer searchAnswer(Student s){
		for(Answer aux: this.getAnswers()){
			if(aux.getStudent().equals(s)){
				return aux;
			}
		}
		return null;
	}
	
	
}
