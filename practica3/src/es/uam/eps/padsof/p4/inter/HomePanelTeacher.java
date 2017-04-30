package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.user.*;

public class HomePanelTeacher extends JPanel {
	//Superior Panel
		private JPanel supPanel = new JPanel();
		private ImageIcon image = new ImageIcon("logov3.png");
		private JLabel imgLabel = new JLabel(image);
		private JLabel homeLabel = new JLabel("Home page");
		private JLabel courses = new JLabel("All courses:");
		private JComboBox<String> listCourses;
		private JButton searchCour = new JButton("Search Course");
		private JButton globalStats = new JButton ("Global Statistics");
		private JButton createCourse = new JButton ("Create Course");
		private JLabel professor = new JLabel("Professor");
		private JButton signOut = new JButton("Sign out");
		private SpringLayout layout = new SpringLayout();
		
		private JLabel noCourse = new JLabel("Welcome back professor! Please, choose a Course.");
		private SpringLayout layout2 = new SpringLayout();
		
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		public HomePanelTeacher(){
			String[] strCourses = {};
			this.setVisible(true);
			this.setSize(screenSize.width, screenSize.height);
			this.setLayout(layout2);
			this.setBackground(Color.decode("#98FB98"));
			
			this.supPanel.setVisible(true);
			this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
			this.supPanel.setBackground(Color.decode("#228B22"));
			this.supPanel.setLayout(layout);
			
			Professor current = Educagram.getInstance().getProfessor();
			int i = 0;
			for(Course c: current.getAllCourses()){
				strCourses[i] = c.getTitle();
				i++;
			}
			
			this.listCourses = new JComboBox<String>(strCourses);
			this.imgLabel.setVisible(true);
			this.imgLabel.setBounds(0,0,200,200);
			
			this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(30f));
			this.professor.setFont(this.professor.getFont().deriveFont(15f));
			this.signOut.setForeground(Color.RED);
			
			this.supPanel.add(imgLabel);
			this.supPanel.add(homeLabel);
			this.supPanel.add(courses);
			this.supPanel.add(listCourses);
			this.supPanel.add(searchCour);
			this.supPanel.add(globalStats);
			this.supPanel.add(professor);
			this.supPanel.add(signOut);
			this.supPanel.add(createCourse);
			
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
			layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
			
			layout.putConstraint(SpringLayout.NORTH, this.courses, 45, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.courses, 0, SpringLayout.WEST, this.listCourses);
			
			layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.listCourses, -100, SpringLayout.HORIZONTAL_CENTER, this.supPanel);
			
			layout.putConstraint(SpringLayout.NORTH, this.searchCour, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.listCourses);
			
			layout.putConstraint(SpringLayout.NORTH, this.globalStats, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.WEST, this.globalStats, 10, SpringLayout.EAST, this.searchCour);
			
			layout.putConstraint(SpringLayout.NORTH, this.createCourse, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.WEST, this.createCourse, 10, SpringLayout.EAST, this.globalStats);
			
			layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.professor, 0, SpringLayout.EAST, this.signOut);
			
			layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
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

}
