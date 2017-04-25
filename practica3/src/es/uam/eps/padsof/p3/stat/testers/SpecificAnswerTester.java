/**
 * 
 */
package es.uam.eps.padsof.p3.stat.testers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;

/**
 * @author Alejo Luis
 *
 */
public class SpecificAnswerTester {
	SpecificAnswer sa1, sa2;
	OpenQuestion oq1;
	MultiQuestion oq2;
	Exercise e;
	Course c;
	Option o1, o2, o3, o4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c = new Course("PADSOF", "A description");
		e = new Exercise("PADSOF", "Another description", false, c);
		e.setWeight(15);
		e.setPenalty(1.5);
		oq1 = new OpenQuestion("Question 1", 5, e);
		oq2 = new MultiQuestion("Question 2", 10, true, e);
		o1 = new Option("Solution1");
		o2 = new Option("Solution2");
		o3 = new Option("Solution3");
		o4 = new Option("Solution4");
		oq1.getSolution().add(o1);
		oq1.getSolution().add(o2);
		oq2.getSolution().add(o3);
		oq2.getSolution().add(o4);
		sa1 = new SpecificAnswer(oq1);
		sa2 = new SpecificAnswer(oq2);
	}

	/**
	 * Test method for ChooseAnswer
	 */
	@Test
	public void testChooseAnswer() {
		assertTrue(sa1.chooseAnswer(o1));
		assertTrue(sa1.getAnswers().contains(o1));
	}
	
	/**
	 * Test method for Invalid ChooseAnswer
	 */
	@Test
	public void testInvChooseAnswer() {
		sa1.chooseAnswer(o1);
		assertFalse(sa1.chooseAnswer(o1));
		assertTrue(sa1.getAnswers().contains(o1));
	}

	/**
	 * Test method for deleteAnswer
	 */
	@Test
	public void testDeleteAnswer() {
		sa1.chooseAnswer(o1);
		sa1.deleteAnswer(o1);
		assertFalse(sa1.getAnswers().contains(o1));
	}

	/**
	 * Test method for calculateMark with correct answers
	 */
	@Test
	public void testCalculateMark() {
		sa1.chooseAnswer(o1);
		sa1.chooseAnswer(o2);
		sa1.calculateMark();
		assertTrue(sa1.getMarkOut10() == 10);
		assertTrue(sa1.getMarkOutWeight() == sa1.getQuestion().getWeight());
	}
	
	/**
	 * Test method for calculateMark with not all answers (multiquestion)
	 */
	@Test
	public void testNotAllCalculateMark() {
		sa2.chooseAnswer(o3);
		sa2.calculateMark();
		assertTrue(sa2.getMarkOut10() == -1);
		assertTrue(sa2.getMarkOutWeight() == -1.5);
	}
	
	/**
	 * Test method for calculateMark with a non-answered question
	 */
	@Test
	public void testNotAnsCalculateMark() {
		sa1.calculateMark();
		assertTrue(sa1.getMarkOut10() == 0);
		assertTrue(sa1.getMarkOutWeight() == 0);
	}
	
	/**
	 * Test method for calculateMark with a wrong answer
	 */
	@Test
	public void testNotRightCalculateMark() {
		sa1.chooseAnswer(o3);
		sa1.calculateMark();
		assertTrue(sa1.getMarkOut10() == -1);
	}
	

}
