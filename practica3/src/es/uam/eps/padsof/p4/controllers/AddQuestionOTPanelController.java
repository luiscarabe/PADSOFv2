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
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;

/**
 * @author Miguel
 *
 */
public class AddQuestionOTPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;

	private AddQuestionOTPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private List<Option> sols;
	private List<Question> questions;
	private boolean eqValue;
	
	public AddQuestionOTPanelController(AddQuestionOTPanel view, Course course, List<Question> questions, boolean eqValue) {
		this.view = view;
		this.course = course;
		this.questions = questions;
		this.eqValue = eqValue;
		this.sols = new ArrayList<Option>();
		
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
			Question question;
			List<Option> todelete = new ArrayList<Option>();
			
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
					if(title.equals("") || this.view.getSolutionModel().isEmpty() == true){
						JOptionPane.showMessageDialog(view, "The question must have title and any solution", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question = new OpenQuestion(title, 0, null);
					for(Option aux: sols){
						((OpenQuestion)question).addSolution(aux);
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
					if(title.equals("") || this.view.getSolutionModel().isEmpty() == true || desc.equals("")){
						JOptionPane.showMessageDialog(view, "The question must have title, any solution and weight", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					try{
						weight = Integer.parseInt(desc);
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(view, "The weight must be a number", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					question = new OpenQuestion(title, weight, null);
					for(Option aux: sols){
						((OpenQuestion)question).addSolution(aux);
					}
					questions.add(question);
					
					newview = MainFrame.getInstance().getCep();
					((CreateExercisePanel)newview).getQuesModel().addElement(question);
					newview.validate();
					newview.repaint();
					
					this.view.dispose();
					return;
				}
			}else if(source == this.view.getAddSolution()){
				if(solutionAdding.equals("")){
					JOptionPane.showMessageDialog(view, "The solution musn't be blank", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				solution = new Option(solutionAdding);
				sols.add(solution);
				this.view.getSolutionModel().addElement(solution);
				this.view.validate();
				this.view.repaint();
			}else if(source == this.view.getDelSolution()){
				todelete = this.view.getSolutionList().getSelectedValuesList();
				if(todelete == null){
					return;
				}
				for(Option aux: todelete){
					sols.remove(aux);
					this.view.getSolutionModel().removeElement(aux);
					this.view.validate();
					this.view.repaint();
				}
			}else if(source == this.view.getCancel()){
				this.view.dispose();
				return;
			}
	}
}
