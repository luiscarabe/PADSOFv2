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
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.Educagram.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateUnitPanel;

/**
 * @author Miguel
 *
 */
public class CreateUnitPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CreateUnitPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	
	public CreateUnitPanelController(CreateUnitPanel view, Course course) {
		this.view = view;
		this.course= course;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		// esto sera la de course !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		HomePanelTeacher hpt;
		JComponent source = (JComponent) e.getSource();
		String title = view.getName();
		String desc = view.getDesc();
		Course course;
		Unit unit;
		
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
				JOptionPane.showMessageDialog(view, "The unit must have a title", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			unit = (Unit) this.course.createUnit(title, desc, false);
			if(unit == null){
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			newview = MainFrame.getInstance().getCtp();
			((CourseTeacherPanel) newview).addUnit(unit);
			((CourseTeacherPanel) newview).getCourseDesc().setText(this.course.getDesc());
			((CourseTeacherPanel) newview).getCourseDesc().repaint();
			((CourseTeacherPanel) newview).getCourseDesc().revalidate();
			((CourseTeacherPanel) newview).getDesc().setText(this.course.getDesc());
			((CourseTeacherPanel) newview).getDesc().repaint();
			((CourseTeacherPanel) newview).getDesc().revalidate();
			
			((CourseTeacherPanel) newview).getCommonButtons().setVisible(true);
			((CourseTeacherPanel) newview).getEdit().setVisible(true);
			((CourseTeacherPanel) newview).getDelete().setVisible(false);
			((CourseTeacherPanel) newview).getHide().setVisible(false);
			
			((CourseTeacherPanel) newview).getUnitButtons().setVisible(false);
			
			((CourseTeacherPanel) newview).getOtherButtons().setVisible(false);
			
			((CourseTeacherPanel) newview).getCommonButtons().validate();
			((CourseTeacherPanel) newview).getCommonButtons().repaint();
			
			((CourseTeacherPanel) newview).getUnitButtons().validate();
			((CourseTeacherPanel) newview).getUnitButtons().repaint();
			
			((CourseTeacherPanel) newview).getOtherButtons().validate();
			((CourseTeacherPanel) newview).getOtherButtons().repaint();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		}else if(source == this.view.getCancel()){
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}