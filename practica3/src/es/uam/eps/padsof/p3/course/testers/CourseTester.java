/**
 * 
 */
package es.uam.eps.padsof.p3.course.testers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.stat.CourseStat;

/**
 * @author Alejo Luis
 *
 */
public class CourseTester {
	Course c1, c2, c3;
	CourseElement u1, u2, u3, u4;
	Student s1, s2, s3, s4;
	Application a1, a2, a3;
	CourseStat cs1;
	
	
	@Before
	public void setUp() throws Exception{
		c1 = new Course("Padsof", "It is known that everyone love it.");
		c2 = new Course("Padsof", "It is known that everyone hate it.");
		c3 = new Course("Soper", "SoperDescription");
		
		u4 = c1.createUnit("Unit 4", "DescriptionUnit 4", false);
		
		
		s2 = new Student("Manuel Blanco", "Manuel.Blanco@esdu.es", "password2");
		
		
		s4 = new Student("Eva Fuertes", "Eva.Fuertes@esdu.es", "password4");
		a1 = new Application(c1, s2);
		a2 = new Application(c1, s4);
		a3 = new Application(c2, s2);
		c1.getApplications().add(a1); 
		c1.getApplications().add(a2);
		c2.getApplications().add(a3);
		
		cs1 = new CourseStat();
		cs1.setAverageMark(9.7);
		c1.setCourseStat(cs1);
		
	}
	
	
	/**
	 * Test method for creating a unit successfully.
	 */
	@Test
	public void testCreateUnitCorrect() {
		
		u1 = c1.createUnit("Unit 1", "DescriptionUnit 1", true);
		assertEquals(u1.getTitle(), "Unit 1");
		assertEquals(u1.getDesc(), "DescriptionUnit 1");
		assertEquals(u1.isHidden(), true);
		assertEquals(c1, u1.getCourse());
		assertTrue(c1.getCourseElements().contains(u1));
		
		
		assertTrue(c1.getCourseElements().size() == 2);
		
		u3 = c1.createUnit("Unit 2", "DescriptionUntitleUnit 2", false);
		
		assertNotNull(u3);
		assertTrue(c1.getCourseElements().size() == 3);
		assertTrue(c1.getCourseElements().contains(u1));
		
	}
	
	/**
	 * Test method for creating a unit incorrectly.
	 */
	@Test
	public void testCreateUnitError() {
		
		assertTrue(c1.getCourseElements().size() == 1);
		u1 = c1.createUnit("Unit 1", "DescriptionUnit 1", true);
		u2 = c1.createUnit("Unit 1", "DescriptionUnit 2", true);
			
		
		assertTrue(c1.getCourseElements().size() == 2);
		/* Main part */
		assertNull(u2);
		assertTrue(c1.getCourseElements().size() == 2);
		
	}

	/**
	 * Test method deleting an existing unit.(success)
	 */
	@Test
	public void testDeleteUnitCorrect() {
		assertTrue(c1.getCourseElements().contains(u4));
		Unit aux = (Unit) u4;
		assertTrue(c1.deleteUnit(aux));
		
		assertFalse(c1.getCourseElements().contains(u4));
	}
	
	/**
	 * Test method deleting a non-existing unit.(fail)
	 */
	@Test
	public void testDeleteUnitError() {
		assertFalse(c1.getCourseElements().contains(u2));
		Unit aux = (Unit) u2;
		assertFalse(c1.deleteUnit(aux));
	}

	/**
	 * Test method testing if an enrolled student is actually enrolled.(success)
	 */
	@Test
	public void testIsEnrolledStudentCorrect() {
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		c1.getEnrolledStudents().add(s1);
		s1.getEnrolledCourses().add(c1);
		assertTrue(c1.isEnrolledStudent(s1));
	}

	/**
	 * Test method testing if a student is enrolled.(fail)
	 */
	@Test
	public void testIsEnrolledStudentError() {
		assertFalse(c1.isEnrolledStudent(s2));
		s3 = new Student("Ana Cordero", "Ana.Cordero@esdu.es", "password3");
		c1.getExpelledStudents().add(s3);
		s3.getExpelledCourses().add(c1);
		assertFalse(c1.isEnrolledStudent(s3));
	}
	
