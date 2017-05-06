package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateCoursePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;

import java.awt.event.ActionListener;

public class CreateNotePanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private CreateNotePanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Unit unit;
	
	public CreateNotePanelController(CreateNotePanel view, Course course, Unit unit) {
		this.view = view;
		this.course = course;
		this.unit = unit;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		// esto sera la de course !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JComponent source = (JComponent) e.getSource();
		String title = view.getName();
		String desc = view.getDesc();
		String content = view.getContent();
		Note note;
		
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
				JOptionPane.showMessageDialog(view, "The note must have a title and content.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			note = this.unit.createNote(title, desc, false, content);
			if(note == null){
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			newview = MainFrame.getInstance().getCtp();
			((CourseTeacherPanel) newview).addNote(note, this.unit);
			
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getCancel()){
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}