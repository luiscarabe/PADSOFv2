/**
 * 
 */
package es.uam.eps.padsof.p3.stat.testers;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;
import es.uam.eps.padsof.p3.user.Student;

/**
 * @author Alejo Luis
 *
 */
public class AnswerTester {
	Answer a1, a2;
	SpecificAnswer sa1, sa2;
	OpenQuestion oq1;
	MultiQuestion oq2;
	Exercise e;
	Course c;
	Student s1;
	Option o1, o2, o3, o4;
	LocalDate ini1, end1;
	
	
	@Before
	public void setUp(){
		c = new Course("PADSOF", "A description");
		e = new Exercise("PADSOF", "Another description", false, c);
		e.setWeight(15);
		oq1 = new OpenQuestion("Question 1", 5, e);
		oq2 = new MultiQuestion("Question 2", 10, true, e);
		sa1 = new SpecificAnswer(oq1);
		sa2 = new SpecificAnswer(oq2);
		sa1.setMarkOutWeight(3);
		sa2.setMarkOutWeight(6);
		s1 = new Student("Name1", "email@some.es", "password");
		a1 = new Answer(e, s1, 2);
		a2 = new Answer(e, s1, 2);
		a1.getSpecificAnswer().add(sa1);
		a1.getSpecificAnswer().add(sa2);
	
	}
	/**
	 * Test method that checks if an answer is allowed to be seen (succeed result).
	 */
	@Test
	public void testAllowedToShowTrue() {
		ini1 = LocalDate.now();
		ini1 = ini1.plusDays(-7);
		
		end1 = LocalDate.now();
		end1 = end1.plusDays(-3);
		
		e.setStartDate(ini1);
		e.setExpDate(end1);
		assertTrue(a1.allowedToShow());
	}
	
	/**
	 * Test method that checks if an answer is allowed to be seen (failed result).
	 */
	@Test
	public void testAllowedToShowFalse() {
		ini1 = LocalDate.now();
		ini1 = ini1.plusDays(-7);
		
		end1 = LocalDate.now();
		end1 = end1.plusDays(3);
		
		e.setStartDate(ini1);
		e.setExpDate(end1);
		assertFalse(a1.allowedToShow());
	}

	/**
	 * Test method that tests the calculation of a mark associated to an answer (succeed result).
	 */
	@Test
	public void testCalculateMarkCorrect() {
		assertFalse(a1.calculateMark() == -1);
		assertTrue(a1.getMarkOut10() == 6);
		assertTrue(a1.getMarkOutWeight() == 9);
	}
	
	/**
	 * Test method that tests the calculation of a mark associated to an answer (failed result).
	 */
	@Test
	public void testCalculateMarkError() {
		assertTrue(a2.calculateMark() == -1);
		assertTrue(a2.getMarkOut10() == -1);
		assertTrue(a2.getMarkOutWeight() == -1);
	}

}
