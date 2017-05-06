/**
 * 
 */
package es.uam.eps.padsof.p3.exercise.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.Option;

/**
 * @author Alejo Luis
 *
 */
public class MultiQuestionTester {
	Course c;
	Exercise e;
	MultiQuestion m;
	Option o, o1, o2, o3;
	
	@Before
	public void setUp() throws Exception {
		c = new Course("CN", "All numbers");
		e = new Exercise("Exercise 1", "Insert description here", false, c);
		m = new MultiQuestion("Select all the natural numbers.", 3.5, false, e);
	}

	/**
	 * Test method for to see if the weight is correctly initialized
	 */
	@Test
	public void testGetIniWeight() {
		assertTrue(m.getWeight() == 3.5);
	}
	
	/**
	 * Test method for the weight setter
	 */
	
	@Test
	public void testSetWeight() {
		m.setWeight(4.2);
		assertTrue(m.getWeight() == 4.2);
	}
	
	/**
	 * Test method to see if the title is correctly initialized
	 */
	@Test
	public void testGetIniTitle() {
		assertEquals(m.getTitle(), "Select all the natural numbers.");
	}
	
	/**
	 *Test method for the title setter
	 */
	@Test
	public void testSetTitle(){
		m.setTitle("Select all the natural numbers (2 correct answers).");
		assertEquals(m.getTitle(), "Select all the natural numbers (2 correct answers).");
	}
	
	/**
	 * Test method for correct addOption
	 */
	@Test
	public void testAddOption() {
		
		m.addOption(new Option("-2"));
		m.addOption(new Option("8"));
		m.addOption(new Option("5"));
		
		assertTrue(m.getAnswers().contains(o));
		assertTrue(m.getAnswers().contains(o1));
		assertTrue(m.getAnswers().contains(o2));
		assertTrue(m.getNumAns() == 3);
	}
	/**
	 * Test method for incorrect addOption
	 */
	@Test
	public void testIncAddOption(){
		o3 = new Option("Four");
		assertFalse(m.getAnswers().contains(o3));
	}
	
	/**
	 * Test method for delete option
	 */
	@Test
	public void testDeleteOption() {
		m.addOption(new Option("2"));
		assertTrue(m.deleteOption(o));
		assertFalse(m.getAnswers().contains(o));
		
	}
	
	/**
	 * Test method for incorrect delete option
	 */
	@Test
	public void testIncDeleteOption() {
		o = new Option("Four");
		assertFalse(m.deleteOption(o));
		assertFalse(m.getAnswers().contains(o));
		
	}
	
	/**
	 * Test method for add solution
	 */
	@Test
	public void testAddSolution() {
		m.addOption(new Option("8"));
		m.addOption(new Option("5"));
		assertTrue(m.addSolution(o1));
		assertTrue(m.addSolution(o2));
		assertTrue(m.getSolution().contains(o1));
		assertTrue(m.getSolution().contains(o2));
		assertTrue(m.getNumSol() == 2);
	}
	
	/**
	 * Test method for incorrect add solution
	 */
	@Test
	public void testIncAddSolution() {
		o = new Option("Four");
		assertFalse(m.addSolution(o));
		assertTrue(m.getNumSol() == 0);
	}
	
	/**
	 * Test method for delete solution
	 */
	@Test
	public void testDeleteSolution() {
		m.addOption(new Option("5"));
		m.addSolution(o2);
		assertTrue(m.deleteSolution(o2));
		assertFalse(m.getSolution().contains(o2));
	}
	
	/**
	 * Test method for invalid delete solution
	 */
	@Test
	public void testInvDeleteSolution() {
		o2 = new Option("Four");
		assertFalse(m.deleteSolution(o2));
		assertFalse(m.getSolution().contains(o2));
	}

	
	/**
	 * Test method for isRandomOrder
	 */
	@Test
	public void testIsRandomOrder() {
		assertTrue(m.isRandomOrder() == false);
	}
	
	/**
	 * Test method that shuffles the list of options successfully.
	 */
	@Test
	public void testRandomizeOrderCorrect(){
		m.addOption(new Option("8"));
		m.addOption(new Option("5"));
		
		m.setRandomOrder(true);
		
		/* 
		 * This Method could not work sometimes. Re-run.
		 * In fact, this "error" prove that Collections.shuffle works.
		 */
		assertTrue(m.randomizeOrder());
		assertTrue(!m.getAnswers().get(0).equals(o1) || !m.getAnswers().get(1).equals(o2)
				|| !m.getAnswers().get(2).equals(o3));
	}
	
	/**
	 * Test method that tries to shuffle the list of options.
	 */
	@Test
	public void testRandomizeOrderError(){
		m.addOption(new Option("8"));
		m.addOption(new Option("8"));
		m.addOption(new Option("5"));
		
		m.setRandomOrder(false);
		
		/* This Method could not work sometimes. Re-run*/
		assertFalse(m.randomizeOrder());
		assertTrue(m.getAnswers().get(0).equals(o1) && m.getAnswers().get(1).equals(o2)
				&& m.getAnswers().get(2).equals(o3));
	}

}
