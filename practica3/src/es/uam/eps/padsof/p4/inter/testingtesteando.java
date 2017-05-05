package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.user.*;

public class testingtesteando {

	public static void main(String[] args) {
		Educagram edu = Educagram.getInstance();
		edu.setCurrentUser(new Student("Paco", "PAco", "Paco"));
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
		ar.add("Adios");
		ar.add("Hola");
		ar.add("Adios");
		ar.add("Hola");
		ar.add("Adios");
		ar.add("Hola");
		ar.add("Adios");
		CourseStudentPanel lo = new CourseStudentPanel("Paco", ar, lcour.get(0));
		MainFrame.getInstance().setCsp(lo, lcour.get(0));
		Unit u1 = new Unit("Unidad 1", "hola", false, null);
		Unit u2 = new Unit("Subunidad 1", "adios", false, null);
		Unit u3 = new Unit("Subunidad 2", "adios", false, null);
		Unit u4 = new Unit("Unidad 2", "adios", false, null);
		Note n1 = new Note("Nota 1", "oagoisa", false, null, null);
		Note n2 = new Note("Nota 2", "oagoisa", false, null, null);
		Exercise e1 = new Exercise("Ejercicio 1", "A", false, null);
		Exercise e2 = new Exercise("Ejercicio 2", "A", false, null);
		Exercise e3 = new Exercise("Ejercicio 3", "A", false, null);
		lo.addUnit(u1);
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
		
		lo.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		//ma.setContentPane(lo);
		ma.add(lo);
		ma.setVisible(true);
		ma.setSize(ma.screenSize.width,ma.screenSize.height);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}