/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.CreateCoursePanel;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.SearchCourStudentPanel;

/**
 * @author gjius
 *
 */
public class SearchCourStudentPanelController implements ActionListener{
	
	private SearchCourStudentPanel view;
	private Educagram edu = Educagram.getInstance();
	
	public SearchCourStudentPanelController(SearchCourStudentPanel view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		String Scourse = this.view.getScourse();
		Course course;
		List<String> auxCoursesNames;
		List<Course> auxCourses;
		List<Application> auxAppli;
		Application application = null;
		Student current = (Student)edu.getCurrentUser();
		int flag = 0;
		
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
			// falta codigop xD
			return;
		}else if(source == this.view.getAppliedButton()){
			auxCoursesNames = this.view.getAppliedList().getSelectedValuesList();
			auxAppli = current.getAppliedCourses();
			for(String aux: auxCoursesNames){
				course = edu.searchCourse(aux);
				if(course == null){
					System.out.println("Error raro en la busqueda de cursos: " + aux);
					return;
				}
				
				for(Application a: auxAppli){
					flag = 0;
					if(a.getCourse().equals(course)){
						flag = 1;
						application = a;
					}
				}
				if(flag == 1){
					current.cancelApplication(application);
				}
				this.view.delAppliedCourse(aux);
				this.view.addApplyCourse(aux);
			}
			this.view.validate();
			this.view.repaint();
			return;
			
		}else if(source == this.view.getApplyButton()){
			auxCoursesNames = this.view.getApplyList().getSelectedValuesList();
			auxCourses = edu.getCourses();
			for(String aux: auxCoursesNames){
				course = edu.searchCourse(aux);
				if(course == null){
					System.out.println("Error raro en la busqueda de cursos: " + aux);
					return;
				}
				
				current.applyCourse(course);
				
				this.view.delApplyCourse(aux);
				this.view.addAppliedCourse(aux);
			}
			this.view.validate();
			this.view.repaint();
			return;
		}
	}
	
}