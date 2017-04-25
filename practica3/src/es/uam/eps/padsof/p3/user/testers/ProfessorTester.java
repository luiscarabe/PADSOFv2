/**
 * 
 */
package es.uam.eps.padsof.p3.user.testers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.user.*;
import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.educagram.Educagram;

/**
 * @author Alejo Luis
 *
 */
public class ProfessorTester {
	Educagram edu;
	Professor p;
	Course c1, c2, c3, c4;
	Student s1, s2, s3;
	Application a1, a2, a3;
	
	
	@Before
	public void setUp() throws Exception{
		edu = Educagram.getInstance();
		p = new Professor("Luis Alejo", "Luis.Alejo@esdu.es", "alejoluis");
		
		c3 = p.createCourse("Course 3", "descCourse3");
		c4 = p.createCourse("Course 4", "descCourse4");
		
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		s2 = new Student("Manuel Blanco", "Manuel.Blanco@esdu.es", "password2");
		s3 = new Student("Ana Cordero", "Ana.Cordero@esdu.es", "password3");
		
		a1 = new Application(c3, s1);
		a2 = new Application(c3, s2);
		a3 = new Application(c3, s3);
	}
	/**
	 * Test method for creating a course:(successful).
	 */
	@Test
	public void testCreateCourseCorrect() {
		c1 = p.createCourse("Course 1", "descCourse1");
		assertNotNull(c1);
		assertEquals(c1.getTitle(), "Course 1");
		assertEquals(c1.getDesc(), "descCourse1");
		assertTrue(edu.getCourses().contains(c1));
		assertTrue(p.getAllCourses().contains(c1));
	}
	
	/**
	 * Test method for creating a course:(fail).
	 */
	@Test
	public void testCreateCourseError() {
		c1 = p.createCourse("Course 1", "descCourse1");
		c2 = p.createCourse("Course 1", "descCourse2");
		assertNull(c2);
	}

	/**
	 * Test method for for accept an application.
	 * @throws Exception
	 */
	@Test
	public void testConfirmApplicationAccept() throws Exception {
		p.confirmApplication(a1, true);
		assertTrue(s1.getEnrolledCourses().contains(c3));
		assertTrue(c3.getEnrolledStudents().contains(s1));
		assertNull(c3.searchApplication(s1));
	}
	
	/**
	 * Test method for for decline an application.
	 * @throws Exception
	 */
	@Test
	public void testConfirmApplicationDecline() throws Exception {
		p.confirmApplication(a2, false);
		assertTrue(c3.isNotAdmittedStudent(s2));
		assertNull(c3.searchApplication(s2));
	}

	/**
	 * Test method for expelling a student from a course.(success)
	 * @throws Exception 
	 */
	@Test
	public void testExpellStudentCorrect() throws Exception {
		/* setting */
		p.confirmApplication(a1, true);
		assertTrue(s1.getEnrolledCourses().contains(c3));
		assertTrue(c3.getEnrolledStudents().contains(s1));
		assertNull(c3.searchApplication(s1));
		
		/* main part */
		assertTrue(p.expellStudent(s1, c3));
		assertTrue(c3.getExpelledStudents().contains(s1));
		assertTrue(s1.getExpelledCourses().contains(c3));
		assertFalse(p.expellStudent(s1, c3));
	}
	
	/**
	 * Test method for expelling a student from a course.(fail)
	 * @throws Exception 
	 */
	@Test
	public void testExpellStudentError() throws Exception {
		/* setting */
		p.confirmApplication(a2, false);
		assertTrue(c3.isNotAdmittedStudent(s2));
		assertNull(c3.searchApplication(s2));
		
		/* main part */
		assertFalse(p.expellStudent(s2, c3));
		
		assertFalse(p.expellStudent(s1, c4));
	}

	/**
	 * Test method for re-admit a student.(success)
	 * @throws  
	 */
	@Test
	public void testReadmitStudentCorrect() throws Exception {
		c3.getExpelledStudents().add(s3);
		s3.getEnrolledCourses().add(c3);
		p.readmitStudent(s3, c3);
		assertTrue(c3.getEnrolledStudents().contains(s3));
		assertTrue(s3.getEnrolledCourses().contains(c3));
	}
	
	/**
	 * Test method for re-admit a student.(fail)
	 * @throws  
	 */
	@Test
	public void testReadmitStudentError() throws Exception {
		p.confirmApplication(a1, true);
		p.confirmApplication(a2, false);
		
		assertFalse(p.readmitStudent(s1, c3));
		assertFalse(p.readmitStudent(s2, c3));
		
	}

}
