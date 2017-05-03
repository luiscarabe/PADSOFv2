/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.*;


/**
 * @author gjius
 *
 */
public class HomePanelTeacherController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private HomePanelTeacher view;
	private Educagram edu = Educagram.getInstance();
	public HomePanelTeacherController(HomePanelTeacher view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		ArrayList<String> allNames = new ArrayList<String>();
		
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
		}else if(source == this.view.getCreateCourse()){
			MainFrame.getInstance().setCcp(new CreateCoursePanel());
			newview = MainFrame.getInstance().getCcp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getListCourses()){
			for(Course aux : edu.getCourses()){
				allNames.add(aux.getTitle());
			}
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			Course course = edu.searchCourse(name);
			MainFrame.getInstance().setCtp(new CourseTeacherPanel( name, "aaa", allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		}
		
	}

}