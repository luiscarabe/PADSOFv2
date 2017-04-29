package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import javax.swing.*;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.user.*;

public class HomePanel extends JPanel{
	
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logo.jpg");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel = new JLabel("Home page");
	private JLabel courses = new JLabel("Courses:");
	private JComboBox<String> listCourses;
	private JButton searchCour = new JButton("Search Course");
	private JButton marks = new JButton ("Marks");
	private JLabel student = new JLabel("Student: "+ Educagram.getInstance().getCurrentUser().getName());
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public HomePanel(){
		String[] strCourses = {};
		this.setVisible(true);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(new FlowLayout());
		
		this.supPanel.setVisible(true);
		this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
		this.supPanel.setBackground(Color.decode("#228B22"));
		this.supPanel.setLayout(layout);
		
		Student current = ((Student)Educagram.getInstance().getCurrentUser());
		int i = 0;
		for(Course c: current.getEnrolledCourses()){
			strCourses[i] = c.getTitle();
			i++;
		}
		this.listCourses = new JComboBox<String>(strCourses);
		this.imgLabel.setVisible(true);
		this.imgLabel.setBounds(0,0,20,20);
		this.imgLabel.setPreferredSize(new Dimension(100,100));
		
		this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(30f));
		
		this.supPanel.add(imgLabel,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(homeLabel,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(courses,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(listCourses,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(searchCour,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(marks,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(student,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(signOut,SpringLayout.VERTICAL_CENTER);
		
		layout.putConstraint(SpringLayout.NORTH, this.homeLabel, 20, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.homeLabel, 150, SpringLayout.WEST, this.supPanel);
		
		layout.putConstraint(SpringLayout.NORTH, this.courses, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.courses, 100, SpringLayout.EAST, this.homeLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.listCourses, 5, SpringLayout.EAST, this.courses);
		
		layout.putConstraint(SpringLayout.NORTH, this.searchCour, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.listCourses);
		
		layout.putConstraint(SpringLayout.NORTH, this.marks, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.marks, 10, SpringLayout.EAST, this.searchCour);
		
		layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.student, 200, SpringLayout.EAST, this.courses);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.signOut, 200, SpringLayout.EAST, this.marks);
		
		layout.putConstraint(SpringLayout.NORTH, this.imgLabel, 0, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.imgLabel, 0, SpringLayout.WEST, this.supPanel);
		
		this.add(this.supPanel);
		

	}
	
}
