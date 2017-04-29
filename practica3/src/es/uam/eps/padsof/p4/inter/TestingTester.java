package es.uam.eps.padsof.p4.inter;

import java.awt.*;

import javax.swing.*;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.*;

public class TestingTester {

	public static void main(String[] args) {
		Educagram.getInstance().setCurrentUser(new Student("Paco", "PAco", "Paco"));
		MainFrame ma = MainFrame.getInstance();
		HomePanel lo = new HomePanel();
		
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		//ma.setContentPane(lo);
		ma.add(lo);
		ma.setVisible(true);
		ma.setSize(1500,1500);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
