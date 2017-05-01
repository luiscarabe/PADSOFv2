package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

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
		private JLabel applyLabel = new JLabel("Apply for:");
		private JButton applyButton = new JButton("Apply");
		private JScrollPane applyPane;
		
		private DefaultListModel<String> enrolModel = new DefaultListModel<String>();
		JList<String> enrolList;
		private JLabel enrolLabel = new JLabel("Your courses:");
		private JScrollPane enrolPane = new JScrollPane(this.enrolList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		private DefaultListModel<String> appliedModel = new DefaultListModel<String>();
		JList<String> appliedList;
		private JLabel appliedLabel = new JLabel("Your applications:");
		private JButton appliedButton = new JButton("Cancel");
		private JScrollPane appliedPane = new JScrollPane(this.appliedList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		private JLabel searchLabel = new JLabel("Search Course:");
		private JTextField searchField = new JTextField(20);
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
			
			for(String s: enrCour){
				this.enrolModel.addElement(s);
			}
			this.enrolList = new JList<String>(enrolModel);
			this.enrolPane = new JScrollPane(this.enrolList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.enrolPane.setPreferredSize(new Dimension(100,100));
			this.enrolList.setBackground(Color.decode("#6495ED"));
			
			
			for(String s: applyCour){
				this.applyModel.addElement(s);
			}
			this.applyList = new JList<String>(applyModel);
			this.applyPane = new JScrollPane(this.applyList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.applyPane.setPreferredSize(new Dimension(100,100));
			this.applyList.setBackground(Color.decode("#6495ED"));
			
			for(String s: appliedCour){
				this.appliedModel.addElement(s);
			}
			this.appliedList = new JList<String>(appliedModel);
			this.appliedPane = new JScrollPane(this.appliedList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			this.appliedPane.setPreferredSize(new Dimension(100,100));
			this.appliedList.setBackground(Color.decode("#6495ED"));
			
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
			
			layout2.putConstraint(SpringLayout.WEST, this.enrolPane, 100, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.enrolPane, 150, SpringLayout.NORTH, this);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.enrolLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.enrolPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.enrolLabel, -10, SpringLayout.NORTH, this.enrolPane);	
			
			layout2.putConstraint(SpringLayout.WEST, this.applyPane, 300, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.applyPane, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.applyPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.applyLabel, -10, SpringLayout.NORTH, this.applyPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.applyButton, 0, SpringLayout.HORIZONTAL_CENTER, this.applyPane);
			layout2.putConstraint(SpringLayout.NORTH, this.applyButton, 10, SpringLayout.SOUTH, this.applyPane);
		
			layout2.putConstraint(SpringLayout.WEST, this.appliedPane, 500, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.appliedPane, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
			layout2.putConstraint(SpringLayout.SOUTH, this.appliedLabel, -10, SpringLayout.NORTH, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedButton, 0, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
			layout2.putConstraint(SpringLayout.NORTH, this.appliedButton, 10, SpringLayout.SOUTH, this.appliedPane);
			
			layout2.putConstraint(SpringLayout.WEST, this.searchLabel, 800, SpringLayout.WEST, this);
			layout2.putConstraint(SpringLayout.NORTH, this.searchLabel, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.WEST, this.searchField, 10, SpringLayout.EAST, this.searchLabel);
			layout2.putConstraint(SpringLayout.NORTH, this.searchField, 0, SpringLayout.NORTH, this.enrolPane);
			
			layout2.putConstraint(SpringLayout.WEST, this.searchButton, 0, SpringLayout.WEST, this.searchField);
			layout2.putConstraint(SpringLayout.NORTH, this.searchButton, 10, SpringLayout.SOUTH, this.searchField);
		}
}
