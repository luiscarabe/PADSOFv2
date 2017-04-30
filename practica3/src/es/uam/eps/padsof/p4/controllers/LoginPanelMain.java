/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import javax.swing.JFrame;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.LoginPanel;
import es.uam.eps.padsof.p4.inter.MainFrame;

/**
 * @author YOLANDA B
 *
 */
public class LoginPanelMain {
	public static void main(String[] args) {
		Educagram model = Educagram.getInstance();
		MainFrame ma = MainFrame.getInstance();
		LoginPanel lo = new LoginPanel();
		//Container container = ma.getContentPane();
		//ma.setLayout(new FlowLayout());
		ma.setContentPane(lo);
		
		ma.setBounds(0,0,MainFrame.screenSize.width, MainFrame.screenSize.height);		
		ma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ma.setVisible(true);
		
		// model
		
		try{
			model.readFile();
		}catch( Exception e){
			System.out.println("Errrrrrrorrr");
		}
		
	}
}