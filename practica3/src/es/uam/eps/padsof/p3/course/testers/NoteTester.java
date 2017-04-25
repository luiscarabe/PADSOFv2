/**
 * 
 */
package es.uam.eps.padsof.p3.course.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Note;

/**
 * @author Alejo Luis
 *
 */
public class NoteTester {
	Note n;
	Note n1;
	Note n2;
	Course c;

	@Before
	public void setUp() throws Exception{
		c = new Course("SOPER", "Amazing description");
		n = new Note("Title", "Testing note", true, "This is a very important note", c);
		n1 = new Note("Title", "Testing note", false, "I've changed the text", c);
		n2 = new Note("NotSameTitle", "Testing note", true, "This is a very important note", c);
	}
	/**
	 * Test method for get Text
	 */
	@Test
	public void testGetText() {
		assertEquals(n.getText(), "This is a very important note");
	}

	/**
	 * Test method for set Text
	 */
	@Test
	public void testSetText() {
		n.setText("I've changed the text");
		assertEquals(n.getText(), "I've changed the text");
	}

	/**
	 * Test method for ishidden
	 */
	@Test
	public void testIsHidden() {
		assertTrue(n.isHidden());
	}
	
	/**
	 * Test method for sethidden
	 */
	@Test
	public void testSetHidden() {
		n.setHidden(false);
		assertFalse(n.isHidden());
	}

	/**
	 * Test method for equal
	 */
	@Test
	public void testEqualsObject() {
		assertTrue(n.equals(n1));
	}
	/**
	 * Test method for invaled equal
	 */
	@Test
	public void testInvEqualsObject() {
		assertFalse(n.equals(n2));
	}

}
