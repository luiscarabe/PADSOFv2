/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p3.exercise;

import java.io.Serializable;
import java.util.*;


public abstract class MUQuestion extends Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Option> answers;
	private int numAns;
	private boolean randomOrder;

	/**
	 * Constructor of MUQuestion
	 * @param title
	 * @param weight
	 * @param randomOrder
	 * @param exer
	 */
	public MUQuestion(String title, double weight, boolean randomOrder, Exercise exer) {
		super(title, weight, exer);
		this.answers =  new ArrayList<Option>();
		this.numAns = 0;
		this.randomOrder = randomOrder;
	}
	/**
	 * @return the answers
	 */
	public List<Option> getAnswers() {
		return answers;
	}
	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Option> answers) {
		this.answers = answers;
	}
	/**
	 * @return the numAns
	 */
	public int getNumAns() {
		return numAns;
	}
	/**
	 * @param numAns the numAns to set
	 */
	public void setNumAns(int numAns) {
		this.numAns = numAns;
	}
	/**
	 * @return the randomOrder
	 */
	public boolean isRandomOrder() {
		return randomOrder;
	}
	/**
	 * @param randomOrder the randomOrder to set
	 */
	public void setRandomOrder(boolean randomOrder) {
		this.randomOrder = randomOrder;
	}
	
	/**
	 * Method to add an option
	 * @param sol
	 */
	
	public void addOption(Option sol){
		this.answers.add(sol);
		this.numAns ++;
	}
	
	/**
	 * Method that allows professors to randomly select the order of question
	 * @return true if shuffles the questions false if not
	 */
	public boolean randomizeOrder(){
		if(this.randomOrder == true){
			Collections.shuffle(this.answers);
			return true;
		}
		return false;
	}
	/**
	 * Abstract method to delete an option
	 * @param o
	 * @return true if successful, false if not
	 */
	
	public abstract boolean deleteOption(Option o);
	
	/**
	 * Abstract method to delete a solution
	 * @param sol
	 * @return true if successful, false if not
	 */
	
	public abstract boolean deleteSolution(Option sol);
	
	public Option searchOption(String name){
		for(Option aux: this.answers){
			if(aux.getOption().equals(name)){
				return aux;
			}
		}
		return null;
	}

	
}
