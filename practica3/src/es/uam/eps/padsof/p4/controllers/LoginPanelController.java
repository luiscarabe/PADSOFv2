

/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.*;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p3.user.User;
import es.uam.eps.padsof.p4.inter.Educagram.HomePanelStudent;
import es.uam.eps.padsof.p4.inter.Educagram.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.Educagram.LoginPanel;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * @author YOLANDA B
 *
 */
public class LoginPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private LoginPanel view;
	private Educagram edu = Educagram.getInstance();
	public LoginPanelController(LoginPanel view) {
		this.view = view;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel newview;
		String email = view.getId();
		String psw = String.valueOf(view.getPsw());
		User current;
		ArrayList<String> cournames = new ArrayList<String>();
		
		//testing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println(email);
		System.out.println(psw);
		
		if (email.equals("") || psw.equals("")) {
			JOptionPane.showMessageDialog(view, "Fill the blank spaces.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try{
			current = edu.signIn(email, psw);
			if(current == null){
				JOptionPane.showMessageDialog(view, "Wrong user. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(current.equals(edu.getProfessor())){
				for(Course aux: edu.getInstance().getCourses()){
					cournames.add(aux.getTitle());
				}
				MainFrame.getInstance().setHpt(new HomePanelTeacher( cournames));
				newview = MainFrame.getInstance().getHpt();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}
			for(Course aux: ((Student)current).getEnrolledCourses()){
				cournames.add(aux.getTitle());
			}
			MainFrame.getInstance().setHps(new HomePanelStudent(current.getName(), cournames));
			newview = MainFrame.getInstance().getHps();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

}