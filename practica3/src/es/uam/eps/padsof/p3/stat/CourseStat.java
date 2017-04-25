/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.stat;

import java.io.Serializable;
import java.util.*;

public class CourseStat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double averageMark;
	private List<CMark> cMarks;
	/**
	 * Constructor of CourseStat
	 */
	public CourseStat() {
		this.averageMark = -1;
		this.cMarks = new ArrayList<CMark>();
	}
	/**
	 * @return the averageMark
	 */
	public double getAverageMark() {
		return averageMark;
	}
	/**
	 * @param averageMark the averageMark to set
	 */
	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
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
	
	/**
	 * Method to add a cMark
	 * @param cMark
	 */
	public void addcMark(CMark cMark){
		if(!this.cMarks.contains(cMark)){
			this.cMarks.add(cMark);
		}
	}
	
	/**
	 * Method to calculate the average Mark of the course
	 */
	
	public void calculateCstat(){
		double aux = 0;
		
		for (CMark c: this.cMarks){
			aux += c.getCourseMark();
		}
		aux /= this.cMarks.size();
		this.averageMark = aux;
	}
	
}
