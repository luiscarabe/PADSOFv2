/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.MUQuestion;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p3.exercise.UniqQuestion;
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.ViewNoteStudentPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeMQExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeOTExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeTFExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeUQExercisePanel;

/**
 * @author Miguel
 *
 */
public class TakeExercisePanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private TakeExercisePanel view;
	private Educagram edu = Educagram.getInstance();
	private Exercise exercise;
	private HashMap<Question, JPanel> map;
	
	public TakeExercisePanelController(TakeExercisePanel view, Exercise exercise) {
		this.view = view;
		this.exercise = exercise;
		map = new HashMap<Question, JPanel>();
		Student current = (Student) Educagram.getInstance().getCurrentUser();
		
		TakeMQExercisePanel mq;
		TakeUQExercisePanel uq;
		TakeOTExercisePanel ot;
		TakeTFExercisePanel tf;
		List<String> nameOp = new ArrayList<String>();
		List<Question> ques;
		int i = 0;
		
		if(this.exercise.isRandomness()){
			Collections.shuffle(this.exercise.getQuestions());
		}
		
		
		for(Question aux: exercise.getQuestions()){
			if(aux instanceof TFQuestion){
				tf = new TakeTFExercisePanel(current.getName(), this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight());
				map.put(aux, tf);
				this.view.getExerTabs().add(""+i, tf);
			}else if(aux instanceof OpenQuestion){
				ot = new TakeOTExercisePanel(current.getName(), this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight());
				map.put(aux, ot);
				this.view.getExerTabs().add(""+i, ot);
			}else if(aux instanceof UniqQuestion){
				if(((UniqQuestion) aux).isRandomOrder() == true){
					Collections.shuffle((((UniqQuestion) aux).getAnswers()));
				}
				for(Option aux2: ((MUQuestion)aux).getAnswers()){
					nameOp.add(aux2.getOption());
				}
				uq = new TakeUQExercisePanel(current.getName(), this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameOp);
				map.put(aux, uq);
				nameOp.clear();
				this.view.getExerTabs().add(""+i, uq);
			}else if(aux instanceof MultiQuestion){
				if(((MUQuestion) aux).isRandomOrder() == true){
					Collections.shuffle((((MUQuestion) aux).getAnswers()));
				}
				for(Option aux2: ((MUQuestion)aux).getAnswers()){
					nameOp.add(aux2.getOption());
				}
				
				mq = new TakeMQExercisePanel(current.getName(), this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameOp);
				map.put(aux, mq);
				nameOp.clear();
				this.view.getExerTabs().add(""+i, mq);
			}
			i++;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		// esto sera la de course !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		JComponent source = (JComponent) e.getSource();
		Student current = (Student) Educagram.getInstance().getCurrentUser();
		Answer answer;
		SpecificAnswer sa;
		List<Option> options;
		
		TakeMQExercisePanel mq;
		TakeUQExercisePanel uq;
		TakeOTExercisePanel ot;
		TakeTFExercisePanel tf;
		
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
		}else if(source == this.view.getSend()){
			answer = new Answer(exercise, current, exercise.getNumQues());
			for(Question aux: exercise.getQuestions()){
				sa = new SpecificAnswer(aux);
				options  = new ArrayList<Option>();
				if(aux instanceof TFQuestion){
					tf = (TakeTFExercisePanel) map.get(aux);
					if(tf.getSolutionF().isSelected() == true){
						options.add(new Option("T"));
						sa.setAnswers(options);
						sa.setQuestion(aux);
						sa.calculateMark();
						sa.setAnswers(options);
					}else if(tf.getSolutionT().isSelected() == true){
						options.add(new Option("F"));
						sa.setAnswers(options);
						sa.setQuestion(aux);
						sa.calculateMark();
						sa.setAnswers(options);
					}else{
						options.add(new Option(""));
						sa.setAnswers(options);
						sa.setQuestion(aux);
						sa.calculateMark();
						sa.setAnswers(options);
					}
					answer.getSpecificAnswer().add(sa);
				}else if(aux instanceof OpenQuestion){
					ot = (TakeOTExercisePanel) map.get(aux);
					options.add(new Option(ot.getSolution().getText()));
					sa.setAnswers(options);
					sa.setQuestion(aux);
					sa.calculateMark();
					answer.getSpecificAnswer().add(sa);
				}else if(aux instanceof UniqQuestion){
					uq = (TakeUQExercisePanel) map.get(aux);
					for(JRadioButton aux2: uq.getListButtons()){
						if(aux2.isSelected()){
							options.add(new Option(aux2.getText()));
							break;
						}
					}
					if(options.isEmpty()){
						options.add(new Option(""));
					}
					sa.setAnswers(options);
					sa.setQuestion(aux);
					sa.calculateMark();
					answer.getSpecificAnswer().add(sa);
				}else if(aux instanceof MultiQuestion){
					mq = (TakeMQExercisePanel) map.get(aux);
					for(JCheckBox aux2: mq.getSolutionGroup()){
						if(aux2.isSelected()){
							options.add(new Option(aux2.getText()));
						}
					}
					if(options.isEmpty()){
						options.add(new Option(""));
					}
					sa.setAnswers(options);
					sa.setQuestion(aux);
					sa.calculateMark();
					answer.getSpecificAnswer().add(sa);
				}
				answer.calculateMark();
				System.out.println(answer.getSpecificAnswer());
				((Student)edu.getCurrentUser()).getAnswers().add(answer);
				System.out.println("Take exercise controller" + ((Student)edu.getCurrentUser()).getAnswers());
				this.exercise.getAnswers().add(answer);
				newview = MainFrame.getInstance().getCsp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				((CourseStudentPanel)newview).getTake().setVisible(false);
				newview.revalidate();
				newview.repaint();
				view.setVisible(false);
				return;
			}
		}else if(source == this.view.getCancel()){
			newview = MainFrame.getInstance().getCsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}
