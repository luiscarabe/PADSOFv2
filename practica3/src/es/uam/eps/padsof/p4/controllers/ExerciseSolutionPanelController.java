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

import javax.swing.JComponent;
import javax.swing.JPanel;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.exercise.MUQuestion;
import es.uam.eps.padsof.p3.exercise.MultiQuestion;
import es.uam.eps.padsof.p3.exercise.OpenQuestion;
import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.exercise.TFQuestion;
import es.uam.eps.padsof.p3.exercise.UniqQuestion;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeMQExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeOTExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeTFExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.TakeUQExercisePanel;
import es.uam.eps.padsof.p4.inter.stats.ExerciseSolutionPanel;
import es.uam.eps.padsof.p4.inter.stats.GlobalStatsPanel;
import es.uam.eps.padsof.p4.inter.stats.MQResolvedPanel;
import es.uam.eps.padsof.p4.inter.stats.OTResolvedPanel;
import es.uam.eps.padsof.p4.inter.stats.TFResolvedPanel;
import es.uam.eps.padsof.p4.inter.stats.UQResolvedPanel;

/**
 * @author luis
 *
 */
public class ExerciseSolutionPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private ExerciseSolutionPanel view;
	private Educagram edu = Educagram.getInstance();
	private Exercise exercise;
	private HashMap<Question, JPanel> map;
	public ExerciseSolutionPanelController(ExerciseSolutionPanel view, Exercise exercise) {
		this.view = view;
		this.exercise = exercise;
		
		map = new HashMap<Question, JPanel>();
		Student current = (Student) Educagram.getInstance().getCurrentUser();

		MQResolvedPanel mq;
		UQResolvedPanel uq;
		OTResolvedPanel ot;
		TFResolvedPanel tf;
		List<String> nameQues = new ArrayList<String>();
		List<String> nameSol = new ArrayList<String>();
		List<Question> ques;
		int i = 1;
		
		for(Question aux: exercise.getQuestions()){
			if(aux instanceof TFQuestion){
				tf = new TFResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(),aux.getSolution().get(0));
				map.put(aux, tf);
				this.view.getExerTabs().add(""+i, tf);
			}else if(aux instanceof OpenQuestion){
				ot = new OTResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(),(ArrayList<Option>) ((OpenQuestion)aux).getSolution());
				map.put(aux, ot);
				this.view.getExerTabs().add(""+i, ot);
			}else if(aux instanceof UniqQuestion){
				for(Option aux2: ((MUQuestion)aux).getAnswers()){
					nameQues.add(aux2.getOption());
				}
				uq = new UQResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameQues, aux.getSolution().get(0).getOption());
				map.put(aux, uq);
				nameQues.clear();
				this.view.getExerTabs().add(""+i, uq);
			}else if(aux instanceof MultiQuestion){
				for(Option aux2: ((MUQuestion)aux).getAnswers()){
					nameQues.add(aux2.getOption());
				}
				for(Option aux2: aux.getSolution()){
					nameSol.add(aux2.getOption());
				}
				
				mq = new MQResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameQues, (ArrayList<String>) nameSol);
				map.put(aux, mq);
				nameQues.clear();
				nameSol.clear();
				this.view.getExerTabs().add(""+i, mq);
			}
			i++;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		
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
			newview = MainFrame.getInstance().getCsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}
	}
}