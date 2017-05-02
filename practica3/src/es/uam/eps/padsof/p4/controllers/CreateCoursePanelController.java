/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.*;

/**
 * @author Miguel
 *
 */
public class CreateCoursePanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CreateCoursePanel view;
	private Educagram edu = Educagram.getInstance();
	
	public CreateCoursePanelController(CreateCoursePanel view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		HomePanelTeacher hpt;
		JComponent source = (JComponent) e.getSource();
		String title = view.getName();
		String desc = view.getDesc();
		Course course;
		
		//para comprobar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println(title);
		System.out.println(desc);
		
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
		}else if(source == this.view.getOk()){
			if(title.equals("")){
				JOptionPane.showMessageDialog(view, "There is no title.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			course = edu.getInstance().getProfessor().createCourse(title, desc);
			if(course == null){
				JOptionPane.showMessageDialog(view, "A course with this title already exists.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			newview = MainFrame.getInstance().getHpt();
			hpt = (HomePanelTeacher)newview;
			hpt.addCourse(course.getTitle());
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		}else if(source == this.view.getCancel()){
			newview = MainFrame.getInstance().getHpt();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}