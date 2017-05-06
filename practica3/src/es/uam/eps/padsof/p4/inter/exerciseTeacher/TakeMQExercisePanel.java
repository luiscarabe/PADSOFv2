package es.uam.eps.padsof.p4.inter.exerciseTeacher;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class TakeMQExercisePanel extends JPanel{
		//Superior Panel
		private JPanel supPanel = new JPanel();
		private ImageIcon image = new ImageIcon("logov3.png");
		private JLabel imgLabel = new JLabel(image);
		private JLabel homeLabel;
	
		private JLabel student;
		private JButton signOut = new JButton("Sign out");
		private SpringLayout layout = new SpringLayout();
		

		private JTextArea questionArea = new JTextArea();
		private JScrollPane questionPane = new JScrollPane(this.questionArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//Inf
		private JButton previous = new JButton("Previous");
		private JButton next = new JButton("Next");
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
			this.setSize(screenSize.width, screenSize.height);
			this.setLayout(layout2);
			this.setBackground(Color.decode("#98FB98"));
			
			// Sup Panel
			
			this.supPanel.setVisible(true);
			this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
			this.supPanel.setBackground(Color.decode("#228B22"));
			this.supPanel.setLayout(layout);
			
			this.homeLabel = new JLabel("Take exercise - " + nameExer);
			this.student = new JLabel("Student: "+ nameStud);
			this.imgLabel.setVisible(true);
			this.imgLabel.setBounds(0,0,200,200);
			
			this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(25f));
			this.student.setFont(this.student.getFont().deriveFont(15f));
			this.signOut.setForeground(Color.RED);
			
			
			this.supPanel.add(imgLabel);
			this.supPanel.add(homeLabel);
			this.supPanel.add(student);
			this.supPanel.add(signOut);
			
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
			layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
			
			layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);
			
			layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
			
			//Inf panel
			
			this.infPanel.setVisible(true);
			this.infPanel.setPreferredSize(new Dimension(350, 80));
			this.infPanel.setBackground(this.getBackground());
			this.infPanel.setLayout(new FlowLayout());
			this.send.setForeground(Color.GREEN);
			this.cancel.setForeground(Color.RED);
			this.infPanel.add(this.previous);
			this.infPanel.add(this.send);
			this.infPanel.add(this.cancel);
			this.infPanel.add(this.next);
			
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
			
			this.add(this.supPanel);
			this.add(this.infPanel);
			this.add(this.questionPane);
			this.add(this.solutionPane);
			
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.infPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.SOUTH, this.infPanel, -10, SpringLayout.SOUTH, this);
			
			layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.SOUTH, this.supPanel);
			
			layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
			layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);
		}
}
