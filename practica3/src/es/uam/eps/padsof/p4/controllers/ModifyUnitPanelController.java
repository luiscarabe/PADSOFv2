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
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateUnitPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifyUnitPanel;

/**
 * @author Miguel
 *
 */
public class ModifyUnitPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private ModifyUnitPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Unit unit;
	
	public ModifyUnitPanelController(ModifyUnitPanel view, Course course, Unit unit) {
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
				JOptionPane.showMessageDialog(view, "The unit must have a title", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			titleaux = this.unit.getTitle();
			descaux = this.unit.getDesc();
			this.unit.setTitle(title);
			this.unit.setDesc(desc);
			for(CourseElement aux: this.course.getCourseElements()){
				if(this.unit != aux){
					if(title.equals(aux.getTitle())){
						flag = 1;
					}
				}
			}
			if(flag == 1){
				this.unit.setTitle(titleaux);
				this.unit.setDesc(descaux);
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			newview = MainFrame.getInstance().getCtp();
			
			((CourseTeacherPanel) newview).getDesc().setText(this.unit.getTitle() + ":\n" + this.unit.getDesc());
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