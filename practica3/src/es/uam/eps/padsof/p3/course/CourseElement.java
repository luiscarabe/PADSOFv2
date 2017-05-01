/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.course;

import java.io.Serializable;

import es.uam.eps.padsof.emailconnection.EmailSystem;
import es.uam.eps.padsof.emailconnection.FailedInternetConnectionException;
import es.uam.eps.padsof.emailconnection.InvalidEmailAddressException;
import es.uam.eps.padsof.p3.user.Student;

public abstract class CourseElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String desc;
	private boolean hidden;
	private static int lastId = 0;
	private Course course;
	
	/**
	 * Constructor of CourseElement
	 * @param title
	 * @param desc
	 * @param hidden
	 * @param course
	 */
	public CourseElement(String title, String desc, boolean hidden, Course course) {
		lastId = lastId + 1;
		this.id = lastId;
		this.title = title;
		this.desc = desc;
		this.hidden = hidden;
		this.course = course;
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
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}



	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}



	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		String str1, str2;
		if(hidden == false){
			this.hidden = hidden;
			try {
				for(Student aux : this.getCourse().getEnrolledStudents()){
					str1 = "New notification at " + this.course.getTitle();
					str2 = "Hello:\nThere is a new element at " + this.course.getTitle() + ". "
							+ this.getTitle();
					
					if(str1.charAt(0) == 'w' || str1.charAt(0) == 'W'){
						throw new FailedInternetConnectionException(str1);
					}
					if(EmailSystem.isValidEmailAddr(aux.getEmail())){
						throw new InvalidEmailAddressException(aux.getEmail());
					}
					EmailSystem.send(aux.getEmail(), str1, str2, true);
				}
				
			} catch (InvalidEmailAddressException e){
				System.out.println(e.getMessage());
			}catch (FailedInternetConnectionException e){
				System.out.println(e.getMessage());
			}
		}else {
			for(CourseElement aux1: this.course.getCourseElements()){
				if(aux1 instanceof Unit){
					for(CourseElement aux2: ((Unit) aux1).getCourseElements()){
						if(this.equals(aux2)){
							if(aux1.isHidden() == true){
								return;
							}
						}
					}
				}
			}
		}
		this.hidden = hidden;
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



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseElement other = (CourseElement) obj;
		if(!course.equals(other.course)){
		    return false;
		}
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}