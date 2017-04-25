/**
 * 
 */
package es.uam.eps.padsof.p3.stat.testers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.*;

import es.uam.eps.padsof.p3.stat.*;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.*;

/**
 * @author Alejo Luis
 *
 */
public class CMarkTester {

	Exercise e1, e2, e3;
	Question q1, q2, q3, q4, q5, q6;
	SpecificAnswer sa1, sa2, sa3, sa4, sa5, sa6;
	Answer a1, a2, a3;
	Student s1;
	Course c1;
	LocalDate ini1, ini2, ini3, end1, end2, end3;
	CMark cm;
	
	@Before
	public void setUp(){
		
		/* Course */
		c1 = new Course("Course1", "descCourse1");
		/* Student */
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		/* Exercises */
		e1 = new Exercise("Exercise 1", "descExercise1", false, c1);
		e2 = new Exercise("Exercise 2", "descExercise2", false, c1);
		e3 = new Exercise("Exercise 3", "descExercise3", false, c1);
		/* Answers */
		a1 = new Answer(e1, s1, 3);
		e1.getAnswers().add(a1);
		a2 = new Answer(e2, s1, 1);
		e2.getAnswers().add(a2);
		a3 = new Answer(e3, s1, 2);
		e3.getAnswers().add(a3);
		
		s1.getAnswers().add(a1);
		s1.getAnswers().add(a2);
		s1.getAnswers().add(a3);
		
		/*  StartDates */
		ini1 = LocalDate.now();
		ini1 = ini1.plusDays(-7);
		ini2 = LocalDate.now();
		ini2 = ini2.plusDays(-4);
		ini3 = LocalDate.now();
		ini3 = ini3.plusDays(-10);
		/* EndDates */
		end1 = LocalDate.now();
		end1 = end1.plusDays(-3);
		end2 = LocalDate.now();
		end2 = end2.plusDays(5);
		end3 = LocalDate.now();
		end3 = end3.plusDays(-5);
		
		/* Setting Dates */
		e1.setStartDate(ini1);
		e1.setExpDate(end1);
		e2.setStartDate(ini2);
		e2.setExpDate(end2);
		e3.setStartDate(ini3);
		e3.setExpDate(end3);
		
		/* Set exercise weight */
		e1.setWeight(10);
		e2.setWeight(8);
		e3.setWeight(20);
		
		/* Set answer mark */
		a1.setMarkOutWeight(7);
		a2.setMarkOutWeight(3);
		a3.setMarkOutWeight(8);
		
		/* CMark */
		cm = new CMark(c1, s1);
		
		
	}
	
	/**
	 * Test method that calculate the global mark of a course with the information of the exercises allowed to be shown.
	 */
	@Test
	public void testCalculateCMarkCorrect() {
		System.out.println(cm.calculateCMark());
		assertTrue(cm.calculateCMark() == 5);
	}
	
	/**
	 * Test method that tries to calculate a mark of a course without exercises allowed to be shown.
	 */
	@Test
	public void testCalculateCMarkError() {
		end1 = LocalDate.now().plusDays(1);
		end2 = LocalDate.now().plusDays(1);
		end3 = LocalDate.now().plusDays(1);
		e1.setExpDate(end1);
		e2.setExpDate(end2);
		e3.setExpDate(end3);
		System.out.println(cm.calculateCMark());
		assertTrue(cm.calculateCMark() == -1);
	}

}
