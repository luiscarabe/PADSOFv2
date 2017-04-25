/**
 * 
 */
package es.uam.eps.padsof.p3.user.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;

/**
 * @author Alejo Luis
 *
 */
public class ApplicationTester {
	Application a, a1, a2;
	Course c, c1;
	Student s, s1, s2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c = new Course("PADSOF", "Stay back, testing in progress");
		s = new Student("Mark Knofler", "mark.kn@edu.es", "Mney4Nothing");
		a = new Application(c, s);
		c1 = new Course("SOPER","Traffic lights are overrated");
		s1 = new Student("David Mustaine", "david.mus@edu.es", "HeyM");
		a1 = new Application(c1, s1);
		s2 = new Student("James Hetfield", "jame.tallica@edu.es", "One");
		a2 = new Application(c1, s2);
	}

	/**
	 * Test method for getCourse.
	 */
	@Test
	public void testGetCourse() {
		assertEquals(c, a.getCourse());
	}

	/**
	 * Test method for get Applied Student
	 */
	@Test
	public void testGetAppliedStudent() {
		assertEquals(s, a.getAppliedStudent());
	}

	/**
	 * Test method for delete Application
	 */
	@Test
	public void testDeleteApplication() {
		a2.deleteApplication();
		assertFalse(s2.getAppliedCourses().contains(a2));
		assertFalse(c1.getApplications().contains(a2));
	}

	/**
	 * Test method for accept application
	 * @throws Exception 
	 */
	@Test
	public void testAcceptApplication() throws Exception {
		a1.acceptApplication();
		assertFalse(s1.getAppliedCourses().contains(a1));
		assertTrue(s1.getEnrolledCourses().contains(c1));
		assertFalse(c1.getApplications().contains(a1));
	}

	/**
	 * Test method for decline application
	 * @throws Exception 
	 */
	@Test
	public void testDeclineApplication() throws Exception {
		a.declineApplication();
		assertFalse(s.getAppliedCourses().contains(a));
		assertFalse(s.getEnrolledCourses().contains(c));
		assertFalse(s.getExpelledCourses().contains(c));
		assertFalse(c.getApplications().contains(a));
	}

}
