package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class supPanelStudent extends JPanel{
			private ImageIcon image = new ImageIcon("logov3.png");
			private JLabel imgLabel = new JLabel(image);
			private JLabel homeLabel = new JLabel("All Courses");
			private JLabel courses = new JLabel("My courses:");
			private JComboBox<String> listCourses;
			private JButton searchCour = new JButton("All Courses");
			private JButton marks = new JButton ("Marks");
			private JLabel student;
			private JButton signOut = new JButton("Sign out");
			private SpringLayout layout = new SpringLayout();
			
			public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			public supPanelStudent(String name, ArrayList<String> enrCour){
				String[] strCourses = {};
				
				this.setVisible(true);
				this.setPreferredSize(new Dimension(screenSize.width, 80));
				this.setBackground(Color.decode("#228B22"));
				this.setLayout(layout);
				
				this.student = new JLabel("Student: "+ name);
				
				int i = 0;
				for(String c: enrCour){
					strCourses = Arrays.copyOf(strCourses, strCourses.length+1);
					strCourses[i] = c;
					i++;
				}
				this.listCourses = new JComboBox<String>(strCourses);
				this.listCourses.setPrototypeDisplayValue("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
				this.listCourses.setSelectedItem(null);
				this.imgLabel.setVisible(true);
				this.imgLabel.setBounds(0,0,200,200);
				
				this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(30f));
				this.student.setFont(this.student.getFont().deriveFont(15f));
				this.signOut.setForeground(Color.RED);
				
				
				this.add(imgLabel);
				this.add(homeLabel);
				this.add(courses);
				this.add(listCourses);
				this.add(searchCour);
				this.add(marks);
				this.add(student);
				this.add(signOut);
				
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this);
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this);
				
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
				layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
				
				layout.putConstraint(SpringLayout.NORTH, this.courses, 45, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.EAST, this.courses, 0, SpringLayout.WEST, this.listCourses);
				
				layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.EAST, this.listCourses, 100, SpringLayout.HORIZONTAL_CENTER, this);
				
				layout.putConstraint(SpringLayout.NORTH, this.searchCour, 40, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.listCourses);
				
				layout.putConstraint(SpringLayout.NORTH, this.marks, 40, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.WEST, this.marks, 10, SpringLayout.EAST, this.searchCour);
				
				layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);
				
				layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this);
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
			 * @return the courses
			 */
			public JLabel getCourses() {
				return courses;
			}

			/**
			 * @return the listCourses
			 */
			public JComboBox<String> getListCourses() {
				return listCourses;
			}

			/**
			 * @return the searchCour
			 */
			public JButton getSearchCour() {
				return searchCour;
			}

			/**
			 * @return the marks
			 */
			public JButton getMarks() {
				return marks;
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
}
