package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class CourseTeacherPanel extends JPanel{
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
	private String[] strCourses = {};
	
	private JLabel courseLabel;
	private JLabel applyLabel = new JLabel("This is the content of the course");
	private JButton studentsButton = new JButton("Students");
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public CourseTeacherPanel(String name, ArrayList<String> allCour, String courName){
		String[] strCourses = {};
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
		this.courseLabel = new JLabel(courName);
		this.listCourses = new JComboBox<String>(strCourses);
		this.listCourses.setPrototypeDisplayValue("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
		this.listCourses.setSelectedItem(null);
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
		layout.putConstraint(SpringLayout.EAST, this.listCourses, 0, SpringLayout.HORIZONTAL_CENTER, this.supPanel);
		
		layout.putConstraint(SpringLayout.NORTH, this.searchCour, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.listCourses);
		
		layout.putConstraint(SpringLayout.NORTH, this.globalStats, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.globalStats, 10, SpringLayout.EAST, this.searchCour);
		
		layout.putConstraint(SpringLayout.NORTH, this.createCourse, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.createCourse, 10, SpringLayout.EAST, this.globalStats);
		
		layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.professor, 0, SpringLayout.HORIZONTAL_CENTER, this.signOut);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
		
		Map attributes = this.courseLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(attributes));
		this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(30f));
		

		this.add(this.supPanel);
		this.add(this.courseLabel);
		this.add(this.applyLabel);
		this.add(this.studentsButton);
		
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.courseLabel, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.courseLabel, 10, SpringLayout.SOUTH, this.supPanel);
		
		layout2.putConstraint(SpringLayout.WEST, this.applyLabel, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.applyLabel, 10, SpringLayout.SOUTH, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.WEST, this.studentsButton, 100, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.studentsButton, 100, SpringLayout.SOUTH, this.courseLabel);
		
	}
}
