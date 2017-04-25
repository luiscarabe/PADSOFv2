/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.exercise;

import java.io.Serializable;
import java.util.*;


public class TFQuestion extends Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Option> solution;

	/**
	 * Constructor of TFQuestion
	 * @param title
	 * @param weight
	 * @param exer
	 */
	public TFQuestion(String title, double weight, Exercise exer) {
		super(title, weight, exer);
		this.solution = new ArrayList<Option>();
	}

	/**
	 * @return the solution
	 */
	public List<Option> getSolution() {
		return solution;
	}


	/**
	 * Method to add a solution
	 * @param s
	 * @return true if created successfully, false if not
	 */
	public boolean setSolution(Option solution) {
		if(solution.getOption().equals("f") || solution.getOption().equals("t")
				|| solution.getOption().equals("F") || solution.getOption().equals("T")){
			this.solution.clear();
			this.solution.add(solution);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Method to remove a solution
	 * @param sol
	 * @return true if it has been successfully deleted, false if not
	 */
	
	public boolean deleteSolution(Option sol){
		if(this.solution.contains(sol)){
			this.solution.clear();
			return true;
		}
		return false;
	}
	
	
}
