/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.MUQuestion;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionMQPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.ModifyQuestionMQPanel;

/**
 * @author Miguel
 *
 */
public class ModifyQuestionMQPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;

	private ModifyQuestionMQPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private List<Question> questions;
	private List<Option> solutions;
	private List<Option> oldOptions;
	private List<Option> options; 
	private boolean eqValue;
	private Question question;
	
	public ModifyQuestionMQPanelController(ModifyQuestionMQPanel view, Course course, List<Question> questions, boolean eqValue, Question question) {
		this.view = view;
		this.course = course;
		this.questions = questions;
		this.eqValue = eqValue;
		this.options = ((MUQuestion)question).getAnswers();
		this.solutions = question.getSolution();
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
			String solutionAdding = view.getAddingSolution();
			List<Option> todelete = new ArrayList<Option>();
			List<Option> tosolution = new ArrayList<Option>();
			
			Option opt;
//			
//			
//			
//			Date iniD = ((SpinnerDateModel)this.view.getIniDate().getModel()).getDate();
//			Date finD = ((SpinnerDateModel)this.view.getFinDate().getModel()).getDate();
//			LocalDate iniLD = iniD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//			LocalDate finLD = finD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			if(source == this.view.getCreate()){
				
				if(this.eqValue == true){
					if(title.equals("") || this.view.getSolutionModel().isEmpty() == true){
						JOptionPane.showMessageDialog(view, "The question must have title and solution", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(options.size() <= solutions.size()){
						JOptionPane.showMessageDialog(view, "The question must have more options than solutions.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question.setTitle(title);
					if(this.view.getAleat().isSelected() == true){
						((MUQuestion)question).setRandomOrder(true);
					}else{
						((MUQuestion)question).setRandomOrder(false);
					}
					
					
				
					newview = MainFrame.getInstance().getCep();
					newview.validate();
					newview.repaint();
					try{
						newview = MainFrame.getInstance().getMep();
						newview.validate();
						newview.repaint();
					}catch(NullPointerException ex){
						this.view.dispose();
					}
					this.view.dispose();
					
					return;
				}else if(this.eqValue == false){
					double weight;
					if(title.equals("") || this.view.getSolutionModel().isEmpty() == true || desc.equals("")){
						JOptionPane.showMessageDialog(view, "The question must have title, any solution and weight", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(options.size() <= solutions.size()){
						JOptionPane.showMessageDialog(view, "The question must have more options than solutions.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						weight = Double.parseDouble(desc);
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(view, "The weight must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(weight < 0){
						JOptionPane.showMessageDialog(view, "Weight must be positive", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question.setTitle(title);
					if(this.view.getAleat().isSelected() == true){
						((MUQuestion)question).setRandomOrder(true);
					}else{
						((MUQuestion)question).setRandomOrder(false);
					}
					
					
					
					
					
					
					newview = MainFrame.getInstance().getCep();
					newview.validate();
					newview.repaint();
					try{
						newview = MainFrame.getInstance().getMep();
						newview.validate();
						newview.repaint();
					}catch(NullPointerException ex){
						this.view.dispose();
					}
					
					this.view.dispose();
					
					return;
				}
			}else if(source == this.view.getAddSolution()){
				if(solutionAdding.equals("")){
					JOptionPane.showMessageDialog(view, "The option musn't be blank", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				opt = new Option(solutionAdding);
				options.add(opt);
				this.view.getOptionsModel().addElement(opt);
				this.view.validate();
				this.view.repaint();
			}else if(source == this.view.getOption2Solution()){
				tosolution = this.view.getOptionsList().getSelectedValuesList();
				if(tosolution == null){
					return;
				}

				for(Option aux: tosolution){
					this.view.getSolutionModel().addElement(aux);
					solutions.add(aux);
				}
				this.view.validate();
				this.view.repaint();
				return;
			}else if(source == this.view.getDelSolution()){
				todelete = this.view.getSolutionList().getSelectedValuesList();
				if(todelete == null){
					return;
				}
				
				for(Option aux: todelete){
					this.view.getSolutionModel().removeElement(aux);
					solutions.remove(aux);
				}
				
				this.view.validate();
				this.view.repaint();
				return;
			}else if(source == this.view.getDeleteOption()){
				todelete = this.view.getOptionsList().getSelectedValuesList();
				if(todelete == null){
					return;
				}
				for(Option aux: todelete){
					this.view.getOptionsModel().removeElement(aux);
					options.remove(aux);
					
					if(solutions.contains(aux)){
						this.view.getSolutionModel().removeElement(aux);
						solutions.remove(aux);
					}
				}
				
				this.view.validate();
				this.view.repaint();
				return;
			}else if(source == this.view.getCancel()){
				this.view.dispose();
				return;
			}
	}
}
