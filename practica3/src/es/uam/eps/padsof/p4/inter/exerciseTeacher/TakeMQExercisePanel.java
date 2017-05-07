package es.uam.eps.padsof.p4.inter.exerciseTeacher;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class TakeMQExercisePanel extends JPanel{

		private JTextArea questionArea = new JTextArea();
		private JScrollPane questionPane = new JScrollPane(this.questionArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Inf
		private JButton cancel = new JButton("Cancel");
		private JButton send = new JButton("Send");
		private JPanel infPanel = new JPanel();
		
		// Solution
		private ArrayList<JCheckBox> solutionGroup = new ArrayList<JCheckBox>();
		private JPanel solutionPanel;
		private JScrollPane solutionPane;
		
		private SpringLayout layout2 = new SpringLayout();
		
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		public TakeMQExercisePanel(String nameStud, String nameExer, String nameQues, float wei, ArrayList<String> options){
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
				JCheckBox sol = new JCheckBox(s);
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
			
			
			layout2.putConstraint(SpringLayout.WEST, this.infPanel, 0, SpringLayout.WEST, this.solutionPanel);
			layout2.putConstraint(SpringLayout.NORTH, this.infPanel, 20, SpringLayout.SOUTH, this.solutionPanel);
			
			layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.NORTH, this);
			
			layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
			layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);
		}
}
