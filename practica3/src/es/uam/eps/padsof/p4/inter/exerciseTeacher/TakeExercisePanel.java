package es.uam.eps.padsof.p4.inter.exerciseTeacher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

public class TakeExercisePanel extends JPanel{
		//Superior Panel
		private JPanel supPanel = new JPanel();
		private ImageIcon image = new ImageIcon("logov3.png");
		private JLabel imgLabel = new JLabel(image);
		private JLabel homeLabel;
		
		private JLabel student;
		private JButton signOut = new JButton("Sign out");
		private SpringLayout layout = new SpringLayout();
		
		JTabbedPane exerTabs = new JTabbedPane();
		
		private SpringLayout layout2 = new SpringLayout();
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
		public TakeExercisePanel(String nameStud, String nameExer){
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
			
			this.add(this.supPanel);
			this.add(this.exerTabs);
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.NORTH, this.exerTabs, 0, SpringLayout.SOUTH, this.supPanel);
			layout2.putConstraint(SpringLayout.WEST, this.exerTabs, 0, SpringLayout.WEST, this);
		}

		public JPanel getSupPanel() {
			return supPanel;
		}

		public ImageIcon getImage() {
			return image;
		}

		public JLabel getImgLabel() {
			return imgLabel;
		}

		public JLabel getHomeLabel() {
			return homeLabel;
		}

		public JLabel getStudent() {
			return student;
		}

		public JButton getSignOut() {
			return signOut;
		}

		public SpringLayout getLayout() {
			return layout;
		}

		public JTabbedPane getExerTabs() {
			return exerTabs;
		}

		public SpringLayout getLayout2() {
			return layout2;
		}

		public static Dimension getScreenSize() {
			return screenSize;
		}
			
}
