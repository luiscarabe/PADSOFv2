/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.StudentsOfCourPanel;

/**
 * @author Miguel
 *
 */
public class StudentsOfCourPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private StudentsOfCourPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	public StudentsOfCourPanelController(StudentsOfCourPanel view, Course course) {
		this.view = view;
		this.course = course;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		List<String> studentsSelected;
		List<Application> applications = new ArrayList<Application>();
		Application app;
		Student s;
		
		
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
		}else if(source == this.view.getReturning()){
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getAppliedAcceptButton()){
			studentsSelected = this.view.getAppliedList().getSelectedValuesList();
			for(String aux: studentsSelected){
				s = edu.searchStudent(aux);
				if(s == null){
					return;
				}
				app = course.searchApplication(s);
				if(app == null){
					return;
				}
				try {
					edu.getProfessor().confirmApplication(app, true);
				} catch (Exception e1) {
					System.out.println("Error sending notification");
				}
				this.view.addEnrStudent(aux);
				this.view.delAppliedStudent(aux);
			}
			this.view.validate();
			this.view.repaint();
			return;
		}else if(source == this.view.getAppliedRejectButton()){
			studentsSelected = this.view.getAppliedList().getSelectedValuesList();
			for(String aux: studentsSelected){
				s = edu.searchStudent(aux);
				if(s == null){
					return;
				}
				app = course.searchApplication(s);
				if(app == null){
					return;
				}
				try {
					edu.getProfessor().confirmApplication(app, false);
				} catch (Exception e1) {
					System.out.println("Error sending notification");
				}
				this.view.delAppliedStudent(aux);
			}
			this.view.validate();
			this.view.repaint();
			return;
		}else if(source == this.view.getEnrolButton()){
			studentsSelected = this.view.getEnrolList().getSelectedValuesList();
			for(String aux: studentsSelected){
				s = edu.searchStudent(aux);
				if(s == null){
					return;
				}
				try {
					edu.getProfessor().expellStudent(s, course);
				} catch (Exception e1) {
					System.out.println("Error sending notification");
				}
				this.view.delEnrStudent(aux);
				this.view.addExpelStudent(aux);;
			}
			this.view.validate();
			this.view.repaint();
			return;
		}else if(source == this.view.getExpelButton()){
			studentsSelected = this.view.getExpelList().getSelectedValuesList();
			for(String aux: studentsSelected){
				s = edu.searchStudent(aux);
				if(s == null){
					return;
				}
				try {
					edu.getProfessor().readmitStudent(s, course);
				} catch (Exception e1) {
					System.out.println("Error sending notification");
				}
				this.view.addEnrStudent(aux);
				this.view.delExpelStudent(aux);;
			}
			this.view.validate();
			this.view.repaint();
			return;
		}
	}
}