/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.SearchCourStudentPanel;
import es.uam.eps.padsof.p4.inter.StudentsOfCourPanel;

/**
 * @author Miguel
 *
 */
public class CourseTeacherPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CourseTeacherPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	public CourseTeacherPanelController(CourseTeacherPanel view, Course course) {
		this.view = view;
		this.course = course;
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
		}else if(source == this.view.getStudentsButton()){
			ArrayList<String> enrNames = new ArrayList<String>();
			ArrayList<String> expNames = new ArrayList<String>();
			ArrayList<String> appNames = new ArrayList<String>();
			ArrayList<Application> app = (ArrayList<Application>) course.getApplications();
			
			
			ArrayList<Student> enr = (ArrayList<Student>) course.getEnrolledStudents();
			for(Student aux: enr){
				enrNames.add(aux.getName());
			}
			
			enr = (ArrayList<Student>) course.getExpelledStudents();
			for(Student aux: enr){
				expNames.add(aux.getName());
			}
			
			for(Application aux: app){
				appNames.add(aux.getAppliedStudent().getName());
			}
			
			MainFrame.getInstance().setSocp(new StudentsOfCourPanel(course.getTitle(), enrNames, expNames, appNames), course);
			newview = MainFrame.getInstance().getSocp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		}else if(source == this.view.getGo()){
			ArrayList<String> allNames = new ArrayList<String>();
			for(Course aux : edu.getCourses()){
				allNames.add(aux.getTitle());
			}
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			Course course = edu.searchCourse(name);
			MainFrame.getInstance().setCtp(new CourseTeacherPanel( name, course.getDesc(), allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		}
	}
}