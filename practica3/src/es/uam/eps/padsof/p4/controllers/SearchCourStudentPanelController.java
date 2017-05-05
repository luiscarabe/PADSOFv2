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
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.AppliedCourPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.NotAppliedCourPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;

/**
 * @author gjius
 *
 */
public class SearchCourStudentPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
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
		List<Course> auxCourses1 = edu.getCourses();
		List<Course> auxCourses2 = new ArrayList<Course>();
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
			auxAppli = current.getAppliedCourses();
			for(Application a: auxAppli){
				auxCourses2.add(a.getCourse());
				if(a.getCourse().equals(course)){
					flag = 1;
				}
			}
			if(course.isEnrolledStudent(current)){
				auxCoursesNames = new ArrayList<String>();
				for(Course aux : auxCourses1){
					auxCoursesNames.add(aux.getTitle());
				}
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				MainFrame.getInstance().setCsp(new CourseStudentPanel(current.getName(), (ArrayList<String>) auxCoursesNames, course), course);
				newview = MainFrame.getInstance().getCsp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}else if(course.isExpelledStudent(current)){
				JOptionPane.showMessageDialog(view, "You've been expelled from this course. You can not access to it.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}else if(flag == 1){
				auxCoursesNames = new ArrayList<String>();
				auxCourses2 = current.getEnrolledCourses();
				for(Course aux : auxCourses2){
					auxCoursesNames.add(aux.getTitle());
				}
				MainFrame.getInstance().setAcp(new AppliedCourPanel(current.getName(), (ArrayList<String>) auxCoursesNames, course.getTitle()), course);
				newview = MainFrame.getInstance().getAcp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}else{
				auxCoursesNames = new ArrayList<String>();
				for(Course aux : auxCourses1){
					auxCoursesNames.add(aux.getTitle());
				}
				MainFrame.getInstance().setNacp(new NotAppliedCourPanel(current.getName(), (ArrayList<String>) auxCoursesNames, course.getTitle()), course);
				newview = MainFrame.getInstance().getNacp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}
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
			auxCourses1 = edu.getCourses();
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
		}else if(source == this.view.getGo()){
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			auxCoursesNames = new ArrayList<String>();
			auxCourses2 = current.getEnrolledCourses();
			for(Course aux : auxCourses2){
				auxCoursesNames.add(aux.getTitle());
			}
			course = edu.searchCourse(name);
			MainFrame.getInstance().setCsp(new CourseStudentPanel(current.getName(), (ArrayList<String>) auxCoursesNames, course), course);
			newview = MainFrame.getInstance().getCsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getEnrolButton()){
			String name = this.view.getEnrolList().getSelectedValue();
			if(name == null){
				return;
			}
			auxCoursesNames = new ArrayList<String>();
			auxCourses2 = current.getEnrolledCourses();
			for(Course aux : auxCourses2){
				auxCoursesNames.add(aux.getTitle());
			}
			course = edu.searchCourse(name);
			MainFrame.getInstance().setCsp(new CourseStudentPanel(current.getName(), (ArrayList<String>) auxCoursesNames, course), course);
			newview = MainFrame.getInstance().getCsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
	
}