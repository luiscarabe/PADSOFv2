/**
 * 
 */
package es.uam.eps.padsof.p3.course.testers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.exercise.Exercise;

/**
 * @author Alejo Luis
 *
 */
public class UnitTester {
	Course c1, c2;
	Unit u1, u2, u3;
	/* SubUnits */
	Unit sub1, sub2, sub3, sub4;
	Note n1, n2, n3, n4;
	Exercise e1, e2, e3, e4, e5;
	
	
	
	@Before
	public void setUp(){
		c1 = new Course("Course 1", "descCourse1");
		c2 = new Course("Course 2", "descCourse2");
		u1 = new Unit("Unit 1", "desUnit1", false, c1);
		n1 = new Note("Note 1", "descNote1", false, "mainText1", c1);
		e1 = new Exercise("Exercise 1", "descExercise1", false, c1);
		
		c1.getCourseElements().add(u1);
		c1.getCourseElements().add(n1);
		c1.getCourseElements().add(e1);
		
		sub3 = u1.createSubUnit("SubUnit 3", "desSubUnit3", false);
		n3 = u1.createNote("Note 3", "descNote3", false, "mainText3");
		e3 = u1.createExercise("Exercise 3", "desExercise3", false);
		
		sub4 = u1.createSubUnit("SubUnit 4", "desSubUnit4", false);
		e4 = u1.createExercise("Exercise 4", "descExercise 4", false);
		e5 = sub4.createExercise("Exercise 5", "descExercise 5", false);
		n4 = sub4.createNote("Note 4", "descNote4", false, "mainText4");
	}

	/**
	 * Test method that success in the creation of a subunit associate to a unit.
	 */
	@Test
	public void testCreateSubUnitCorrect() {
		sub1 = u1.createSubUnit("SubUnit 1", "desSubUnit1", false);
		assertNotNull(sub1);
		assertEquals(c1, sub1.getCourse());
		assertTrue(c1.getCourseElements().contains(sub1));		
	}
	
	/**
	 * Test method that fails in the creation of a subunit (because it already exists) associate to a unit.
	 */
	@Test
	public void testCreateSubUnitError() {
		sub2 = u1.createSubUnit("Unit 1", "desSubUnit1", false);
		assertNull(sub2);
		assertFalse(c1.getCourseElements().contains(sub2));
	}

	/**
	 * Test method when a the professor tries to delete a subunit that is in the unit.
	 */
	@Test
	public void testDeleteSubUnitCorrect() {
		assertTrue(u1.deleteSubUnit(sub3));
		assertFalse(u1.getCourseElements().contains(sub3));
		assertFalse(c1.getCourseElements().contains(sub3));
		
	}
	
	/**
	 * Test method when a the professor tries to delete a subunit that isn't in the unit.
	 */
	@Test
	public void testDeleteSubUnitError() {
		sub1 = new Unit("SubUnit 7", "desSubUnit1", false, c1);
		assertFalse(u1.deleteSubUnit(sub1));
	}

	/**
	 * Test method that success in the creation of a note associate to a unit.
	 */
	@Test
	public void testCreateNoteCorrect() {
		n2 = u1.createNote("Note 2", "descNote2", false, "mainText2");
		assertNotNull(n2);
		assertEquals(c1, n2.getCourse());
		assertTrue(c1.getCourseElements().contains(n2));	
	}
	
	/**
	 * Test method that fails in the creation of a note (because it already exists) associate to a unit.
	 */
	@Test
	public void testCreateNoteError() {
		/* not avoid CourseElements with the same title (Note 1) */
		n2 = u1.createNote("Note 1", "desNote2", false, "mainText2");
		assertNull(n2);
		assertFalse(c1.getCourseElements().contains(n2));
	}

	/**
	 * Test method when a the professor tries to delete a note that is in the unit.
	 */
	@Test
	public void testDeleteNoteCorrect() {
		assertTrue(u1.deleteNote(n3));
		assertFalse(u1.getCourseElements().contains(n3));
		assertFalse(c1.getCourseElements().contains(n3));
	}
	
	/**
	 * Test method when a the professor tries to delete a note that isn't in the unit.
	 */
	@Test
	public void testDeleteNoteError() {
		n2 = new Note("Note 1", "desNote1", false, "mainText", c1);
		assertFalse(u1.deleteNote(n2));
	}

	/**
	 * Test method that success in the creation of an exercise associate to a unit.
	 */
	@Test
	public void testCreateExerciseCorrect() {
		e2 = u1.createExercise("Exercise 2", "descExercise1", false);
		assertNotNull(e2);
		assertEquals(c1, e2.getCourse());
		assertTrue(c1.getCourseElements().contains(e2));	
	}
	
	/**
	 * Test method that fails in the creation of an exercise (because it already exists) associate to a unit.
	 */
	@Test
	public void testCreateExerciseError() {
		e2 = u1.createExercise("Exercise 1", "descExercise 2", false);
		assertNull(e2);
		assertFalse(c1.getCourseElements().contains(e2));
	}

	/**
	 * Test method when a the professor tries to delete a exercise that is in the unit.
	 */
	@Test
	public void testDeleteExerciseCorrect() {
		assertTrue(u1.deleteExercise(e3));
		assertFalse(u1.getCourseElements().contains(e3));
		assertFalse(c1.getCourseElements().contains(e3));
	}
	
	/**
	 * Test method when a the professor tries to delete a note that isn't in the unit.
	 */
	@Test
	public void testDeleteExerciseError() {
		e2 = new Exercise("Exercise 2", "descExercise1", false, c1);
		assertFalse(u1.deleteExercise(e2));
	}
	
	/**
	 * Test method used to hide all course elements owned by a unit.
	 */
	@Test
	public void testUnitHide() {
	    u1.unitHide();
	    for(CourseElement ce: u1.getCourseElements()){
	        assertTrue(ce.isHidden());
	    }
	    assertTrue(u1.isHidden());
	}

	/**
	 * Test method to compare two Unit objects. Successful comparison (equal title and course).
	 */
	@Test
	public void testEqualsObjectCorrect() {
	    u2 = new Unit("Unit 1", "desUnit2", true, c1);
	    assertTrue(u2.equals(u1));
	}
	
	/**
	 * Test method to compare two Unit objects. Failure comparison (different title or course).
	 */
	@Test
	public void testEqualsObjectError() {
		u3 = new Unit("Unit 1", "desUnit2", true, c2);
		assertFalse(u3.equals(u1));
	}

}