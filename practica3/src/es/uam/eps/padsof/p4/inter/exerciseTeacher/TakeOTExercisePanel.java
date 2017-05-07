package es.uam.eps.padsof.p4.inter.exerciseTeacher;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

public class TakeOTExercisePanel extends JPanel{
		private JTextArea questionArea = new JTextArea();
		private JScrollPane questionPane = new JScrollPane(this.questionArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Solution
		private JTextField solution = new JTextField(30);
		
		private SpringLayout layout2 = new SpringLayout();
		
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		public TakeOTExercisePanel(String nameStud, String nameExer, String nameQues, float wei){
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
			
			this.add(this.questionPane);
			this.add(this.solution);
			
			layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.NORTH, this);
			
			layout2.putConstraint(SpringLayout.WEST, this.solution, 0, SpringLayout.WEST, this.questionPane);
			layout2.putConstraint(SpringLayout.NORTH, this.solution, 10, SpringLayout.SOUTH, this.questionPane);
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
		 * @return the solution
		 */
		public JTextField getSolution() {
			return solution;
		}

		/**
		 * @return the layout2
		 */
		public SpringLayout getLayout2() {
			return layout2;
		}
		
		
}
