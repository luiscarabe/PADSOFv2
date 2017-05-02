/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.stat.CMark;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.stat.Answer;


public class Student extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Answer> answers;
	private List<Course> expelledCourses;
	private List<Course> enrolledCourses;
	private List<Application> appliedCourses;
	private List<CMark> cMarks;
	/**
	 * Constructor of Student
	 * @param name
	 * @param email
	 * @param password
	 */
	public Student(String name, String email, String password) {
		super(name, email, password);
		expelledCourses = new ArrayList<Course>();
		enrolledCourses = new ArrayList<Course>();
		answers = new ArrayList<Answer>();
		appliedCourses = new ArrayList<Application>();
		cMarks = new ArrayList<CMark>();
	}
	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	/**
	 * @return the expelledCourses
	 */
	public List<Course> getExpelledCourses() {
		return expelledCourses;
	}
	/**
	 * @param expelledCourses the expelledCourses to set
	 */
	public void setExpelledCourses(List<Course> expelledCourses) {
		this.expelledCourses = expelledCourses;
	}
	/**
	 * @return the enrolledCourses
	 */
	public List<Course> getEnrolledCourses() {
		return enrolledCourses;
	}
	/**
	 * @param enrolledCourses the enrolledCourses to set
	 */
	public void setEnrolledCourses(List<Course> enrolledCourses) {
		this.enrolledCourses = enrolledCourses;
	}
	/**
	 * @return the appliedCourses
	 */
	public List<Application> getAppliedCourses() {
		return appliedCourses;
	}
	/**
	 * @param appliedCourses the appliedCourses to set
	 */
	public void setAppliedCourses(List<Application> appliedCourses) {
		this.appliedCourses = appliedCourses;
	}
	/**
	 * @return the cMarks
	 */
	public List<CMark> getcMarks() {
		return cMarks;
	}
	/**
	 * @param cMarks the cMarks to set
	 */
	public void setcMarks(List<CMark> cMarks) {
		this.cMarks = cMarks;
	}
	
	/* Methods */
	
	/**
	 * Method to apply for a Course
	 * @param c
	 * @return true if successful, false if not
	 */
	
	public boolean applyCourse(Course c){
		
		Application a;
		
		for (Application aux: this.appliedCourses){
			if(aux.getCourse().equals(c)){
				return false;
			}
		}
		
		if (this.enrolledCourses.contains(c) || this.expelledCourses.contains(c)){
			return false;
		}
		
		a = new Application(c, this);
		this.appliedCourses.add(a);
		c.getApplications().add(a);
		
		return true;
	}
	
	/**
	 * Method that cancel an application to a course.
	 * @param a Application to delete
	 */
	public void cancelApplication(Application a){
		if(this.getAppliedCourses().contains(a)){
			a.deleteApplication();
		}
	}
	
	/**
	 * Method that allows a student to get his/her course mark with a double
	 * @param c course of the mark to see
	 * @return double personal mark of the course out of 10
	 */
	public double seeCMark(Course c){
		for(CMark aux: this.getcMarks()){
			if(c.equals(aux.getCourse())){
				return aux.getCourseMark();
			}
		}
		return -1;
	}
	
	
	
}