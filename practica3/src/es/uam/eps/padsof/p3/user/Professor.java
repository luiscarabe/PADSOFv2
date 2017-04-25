/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.user;

import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.educagram.Educagram;

import java.io.Serializable;
import java.util.*;

public class Professor extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Course> allCourses;
	
	/**
	 * Constructor of Professor
	 * @param name
	 * @param email
	 * @param password
	 */
	public Professor(String name, String email, String password) {
		super(name, email, password);
		allCourses = new ArrayList<Course>();
		
	}
	
	/**
	 * @return the allCourses
	 */
	public List<Course> getAllCourses() {
		return allCourses;
	}
	
	/* Methods */
	
	/**
	 * Create a new Course
	 * @param title
	 * @param desc
	 * @return Course if the creation of the course succeed null if not
	 */
	public Course createCourse(String title, String desc){
		Course c;
		
		c = new Course(title, desc);
		
		for(Course aux: this.allCourses){
			if(aux.equals(c)){
				return null;
			}
		}
		this.getAllCourses().add(c);
		
		Educagram.getInstance().setCourses(this.allCourses);
		return c;
		
	}
	
	/**
	 * Method that allows the professor to accept or decline an application
	 * @param a
	 * @param bool
	 */
	public void confirmApplication(Application a, boolean bool) throws Exception{
		if(bool == true){
			a.acceptApplication();
		}else {
			a.declineApplication();
		}
	}
	
	/**
	 * Method that allows the professor to expel a student from a course
	 * @param s Student to expel
	 * @param c Course
	 * @return true if successful, false if not
	 * @throws Exception 
	 */
	public boolean expellStudent(Student s, Course c) throws Exception{
		if(c == null || s == null){
			return false;
		}
		return c.expellStudent(s);
	}
	
	/**
	 * Method that allows the professor to re-admit a student in a course
	 * @param s Student to re-admit
	 * @param c Course
	 * @return
	 * @throws Exception 
	 */
	public boolean readmitStudent(Student s, Course c) throws Exception{
		return c.readmitStudent(s);
	}
	
	
}
