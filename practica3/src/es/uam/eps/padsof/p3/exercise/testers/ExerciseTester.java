/**
 * 
 */
package es.uam.eps.padsof.p3.exercise.testers;

import static org.junit.Assert.*;

import java.time.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.CMark;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;
import es.uam.eps.padsof.p3.user.Student;

/**
 * @author Alejo Luis
 *
 */
public class ExerciseTester {
	
	
	Exercise e1, e2, e3, e4;
	Question q1, q2, q3, q4, q5, q6;
	SpecificAnswer sa1, sa2, sa3, sa4, sa5, sa6;
	Answer a1, a2, a3, a4;
	Student s1, s2;
	Course c1;
	LocalDate ini1, ini2, ini3, ini4, end1, end2, end3, end4;
	CMark cm;
	
	
	@Before
	public void setUp(){
		
		/* Course */
		c1 = new Course("Course1", "descCourse1");
		
		/* Student */
		s1 = new Student("Jorge Alcazar", "Jorge.Alcazar@esdu.es", "password1");
		s2 = new Student("David Mustaine", "david.mus@edu.es", "HeyM");
		
		/* Exercises */
		e1 = new Exercise("Exercise 1", "descExercise1", false, c1);
		e2 = new Exercise("Exercise 2", "descExercise2", false, c1);
		e3 = new Exercise("Exercise 3", "descExercise3", false, c1);
		e4 = new Exercise("Exercise 4", "descExercise4", false, c1);
		
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
		ini4 = LocalDate.now();
		ini4 = ini4.plusDays(-8);
		
		/* EndDates */
		end1 = LocalDate.now();
		end1 = end1.plusDays(-3);
		end2 = LocalDate.now();
		end2 = end2.plusDays(5);
		end3 = LocalDate.now();
		end3 = end3.plusDays(-5);
		end4 = LocalDate.now();
		end4 = end4.plusDays(6);
		
		/* Setting Dates */
		e1.setStartDate(ini1);
		e1.setExpDate(end1);
		e2.setStartDate(ini2);
		e2.setExpDate(end2);
		e3.setStartDate(ini3);
		e3.setExpDate(end3);
		e4.setStartDate(ini4);
		e4.setExpDate(end4);
		
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
		
		/* Questions */
		q4 = new OpenQuestion("Question 1", 5, e2);
		q5 = new MultiQuestion("Question 2", 10, true, e2);
		q6 = new MultiQuestion("Question 2", 10, true, e2);
		e2.addQuestion(q4);
		e2.addQuestion(q5);
		e2.addQuestion(q6);
		
		
	}
	/**
	 * Test method that informs if a student has taken the exercise or not.(Correct return)
	 */
	@Test
	public void testIsModifiableCorrect() {
 		assertFalse(e2.isModifiable());
		assertFalse(e1.isModifiable());
	}
	
	/**
	 * Test method that informs if a student has taken the exercise or not.(Incorrect return)
	 */
	@Test
	public void testIsModifiableError() {
		assertTrue(e4.isModifiable());
	}

	/**
	 * Test method that adds a question to an exercise successfully.
	 */
	@Test
	public void testAddQuestionCorrect() {
		q1 = new OpenQuestion("Question 1", 5, e1);
		q2 = new MultiQuestion("Question 2", 10, true, e1);
		assertTrue(e1.addQuestion(q1));
		assertTrue(e1.getQuestions().contains(q1));
	}
	
	/**
	 * Test method that adds a question to an exercise incorrectly.
	 */
	@Test
	public void testAddQuestionError() {
		q2 = new MultiQuestion("Question 2", 10, true, e1);
		e1.getQuestions().add(q2);
		assertFalse(e1.addQuestion(q2));
	}

	/**
	 * Test method that deletes a question associated to an exercise successfully.
	 */
	@Test
	public void testDeleteQuestionCorrect() {
		q1 = new OpenQuestion("Question 1", 5, e1);
		e1.getQuestions().add(q1);
		assertTrue(e1.deleteQuestion(q1));
		assertFalse(e1.getQuestions().contains(q1));
	}
	
