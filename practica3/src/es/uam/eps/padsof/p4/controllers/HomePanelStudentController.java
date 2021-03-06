package es.uam.eps.padsof.p4.controllers;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p3.user.User;
import es.uam.eps.padsof.p4.inter.Educagram.HomePanelStudent;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateCoursePanel;

public class HomePanelStudentController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private HomePanelStudent view;
	private Educagram edu = Educagram.getInstance();
	public HomePanelStudentController(HomePanelStudent view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		Student current = (Student)edu.getCurrentUser();
		System.out.println(edu.getCurrentUser() == null);
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
			if(this.view.getListCourses().getSelectedItem() == null){
				return;
			}
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
		}
	}
}