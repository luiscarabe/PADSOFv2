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
import es.uam.eps.padsof.p3.course.CourseElement;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifySubunitPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifyUnitPanel;

/**
 * @author gjius
 *
 */
public class ModifySubUnitPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private ModifySubunitPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Unit subunit;
	
	public ModifySubUnitPanelController( ModifySubunitPanel view, Course course, Unit subunit) {
		this.view = view;
		this.course= course;
		this.subunit = subunit;
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
		String titleaux;
		String descaux;
		Course course;
		Unit unit;
		int flag = 0;
		
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
				JOptionPane.showMessageDialog(view, "The subunit must have a title", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			titleaux = this.subunit.getTitle();
			descaux = this.subunit.getDesc();
			this.subunit.setTitle(title);
			this.subunit.setDesc(desc);
			for(CourseElement aux: this.course.getCourseElements()){
				if(this.subunit != aux){
					if(title.equals(aux.getTitle())){
						flag = 1;
					}
				}
			}
			if(flag == 1){
				this.subunit.setTitle(titleaux);
				this.subunit.setDesc(descaux);
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			newview = MainFrame.getInstance().getCtp();
			
			((CourseTeacherPanel) newview).getDesc().setText(this.subunit.getTitle() + ":\n" + this.subunit.getDesc());
			((CourseTeacherPanel) newview).getDesc().repaint();
			((CourseTeacherPanel) newview).getDesc().revalidate();
			
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
