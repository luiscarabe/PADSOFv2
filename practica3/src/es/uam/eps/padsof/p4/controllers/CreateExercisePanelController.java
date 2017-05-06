/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerDateModel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionUQPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;

/**
 * @author Miguel
 *
 */
public class CreateExercisePanelController implements ActionListener{
	private static final long serialVersionUID = 1L;

	private CreateExercisePanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Unit unit;
	private List<Question> questions;
	
	public CreateExercisePanelController(CreateExercisePanel view, Course course, Unit unit) {
		this.view = view;
		this.course = course;
		this.unit = unit;
		this.questions = new ArrayList<Question>();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		
		JComponent source = (JComponent) e.getSource();
		
		// esto sera la de course !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String title = view.getName();
		String desc = view.getDesc();
		
		
		
		Date iniD = ((SpinnerDateModel)this.view.getIniDate().getModel()).getDate();
		Date finD = ((SpinnerDateModel)this.view.getFinDate().getModel()).getDate();
		LocalDate iniLD = iniD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate finLD = finD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
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
		}else if(source == this.view.getAddTFQues()){
			if(this.view.getEqValued().isSelected() == true){
				MainFrame.getInstance().setAqtfp(new AddQuestionTFPanel(), course, questions, true);
			}else{
				MainFrame.getInstance().setAqtfp(new AddQuestionTFPanel(), course, questions, false);
			}
			return;
		}else if(source == this.view.getAddOpenQues()){
			if(this.view.getEqValued().isSelected() == true){
				MainFrame.getInstance().setAqotp(new AddQuestionOTPanel(), course, questions, true);
			}else{
				MainFrame.getInstance().setAqotp(new AddQuestionOTPanel(), course, questions, false);
			}
			return;
		}else if(source == this.view.getAddUniqQues()){
			if(this.view.getEqValued().isSelected() == true){
				MainFrame.getInstance().setAquqp(new AddQuestionUQPanel(), course, questions, true);
			}else{
				MainFrame.getInstance().setAquqp(new AddQuestionUQPanel(), course, questions, false);
			}
			return;
		}else if(source == this.view.getAddMultiQues()){
			MainFrame.getInstance().setAqtfp(new AddQuestionTFPanel(), course, questions, true);
			return;
		}else if(source == this.view.getCreate()){
//			if(title.equals("") || content.equals("")){
//				JOptionPane.showMessageDialog(view, "The note must have a title and content.", "Error", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			note = this.unit.createNote(title, desc, false, content);
//			if(note == null){
//				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			newview = MainFrame.getInstance().getCtp();
//			((CourseTeacherPanel) newview).addNote(note, this.unit);
//			
//			MainFrame.getInstance().setContentPane(newview);
//			newview.setVisible(true);
//			view.setVisible(false);
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
