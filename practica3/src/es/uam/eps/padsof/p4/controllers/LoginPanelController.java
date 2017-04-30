

/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import es.uam.eps.padsof.p4.inter.HomePanelStudent;
import es.uam.eps.padsof.p4.inter.LoginPanel;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p3.educagram.*;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p3.user.User;

import java.awt.event.*;

import javax.swing.*;

/**
 * @author YOLANDA B
 *
 */
public class LoginPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private LoginPanel view;
	private Educagram edu = Educagram.getInstance();
	public LoginPanelController(LoginPanel view, Educagram model) {
		this.view = view;
		this.edu = model;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel newview;
		String email = view.getId();
		String psw = String.valueOf(view.getPsw());
		User current;
		
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
				newview = MainFrame.getInstance().getHpt();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
			}
			MainFrame.getInstance().setHps(new HomePanelStudent());
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