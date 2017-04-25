/**
 * 
 */
package es.uam.eps.padsof.p3.stat.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.stat.CMark;
import es.uam.eps.padsof.p3.stat.CourseStat;
import es.uam.eps.padsof.p3.user.Student;

/**
 * @author Alejo Luis
 *
 */
public class CourseStatTester {
	CourseStat cs;
	Course c1;
	Student s1,s2;
	CMark cm1, cm2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		cs = new CourseStat();
		c1 = new Course("PADSOF", "Great");
		s1 = new Student("Luis", "luis.a@edu.com", "123");
		s2 = new Student("Luis2", "luis2.a@edu.com", "122");
		cm1 = new CMark(c1, s1);
		cm2 = new CMark(c1, s2);
	}

	/**
	 * Test method for addcMark
	 */
	@Test
	public void testAddcMark() {
		cs.addcMark(cm1);
		cs.addcMark(cm2);
		assertTrue(cs.getcMarks().contains(cm1));
		assertTrue(cs.getcMarks().contains(cm2));
	}

	/**
	 * Test method for calculate c stat mark (the average mark of all students in a course)
	 */
	@Test
	public void testCalculateCstat() {
		cm1.setCourseMark(5.7);
		cm2.setCourseMark(9);
		cs.addcMark(cm1);
		cs.addcMark(cm2);
		cs.calculateCstat();
		assertTrue(cs.getAverageMark() == ((5.7+9)/2));
	}

}
