/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.StudentsOfCourPanel;

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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}