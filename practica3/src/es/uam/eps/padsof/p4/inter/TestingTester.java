package es.uam.eps.padsof.p4.inter;

import java.awt.*;

import javax.swing.*;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.*;

public class TestingTester {

	public static void main(String[] args) {
		Educagram.getInstance().setCurrentUser(new Professor("Paco", "PAco", "Paco"));
		Professor p = (Professor) Educagram.getInstance().getCurrentUser();
		p.createCourse("Manolo", "Interesante");
		System.out.println(Educagram.getInstance().getCourses().get(0));
		MainFrame ma = MainFrame.getInstance();
		HomePanelTeacher lo = new HomePanelTeacher();
		
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		//ma.setContentPane(lo);
		ma.add(lo);
		ma.setVisible(true);
		ma.setSize(1500,1500);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
