/**
 * 
 */
package es.uam.eps.padsof.p3.exercise.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.UniqQuestion;

/**
 * @author Alejo Luis
 *
 */
public class UniqQuestionTester {
	UniqQuestion u;
	Exercise e;
	Course c;
	Option o, o1, o2, o3, o4;
	Boolean b1;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c = new Course("BC", "Basic computer");
		e = new Exercise("Exercise1", "descExercise1", true, c);		
		u = new UniqQuestion("How many bits correspond to one byte?", 4.5, false, e);
		
		u.addOption(new Option("2"));
		u.addOption(new Option("8"));
		u.addOption(new Option("5"));
		o3 = new Option("Four");
		
		b1 = u.addSolution(o1);

	}

	
	
	/**
	 * Test method trying to add an existing option.(success)
	 */
	@Test
	public void testAddOptionCorrect() {
		assertTrue(u.getAnswers().contains(o));
		assertTrue(u.getAnswers().contains(o1));
		assertTrue(u.getAnswers().contains(o2));
	}
	
	/**
	 * Test method trying to add a non-existing option.(fail)
	 */
	@Test
	public void testAddOptionError() {
		assertFalse(u.getAnswers().contains(o3));
	}
	
	/**
	 * Test method trying to delete an existing option.(success)
	 */
	@Test
	public void testDeleteOptionCorrect() {
		assertTrue(u.deleteOption(o2));
		assertFalse(u.getAnswers().contains(o2));
	}

	/**
	 * Test method trying to delete a non-existing option.(fail)
	 */
	@Test
	public void testDeleteOptionError() {
		o4 = new Option("Five");
		assertFalse(u.deleteOption(o4));
		assertFalse(u.getAnswers().contains(o4));
	}
	
	/**
	 * Test method for a solution from the existing options.(success)
	 */
	@Test
	public void testAddSolutionOptionECorrect() {
		assertTrue(u.addSolution(o2));
		assertTrue(u.getSolution().contains(o2));
		
	}
	
	/**
	 * Test method for a solution from the existing options.(fail)
	 */
	@Test
	public void testAddSolutionOptionError() {
		assertFalse(u.addSolution(o3));
		assertFalse(u.getSolution().contains(o3));
		o4 = new Option("Five");
	}
	
	
	/**
	 * Test method deleting an existing solution.(success)
	 */
	@Test
	public void testDeleteSolutionOptionCorrect() {
		assertTrue(u.deleteSolution(o1));
		assertFalse(u.getSolution().contains(o1));
	}

	/**
	 * Test method deleting a non-existing solution.(fail)
	 */
	@Test
	public void testDeleteSolutionOptionError() {
		assertFalse(u.deleteSolution(o2));
		assertFalse(u.getSolution().isEmpty());
	}
	
	
	/**
	 * Test method that shuffles the list of options successfully.
	 */
	@Test
	public void testRandomizeOrderCorrect(){
		u.getAnswers().clear();
		u.addOption(new Option("8"));
		u.addOption(new Option("5"));
		u.addOption(new Option("7"));
		
		u.setRandomOrder(true);
		
		/* 
		 * This Method could not work sometimes. Re-run.
		 * In fact, this "error" prove that Collections.shuffle works.
		 */
		assertTrue(u.randomizeOrder());
		assertTrue(!u.getAnswers().get(0).equals(o1) || !u.getAnswers().get(1).equals(o2)
				|| !u.getAnswers().get(2).equals(o3));
	}
	
	/**
	 * Test method that tries to shuffle the list of options.
	 */
	@Test
	public void testRandomizeOrderError(){
		u.getAnswers().clear();
		u.addOption(new Option("8"));
		u.addOption(new Option("5"));
		u.addOption(new Option("7"));
		
		u.setRandomOrder(false);
		
		/* This Method could not work sometimes. Re-run*/
		assertFalse(u.randomizeOrder());
		assertTrue(u.getAnswers().get(0).equals(o1) && u.getAnswers().get(1).equals(o2)
				&& u.getAnswers().get(2).equals(o3));
	}

	/**
	 * Test method for {@link es.uam.eps.padsof.p3.exercise.Question#getExer()}.
	 */
	@Test
	public void testGetExer() {
		assertEquals(e, u.getExer());
	}

}
