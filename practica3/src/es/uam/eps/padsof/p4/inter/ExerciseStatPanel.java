package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.*;

import es.uam.eps.padsof.p3.exercise.Option;
import es.uam.eps.padsof.p3.stat.*;

public class ExerciseStatPanel extends JDialog{
	
	private JPanel jp = new JPanel();
	
	private JLabel firstLabel = new JLabel();
	
	private JPanel marksPanel = new JPanel();
	private JScrollPane marksPane = new JScrollPane(this.marksPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton back = new JButton("Return");
	
	private SpringLayout layout = new SpringLayout();
	
	public ExerciseStatPanel(ExerciseStat es){
		this.jp.setVisible(true);
		this.jp.setPreferredSize(new Dimension(900, 500));
		this.jp.setLayout(layout);

		this.firstLabel.setText(es.getExercise().getTitle());
		Map attributes = this.firstLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.firstLabel.setFont(this.firstLabel.getFont().deriveFont(attributes));
		
		this.marksPanel.setVisible(true);
		this.marksPanel.setLayout(new GridLayout(es.getExercise().getAnswers().size(),1));
		for(Answer a: es.getExercise().getAnswers()){
			JLabel jl = new JLabel(a.getStudent().getName() + ": "+a.getMarkOut10());
			this.marksPanel.add(jl);
		}
		this.marksPane.setPreferredSize(new Dimension(300,450));
		this.marksPane.setBorder(null);
		this.marksPanel.setBackground(Color.BLACK);
		this.marksPane.setBackground(Color.black);
		
		this.jp.add(this.marksPane);
		this.jp.add(this.firstLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.firstLabel, 10, SpringLayout.NORTH, this.jp);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.firstLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.jp);
	
		layout.putConstraint(SpringLayout.NORTH, this.marksPane, 10, SpringLayout.SOUTH, this.firstLabel);
		layout.putConstraint(SpringLayout.WEST, this.marksPane, 10, SpringLayout.WEST, this.jp);
		
		this.setContentPane(this.jp);
		this.setVisible(true);
		this.setSize(new Dimension(900,500));
	}
	
}
