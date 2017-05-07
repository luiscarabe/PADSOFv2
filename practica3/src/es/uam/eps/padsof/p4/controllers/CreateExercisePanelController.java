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
import es.uam.eps.padsof.p3.exercise.MUQuestion;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p3.exercise.UniqQuestion;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionUQPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionUQPanel;

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
		Question editable;
		
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
			if(this.view.getEqValued().isSelected() == true){
				MainFrame.getInstance().setAqmqp(new AddQuestionMQPanel(), course, questions, true);
			}else{
				MainFrame.getInstance().setAqmqp(new AddQuestionMQPanel(), course, questions, false);
			}
			return;
		}else if(source == this.view.getEdit()){
			editable =  this.view.getQuesList().getSelectedValue();
			if(editable == null){
				return;
			}
			if(editable instanceof TFQuestion){
				double wei = editable.getWeight();
				String strwei = String.valueOf(wei);
				List<Option> sol = editable.getSolution();
				String au = sol.get(0).getOption();
				boolean boolsol;
				if(au.equals("T") || au.equals("t")){
					boolsol = true;
				}else{
					boolsol = false;
				}
				if(this.view.getEqValued().isSelected() == true){
					MainFrame.getInstance().setMqtfp(new ModifyQuestionTFPanel(editable.getTitle(), strwei, boolsol), course, questions, true, editable);
				}else{
					MainFrame.getInstance().setMqtfp(new ModifyQuestionTFPanel(editable.getTitle(), strwei, boolsol), course, questions, false, editable);
				}
				return;
			}else if(editable instanceof OpenQuestion){
				double wei = editable.getWeight();
				String strwei = String.valueOf(wei);
				List<Option> sol = editable.getSolution();
				if(this.view.getEqValued().isSelected() == true){
					MainFrame.getInstance().setMqotp(new ModifyQuestionOTPanel(editable.getTitle(), (ArrayList<Option>) sol, strwei), course, questions, true, editable);
				}else{
					MainFrame.getInstance().setMqotp(new ModifyQuestionOTPanel(editable.getTitle(), (ArrayList<Option>) sol, strwei), course, questions,false, editable);
				}
				return;
			}else if(editable instanceof UniqQuestion){
				double wei = editable.getWeight();
				String strwei = String.valueOf(wei);
				List<Option> sol = editable.getSolution();
				if(this.view.getEqValued().isSelected() == true){
					MainFrame.getInstance().setMquqp(new ModifyQuestionUQPanel(editable.getTitle(), (ArrayList<Option>) ((UniqQuestion) editable).getAnswers(), editable.getSolution().get(0), strwei, ((MUQuestion)editable).isRandomOrder()), course, questions, true, editable);
				}else{
					MainFrame.getInstance().setMquqp(new ModifyQuestionUQPanel(editable.getTitle(), (ArrayList<Option>) ((UniqQuestion) editable).getAnswers(), editable.getSolution().get(0), strwei, ((MUQuestion)editable).isRandomOrder()), course, questions, false, editable);
					}
				return;
			}else if(editable instanceof MultiQuestion){
				if(this.view.getEqValued().isSelected() == true){
					MainFrame.getInstance().setAqmqp(new AddQuestionMQPanel(), course, questions, true);
				}else{
					MainFrame.getInstance().setAqmqp(new AddQuestionMQPanel(), course, questions, false);
				}
				return;
			}
			return;
		}else if(source == this.view.getEqValued()){
			if(this.view.getQuesModel().isEmpty() == false){
				if(this.view.getEqValued().isSelected() == true){
					this.view.getEqValued().setSelected(false);
				}else {
					this.view.getEqValued().setSelected(true);
				}
			}
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
