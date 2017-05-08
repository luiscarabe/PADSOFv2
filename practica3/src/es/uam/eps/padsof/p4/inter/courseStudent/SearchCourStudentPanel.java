/**
* @author Luis Carabe 
* @author Alejo Polania 
*/

package es.uam.eps.padsof.p4.inter.courseStudent;

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
		private JButton go = new JButton("Go");
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
		private JButton enrolButton = new JButton("Go");
		
		private DefaultListModel<String> expelModel = new DefaultListModel<String>();
		JList<String> expelList;
		private JLabel expelLabel = new JLabel("Expelled");
		private JScrollPane expelPane;
		
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
		
		/**
		 * Constructor of SearchCourStudentPanel
		 * @param name
		 * @param enrCour
		 * @param applyCour
		 * @param appliedCour
		 * @param expelCour
		 */
		public SearchCourStudentPanel(String name, ArrayList<String> enrCour, ArrayList<String> applyCour, ArrayList<String> appliedCour, ArrayList<String> expelCour){
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

			Map attributes = this.enrolLabel.getFont().getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			
			
			for(String s: enrCour){
				this.enrolModel.addElement(s);
			}
			this.enrolList = new JList<String>(enrolModel);
			this.enrolList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.enrolPane = new JScrollPane(this.enrolList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.enrolPane.setPreferredSize(new Dimension(200,500));
			this.enrolList.setBackground(Color.decode("#20B2AA"));
			this.enrolLabel.setFont(this.enrolLabel.getFont().deriveFont(attributes));
			this.enrolLabel.setFont(this.enrolLabel.getFont().deriveFont(15f));
			
			for(String s: expelCour){
				this.expelModel.addElement(s);
			}
			this.expelList = new JList<String>(expelModel);
			this.expelPane = new JScrollPane(this.expelList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.expelPane.setPreferredSize(new Dimension(200,500));
			this.expelList.setBackground(Color.decode("#20B2AA"));
			this.expelLabel.setFont(this.expelLabel.getFont().deriveFont(attributes));
			this.expelLabel.setFont(this.expelLabel.getFont().deriveFont(15f));
			
			
			for(String s: applyCour){
				this.applyModel.addElement(s);
			}
			this.applyList = new JList<String>(applyModel);
			this.applyPane = new JScrollPane(this.applyList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.applyPane.setPreferredSize(new Dimension(200,500));
			this.applyList.setBackground(Color.decode("#20B2AA"));
			this.applyLabel.setFont(this.applyLabel.getFont().deriveFont(attributes));
			this.applyLabel.setFont(this.applyLabel.getFont().deriveFont(15f));
			
			for(String s: appliedCour){
				this.appliedModel.addElement(s);
			}
			this.appliedList = new JList<String>(appliedModel);
			this.appliedPane = new JScrollPane(this.appliedList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.appliedPane.setPreferredSize(new Dimension(200,500));
			this.appliedList.setBackground(Color.decode("#20B2AA"));
			this.appliedLabel.setFont(this.applyLabel.getFont().deriveFont(attributes));
			this.appliedLabel.setFont(this.appliedLabel.getFont().deriveFont(15f));
			
			this.searchLabel.setLabelFor(this.searchField);
			
			this.add(this.supPanel);
			this.add(this.enrolPane);
			this.add(this.expelLabel);
			this.add(this.expelPane);
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
			this.add(this.enrolButton);
			
		
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.EAST, this.enrolPane, -60, SpringLayout.WEST, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.enrolPane, 150, SpringLayout.NORTH, this);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.enrolLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.enrolPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.enrolLabel, -10, SpringLayout.NORTH, this.enrolPane);	
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyPane, -250, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.NORTH, this.applyPane, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.applyPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.applyLabel, -10, SpringLayout.NORTH, this.applyPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyButton, 0, SpringLayout.HORIZONTAL_CENTER, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.applyButton, 10, SpringLayout.SOUTH, this.applyPane);
		
			layout2.putConstraint(SpringLayout.WEST, this.appliedPane, 60, SpringLayout.EAST, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.appliedPane, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.appliedLabel, -10, SpringLayout.NORTH, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedButton, 0, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
			layout2.putConstraint(SpringLayout.NORTH, this.appliedButton, 10, SpringLayout.SOUTH, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.WEST, this.expelPane, 60, SpringLayout.EAST, this.appliedPane);
			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.expelPane, 0, SpringLayout.VERTICAL_CENTER, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.expelLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.expelPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.expelLabel, -10, SpringLayout.NORTH, this.expelPane);	
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.searchField);
			layout2.putConstraint(SpringLayout.SOUTH, this.searchLabel, -10, SpringLayout.NORTH, this.searchField);
			
			layout2.putConstraint(SpringLayout.WEST, this.searchField, 30, SpringLayout.EAST, this.expelPane);
			layout2.putConstraint(SpringLayout.NORTH, this.searchField, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchButton, 0, SpringLayout.HORIZONTAL_CENTER, this.searchField);
			layout2.putConstraint(SpringLayout.NORTH, this.searchButton, 10, SpringLayout.SOUTH, this.searchField);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.enrolButton, 0, SpringLayout.HORIZONTAL_CENTER, this.enrolPane);
			layout2.putConstraint(SpringLayout.NORTH, this.enrolButton, 10, SpringLayout.SOUTH, this.enrolPane);
		}
		
		/**
		 * @return
		 */
		
		public String getSearched(){
			return this.searchField.getText();
		}
		/**
		 * 
		 * @param name
		 */
		
		public void addEnrCourse(String name){
			this.enrolModel.addElement(name);
		}
		/**
		 * 
		 * @param name
		 */
		
		public void delEnrCourse(String name){
			this.enrolModel.removeElement(name);
		}
		/**
		 * 
		 * @param name
		 */
		
		public void addApplyCourse(String name){
			this.applyModel.addElement(name);
		}
		/**
		 * 
		 * @param name
		 */
		
		public void delApplyCourse(String name){
			this.applyModel.removeElement(name);
		}
		/**
		 * 
		 * @param name
		 */
		
		public void addAppliedCourse(String name){
			this.appliedModel.addElement(name);
		}
		/**
		 * 
		 * @param name
		 */
		
		public void delAppliedCourse(String name){
			this.appliedModel.removeElement(name);
		}
		/**
		 * Method to set controllers
		 * @param c
		 */
		
		public void setController(ActionListener c) {
			this.signOut.addActionListener(c);
			this.searchCour.addActionListener(c);
			this.searchButton.addActionListener(c);
			this.applyButton.addActionListener(c);
			this.appliedButton.addActionListener(c);
			this.go.addActionListener(c);
			this.enrolButton.addActionListener(c);
		}
		/**
		 * 
		 * @return
		 */
		
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
		
		/**
		 * @return the go
		 */
		public JButton getGo() {
			return go;
		}
		
		/**
		 * @return the enrolButton
		 */
		public JButton getEnrolButton() {
			return enrolButton;
		}
		
		
}