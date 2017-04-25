/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.user;

import java.io.Serializable;

import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import es.uam.eps.padsof.p3.course.Course;

public class Application implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	private Student appliedStudent;
	
	/**
	 * Constructor of Application
	 * @param course
	 * @param student
	 */
	
	public Application(Course course, Student student) {
		this.course = course;
		this.appliedStudent = student;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the appliedStudent
	 */
	public Student getAppliedStudent() {
		return appliedStudent;
	}

	/**
	 * @param appliedStudent the appliedStudent to set
	 */
	public void setAppliedStudent(Student appliedStudent) {
		this.appliedStudent = appliedStudent;
	}
	
	/* Methods */
	/**
	 * Method that deletes an application
	 */
	public void deleteApplication(){
		this.getCourse().getApplications().remove(this);
		this.getAppliedStudent().getAppliedCourses().remove(this);
	}
	
	/**
	 * Method that accepts a student in a course
	 */
	public void acceptApplication() throws Exception{
		String str1, str2;
		this.getCourse().getEnrolledStudents().add(this.getAppliedStudent());
		this.getAppliedStudent().getEnrolledCourses().add(this.getCourse());
		
		try {
			str1 = "Accepted into course: " + this.course.getTitle();
			str2 = "Hello " + this.appliedStudent.getName() + ".\n You have been accepted into course "
				+ this.course.getTitle() + ". Don't answer this message (auto-generated).";
			
			if(str1.charAt(0) == 'w' || str1.charAt(0) == 'W'){
				throw new FailedInternetConnectionException(str1);
			}
			if(EmailSystem.isValidEmailAddr(this.appliedStudent.getEmail()) == false){
				throw new InvalidEmailAddressException(this.appliedStudent.getEmail());
			}
			EmailSystem.send(this.appliedStudent.getEmail(), str1, str2, true);
		} catch (InvalidEmailAddressException e){
			System.out.println(e.getMessage());
		}catch (FailedInternetConnectionException e){
			System.out.println(e.getMessage());
		}
		
		this.deleteApplication();
	}
	
	/**
	 * Method that denies the application for a course
	 */
	public void declineApplication() throws Exception{
		String str1, str2;
		try {
			str1 = "Not accepted into course: " + this.course.getTitle();
			str2 = "Hello " + this.appliedStudent.getName() + ".\n Your application for the course "
					+ this.course.getTitle() + " has been declined. Don't answer this message (auto-generated).";
			
			if(str1.charAt(0) == 'w' || str1.charAt(0) == 'W'){
				throw new FailedInternetConnectionException(str1);
			}
			if(EmailSystem.isValidEmailAddr(this.appliedStudent.getEmail()) == false){
				throw new InvalidEmailAddressException(this.appliedStudent.getEmail());
			}
			EmailSystem.send(this.appliedStudent.getEmail(), str1, str2, true);
		} catch (InvalidEmailAddressException e){
			System.out.println(e.getMessage());
		}catch (FailedInternetConnectionException e){
			System.out.println(e.getMessage());
		}
		
		this.deleteApplication();
	}
}
