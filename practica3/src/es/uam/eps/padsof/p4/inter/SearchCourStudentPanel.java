package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;

public class SearchCourStudentPanel extends JPanel{

		//Superior Panel
		private JPanel supPanel = new JPanel();
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
		
		
		private DefaultListModel<String> applyModel = new DefaultListModel<String>(); 
		JList<String> applyList;
		private JLabel applyLabel = new JLabel("Apply for");
		private JButton applyButton = new JButton("Apply");
		private JScrollPane applyPane;
		
		private DefaultListModel<String> enrolModel = new DefaultListModel<String>();
		JList<String> enrolList;
		private JLabel enrolLabel = new JLabel("Your courses");
		private JScrollPane enrolPane;
		
		private DefaultListModel<String> appliedModel = new DefaultListModel<String>();
		JList<String> appliedList;
		private JLabel appliedLabel = new JLabel("Your applications");
		private JButton appliedButton = new JButton("Cancel");
		private JScrollPane appliedPane;
		
		private JLabel searchLabel = new JLabel("Search Course");
		private JTextField searchField = new JTextField(15);
		private JButton searchButton = new JButton("Search");
		
		private SpringLayout layout2 = new SpringLayout();
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		public SearchCourStudentPanel(String name, ArrayList<String> enrCour, ArrayList<String> applyCour, ArrayList<String> appliedCour){
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
			
			
			this.supPanel.add(imgLabel);
			this.supPanel.add(homeLabel);
			this.supPanel.add(courses);
			this.supPanel.add(listCourses);
			this.supPanel.add(searchCour);
			this.supPanel.add(marks);
			this.supPanel.add(student);
			this.supPanel.add(signOut);
			
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
			layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
			
			layout.putConstraint(SpringLayout.NORTH, this.courses, 45, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.courses, 0, SpringLayout.WEST, this.listCourses);
			
			layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.listCourses, 100, SpringLayout.HORIZONTAL_CENTER, this.supPanel);
			
			layout.putConstraint(SpringLayout.NORTH, this.searchCour, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.listCourses);
			
			layout.putConstraint(SpringLayout.NORTH, this.marks, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.WEST, this.marks, 10, SpringLayout.EAST, this.searchCour);
			
			layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);
			
			layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
			
			Font font = this.enrolLabel.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			
			
			for(String s: enrCour){
				this.enrolModel.addElement(s);
			}
			this.enrolList = new JList<String>(enrolModel);
			this.enrolPane = new JScrollPane(this.enrolList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.enrolPane.setPreferredSize(new Dimension(250,500));
			this.enrolList.setBackground(Color.decode("#6495ED"));
			this.enrolLabel.setFont(font.deriveFont(attributes));
			this.enrolLabel.setFont(this.enrolLabel.getFont().deriveFont(15f));
			
			
			for(String s: applyCour){
				this.applyModel.addElement(s);
			}
			this.applyList = new JList<String>(applyModel);
			this.applyPane = new JScrollPane(this.applyList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.applyPane.setPreferredSize(new Dimension(250,500));
			this.applyList.setBackground(Color.decode("#6495ED"));
			this.applyLabel.setFont(font.deriveFont(attributes));
			this.applyLabel.setFont(this.applyLabel.getFont().deriveFont(15f));
			
			for(String s: appliedCour){
				this.appliedModel.addElement(s);
			}
			this.appliedList = new JList<String>(appliedModel);
			this.appliedPane = new JScrollPane(this.appliedList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.appliedPane.setPreferredSize(new Dimension(250,500));
			this.appliedList.setBackground(Color.decode("#6495ED"));
			this.appliedLabel.setFont(font.deriveFont(attributes));
			this.appliedLabel.setFont(this.appliedLabel.getFont().deriveFont(15f));
			
			this.searchLabel.setLabelFor(this.searchField);
			
			this.add(this.supPanel);
			this.add(this.enrolPane);
			this.add(this.applyPane);
			this.add(this.appliedPane);
			this.add(this.appliedLabel);
			this.add(this.applyLabel);
			this.add(this.enrolLabel);
			this.add(this.appliedButton);
			this.add(this.applyButton);
			this.add(this.searchButton);
			this.add(this.searchLabel);
			this.add(this.searchField);
			
		
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.EAST, this.enrolPane, -100, SpringLayout.WEST, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.enrolPane, 150, SpringLayout.NORTH, this);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.enrolLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.enrolPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.enrolLabel, -10, SpringLayout.NORTH, this.enrolPane);	
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyPane, -100, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.NORTH, this.applyPane, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.applyPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.applyLabel, -10, SpringLayout.NORTH, this.applyPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyButton, 0, SpringLayout.HORIZONTAL_CENTER, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.applyButton, 10, SpringLayout.SOUTH, this.applyPane);
		
			layout2.putConstraint(SpringLayout.WEST, this.appliedPane, 100, SpringLayout.EAST, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.appliedPane, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.appliedLabel, -10, SpringLayout.NORTH, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedButton, 0, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
			layout2.putConstraint(SpringLayout.NORTH, this.appliedButton, 10, SpringLayout.SOUTH, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.searchField);
			layout2.putConstraint(SpringLayout.SOUTH, this.searchLabel, -10, SpringLayout.NORTH, this.searchField);
			
			layout2.putConstraint(SpringLayout.EAST, this.searchField, -50, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.searchField, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchButton, 0, SpringLayout.HORIZONTAL_CENTER, this.searchField);
			layout2.putConstraint(SpringLayout.NORTH, this.searchButton, 10, SpringLayout.SOUTH, this.searchField);
		}
		
		public void setController(ActionListener c) {
			this.signOut.addActionListener(c);
			this.searchCour.addActionListener(c);
			this.searchButton.addActionListener(c);
		}
		
		public String getScourse(){
			return this.searchField.getText();
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
		 * @return the applyModel
		 */
		public DefaultListModel<String> getApplyModel() {
			return applyModel;
		}

		/**
		 * @return the applyList
		 */
		public JList<String> getApplyList() {
			return applyList;
		}

		/**
		 * @return the applyLabel
		 */
		public JLabel getApplyLabel() {
			return applyLabel;
		}

		/**
		 * @return the applyButton
		 */
		public JButton getApplyButton() {
			return applyButton;
		}

		/**
		 * @return the applyPane
		 */
		public JScrollPane getApplyPane() {
			return applyPane;
		}

		/**
		 * @return the enrolModel
		 */
		public DefaultListModel<String> getEnrolModel() {
			return enrolModel;
		}

		/**
		 * @return the enrolList
		 */
		public JList<String> getEnrolList() {
			return enrolList;
		}

		/**
		 * @return the enrolLabel
		 */
		public JLabel getEnrolLabel() {
			return enrolLabel;
		}

		/**
		 * @return the enrolPane
		 */
		public JScrollPane getEnrolPane() {
			return enrolPane;
		}

		/**
		 * @return the appliedModel
		 */
		public DefaultListModel<String> getAppliedModel() {
			return appliedModel;
		}

		/**
		 * @return the appliedList
		 */
		public JList<String> getAppliedList() {
			return appliedList;
		}

		/**
		 * @return the appliedLabel
		 */
		public JLabel getAppliedLabel() {
			return appliedLabel;
		}

		/**
		 * @return the appliedButton
		 */
		public JButton getAppliedButton() {
			return appliedButton;
		}

		/**
		 * @return the appliedPane
		 */
		public JScrollPane getAppliedPane() {
			return appliedPane;
		}

		/**
		 * @return the searchLabel
		 */
		public JLabel getSearchLabel() {
			return searchLabel;
		}

		/**
		 * @return the searchField
		 */
		public JTextField getSearchField() {
			return searchField;
		}

		/**
		 * @return the searchButton
		 */
		public JButton getSearchButton() {
			return searchButton;
		}

		/**
		 * @return the layout2
		 */
		public SpringLayout getLayout2() {
			return layout2;
		}
		
		
}