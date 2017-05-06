package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.CMark;
import es.uam.eps.padsof.p3.stat.CourseStat;
import es.uam.eps.padsof.p3.user.*;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.*;

public class TestingTester {

	public static void main(String[] args) {
		Educagram edu = Educagram.getInstance();
		Student s = new Student("Paco", "PAco", "Paco");
		edu.setCurrentUser(s);
		//Professor p = (Professor) Educagram.getInstance().getCurrentUser();
		//p.createCourse("Manolo", "Interesante");
		//System.out.println(Educagram.getInstance().getCourses().get(0));
		MainFrame ma = MainFrame.getInstance();
		ArrayList<Course> lcour = new ArrayList<Course>();
		lcour.add(new Course("ADSOff", "IS THE BEST"));
		((Student)edu.getCurrentUser()).setEnrolledCourses(lcour);
		ArrayList<String> ar = new ArrayList<String>();
		ArrayList<Question> q = new ArrayList<Question>();
		ar.add("Hola");
		ar.add("Adios");
		ar.add("Holaaaaaaaaaaaaaaaaaaaaaaaa");
		ar.add("Adios");
		ar.add("Hola");
		ar.add("Adios");
		ar.add("Hola");
		ar.add("Adios");
		ar.add("Hola");
		ar.add("Adios");
		Unit u1 = new Unit("Unidad 1", "hola", false, null);
		Unit u2 = new Unit("Subunidad 1", "adios", false, null);
		Unit u3 = new Unit("Subunidad 2", "adios", false, null);
		Unit u4 = new Unit("Unidad 2", "adios", false, null);
		Note n1 = new Note("Nota 1", "oagoisa", false, null, null);
		Note n2 = new Note("Nota 2", "oagoisa", false, null, null);
		Exercise e1 = new Exercise("Ejercicio 1", "A", false, null);
		Exercise e2 = new Exercise("Ejercicio 2", "A", false, null);
		Exercise e3 = new Exercise("Ejercicio 3", "A", false, null);		
		TFQuestion a = new TFQuestion("aaaa", 0, e3);
		q.add(a);
		LocalDate ld =  LocalDate.now();
		LocalDate ld1 =  ld.minusDays(5);
		LocalDate ld2 =  ld.plusDays(100);
		e1.setExpDate(ld2);
		e1.setStartDate(ld1);
		CMark cm = new CMark(lcour.get(0), s);
		
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Answer a1 = new Answer(e1, s, 0);
		a1.setMarkOut10(6);
		e1.setWeight(4);
		e1.setCourse(lcour.get(0));
		answers.add(a1);
		Answer a2 = new Answer(e2, s, 0);
		a2.setMarkOut10(6);
		e2.setWeight(4);
		e2.setCourse(lcour.get(0));
		answers.add(a2);
		Answer a3 = new Answer(e3, s, 0);
		a3.setMarkOut10(6);
		e3.setWeight(4);
		e3.setCourse(lcour.get(0));
		answers.add(a3);
		
		a1.setMarkOut10(6);
		e1.setWeight(4);
		e1.setCourse(lcour.get(0));
		answers.add(a1);
		cm.getStudent().setAnswers(answers);
		
		CourseStat cs = new CourseStat();
		ArrayList<CMark> cms = new ArrayList<CMark>();
		cms.add(cm);
		cs.setcMarks(cms);
		
		GlobalStatsPanel lo = new GlobalStatsPanel(cs);
		
		/*lo.addUnit(u1);
		lo.addUnit(u4);
		lo.addSubunit(u2, u1);
		lo.addSubunit(u3, u1);
		lo.addNote(n1, u2);
		lo.addNote(n2, u4);
		lo.addExercise(e1, u3);
		lo.addExercise(e2, u1);
		lo.addExercise(e3, u1);
		
		lo.removeExercise(e2, u1);
		lo.removeNote(n2,u4);
		
		lo.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaa");*/
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		//ma.setContentPane(lo);
		
		ma.add(lo);
		ma.setVisible(true);
		ma.setSize(ma.screenSize.width,ma.screenSize.height);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}