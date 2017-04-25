/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.exercise;

import java.io.Serializable;
import java.util.*;


public class UniqQuestion extends MUQuestion implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Option> solution;
	
	/**
	 * Constructor of UniqQuestion
	 * @param title
	 * @param weight
	 * @param randomOrder
	 * @param exer
	 */
	public UniqQuestion(String title, double weight, boolean randomOrder, Exercise exer) {
		super(title, weight, randomOrder, exer);
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
	 * @param o
	 * @return
	 */
	public boolean addSolution(Option o) {
		if (!this.getAnswers().contains(o)){
			return false;
		}
		if(this.solution.isEmpty() == false){
			this.solution.clear();
		}
		this.solution.add(o);
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
	
	/**
	 * Method to delete an option
	 * @param sol
	 * @return true if it has been successfully deleted, false if not
	 */
	
	public boolean deleteOption(Option sol){
		if(this.solution.isEmpty() == false){
			if(this.solution.contains(sol)){
				this.solution.clear();
			}
		}
		if(this.getAnswers().contains(sol)){
			this.getAnswers().remove(sol);
			this.setNumAns(this.getNumAns()-1);
			return true;
		}
		return false;
	}
	
}
