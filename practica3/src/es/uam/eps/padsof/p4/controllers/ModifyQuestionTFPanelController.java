/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionTFPanel;

/**
 * @author gjius
 *
 */
public class ModifyQuestionTFPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;

	private ModifyQuestionTFPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private List<Question> questions;
	private boolean eqValue;
	private Question question;
	
	public ModifyQuestionTFPanelController(ModifyQuestionTFPanel view, Course course, List<Question> questions, boolean eqValue, Question question) {
		this.view = view;
		this.course = course;
		this.questions = questions;
		this.eqValue = eqValue;
		this.question = question;
		
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
					
					question.setTitle(title);
					if(this.view.getSolutionF().isSelected() == true){
						((TFQuestion)question).setSolution(new Option("F"));
					}else{
						((TFQuestion)question).setSolution(new Option("T"));
					}
					
					
					newview = MainFrame.getInstance().getCep();
					
					newview.validate();
					newview.repaint();
					
					this.view.dispose();
					
					return;
				}else if(this.eqValue == false){
					double weight;
					if(title.equals("") || (this.view.getSolutionF().isSelected() == false && this.view.getSolutionT().isSelected() == false) || desc.equals("")){
						JOptionPane.showMessageDialog(view, "The question must have  title, solution and weight", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						weight = Double.parseDouble(desc);
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(view, "The weight must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question.setTitle(title);
					question.setWeight(weight);
					if(this.view.getSolutionF().isSelected() == true){
						((TFQuestion)question).setSolution(new Option("F"));
					}else{
						((TFQuestion)question).setSolution(new Option("T"));
					}
					
					
					newview = MainFrame.getInstance().getCep();
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
