/**
* @author Luis Carabe 
* @author Alejo Polania 
*/
package es.uam.eps.padsof.p4.inter.Educagram;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;


import es.uam.eps.padsof.p3.course.Course;

import es.uam.eps.padsof.p4.controllers.HomePanelTeacherController;

public class HomePanelTeacher extends JPanel {
	//Superior Panel
		private JPanel supPanel = new JPanel();
		private ImageIcon image = new ImageIcon("logov3.png");
		private JLabel imgLabel = new JLabel(image);
		private JLabel homeLabel = new JLabel("Home page");
		private JLabel courses = new JLabel("All courses:");
		private JComboBox<String> listCourses;
		private JButton go = new JButton("Go");
		private JButton searchCour = new JButton("Search Course");
		private JButton createCourse = new JButton ("Create Course");
		private JLabel professor = new JLabel("Professor");
		private JButton signOut = new JButton("Sign out");
		private SpringLayout layout = new SpringLayout();
		private String[] strCourses = {};
		
		private JLabel noCourse = new JLabel("Welcome back professor! Please, choose a Course.");
		private SpringLayout layout2 = new SpringLayout();
		
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		/**
		 * Constructor of HomePanelTeacher
		 * @param allCour
		 */
		public HomePanelTeacher(ArrayList<String> allCour){
			this.setVisible(true);
			this.setSize(screenSize.width, screenSize.height);
			this.setLayout(layout2);
			this.setBackground(Color.decode("#98FB98"));
			
			this.supPanel.setVisible(true);
			this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
			this.supPanel.setBackground(Color.decode("#228B22"));
			this.supPanel.setLayout(layout);
			
			int i = 0;
			
			for(String c: allCour){
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
			this.professor.setFont(this.professor.getFont().deriveFont(15f));
			this.signOut.setForeground(Color.RED);
		
			this.go.setPreferredSize(new Dimension(53,25));
			
			this.supPanel.add(imgLabel);
			this.supPanel.add(homeLabel);
			this.supPanel.add(courses);
			this.supPanel.add(listCourses);
			this.supPanel.add(searchCour);
			this.supPanel.add(professor);
			this.supPanel.add(signOut);
			this.supPanel.add(createCourse);
			this.supPanel.add(go);
			
			
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
			layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
			
			layout.putConstraint(SpringLayout.NORTH, this.courses, 45, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.courses, 0, SpringLayout.WEST, this.listCourses);
			
			layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.listCourses, 60, SpringLayout.HORIZONTAL_CENTER, this.supPanel);

			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.go, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
			layout.putConstraint(SpringLayout.WEST, this.go, 10, SpringLayout.EAST, this.listCourses);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.searchCour, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
			layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.go);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.createCourse, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
			layout.putConstraint(SpringLayout.WEST, this.createCourse, 10, SpringLayout.EAST, this.searchCour);
			
			layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.professor, 0, SpringLayout.HORIZONTAL_CENTER, this.signOut);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.signOut, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
			layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
			
			this.noCourse.setFont(this.noCourse.getFont().deriveFont(25f));
			
			this.add(this.supPanel);
			this.add(this.noCourse);
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.noCourse, 0, SpringLayout.VERTICAL_CENTER, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.noCourse, 0, SpringLayout.HORIZONTAL_CENTER, this);

			

		}
		/**
		 * Method to add a course
		 * @param name
		 */
		
		public void addCourse(String name){
			strCourses = Arrays.copyOf(strCourses, strCourses.length+1);
			strCourses[strCourses.length - 1] = name;
			this.listCourses.addItem(name);
			this.listCourses.setSelectedItem(null);
		}
		/**
		 * Method to set the controllers
		 * @param c
		 */
		
		public void setController(ActionListener c) {
			this.signOut.addActionListener(c);
			this.createCourse.addActionListener(c);
			this.go.addActionListener(c);
			this.searchCour.addActionListener(c);
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
		 * @return the createCourse
		 */
		public JButton getCreateCourse() {
			return createCourse;
		}

		/**
		 * @return the professor
		 */
		public JLabel getProfessor() {
			return professor;
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
		 * @return the strCourses
		 */
		public String[] getStrCourses() {
			return strCourses;
		}

		/**
		 * @return the noCourse
		 */
		public JLabel getNoCourse() {
			return noCourse;
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
		public JButton getGo(){
			return go;
		}

}