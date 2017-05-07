package es.uam.eps.padsof.p4.inter.stats;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

import es.uam.eps.padsof.p3.exercise.Option;

public class TFResolvedPanel extends JPanel{
	
	private JTextArea questionArea = new JTextArea();
	private JScrollPane questionPane = new JScrollPane(this.questionArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	// Solution
	private JRadioButton solutionT = new JRadioButton("True");
	private JRadioButton solutionF = new JRadioButton("False");
	private ButtonGroup solutionGroup = new ButtonGroup();
	private JPanel solutionPanel = new JPanel(new GridLayout(1, 2));
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public TFResolvedPanel(String nameExer, String nameQues, float wei, Option op){
		this.setVisible(true);
		this.setSize(new Dimension(screenSize.width, 800));
		this.setPreferredSize(new Dimension(screenSize.width, 800));
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));
		
		
		this.questionArea.setText("("+wei+" points) " + nameQues);
		this.questionArea.setFont(this.questionArea.getFont().deriveFont(15f));
		this.questionArea.setLineWrap(true);
		this.questionArea.setWrapStyleWord(true);
		this.questionPane.setPreferredSize(new Dimension(500, 80));
		this.questionArea.setEditable(false);
		this.questionArea.setBackground(this.getBackground());
		this.questionPane.setBorder(null);
		
		this.solutionGroup.add(solutionT);
		this.solutionGroup.add(solutionF);
		this.solutionPanel.add(this.solutionT);
		this.solutionPanel.add(this.solutionF);
		this.solutionF.setBackground(this.getBackground());
		this.solutionT.setBackground(this.getBackground());
		this.solutionPanel.setBackground(this.getBackground());
		
		if(op.getOption().equals("T"))
			this.solutionT.setSelected(true);
		else
			this.solutionF.setSelected(true);
		
		this.solutionF.setEnabled(false);
		this.solutionT.setEnabled(false);
		
		this.add(this.questionPane);
		this.add(this.solutionPanel);
		
		layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.NORTH, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.solutionPanel, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPanel, 10, SpringLayout.SOUTH, this.questionPane);
	}
	
	public TFResolvedPanel(String nameExer, String nameQues, float wei, Option op, boolean correct){
		this( nameExer, nameQues, wei, op);
		JLabel yourMark = new JLabel();
		if (correct == true)
			yourMark.setText("Your mark: "+ wei +"/"+ wei);
		else
			yourMark.setText("Yout mark: 0/" + wei);
		this.add(yourMark);
		
		layout2.putConstraint(SpringLayout.NORTH, yourMark, 10, SpringLayout.SOUTH, this.solutionPanel);
		layout2.putConstraint(SpringLayout.WEST, yourMark, 0, SpringLayout.WEST, this.solutionPanel);
	}
}