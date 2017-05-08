/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p4.inter.exerciseStudent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

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
		
		//Inf
		private JButton cancel = new JButton("Cancel");
		private JButton send = new JButton("Send");
		private JPanel infPanel = new JPanel();
		
		JTabbedPane exerTabs = new JTabbedPane();
		
		private SpringLayout layout2 = new SpringLayout();
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/**
		 * Constructor of TakeExercisePanel
		 * @param nameStud
		 * @param nameExer
		 */
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
			
			this.exerTabs.setPreferredSize(new Dimension(screenSize.width, 500));
			
			//Inf panel
			
			this.infPanel.setVisible(true);
			this.infPanel.setPreferredSize(new Dimension(350, 80));
			this.infPanel.setBackground(this.getBackground());
			this.infPanel.setLayout(new FlowLayout());
			this.send.setForeground(Color.GREEN);
			this.cancel.setForeground(Color.RED);
			this.infPanel.add(this.send);
			this.infPanel.add(this.cancel);
			
			this.add(this.infPanel);
			this.add(this.supPanel);
			this.add(this.exerTabs);
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.infPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.NORTH, this.infPanel, 20, SpringLayout.SOUTH, this.exerTabs );
			
			layout2.putConstraint(SpringLayout.NORTH, this.exerTabs, 0, SpringLayout.SOUTH, this.supPanel);
			layout2.putConstraint(SpringLayout.WEST, this.exerTabs, 0, SpringLayout.WEST, this);
			
		}
		/**
		 * Method to set controllers
		 * @param c
		 */

		public void setController(ActionListener c){
			this.signOut.addActionListener(c);
			this.send.addActionListener(c);
			this.cancel.addActionListener(c);
		}

		/**
		 * @return the supPanel
		 */
		public JPanel getSupPanel() {
			return supPanel;
		}

		/**
		 * @return the image
		 */
		public ImageIcon getImage() {
			return image;
		}

		/**
		 * @return the imgLabel
		 */
		public JLabel getImgLabel() {
			return imgLabel;
		}

		/**
		 * @return the homeLabel
		 */
		public JLabel getHomeLabel() {
			return homeLabel;
		}

		/**
		 * @return the student
		 */
		public JLabel getStudent() {
			return student;
		}

		/**
		 * @return the signOut
		 */
		public JButton getSignOut() {
			return signOut;
		}

		/**
		 * @return the layout
		 */
		public SpringLayout getLayout() {
			return layout;
		}

		/**
		 * @return the cancel
		 */
		public JButton getCancel() {
			return cancel;
		}

		/**
		 * @return the send
		 */
		public JButton getSend() {
			return send;
		}

		/**
		 * @return the infPanel
		 */
		public JPanel getInfPanel() {
			return infPanel;
		}

		/**
		 * @return the exerTabs
		 */
		public JTabbedPane getExerTabs() {
			return exerTabs;
		}

		/**
		 * @return the layout2
		 */
		public SpringLayout getLayout2() {
			return layout2;
		}
		
		
			
}
