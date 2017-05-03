package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

public class CourseTeacherPanel extends JPanel{
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel = new JLabel("Home page");
	private JLabel courses = new JLabel("All courses:");
	private JComboBox<String> listCourses;
	private JButton go = new JButton("Go");
	private JButton searchCour = new JButton("Search Course");
	private JButton globalStats = new JButton ("Global Statistics");
	private JButton createCourse = new JButton ("Create Course");
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	private String[] strCourses = {};
	
	private JLabel courseLabel;
	private JTextArea courseDesc;
	private JScrollPane coursePane;
	private JButton studentsButton = new JButton("Students");
	
	private JButton createUnit = new JButton("Create unit");
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public CourseTeacherPanel( String courName, String courDesc, ArrayList<String> allCour){
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
		layout.putConstraint(SpringLayout.EAST, this.listCourses, 0, SpringLayout.HORIZONTAL_CENTER, this.supPanel);

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
		
		Map attributes = this.courseLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(attributes));
		this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(30f));
		this.courseDesc = new JTextArea(courDesc);
		this.courseDesc.setEditable(false);
		this.courseDesc.setBackground(this.getBackground());
		this.courseDesc.setLineWrap(true);
		this.courseDesc.setWrapStyleWord(true);
		this.coursePane = new JScrollPane(this.courseDesc, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.coursePane.setBorder(null);
		this.coursePane.setPreferredSize(new Dimension(500, 60));
		
		this.add(this.supPanel);
		this.add(this.courseLabel);
		this.add(this.coursePane);
		this.add(this.studentsButton);
		this.add(this.createUnit);
		
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.createUnit, 50, SpringLayout.EAST, this.courseLabel);
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.createUnit, 0, SpringLayout.VERTICAL_CENTER, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.WEST, this.courseLabel, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.courseLabel, 10, SpringLayout.SOUTH, this.supPanel);
		
		layout2.putConstraint(SpringLayout.WEST, this.coursePane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.coursePane, 10, SpringLayout.SOUTH, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.studentsButton, 0, SpringLayout.VERTICAL_CENTER, this.createUnit);
		layout2.putConstraint(SpringLayout.WEST, this.studentsButton, 10, SpringLayout.EAST, this.createUnit);
		
	}
	
	public void setController(ActionListener c){
		this.signOut.addActionListener(c);
		this.studentsButton.addActionListener(c);
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
	 * @return the globalStats
	 */
	public JButton getGlobalStats() {
		return globalStats;
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
	 * @return the courseLabel
	 */
	public JLabel getCourseLabel() {
		return courseLabel;
	}


	/**
	 * @return the studentsButton
	 */
	public JButton getStudentsButton() {
		return studentsButton;
	}

	/**
	 * @return the layout2
	 */
	public SpringLayout getLayout2() {
		return layout2;
	}
	
	
}