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
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.MUQuestion;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p3.exercise.UniqQuestion;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.AddQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.AddQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.AddQuestionUQPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.CreateExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.ModifyQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.ModifyQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.ModifyQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.ModifyQuestionUQPanel;

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
		String pesop = view.getWeightText();
		String penal = view.getPenaltyText();
		
		
		
		Date iniD = ((SpinnerDateModel)this.view.getIniDate().getModel()).getDate();
		Date finD = ((SpinnerDateModel)this.view.getFinDate().getModel()).getDate();
		LocalDate iniLD = iniD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate finLD = finD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(iniLD);
		System.out.println(finLD);
		
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
				System.out.println(((UniqQuestion) editable).getSolution());
				if(this.view.getEqValued().isSelected() == true){
					MainFrame.getInstance().setMquqp(new ModifyQuestionUQPanel(editable.getTitle(), (ArrayList<Option>) ((UniqQuestion) editable).getAnswers(), ((UniqQuestion) editable).getSolution().get(0), strwei, ((MUQuestion)editable).isRandomOrder()), course, questions, true, editable);
				}else{
					MainFrame.getInstance().setMquqp(new ModifyQuestionUQPanel(editable.getTitle(), (ArrayList<Option>) ((UniqQuestion) editable).getAnswers(), ((UniqQuestion) editable).getSolution().get(0), strwei, ((MUQuestion)editable).isRandomOrder()), course, questions, false, editable);
					}
				return;
			}else if(editable instanceof MultiQuestion){
				double wei = editable.getWeight();
				String strwei = String.valueOf(wei);
				List<Option> sol = editable.getSolution();
				System.out.println(((MultiQuestion) editable).getSolution());
				if(this.view.getEqValued().isSelected() == true){
					MainFrame.getInstance().setMqmqp(new ModifyQuestionMQPanel(editable.getTitle(), (ArrayList<Option>) ((MultiQuestion) editable).getAnswers(), (ArrayList<Option>) ((MultiQuestion) editable).getSolution(), strwei, ((MUQuestion)editable).isRandomOrder()), course, questions, true, editable);
				}else{
					MainFrame.getInstance().setMqmqp(new ModifyQuestionMQPanel(editable.getTitle(), (ArrayList<Option>) ((MultiQuestion) editable).getAnswers(), (ArrayList<Option>) ((MultiQuestion) editable).getSolution(), strwei, ((MUQuestion)editable).isRandomOrder()), course, questions, false, editable);
					}
				return;
				
			}
			return;
		}else if(source == this.view.getDelete()){
			editable =  this.view.getQuesList().getSelectedValue();
			if(editable == null){
				return;
			}
			questions.remove(editable);
			this.view.getQuesModel().addElement(editable);
			view.validate();
			view.repaint();
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
			double weight;
			double penalty;
			boolean bool;
			Exercise exercise;
			
			if(title.equals("") || pesop.equals("") || penal.equals("")){
				JOptionPane.showMessageDialog(view, "The exercise must have a title, weight and penalty.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(questions.size() < 1){
				JOptionPane.showMessageDialog(view, "The exercise must have questions.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				weight = Double.parseDouble( pesop);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(view, "The weight must be a number", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				penalty = Double.parseDouble(penal);
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(view, "The penalty must be a number", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(weight < 0 || penalty < 0){
				JOptionPane.showMessageDialog(view, "Weight and penalty must be positive", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			exercise = unit.createExercise(title, desc, unit.isHidden());             
			if(exercise == null){
				JOptionPane.showMessageDialog(view, "There is already an element of the course with this name.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			bool = exercise.addStartDate(iniLD);
			if(bool == false){
				JOptionPane.showMessageDialog(view, "Incorrect initial date.", "Error", JOptionPane.ERROR_MESSAGE);
				unit.deleteExercise(exercise);
				return;
			}
			bool = exercise.addExpirationDate(finLD);
			if(bool == false){
				JOptionPane.showMessageDialog(view, "Incorrect expiration date.", "Error", JOptionPane.ERROR_MESSAGE);
				unit.deleteExercise(exercise);
				return;
			}
			for(Question aux: questions){
				aux.setExer(exercise);
			}
			
			exercise.setPenalty(penalty);
			exercise.setWeight(weight);
			exercise.setNumQues(questions.size());
			exercise.setQuestions(questions);
			exercise.setRandomness(this.view.getAleatOrder().isSelected());
			exercise.setEqValue(this.view.getEqValued().isSelected());
			
			if(this.view.getEqValued().isSelected() == true){
				for(Question aux : exercise.getQuestions()){
					aux.setWeight(weight/exercise.getNumQues());
				}
			}
			
			newview = MainFrame.getInstance().getCtp();
			((CourseTeacherPanel) newview).addExercise(exercise, this.unit);
			
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
