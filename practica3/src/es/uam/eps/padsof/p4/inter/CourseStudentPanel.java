package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;

public class CourseStudentPanel extends JPanel{
	//Superior Panel
			private JPanel supPanel = new JPanel();
			private ImageIcon image = new ImageIcon("logov3.png");
			private JLabel imgLabel = new JLabel(image);
			private JLabel homeLabel = new JLabel("Home page");
			private JLabel courses = new JLabel("My courses:");
			private JComboBox<String> listCourses;
			private JButton go = new JButton("Go");
			private JButton searchCour = new JButton("All Courses");
			private JButton marks = new JButton ("Marks");
			private JLabel student;
			private JButton signOut = new JButton("Sign out");
			private SpringLayout layout = new SpringLayout();
			
			private JLabel courseLabel;
			private JLabel applyLabel = new JLabel("This is the content of the course");
			
			private SpringLayout layout2 = new SpringLayout();
			
			public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			public CourseStudentPanel(String name, ArrayList<String> enrCourses, String courName){
				String[] strCourses = {};
				this.setVisible(true);
				this.setSize(screenSize.width, screenSize.height);
				this.setLayout(layout2);
				this.setBackground(Color.decode("#98FB98"));
				
				this.supPanel.setVisible(true);
				this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
				this.supPanel.setBackground(Color.decode("#228B22"));
				this.supPanel.setLayout(layout);
				
				this.student = new JLabel("Student: "+ name);
				int i = 0;
				for(String c: enrCourses){
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
				
				this.go.setPreferredSize(new Dimension(53,25));
				
				
				this.supPanel.add(imgLabel);
				this.supPanel.add(homeLabel);
				this.supPanel.add(courses);
				this.supPanel.add(listCourses);
				this.supPanel.add(searchCour);
				this.supPanel.add(marks);
				this.supPanel.add(student);
				this.supPanel.add(signOut);
				this.supPanel.add(go);
				
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
				
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
				layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
				
				layout.putConstraint(SpringLayout.NORTH, this.courses, 45, SpringLayout.NORTH, this.supPanel);
				layout.putConstraint(SpringLayout.EAST, this.courses, 0, SpringLayout.WEST, this.listCourses);
				
				layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this.supPanel);
				layout.putConstraint(SpringLayout.EAST, this.listCourses, 100, SpringLayout.HORIZONTAL_CENTER, this.supPanel);
				
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.go, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
				layout.putConstraint(SpringLayout.WEST, this.go, 10, SpringLayout.EAST, this.listCourses);
				
				
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.searchCour, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
				layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.go);
				
				layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.marks, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
				layout.putConstraint(SpringLayout.WEST, this.marks, 10, SpringLayout.EAST, this.searchCour);
				
				layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
				layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);
				
				layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
				layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
				
				Map attributes = this.courseLabel.getFont().getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(attributes));
				this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(30f));
				

				this.add(this.supPanel);
				this.add(this.courseLabel);
				this.add(this.applyLabel);
				
				
				layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
				layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
				layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
				layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
				
				layout2.putConstraint(SpringLayout.WEST, this.courseLabel, 10, SpringLayout.WEST, this);
				layout2.putConstraint(SpringLayout.NORTH, this.courseLabel, 10, SpringLayout.SOUTH, this.supPanel);
				
				layout2.putConstraint(SpringLayout.WEST, this.applyLabel, 10, SpringLayout.WEST, this);
				layout2.putConstraint(SpringLayout.NORTH, this.applyLabel, 10, SpringLayout.SOUTH, this.courseLabel);
				
			}
			
			public void setController(ActionListener c){
				this.signOut.addActionListener(c);
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

			/**
			 * @return the courseLabel
			 */
			public JLabel getCourseLabel() {
				return courseLabel;
			}

			/**
			 * @return the applyLabel
			 */
			public JLabel getApplyLabel() {
				return applyLabel;
			}

			/**
			 * @return the layout2
			 */
			public SpringLayout getLayout2() {
				return layout2;
			}
			
			
}