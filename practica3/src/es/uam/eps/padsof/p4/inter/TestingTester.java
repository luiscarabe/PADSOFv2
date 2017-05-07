package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.ExerciseStat;
import es.uam.eps.padsof.p3.user.*;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.TakeExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.TakeOTExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.TakeTFExercisePanel;

public class TestingTester {

	public static void main(String[] args) {
		Educagram edu = Educagram.getInstance();
		Student st = new Student("Paco", "PAco", "Paco");
		Student st2 = new Student("Pac222o", "PA22co", "Pac22");
		edu.setCurrentUser(st);
		//Professor p = (Professor) Educagram.getInstance().getCurrentUser();
		//p.createCourse("Manolo", "Interesante");
		//System.out.println(Educagram.getInstance().getCourses().get(0));
		MainFrame ma = MainFrame.getInstance();
		ArrayList<Course> lcour = new ArrayList<Course>();
		lcour.add(new Course("ADSOff", "IS THE BEST"));
		((Student)edu.getCurrentUser()).setEnrolledCourses(lcour);
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("Hola");
		ar.add("Adios");
		ar.add("Holaaaaaaaaaaaaaaaaaaaaaaaa");
		ar.add("Adiosafs");
		ar.add("Holaeg");
		ar.add("Adisdgos");
		ar.add("Holega");
		ar.add("Adisdbos");
		ar.add("Howetla");
		ar.add("Adi42os");
		
		Unit u1 = new Unit("Unidad 1", "hola", false, null);
		Unit u2 = new Unit("Subunidad 1", "adios", false, null);
		Unit u3 = new Unit("Subunidad 2", "adios", false, null);
		Unit u4 = new Unit("Unidad 2", "adios", false, null);
		Note n1 = new Note("Nota 1", "oagoisa", false, null, null);
		Note n2 = new Note("Nota 2", "oagoisa", false, null, null);
		Exercise e1 = new Exercise("Ejercicio 1", "A", false, null);
		Exercise e2 = new Exercise("Ejercicio 2", "A", false, null);
		Exercise e3 = new Exercise("Ejercicio 3", "A", false, null);
		
		ArrayList<String> ar2 = new ArrayList<String>();
		ar2.add("Hola");
		ar2.add("Adi42os");
		
		
		ArrayList<Answer> ans = new ArrayList<Answer>();
		Answer a1 = new Answer(e1,st, 0);
		Answer a2 = new Answer(e1,st2, 0);
		ans.add(a1);
		ans.add(a2);
		
		e1.setAnswers(ans);
		
		ExerciseStat es = new ExerciseStat(e1);
		es.setAll();
		
		Option o = new Option("T");
		
		ArrayList<Option> os = new ArrayList<Option>();
		os.add(o);
		
		UQResolvedPanel lo = new UQResolvedPanel("aaaaa", "bbbb", "miii", 2, ar, ar.get(5), true);
		/*TakeTFExercisePanel aa = new TakeTFExercisePanel("ua", "ua", "uammmmmm", 30);
		TakeOTExercisePanel ab = new TakeOTExercisePanel("ua", "ua", "uammmmmm", 30);
		lo.getExerTabs().add("Ques", aa);
		lo.getExerTabs().add("Ques", ab);
		*/
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