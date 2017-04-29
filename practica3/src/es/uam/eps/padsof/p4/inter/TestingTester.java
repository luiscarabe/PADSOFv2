package es.uam.eps.padsof.p4.inter;

import java.awt.*;

import javax.swing.*;

public class TestingTester {

	public static void main(String[] args) {
		MainFrame ma = MainFrame.getInstance();
		LoginPanel lo = new LoginPanel();
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		ma.setContentPane(lo);
		
		ma.setVisible(true);
		ma.setBounds(0,0,ma.screenSize.width,ma.screenSize.height);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ma.setVisible(true);


	}

}
