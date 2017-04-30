package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.user.*;
import es.uam.eps.padsof.p4.controllers.HomePanelStudentController;
import es.uam.eps.padsof.p4.controllers.HomePanelTeacherController;

public class HomePanelStudent extends JPanel{

	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel = new JLabel("Home page");
	private JLabel courses = new JLabel("My courses:");
	private JComboBox<String> listCourses;
	private JButton searchCour = new JButton("Search Course");
	private JButton marks = new JButton ("Marks");
	private JLabel student = new JLabel("Student: "+ Educagram.getInstance().getCurrentUser().getName());
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	
	private JLabel noCourse = new JLabel("Welcome back " + Educagram.getInstance().getCurrentUser().getName() +"!\n Please, choose a Course.");
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public HomePanelStudent(){
		String[] strCourses = {};
		this.setVisible(true);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));
		
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
		layout.putConstraint(SpringLayout.EAST, this.listCourses, 0, SpringLayout.HORIZONTAL_CENTER, this.supPanel);
		
		layout.putConstraint(SpringLayout.NORTH, this.searchCour, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.listCourses);
		
		layout.putConstraint(SpringLayout.NORTH, this.marks, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.marks, 10, SpringLayout.EAST, this.searchCour);
		
		layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
		
		this.noCourse.setFont(this.noCourse.getFont().deriveFont(25f));
		

		this.add(this.supPanel);
		this.add(this.noCourse);
		
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.noCourse, 0, SpringLayout.VERTICAL_CENTER, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.noCourse, 0, SpringLayout.HORIZONTAL_CENTER, this);

		// a lo mejor en el main!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// controller
		HomePanelStudentController controller = new HomePanelStudentController(this, Educagram.getInstance());
		// Associate controller to view
		this.setController(controller);

	}
	
	public void setController(ActionListener c) {
		this.signOut.addActionListener(c);
	}
}
