package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import es.uam.eps.padsof.p3.exercise.Option;

public class UQResolvedPanel extends JPanel {
	private JTextArea questionArea = new JTextArea();
	private JScrollPane questionPane = new JScrollPane(this.questionArea,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	// Inf
	private JButton cancel = new JButton("Cancel");
	private JButton send = new JButton("Send");
	private JPanel infPanel = new JPanel();

	// Solution
	private ButtonGroup solutionGroup = new ButtonGroup();
	private JPanel solutionPanel;
	private JScrollPane solutionPane;

	private SpringLayout layout2 = new SpringLayout();

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public UQResolvedPanel(String nameStud, String nameExer, String nameQues, float wei, ArrayList<String> options, String solution){
		this.setVisible(true);
		this.setSize(new Dimension(screenSize.width, 800));
		this.setPreferredSize(new Dimension(screenSize.width, 800));
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));
		
		//Inf panel
		
		this.infPanel.setVisible(true);
		this.infPanel.setPreferredSize(new Dimension(350, 80));
		this.infPanel.setBackground(this.getBackground());
		this.infPanel.setLayout(new FlowLayout());
		this.send.setForeground(Color.GREEN);
		this.cancel.setForeground(Color.RED);
		this.infPanel.add(this.send);
		this.infPanel.add(this.cancel);
		
		this.questionArea.setText("("+wei+" points) " + nameQues);
		this.questionArea.setFont(this.questionArea.getFont().deriveFont(15f));
		this.questionArea.setLineWrap(true);
		this.questionArea.setWrapStyleWord(true);
		this.questionPane.setPreferredSize(new Dimension(500, 80));
		this.questionArea.setEditable(false);
		this.questionArea.setBackground(this.getBackground());
		this.questionPane.setBorder(null);
		
		//Solution
		this.solutionPanel= new JPanel(new GridLayout(options.size()+1, 1));
		this.solutionPanel.setVisible(true);
		for (String s: options){
			JRadioButton sol = new JRadioButton(s);
			if(s.equals(solution))
				sol.setSelected(true);
			sol.setEnabled(false);
			sol.setBackground(this.getBackground());
			this.solutionGroup.add(sol);
			this.solutionPanel.add(sol);
		}
		this.solutionPane =  new JScrollPane(this.solutionPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.solutionPane.setPreferredSize(new Dimension (500,300));
		this.solutionPane.setBorder(null);
		this.solutionPanel.setBackground(this.getBackground());
		
		this.add(this.infPanel);
		this.add(this.questionPane);
		this.add(this.solutionPane);
		
		layout2.putConstraint(SpringLayout.WEST, this.infPanel, 0, SpringLayout.WEST, this.solutionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.infPanel, 20, SpringLayout.SOUTH, this.solutionPane);
		
		layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.NORTH, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);
	}
	public UQResolvedPanel(String nameStud, String nameExer, String nameQues, float wei, ArrayList<String> options, String solution, boolean correct){
		this(nameStud, nameExer, nameQues, wei, options, solution);
		JLabel yourMark = new JLabel();
		if (correct == true)
			yourMark.setText("Your mark: " + wei + "/" + wei);
		else
			yourMark.setText("Yout mark: 0/" + wei);
		this.add(yourMark);
		
		layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);

		layout2.putConstraint(SpringLayout.NORTH, yourMark, 10, SpringLayout.SOUTH, this.solutionPane);
		layout2.putConstraint(SpringLayout.WEST, yourMark, 0, SpringLayout.WEST, this.solutionPane);
	}
}
