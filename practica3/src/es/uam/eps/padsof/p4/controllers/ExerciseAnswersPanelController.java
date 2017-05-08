/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.SpecificAnswer;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.stats.ExerciseAnswersPanel;
import es.uam.eps.padsof.p4.inter.stats.ExerciseSolutionPanel;
import es.uam.eps.padsof.p4.inter.stats.MQResolvedPanel;
import es.uam.eps.padsof.p4.inter.stats.OTResolvedPanel;
import es.uam.eps.padsof.p4.inter.stats.TFResolvedPanel;
import es.uam.eps.padsof.p4.inter.stats.UQResolvedPanel;

/**
 * @author Miguel
 *
 */
public class ExerciseAnswersPanelController implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private ExerciseAnswersPanel view;
	private Educagram edu = Educagram.getInstance();
	private Exercise exercise;
	private HashMap<Question, JPanel> map;
	public ExerciseAnswersPanelController(ExerciseAnswersPanel view, Exercise exercise) {
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
		List<String> optNames = new ArrayList<String>();
		Option option = null;
		SpecificAnswer algo =  null;
		int i = 1;
		
		for(Question aux: exercise.getQuestions()){
			if(aux instanceof TFQuestion){
				for(Answer aux2: ((Student)edu.getInstance().getCurrentUser()).getAnswers()){
					if(aux2.getExercise().equals(this.exercise)){
						for(SpecificAnswer aux3: aux2.getSpecificAnswer()){
							if(aux3.getQuestion().equals(aux)){
								if(aux3.getAnswers().isEmpty()){
									option = null;
								}else{
									option = aux3.getAnswers().get(0);
								}
								break;
							}
						}
					}
				}
				if(option == null){
					tf = new TFResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), new Option("J"), false, 0);
				
				}else{
					tf = new TFResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(),option, option.getOption().equals(aux.getSolution().get(0)), (float) this.exercise.getPenalty());
				}
				map.put(aux, tf);
				this.view.getExerTabs().add(""+i, tf);
			}else if(aux instanceof OpenQuestion){
				for(Answer aux2: ((Student)edu.getInstance().getCurrentUser()).getAnswers()){
					if(aux2.getExercise().equals(this.exercise)){
						for(SpecificAnswer aux3: aux2.getSpecificAnswer()){
							if(aux3.getQuestion().equals(aux)){
								if(aux3.getAnswers().isEmpty()){
									option = null;
								}else{
									option = aux3.getAnswers().get(0);
								}
								break;
							}
						}
					}
				}
				if(option == null){
					ot = new OTResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), new Option(""), false, 0);
				}else{
					ot = new OTResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), option, option.getOption().equals(aux.getSolution().get(0)), (float) this.exercise.getPenalty());
				}
				map.put(aux, ot);
				this.view.getExerTabs().add(""+i, ot);
			}else if(aux instanceof UniqQuestion){
				for(Option aux2: ((MUQuestion)aux).getAnswers()){
					nameQues.add(aux2.getOption());
				}
				for(Answer aux2: ((Student)edu.getInstance().getCurrentUser()).getAnswers()){
					if(aux2.getExercise().equals(this.exercise)){
						for(SpecificAnswer aux3: aux2.getSpecificAnswer()){
							if(aux3.getQuestion().equals(aux)){
								if(aux3.getAnswers().isEmpty()){
									option = null;
								}else{
									option = aux3.getAnswers().get(0);
								}
								break;
							}
						}
					}
				}
				if(option == null){
					uq = new UQResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameQues, "", false, 0);
				}else{
					uq = new UQResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameQues, option.getOption(), option.getOption().equals(aux.getSolution().get(0)), (float) this.exercise.getPenalty());
				}
			
				map.put(aux, uq);
				nameQues.clear();
				this.view.getExerTabs().add(""+i, uq);
			}else if(aux instanceof MultiQuestion){
				for(Option aux2: ((MUQuestion)aux).getAnswers()){
					nameQues.add(aux2.getOption());
				}
				for(Answer aux2: ((Student)edu.getInstance().getCurrentUser()).getAnswers()){
					if(aux2.getExercise().equals(this.exercise)){
						for(SpecificAnswer aux3: aux2.getSpecificAnswer()){
							if(aux3.getQuestion().equals(aux)){
								algo = aux3;
								for(Option aux4: aux3.getAnswers()){
									optNames.add(aux4.getOption());
								}
							}
						}
					}
				}
				
				mq = new MQResolvedPanel(this.exercise.getTitle(), aux.getTitle(), (float) aux.getWeight(), (ArrayList<String>) nameQues, (ArrayList<String>) optNames, algo.getMarkOutWeight() == aux.getWeight(), (float) this.exercise.getPenalty());
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