	/**
	 * Test method that tries to delete a question that is not associated to an exercise.
	 */
	@Test
	public void testDeleteQuestionError() {
		q1 = new OpenQuestion("Question 1", 5, e1);
		assertFalse(e1.deleteQuestion(q1));
		assertTrue(e1.getQuestions().isEmpty());
		assertFalse(e1.getQuestions().contains(q1));
		assertFalse(e1.deleteQuestion(q2));
		assertTrue(e1.getQuestions().isEmpty());
		assertFalse(e1.getQuestions().contains(q2));
	}

	/**
	 * Test method that shuffles the list of questions successfully.
	 */
	@Test
	public void testRandomizeOrderCorrect() {
		q1 = new OpenQuestion("Question 1", 5, e1);
		q2 = new MultiQuestion("Question 2", 10, true, e1);
		q3 = new MultiQuestion("Question 2", 10, true, e1);
		
		e1.getQuestions().add(q1);
		e1.getQuestions().add(q2);
		e1.getQuestions().add(q3);
		
		e1.setRandomness(true);
		
		/* 
		 * This Method could not work sometimes. Re-run.
		 * In fact, this "error" prove that Collections.shuffle works.
		 */
		assertTrue(e1.randomizeOrder());
		assertTrue(!e1.getQuestions().get(0).equals(q1) || !e1.getQuestions().get(1).equals(q2)
				|| !e1.getQuestions().get(2).equals(q3));
	}

	/**
	 * Test method that tries to shuffle the list of questions.
	 */
	@Test
	public void testRandomizeOrderError() {
		q1 = new OpenQuestion("Question 1", 5, e1);
		q2 = new MultiQuestion("Question 2", 10, true, e1);
		q3 = new MultiQuestion("Question 2", 10, true, e1);
		
		e1.getQuestions().add(q1);
		e1.getQuestions().add(q2);
		e1.getQuestions().add(q3);
		
		e1.setRandomness(false);
		
		/* This Method could not work sometimes. Re-run*/
		assertFalse(e1.randomizeOrder());
		assertTrue(e1.getQuestions().get(0).equals(q1) && e1.getQuestions().get(1).equals(q2)
				&& e1.getQuestions().get(2).equals(q3));
	}
	
	/**
	 * Test method to check if a date is valid (success).
	 */
	@Test
	public void testIsValidDateCorrect() {
		LocalDate ld = LocalDate.now();
		ld = ld.plusDays(7);
		assertTrue(e1.isValidDate(ld));
	}
	
	/**
	 * Test method to check if a date is valid (fail).
	 */
	@Test
	public void testIsValidDateError() {
		LocalDate ld = LocalDate.now();
		ld = ld.plusDays(-7);
		assertFalse(e1.isValidDate(ld));
	}

	/**
	 * Test method that tries to set a correct start date.
	 */
	@Test
	public void testAddStartCorrect() {
		LocalDate ld = LocalDate.now();
		ld = ld.plusDays(1);
		
		assertTrue(e4.addStartDate(ld));
		assertTrue(e4.getStartDate().equals(ld));
		
	}

	/**
	 * Test method that tries to set an correct start date.
	 */
	@Test
	public void testAddStartError() {
		/* ExpirationDate Expired */
		LocalDate ld = LocalDate.now();
		ld = ld.plusDays(1);
		
		assertFalse(e1.addStartDate(ld));
		assertFalse(e1.getStartDate().equals(ld));
		/* Isn't Modifiable */
		assertFalse(e2.addStartDate(ld));
		assertFalse(e2.getStartDate().equals(ld));
		/* Invalid Date */
		ld = ld.plusDays(-2);
		assertFalse(e2.addStartDate(ld));
		assertFalse(e1.getStartDate().equals(ld));
		/* Date to set over Expiration date */
		ld = ld.plusDays(30);
		assertFalse(e2.addStartDate(ld));
		assertFalse(e1.getStartDate().equals(ld));
		
	}
	
