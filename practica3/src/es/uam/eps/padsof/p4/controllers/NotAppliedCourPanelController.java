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
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.AppliedCourPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.NotAppliedCourPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;

/**
 * @author Miguel
 *
 */
public class NotAppliedCourPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private NotAppliedCourPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	public NotAppliedCourPanelController(NotAppliedCourPanel view, Course course) {
		this.view = view;
		this.course = course;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		Student current = (Student)edu.getCurrentUser();
		ArrayList<Course> enrolled = (ArrayList<Course>) current.getEnrolledCourses();
		ArrayList<Application> applied = (ArrayList<Application>) current.getAppliedCourses();
		ArrayList<Course> forapply = new ArrayList<Course>();
		ArrayList<Course> expelled = (ArrayList<Course>) current.getExpelledCourses();
		ArrayList<String> enrNames = new ArrayList<String>();
		ArrayList<String> appNames = new ArrayList<String>();
		ArrayList<String> foraNames = new ArrayList<String>();
		ArrayList<String> expNames = new ArrayList<String>();
		int flag = 0;
		
		for(Course aux1: edu.getCourses()){
			flag = 0;
			if(!enrolled.contains(aux1)){
				for(Application aux2: applied){
					if(aux1.equals(aux2.getCourse())){
						flag = 1;
					}
				}
				if(expelled.contains(aux1)){
					flag = 1;
				}
				if(flag == 0){
					forapply.add(aux1);
				}
			}
		}
		
		
		for(Course c: enrolled){
			enrNames.add(c.getTitle());
		}
		for(Application a: applied){
			appNames.add(a.getCourse().getTitle());
		}
		for(Course c: forapply){
			foraNames.add(c.getTitle());
		}
		for(Course c: expelled){
			expNames.add(c.getTitle());
		}
		
		
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
		}else if(source == this.view.getSearchCour()){
			MainFrame.getInstance().setScsp(new SearchCourStudentPanel(current.getName(), enrNames, foraNames, appNames, expNames));
			newview = MainFrame.getInstance().getScsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getGo()){
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			Course course = edu.searchCourse(name);
			MainFrame.getInstance().setCsp(new CourseStudentPanel(current.getName(), enrNames, course), course);
			newview = MainFrame.getInstance().getCsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getApplyButton()){
			System.out.println("Holap cachopo");
			current.applyCourse(course);
			MainFrame.getInstance().setAcp(new AppliedCourPanel(current.getName(), (ArrayList<String>) enrNames, course.getTitle()), course);
			newview = MainFrame.getInstance().getAcp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}