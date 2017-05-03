/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.AppliedCourPanel;
import es.uam.eps.padsof.p4.inter.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.MainFrame;
/**
 * @author Miguel
 *
 */
public class AppliedCourPanelController implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private AppliedCourPanel view;
	private Educagram edu = Educagram.getInstance();
	public AppliedCourPanelController(AppliedCourPanel view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		
		
		if(source == this.view.getSignOut()){
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

}