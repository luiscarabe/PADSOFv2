/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.exercise;

import java.io.Serializable;
import java.util.*;


public class OpenQuestion extends Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Option> solution;


	/**
	 * Constructor of OpenQuestion
	 * @param title
	 * @param weight
	 * @param exer
	 */
	public OpenQuestion(String title, double weight, Exercise exer) {
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
	 * @param solution the solution to set
	 */
	public void setSolution(List<Option> solution) {
		this.solution = solution;
	}

	/**
	 * Method to add a solution
	 * @param sol
	 */

	public boolean addSolution(Option sol){
		for(Option aux: this.getSolution()){
			if(aux.equals(sol)){
				return false;
			}
		}
		this.solution.add(sol);
		return true;
	}
	
	/**
	 * Method to remove a solution
	 * @param sol
	 * @return true if it has been successfully deleted, false if not
	 */
	
	public boolean deleteSolution(Option sol){
		if(this.solution.contains(sol)){
			this.solution.remove(sol);
			return true;
		}
		return false;
	}

}
