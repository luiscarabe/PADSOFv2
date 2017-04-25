/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.stat;

import java.io.Serializable;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.user.Student;

public class CMark implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double courseMark;
	private Course course;
	private Student student;
	
	/**
	 * Constructor of CMark
	 * @param course
	 * @param student
	 */
	public CMark(Course course, Student student) {
		this.course = course;
		this.student = student;
		this.courseMark = -1;
	}

	/**
	 * @return the courseMark
	 */
	public double getCourseMark() {
		return courseMark;
	}

	/**
	 * @param courseMark the courseMark to set
	 */
	public void setCourseMark(double courseMark) {
		this.courseMark = courseMark;
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
	 * Method to calculate the course mark of a student
	 * @return the mark
	 */
	
	public double calculateCMark(){
		double aux = 0;
		double totalWeight = 0;
		if(this.student.getAnswers().isEmpty()){
			return -1;
		}
		for (Answer a: this.student.getAnswers()){
			if (a.getExercise().getCourse().equals(this.course)){
				if(a.allowedToShow()){
					aux += a.getMarkOutWeight();
					totalWeight += a.getExercise().getWeight();
				}
			}
		}
		if(totalWeight == 0){
			return -1;
		}
		aux /= totalWeight;
		aux *= 10;
		this.courseMark = aux;
		return aux;
	}
		
}
