package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.*;

public class TestingTester {

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
		CourseTeacherPanel lo = new CourseTeacherPanel(lcour.get(0), ar );
		Unit u1 = new Unit("Hola", "hola", false, null);
		Unit u2 = new Unit("Adios", "adios", false, null);
		lo.addUnit(u1);
		lo.addSubunit(u2, u1);
		
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		//ma.setContentPane(lo);
		ma.add(lo);
		ma.setVisible(true);
		ma.setSize(ma.screenSize.width,ma.screenSize.height);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}