	/**
	 * Test method that tries to set an correct expiration date.
	 */
	@Test
	public void testAddExpirationDateCorrect() {
		LocalDate ld = LocalDate.now();
		
		ld = ld.plusDays(30);
		assertTrue(e2.addExpirationDate(ld));
		assertTrue(e2.getExpDate().equals(ld));
		
		assertTrue(e4.addExpirationDate(ld));
		assertTrue(e4.getExpDate().equals(ld));
		
	}

	/**
	 * Test method that tries to set an incorrect start date.
	 */
	@Test
	public void testAddExpirationDateError() {
		/* ExpirationDate Expired */
		LocalDate ld = LocalDate.now();
		ld = ld.plusDays(1);
		
		assertFalse(e1.addExpirationDate(ld));
		assertFalse(e1.getExpDate().equals(ld));
		/* Isn't Modifiable */
		assertFalse(e2.addStartDate(ld));
		assertFalse(e2.getExpDate().equals(ld));
		/* Invalid Date */
		ld = ld.plusDays(-2);
		assertFalse(e2.addExpirationDate(ld));
		assertFalse(e1.getExpDate().equals(ld));
		
	}
	
	@Test
	public void testIsAllowedToShowCorrect() {
		assertTrue(e1.isAllowedToShow());
	}
	
	@Test
	public void testIsAllowedToShowError() {
		assertFalse(e2.isAllowedToShow());
	}

	/**
	 * Test method that informs if a student is able to take an exercise or not. (success)
	 */
	@Test
	public void testCanTakeExerciseCorrect() {
		assertTrue(e2.canTakeExercise(s2));
	}
	
	/**
	 * Test method that informs if a student is able to take an exercise or not. (fail)
	 */
	@Test
	public void testCanTakeExerciseError() {
		assertFalse(e1.canTakeExercise(s2));
		assertFalse(e2.canTakeExercise(s1));
	}

	/**
	 * Test method that informs if a student has taken an exercise.(successful)
	 */
	@Test
	public void testIsTakenExerciseByStudentCorrect() {
		assertTrue(e1.isTakenExerciseByStudent(s1));
	}
	
	/**
	 * Test method that informs if a student has taken an exercise.(fail)
	 */
	@Test
	public void testIsTakenExerciseByStudentError() {
		assertFalse(e1.isTakenExerciseByStudent(s2));
	}

	/**
	 * Test method to take an exercise that you haven't taken. It should be called after some checks.(successful)
	 */
	@Test
	public void testTakeExerciseCorrect() {
		assertNotNull(a4 = e2.takeExercise(s2));
		assertTrue(a4.getStudent().equals(s2));
		assertTrue(s2.getAnswers().contains(a4));
	}

	/**
	 * Test method to take an exercise that you haven't taken. It should be called after some checks.(fail)
	 */
	@Test
	public void testTakeExerciseError() {
		assertNull(a4 = e2.takeExercise(s1));
	}
	
	/**
	 * Test method for cancel an exercise. It should be call before the answer has been sent. (success)
	 */
	@Test
	public void testCancelExerciseCorrect() {
		Student saux1;
		
		assertTrue(e1.getAnswers().contains(a1));
		saux1 = a1.getStudent();
		assertTrue(e1.cancelExercise(a1));
		assertFalse(e1.getAnswers().contains(a1));
		assertFalse(saux1.getAnswers().contains(a1));
		
	}
	
	/**
	 * Test method for cancel an exercise. It should be call before the answer has been sent. (fail)
	 */
	@Test
	public void testCancelExerciseError() {
		
		assertTrue(e1.getAnswers().contains(a1));
		assertTrue(e1.getAnswers().remove(a1));
		assertFalse(e1.cancelExercise(a1));
	}

	/**
	 * Test method to search the answer associated to a student.(successful)
	 */
	@Test
	public void testSearchAnswerCorrect() {

		assertNotNull(e1.searchAnswer(s1));
	}
	
	/**
	 * Test method to search the answer associated to a student.(fail)
	 */
	@Test
	public void testSearchAnswerError() {

		assertNull(e1.searchAnswer(s2));
	}

}
