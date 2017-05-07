/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.exercise;

import java.io.Serializable;
import java.util.*;



public class MultiQuestion extends MUQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Option> solution;
	private int numSol;
	
	/**
	 * Constructor of MultiQuestion
	 * @param title
	 * @param weight
	 * @param randomOrder
	 * @param exer
	 */
	public MultiQuestion(String title, double weight, boolean randomOrder, Exercise exer) {
		super(title, weight, randomOrder, exer);
		this.solution = new ArrayList<Option>();
		this.numSol = 0;
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
	 * @return the numSol
	 */
	public int getNumSol() {
		return numSol;
	}

	/**
	 * @param numSol the numSol to set
	 */
	public void setNumSol(int numSol) {
		this.numSol = numSol;
	}

	/**
	 * Method to add solution
	 * @param o
	 * @return
	 */
	
	public boolean addSolution(Option o){
		if (!this.getAnswers().contains(o) || this.solution.contains(o)){
			System.out.println("cachooopo");
			return false;
		}
		System.out.println("cachooopo22222");
		this.solution.add(o);
		this.numSol ++;
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
		if(this.solution.contains(sol)){
			this.solution.remove(sol);
			this.numSol --;
		}
		if(this.getAnswers().contains(sol)){
			this.getAnswers().remove(sol);
			this.setNumAns(this.getNumAns()-1);
			return true;
		}
		return false;
	}
	
}
