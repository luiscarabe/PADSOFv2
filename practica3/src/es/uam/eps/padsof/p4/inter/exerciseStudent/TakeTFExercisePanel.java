package es.uam.eps.padsof.p4.inter.exerciseStudent;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class TakeTFExercisePanel extends JPanel{
	
	private JTextArea questionArea = new JTextArea();
	private JScrollPane questionPane = new JScrollPane(this.questionArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	
	// Solution
	private JRadioButton solutionT = new JRadioButton("True");
	private JRadioButton solutionF = new JRadioButton("False");
	private ButtonGroup solutionGroup = new ButtonGroup();
	private JPanel solutionPanel = new JPanel(new GridLayout(1, 2));
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public TakeTFExercisePanel(String nameStud, String nameExer, String nameQues, float wei){
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
		
		this.add(this.questionPane);
		this.add(this.solutionPanel);
		
		layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.NORTH, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.solutionPanel, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPanel, 10, SpringLayout.SOUTH, this.questionPane);
	}

	/**
	 * @return the questionArea
	 */
	public JTextArea getQuestionArea() {
		return questionArea;
	}

	/**
	 * @return the questionPane
	 */
	public JScrollPane getQuestionPane() {
		return questionPane;
	}

	/**
	 * @return the solutionT
	 */
	public JRadioButton getSolutionT() {
		return solutionT;
	}

	/**
	 * @return the solutionF
	 */
	public JRadioButton getSolutionF() {
		return solutionF;
	}

	/**
	 * @return the solutionGroup
	 */
	public ButtonGroup getSolutionGroup() {
		return solutionGroup;
	}

	/**
	 * @return the solutionPanel
	 */
	public JPanel getSolutionPanel() {
		return solutionPanel;
	}

	/**
	 * @return the layout2
	 */
	public SpringLayout getLayout2() {
		return layout2;
	}
	
	
}