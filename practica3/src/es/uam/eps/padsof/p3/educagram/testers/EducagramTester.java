/**
 * 
 */
package es.uam.eps.padsof.p3.educagram.testers;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p3.user.User;

/**
 * @author Alejo Luis
 *
 */
public class EducagramTester {
	Educagram edu;
	User u, u1, u2;
	Student s, s1;
	Course c, c1, c2;
	
	
	@Before
	public void setup() throws Exception{
		edu = Educagram.getInstance();
		
		edu.readFile();
	}
	
	/**
	 * Test method for {@link es.uam.eps.padsof.p3.educagram.Educagram#readFile()}.
	 * @throws IOException 
	 */
	@Test
	public void testReadFile() throws IOException {
		assertNotNull(edu.getStudents());
		assertTrue(edu.getFirstLogin() == 1);
		assertEquals(edu.getStudents().get(0).getName(), "Jorge Alcazar");
		assertEquals(edu.getStudents().get(0).getEmail(), "Jorge.Alcazar@esdu.es");
		assertEquals(edu.getStudents().get(0).getPassword(), "JoA");
		assertEquals(edu.getStudents().get(1).getName(), "Manuel Blanco");
		assertEquals(edu.getStudents().get(1).getEmail(), "Manuel.Blanco@esdu.es");
		assertEquals(edu.getStudents().get(1).getPassword(), "anuel.Bl");
	}
	
	/**
	 * Test method for Search Student
	 */
	@Test
	public void testSearchStudent() {
		
		s = edu.searchStudent("Javier Carrera");
		assertEquals(s.getEmail(), "Javier.Carrera@aadu.es");
	}
	
	/**
	 * Test method for Invalid Search Student
	 */
	@Test
	public void testInvSearchStudent() {
		
		s1 = edu.searchStudent("Invalid User");
		assertNull(s1);
	}
	
	/**
	 * Test method for Search Course
	 */
	@Test
	public void testSearchCourse() {
		c = new Course("PADSOF", "Only testing");
		edu.getCourses().add(c);
		c1 = edu.searchCourse("PADSOF");
		assertEquals(c,c1);
	}
	
	/**
	 * Test method for Invalid Search Course
	 */
	@Test
	public void testInvSearchCourse() {
		c2 = edu.searchCourse("ADSOF");
		assertNull(c2);
	}
	
	/**
	 * Test method for Sign In
	 * @throws Exception 
	 */
	@Test
	public void testSignIn() throws Exception {
		edu.signOut();
		assertNull(edu.getCurrentUser());
		u = edu.signIn("Roberto.Paz@aadu.es", "Rerto");
		assertEquals(u.getName(), "Roberto Paz");
		assertNotNull(edu.getCurrentUser());
		
	}
	/**
	 * Test method for TeacherSign In
	 * @throws Exception 
	 */
	@Test
	public void testTeachSignIn() throws Exception {
		assertNull(edu.getCurrentUser());
		u2 = edu.signIn("teacher@teadu.com", "lovingPADSOF");
		assertEquals(u2.getName(), "Teacher");
		assertNotNull(edu.getCurrentUser());
		
	}
	/**
	 * Test method for Invalid Sign In
	 * @throws Exception 
	 */
	@Test
	public void testInvSignIn() throws Exception {
		assertNull(edu.getCurrentUser());
		u1 = edu.signIn("Roberto.Paz@aadu.es", "notValidPsw");
		assertNull(u1);
		assertNull(edu.getCurrentUser());
		
	}
	
	/**
	 * Test method that test when a user sign out.
	 * @throws Exception 
	 */
	@Test
	public void testSignOutCorrect() throws Exception{
		u = edu.signIn("Roberto.Paz@aadu.es", "Rerto");
		assertNotNull(edu.getCurrentUser());
		assertTrue(edu.signOut());
		assertNull(edu.getCurrentUser());
	}
	
	/**
	 * Test method that test when it is called  signOut() method when there is no current user.
	 * @throws 
 
	 */
	@Test
	public void testSignOutError() throws Exception{
		u = edu.signIn("Roberto.Paz@aadu.es", "Rerto");
		assertNotNull(edu.getCurrentUser());
		edu.setCurrentUser(null);
		assertFalse(edu.signOut());
		assertNull(edu.getCurrentUser());
	}

}
