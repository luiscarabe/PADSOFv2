/**
 * 
 */
package es.uam.eps.padsof.p4.inter.courseTeacher;

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

/**
 * @author e341020
 *
 */
public class SearchCourTeacherPanel extends JPanel{
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
	
	
	private DefaultListModel<String> courModel = new DefaultListModel<String>();
	JList<String> courList;
	private JLabel courLabel = new JLabel("All courses");
	private JButton courButton = new JButton("Go");
	private JScrollPane courPane;
	
	private JLabel searchLabel = new JLabel("Search Course");
	private JTextField searchField = new JTextField(15);
	private JButton searchButton = new JButton("Search");
	
	private SpringLayout layout2 = new SpringLayout();
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public SearchCourTeacherPanel(ArrayList<String> allCour){
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

		Map attributes = this.courLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		
		
		for(String s: allCour){
			this.courModel.addElement(s);
		}
		this.courList = new JList<String>(courModel);
		this.courList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.courPane = new JScrollPane(this.courList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.courPane.setPreferredSize(new Dimension(200,500));
		this.courList.setBackground(Color.decode("#20B2AA"));
		this.courLabel.setFont(this.courLabel.getFont().deriveFont(attributes));
		this.courLabel.setFont(this.courLabel.getFont().deriveFont(15f));
		
		
		
		this.searchLabel.setLabelFor(this.searchField);
		
		this.add(this.supPanel);
		this.add(this.courPane);
		this.add(this.courLabel);
		this.add(this.courButton);
		this.add(this.searchButton);
		this.add(this.searchLabel);
		this.add(this.searchField);
		
	
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.courLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.courPane);
		layout2.putConstraint(SpringLayout.SOUTH, this.courLabel, -10, SpringLayout.NORTH, this.courPane);
		
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.courButton, 0, SpringLayout.HORIZONTAL_CENTER, this.courPane);
		layout2.putConstraint(SpringLayout.NORTH, this.courButton, 10, SpringLayout.SOUTH, this.courPane);
		
		layout2.putConstraint(SpringLayout.WEST, this.courPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.courPane, 150, SpringLayout.NORTH, this);
	
		
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.searchField);
		layout2.putConstraint(SpringLayout.SOUTH, this.searchLabel, -10, SpringLayout.NORTH, this.searchField);
		
		layout2.putConstraint(SpringLayout.WEST, this.searchField, 30, SpringLayout.EAST, this.courPane);
		layout2.putConstraint(SpringLayout.NORTH, this.searchField, 0, SpringLayout.NORTH, this.courPane);
		
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchButton, 0, SpringLayout.HORIZONTAL_CENTER, this.searchField);
		layout2.putConstraint(SpringLayout.NORTH, this.searchButton, 10, SpringLayout.SOUTH, this.searchField);
	}
	
	public void setController(ActionListener c) {
		this.signOut.addActionListener(c);
		this.searchCour.addActionListener(c);
		this.searchButton.addActionListener(c);
		this.go.addActionListener(c);
		this.courButton.addActionListener(c);
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
	 * @return the go
	 */
	public JButton getGo() {
		return go;
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
	 * @return the courModel
	 */
	public DefaultListModel<String> getCourModel() {
		return courModel;
	}

	/**
	 * @return the courList
	 */
	public JList<String> getCourList() {
		return courList;
	}

	/**
	 * @return the courLabel
	 */
	public JLabel getCourLabel() {
		return courLabel;
	}

	/**
	 * @return the courButton
	 */
	public JButton getCourButton() {
		return courButton;
	}

	/**
	 * @return the courPane
	 */
	public JScrollPane getCourPane() {
		return courPane;
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
