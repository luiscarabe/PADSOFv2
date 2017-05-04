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
import es.uam.eps.padsof.p4.inter.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.CreateSubUnitPanel;
import es.uam.eps.padsof.p4.inter.CreateUnitPanel;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;

/**
 * @author Miguel
 *
 */
public class CreateSubUnitPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CreateSubUnitPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Unit unit;
	
	public CreateSubUnitPanelController(CreateSubUnitPanel view, Course course, Unit unit) {
		this.view = view;
		this.course= course;
		this.unit = unit;
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
		Unit subunit;
		
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
				JOptionPane.showMessageDialog(view, "The subunit must have a title.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			subunit = (Unit) this.unit.createSubUnit(title, desc, false);
			if(subunit == null){
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			newview = MainFrame.getInstance().getCtp();
			((CourseTeacherPanel) newview).addSubunit(subunit, this.unit);
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