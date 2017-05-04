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
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.CreateCoursePanel;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.ModifyCoursePanel;

/**
 * @author e341020
 *
 */
public class ModifyCoursePanelController implements ActionListener{
private static final long serialVersionUID = 1L;
	
	private ModifyCoursePanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	
	public ModifyCoursePanelController(ModifyCoursePanel view, Course course) {
		this.view = view;
		this.course = course;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		HomePanelTeacher hpt;
		JComponent source = (JComponent) e.getSource();
		/*String title = view.getName();*/
		String desc = view.getDesc();
		Course course;
		
		//para comprobar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
			this.course.setDesc(desc);

			newview = MainFrame.getInstance().getCtp();
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
