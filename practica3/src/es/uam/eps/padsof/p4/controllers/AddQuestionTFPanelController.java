/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpinnerDateModel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;

/**
 * @author Miguel
 *
 */
public class AddQuestionTFPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;

	private AddQuestionTFPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private List<Question> questions;
	private boolean eqValue;
	
	public AddQuestionTFPanelController(AddQuestionTFPanel view, Course course, List<Question> questions, boolean eqValue) {
		this.view = view;
		this.course = course;
		this.questions = questions;
		this.eqValue = eqValue;
		
		if(eqValue == true){
			this.view.getWeightLabel().setVisible(false);
			this.view.getWeightField().setVisible(false);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			JPanel newview;
			
			JComponent source = (JComponent) e.getSource();
			
			// esto sera la de course !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			String title = view.getName();
			String desc = view.getWeightText();
			Question question;
			Option solution;
//			
//			
//			
//			Date iniD = ((SpinnerDateModel)this.view.getIniDate().getModel()).getDate();
//			Date finD = ((SpinnerDateModel)this.view.getFinDate().getModel()).getDate();
//			LocalDate iniLD = iniD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//			LocalDate finLD = finD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if(source == this.view.getCreate()){
				
				if(this.eqValue == true){
					if(title.equals("") || (this.view.getSolutionF().isSelected() == false && this.view.getSolutionT().isSelected() == false)){
						JOptionPane.showMessageDialog(view, "The question must have  title and solution", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question = new TFQuestion(title, 0, null);
					if(this.view.getSolutionF().isSelected() == true){
						((TFQuestion)question).setSolution(new Option("F"));
					}else{
						((TFQuestion)question).setSolution(new Option("T"));
					}
					questions.add(question);
					
					newview = MainFrame.getInstance().getCep();
					((CreateExercisePanel)newview).getQuesModel().addElement(question);
					newview.validate();
					newview.repaint();
					
					this.view.dispose();
					
					return;
				}else if(this.eqValue == false){
					int weight;
					if(title.equals("") || (this.view.getSolutionF().isSelected() == false && this.view.getSolutionT().isSelected() == false) || desc.equals("")){
						JOptionPane.showMessageDialog(view, "The question must have  title, solution and weight", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						weight = Integer.parseInt(desc);
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(view, "The weight must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question = new TFQuestion(title, weight, null);
					if(this.view.getSolutionF().isSelected() == true){
						((TFQuestion)question).setSolution(new Option("F"));
					}else{
						((TFQuestion)question).setSolution(new Option("T"));
					}
					questions.add(question);
					
					newview = MainFrame.getInstance().getCep();
					((CreateExercisePanel)newview).getQuesModel().addElement(question);
					newview.validate();
					newview.repaint();
					
					this.view.dispose();
					return;
				}
			}else if(source == this.view.getCancel()){
				this.view.dispose();
				return;
			}
	}
}
