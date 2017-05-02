/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.course;

import es.uam.eps.padsof.p3.user.*;

import java.io.Serializable;
import java.util.*;

import es.uam.eps.padsof.p3.stat.CourseStat;
import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import es.uam.eps.padsof.p3.exercise.*;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String desc;
	private CourseStat courseStat;
	private List<Student> enrolledStudents;
	private List<Student> expelledStudents;
	private static int lastId = 0;
	private List<Application> applications;
	private List<CourseElement> courseElements;

	/**
	 * Constructor of Course
	 * @param title
	 * @param desc
	 */
	
	public Course(String title, String desc) {
		lastId = lastId + 1;
		this.id = lastId;
		this.title = title;
		this.desc = desc;
		this.courseStat = null;
		this.enrolledStudents = new ArrayList<Student>();
		this.expelledStudents = new ArrayList<Student>();
		this.courseElements = new ArrayList<CourseElement>();
		this.applications = new ArrayList<Application>();
	}
	
	/**
	 * @return the courseElements
	 */
	public List<CourseElement> getCourseElements() {
		return courseElements;
	}



	/**
	 * @param courseElements the courseElements to set
	 */
	public void setCourseElements(List<CourseElement> courseElements) {
		this.courseElements = courseElements;
	}

	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the courseStat
	 */
	public CourseStat getCourseStat() {
		return courseStat;
	}

	/**
	 * @param courseStat the courseStat to set
	 */
	public void setCourseStat(CourseStat courseStat) {
		this.courseStat = courseStat;
	}

	/**
	 * @return the enrolledStudents
	 */
	public List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	/**
	 * @param enrolledStudents the enrolledStudents to set
	 */
	public void setEnrolledStudents(List<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	/**
	 * @return the expelledStudents
	 */
	public List<Student> getExpelledStudents() {
		return expelledStudents;
	}

	/**
	 * @param expelledStudents the expelledStudents to set
	 */
	public void setExpelledStudents(List<Student> expelledStudents) {
		this.expelledStudents = expelledStudents;
	}

	/**
	 * @return the lastId
	 */
	public static int getLastId() {
		return lastId;
	}
	
	/**
	 * @return the applications
	 */
	public List<Application> getApplications() {
		return applications;
	}

	/**
	 * @param applications the applications to set
	 */
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	/* Methods */
	
	/**
	 * Method that creates a unit associated to a course
	 * @param title
	 * @param desc
	 * @param hidden
	 * @return
	 */
	public CourseElement createUnit(String title, String desc, boolean hidden){
		CourseElement ce;
		
		
		ce = new Unit(title, desc, hidden, this);
		
		for(CourseElement aux: this.courseElements){
			if(ce.equals(aux)){
				return null;
			}
		}
		this.courseElements.add(ce);
		return ce;
	}
	/**
	 * Method that deletes a unit
	 * @param u unit to delete
	 * @return true if the unit has been deleted false if not
	 */
	public boolean deleteUnit(Unit u){
		if(this.courseElements.contains(u)){
			for(CourseElement aux: u.getCourseElements()){
				if(aux instanceof Unit){
					Unit other = (Unit) aux;
					u.deleteSubUnit(other);
				}
				if(aux instanceof Note){
					Note other = (Note) aux;
					u.deleteNote(other);
				}
				if(aux instanceof Exercise){
					Exercise other = (Exercise) aux;
					u.deleteExercise(other);
				}
			}
			this.courseElements.remove(u);
			return true;
		}
		return false;
	}
	
	/**
	 * Method that informs if a student is enrolled in a course
	 * @param student
	 * @return true if the student is accepted false if not
	 */
	public boolean isEnrolledStudent(Student student){
		if(enrolledStudents.contains(student)){
			return true;
		}
		return false;
	}
	
	/**
	 * Method that informs if a student has been expelled from a course
	 * @param student
	 * @return true if the student has been false if not
	 */
	public boolean isExpelledStudent(Student student){
		if(expelledStudents.contains(student)){
			return true;
		}
		return false;
	}
	/**
	 * Method that informs if a student has not been admitted from a course
	 * @param student
	 * @return true if the student has been false if not
	 */
	
	public boolean isNotAdmittedStudent(Student student){
		if(this.isExpelledStudent(student) == false && this.isEnrolledStudent(student) == false){
			return true;
		}
		return false;
	}
	/**
	 * Method that returns the average mark associated to a course
	 * @return double mark out of 10
	 */
	
	public double getTotalAverage(){
		if(this.getCourseStat() == null){
			return -1;
		}
		return this.getCourseStat().getAverageMark();
	}
	
	/**
	 * Method that expels a student from a course
	 * @param s Student
	 * @return true if the student has been expelled false if not
	 */
	public boolean expellStudent(Student s) throws Exception{
		String str1, str2;
		if(this.isEnrolledStudent(s)){
			this.enrolledStudents.remove(s);
			this.expelledStudents.add(s);
			s.getEnrolledCourses().remove(this);
			s.getExpelledCourses().add(this);
			try {
				str1 = "Expelled from course: " + this.getTitle();
				str2 = "Hello " + s.getName() + ".\n You have been expelled from course "
					+ this.getTitle() + ". Don't answer this message (auto-generated).";
				
				if(str1.charAt(0) == 'w' || str1.charAt(0) == 'W'){
					throw new FailedInternetConnectionException(str1);
				}
				if(EmailSystem.isValidEmailAddr(s.getEmail()) == false){
					throw new InvalidEmailAddressException(s.getEmail());
				}
				EmailSystem.send(s.getEmail(), str1, str2, true);
				return true;
			} catch (InvalidEmailAddressException e){
				System.out.println(e.getMessage());
				return false;
			}catch (FailedInternetConnectionException e){
				System.out.println(e.getMessage());
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Method that re-admits a student in a course
	 * @param s Student to re-admit
	 * @return true if the student has been re-admitted false if not
	 * @throws Exception 
	 */
	public boolean readmitStudent(Student s) throws Exception{
		String str1, str2;
		if(this.isExpelledStudent(s)){
			this.expelledStudents.remove(s);
			this.enrolledStudents.add(s);
			s.getExpelledCourses().remove(this);
			s.getEnrolledCourses().add(this);
			try {
				str1 = "Re-admitted to course: " + this.getTitle();
				str2 = "Hello " + s.getName() + ".\n You have been re-admited to the course "
					+ this.getTitle() + ". Don't answer this message (auto-generated).";
				
				if(str1.charAt(0) == 'w' || str1.charAt(0) == 'W'){
					throw new FailedInternetConnectionException(str1);
				}
				if(EmailSystem.isValidEmailAddr(s.getEmail()) == false){
					throw new InvalidEmailAddressException(s.getEmail());
				}
				EmailSystem.send(s.getEmail(), str1, str2, true);
				return true;
			} catch (InvalidEmailAddressException e){
				System.out.println(e.getMessage());
				return false;
			}catch (FailedInternetConnectionException e){
				System.out.println(e.getMessage());
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Method that search an application of a student passed by argument
	 * @param s student of the application to search
	 * @return Application if the student has applied for the course null if not
	 */
	public Application searchApplication(Student s){
		for(Application aux: this.getApplications()){
			if(aux.getAppliedStudent().equals(s)){
				return aux;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	
	
	

	/**
	 *  Function equals that compares the id of two courses
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	
}
