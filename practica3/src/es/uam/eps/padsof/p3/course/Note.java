/**
* @author Luis Carabe 
* @author Alejo Polania 
*/
package es.uam.eps.padsof.p3.course;

import java.io.Serializable;

public class Note extends CourseElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;

	/**
	 * Constructor of Note
	 * @param title
	 * @param desc
	 * @param hidden
	 * @param text
	 * @param course
	 */
	public Note(String title, String desc, boolean hidden, String text, Course course) {
		super(title, desc, hidden, course);
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}
	
	
	
}
