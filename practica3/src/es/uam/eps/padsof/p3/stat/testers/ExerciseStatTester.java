/**
 * 
 */
package es.uam.eps.padsof.p3.stat.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.ExerciseStat;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;
import es.uam.eps.padsof.p3.user.Student;

/**
 * @author Alejo Luis
 *
 */
public class ExerciseStatTester {
	ExerciseStat es;
	Exercise e;
	Course c;
	TFQuestion q1, q2;
	Student s1, s2;
	Answer a1, a2;
	SpecificAnswer sa1, sa2, sa3, sa4;
	Option o, o2;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c = new Course("PADSOF", "Description");
		e = new Exercise("Exercise 1", "A very difficult exercise", false, c);
		/* We prepare the exercise*/
		e.setPenalty(0);
		e.setNumQues(2);
		e.setWeight(10);
		q1 = new TFQuestion("We have two hands", 4.5, e);
		q2 = new TFQuestion("We have three eyes", 5.5, e);
		e.getQuestions().add(q1);
		e.getQuestions().add(q2);
		s1 = new Student("Student1", "email@edu.com", "asdf");
		s2 = new Student("Student2", "email2@edu.com", "asdf2");
		a1 = new Answer(e, s1, 2);
		a2 = new Answer(e, s2, 2);
		/*The answers of the two students*/
		sa1 = new SpecificAnswer(q1);
		sa2 = new SpecificAnswer(q2);
		sa3 = new SpecificAnswer(q1);
		sa4 = new SpecificAnswer(q2);
		o = new Option("f");
		o2 = new Option("t");
		/*The first student answers the two questions correctly*/
		sa1.setMarkOut10(4.5);
		sa2.setMarkOut10(5.5);
		sa1.getAnswers().add(o2);
		sa2.getAnswers().add(o);
		/*The second student answers wrongly only one question*/
		sa3.setMarkOut10(0);
		sa3.getAnswers().add(o);
		sa4.setMarkOut10(0);
		a1.getSpecificAnswer().add(sa1);
		a1.getSpecificAnswer().add(sa2);
		a2.getSpecificAnswer().add(sa3);
		a2.getSpecificAnswer().add(sa4);
		e.getAnswers().add(a1);
		e.getAnswers().add(a2);
		es = new ExerciseStat(e);
		e.setStats(es);
	}

	/**
	 * Test method for set all, with two students answering to a exercise with two Questions
	 */
	@Test
	public void testSetAll() {
		es.setAll();
		assertTrue(es.getqAnswered()[0] == 2);
		assertTrue(es.getqAnswered()[1] == 1);
		assertTrue(es.getqNotAnswered()[0] == 0);
		assertTrue(es.getqNotAnswered()[1] == 1);
		assertTrue(es.getRightAns()[0] == 1);
		assertTrue(es.getRightAns()[1] == 1);
		assertTrue(es.getWrongAns()[0] == 1);
		assertTrue(es.getWrongAns()[1] == 0);
	}

}
