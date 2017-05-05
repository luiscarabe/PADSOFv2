/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.SearchCourTeacherPanel;

/**
 * @author e341020
 *
 */
public class SearchCourTeacherPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private SearchCourTeacherPanel view;
	private Educagram edu = Educagram.getInstance();
	
	public SearchCourTeacherPanelController(SearchCourTeacherPanel view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		ArrayList<String> allNames = new ArrayList<String>();
		String Scourse = this.view.getScourse();
		Course course;
		
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
		}else if(source == this.view.getSearchButton()){
			if(Scourse.equals("")){
				JOptionPane.showMessageDialog(view, "Write the name of a course.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			course = edu.searchCourse(Scourse);
			if(course == null){
				JOptionPane.showMessageDialog(view, "There is not a course with that name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for(Course aux : edu.getCourses()){
				allNames.add(aux.getTitle());
			}
			MainFrame.getInstance().setCtp(new CourseTeacherPanel(course, allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getGo()){
			if(this.view.getListCourses().getSelectedItem() == null){
				return;
			}
			
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			for(Course aux : edu.getCourses()){
				allNames.add(aux.getTitle());
			}
			course = edu.searchCourse(name);
			MainFrame.getInstance().setCtp(new CourseTeacherPanel(course, allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getSearchCour()){
			return;
		}else if(source == this.view.getCourButton()){
			String name = this.view.getCourList().getSelectedValue();
			if(name == null){
				return;
			}
			course = edu.searchCourse(name);
			for(Course aux : edu.getCourses()){
				allNames.add(aux.getTitle());
			}
			
			MainFrame.getInstance().setCtp(new CourseTeacherPanel(course, allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}
