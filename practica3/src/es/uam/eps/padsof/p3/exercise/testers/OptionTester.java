/**
 * 
 */
package es.uam.eps.padsof.p3.exercise.testers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.exercise.Option;

/**
 * @author Alejo Luis
 *
 */
public class OptionTester {
	Option o1, o2, o3, o4;
	
	@Before
	public void setUp(){
		o1 = new Option("Something1");
		o2 = new Option("Something1");
		o3 = new Option("Something2");
		o4 = null;
	}
	
	
	/**
	 * Test method for get Option
	 */
	@Test
	public void testGetOption() {
		assertEquals(o1.getOption(), "Something1");
	}

	/**
	 * Test method for set Option
	 */
	@Test
	public void testSetOption() {
		o1.setOption("Something2");
		assertEquals(o1.getOption(), "Something2");
	}

	/**
	 * Test method for equals
	 */
	@Test
	public void testEqualsObject() {
		assertTrue(o1.equals(o2));
		assertFalse(o1.equals(o3));
		assertFalse(o1.equals(o4));
		
	}

}
