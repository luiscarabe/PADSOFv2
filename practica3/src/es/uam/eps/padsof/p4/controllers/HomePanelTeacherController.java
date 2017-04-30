/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.*;

import javax.swing.*;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;


/**
 * @author gjius
 *
 */
public class HomePanelTeacherController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private HomePanelTeacher view;
	private Educagram edu = Educagram.getInstance();
	public HomePanelTeacherController(HomePanelTeacher view, Educagram model) {
		this.view = view;
		this.edu = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		
		try{
			
			Educagram.getInstance().signOut();
			
			
			newview = MainFrame.getInstance().getLp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
	}

}
