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
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.Educagram.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifyNotePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifySubunitPanel;

/**
 * @author gjius
 *
 */
public class ModifyNotePanelController implements ActionListener{
private static final long serialVersionUID = 1L;
	
	private ModifyNotePanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Note note;
	
	public ModifyNotePanelController( ModifyNotePanel view, Course course, Note note) {
		this.view = view;
		this.course= course;
		this.note = note;
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
		String content = view.getContent();
		String titleaux;
		String descaux;
		String contentaux;
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
			if(title.equals("") || content.equals("")){
				JOptionPane.showMessageDialog(view, "TThe note must have a title and content.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			titleaux = this.note.getTitle();
			descaux = this.note.getDesc();
			contentaux = this.note.getText();
			this.note.setTitle(title);
			this.note.setDesc(desc);
			this.note.setText(content);
			for(CourseElement aux: this.course.getCourseElements()){
				if(this.note != aux){
					if(title.equals(aux.getTitle())){
						flag = 1;
					}
				}
			}
			if(flag == 1){
				this.note.setTitle(titleaux);
				this.note.setDesc(descaux);
				this.note.setText(contentaux);
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			newview = MainFrame.getInstance().getCtp();
			
			((CourseTeacherPanel) newview).getDesc().setText(this.note.getTitle() + ":\n" + this.note.getDesc());
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
