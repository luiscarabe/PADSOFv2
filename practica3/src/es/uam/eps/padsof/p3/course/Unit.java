/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.course;

import java.io.Serializable;
import java.util.*;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.stat.Answer;

public class Unit extends CourseElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<CourseElement> courseElements;

	/**
	 * Constructor of Unit
	 * @param title
	 * @param desc
	 * @param hidden
	 * @param course
	 */

	public Unit(String title, String desc, boolean hidden, Course course) {
		super(title, desc, hidden, course);
		this.courseElements = new ArrayList<CourseElement>();
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
	
	
	
	/* Methods */

	/**
	 * Method that creates a unit associated to a course
	 * @param title
	 * @param desc
	 * @param hidden
	 * @return The unit or null if something went wrong
	 */
	public Unit createSubUnit(String title, String desc, boolean hidden){
		Unit ce;
		
		
		ce = new Unit(title, desc, hidden, this.getCourse());
		
		for(CourseElement aux: this.getCourse().getCourseElements()){
			if(ce.equals(aux)){
				return null;
			}
		}
		this.courseElements.add(ce);
		this.getCourse().getCourseElements().add(ce);
		return ce;
	}
	
	/**
	 * Method that allows professors to delete subunits
	 * @param u subunit to delete
	 * @return true if the subunit has been deleted false if not
	 */
	public boolean deleteSubUnit(Unit u){
		if(this.courseElements.contains(u)){
			for(CourseElement ce: u.courseElements){
				if(ce instanceof Note){
					Note other = (Note) ce;
					u.deleteNote(other);
				}
				if(ce instanceof Exercise){
					Exercise other = (Exercise) ce;
					u.deleteExercise(other);
				}
			}
			this.getCourseElements().remove(u);
			this.getCourse().getCourseElements().remove(u);
			return true;
		}
		return false;
	}
	
	/**
	 * Method that creates a note associated to a unit and its course
	 * @param title
	 * @param desc
	 * @param hidden
	 * @return The Note or null if something went wrong
	 */
	public Note createNote(String title, String desc, boolean hidden, String text){
		Note ce;
		
		
		ce = new Note(title, desc, hidden, text, this.getCourse());
		
		for(CourseElement aux: this.getCourse().getCourseElements()){
			if(ce.equals(aux)){
				return null;
			}
		}
		this.courseElements.add(ce);
		this.getCourse().getCourseElements().add(ce);
		return ce;
	}
	
	/**
	 * Method that deletes a note from a unit and its course
	 * @param n
	 * @return true if successful, false if not
	 */
	public boolean deleteNote(Note n){
		if(this.courseElements.contains(n)){
			this.courseElements.remove(n);
			this.getCourse().getCourseElements().remove(n);
			return true;
		}
		return false;
	}
	/**
	 * Method that creates a note associated to a unit and its course
	 * @param title
	 * @param desc
	 * @param hidden
	 * @return The exercise or null if something went wrong
	 */
	public Exercise createExercise(String title, String desc, boolean hidden){
		Exercise ce;
		
		
		ce = new Exercise(title, desc, hidden, this.getCourse());
		
		for(CourseElement aux: this.getCourse().getCourseElements()){
			if(ce.equals(aux)){
				return null;
			}
		}
		
		this.courseElements.add(ce);
		this.getCourse().getCourseElements().add(ce);
		return ce;
	}
	
	
	/**
	 * Method that allows a professor to delete an exercise from a unit and its course
	 * @param e exercise to delete
	 * @return true if the exercise has been deleted false if not
	 */
	public boolean deleteExercise(Exercise e){
		if(this.courseElements.contains(e)){
			if(e.getAnswers().isEmpty() != true){
				for(Answer a: e.getAnswers()){
					a.getStudent().getAnswers().remove(a);
				}
			}
			this.courseElements.remove(e);
			this.getCourse().getCourseElements().remove(e);
			
			return true;
		}
		return false;
	}
	
	/**
	 * Method that hide a unit and all its elements
	 */
	public void unitHide(){
		List<CourseElement> sub = new ArrayList<CourseElement>();
		for(CourseElement aux: this.getCourseElements()){
			if(aux instanceof Unit){
				sub.add((Unit) aux);
			}else{
				aux.setHidden(true);
			}
		}
		for(CourseElement aux: sub){
			((Unit) aux).unitHide();
		}
		this.setHidden(true);
	}

	@Override
	public String toString() {
		return this.getTitle();
	}
	
	
	
}