	/**
	 * Test method checking if a student is expelled.(success)
	 * @throws Exception
	 */
	@Test
	public void testIsExpelledStudentCorrect() throws Exception {
		s3 = new Student("Ana Cordero", "Ana.Cordero@esdu.es", "password3");
		c1.getExpelledStudents().add(s3);
		s3.getExpelledCourses().add(c1);
		assertTrue(c1.isExpelledStudent(s3));
		
	}
	
	/**
	 * Test method checking if a student is expelled.(fail)
	 * @throws Exception
	 */
	@Test
	public void testIsExpelledStudentError() throws Exception {
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		c1.getEnrolledStudents().add(s1);
		s1.getEnrolledCourses().add(c1);
		assertFalse(c1.isExpelledStudent(s1));
		
	}
	
	/**
	 * Test method that checks if a student ha been admitted. (success)
	 */
	@Test
	public void testIsNotAdmittedStudentCorrect() {
		
		assertTrue(c1.isNotAdmittedStudent(s2));
	}
	/**
	 * Test method that checks if a student ha been admitted. (fail)
	 */
	@Test
	public void testIsNotAdmittedStudentError() {
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		c1.getEnrolledStudents().add(s1);
		s1.getEnrolledCourses().add(c1);
		s3 = new Student("Ana Cordero", "Ana.Cordero@esdu.es", "password3");
		c1.getExpelledStudents().add(s3);
		s3.getExpelledCourses().add(c1);
		
		assertFalse(c1.isNotAdmittedStudent(s1));
		assertFalse(c1.isNotAdmittedStudent(s3));
	}
	/**
	 * Test method for get the total average of a course.(success)
	 */
	@Test
	public void testGetTotalAverage() {
		assertTrue(c1.getTotalAverage() == (double)(9.7));
		assertTrue(c2.getTotalAverage() == -1);
	}

	/**
	 * Test method expelling an enrolled student.(success)
	 * @throws Exception
	 */
	@Test
	public void testExpellStudentCorrect() throws Exception{
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		c1.getEnrolledStudents().add(s1);
		s1.getEnrolledCourses().add(c1);
		
		assertTrue(c1.expellStudent(s1));
		
		assertTrue(c1.getExpelledStudents().contains(s1));
	}
	
	/**
	 * Test method expelling a non-enrolled student.(fail)
	 * @throws Exception
	 */
	@Test
	public void testExpellStudentError() throws Exception{
		s3 = new Student("Ana Cordero", "Ana.Cordero@esdu.es", "password3");
		c1.getExpelledStudents().add(s3);
		s3.getExpelledCourses().add(c1);
		assertFalse(c1.expellStudent(s3));
		assertTrue(c1.getExpelledStudents().contains(s3));
	}

	/**
	 * Test method re-admitting an expelled student.(success).
	 * @throws Exception
	 */
	@Test
	public void testReadmitStudentCorrect() throws Exception{
		s3 = new Student("Ana Cordero", "Ana.Cordero@esdu.es", "password3");
		c1.getExpelledStudents().add(s3);
		s3.getExpelledCourses().add(c1);
		assertTrue(c1.readmitStudent(s3));
		assertTrue(c1.getEnrolledStudents().contains(s3));
		assertFalse(c1.getExpelledStudents().contains(s3));
	}
	
	/**
	 * Test method re-admitting a non-expelled student.(fail).
	 * @throws Exception
	 */
	@Test
	public void testReadmitStudentError() throws Exception{
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		c1.getEnrolledStudents().add(s1);
		s1.getEnrolledCourses().add(c1);
		assertFalse(c1.readmitStudent(s1));
		assertTrue(c1.getEnrolledStudents().contains(s1));
		assertFalse(c1.getExpelledStudents().contains(s1));
	}

	/**
	 * Test method searching an associated application.(success)
	 */
	@Test
	public void testSearchApplicationCorrect() {
		assertNull(c2.searchApplication(s4));
	}
	
	/**
	 * Test method searching a non-associated application.(fail)
	 */
	@Test
	public void testSearchApplicationError() {
		assertNotNull(c1.searchApplication(s2));
	}

	/**
	 * Test method checking successfully if two courses are equals.(success)
	 */
	@Test
	public void testEqualsObjectCorrect() {
		assertTrue(c1.equals(c2));
	}
	
	/**
	 * Test method checking if two courses are equals.(fail)
	 */
	@Test
	public void testEqualsObjectError() {
		assertFalse(c1.equals(c3));
	}

}
	