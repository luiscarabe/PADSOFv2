package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.swing.*;

public class StudentsOfCourPanel extends JPanel {
	// Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel;
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();

	private DefaultListModel<String> expelModel = new DefaultListModel<String>();
	JList<String> expelList;
	private JLabel expelLabel = new JLabel("Expelled students");
	private JButton expelButton = new JButton("Readmit");
	private JScrollPane expelPane;

	private DefaultListModel<String> enrolModel = new DefaultListModel<String>();
	JList<String> enrolList;
	private JLabel enrolLabel = new JLabel("Enrolled students");
	private JScrollPane enrolPane;
	private JButton enrolButton = new JButton("Expel");

	private DefaultListModel<String> appliedModel = new DefaultListModel<String>();
	JList<String> appliedList;
	private JLabel appliedLabel = new JLabel("Applications");
	private JButton appliedAcceptButton = new JButton("Acept");
	private JButton appliedRejectButton = new JButton("Reject");
	private JScrollPane appliedPane;

	private JLabel searchLabel = new JLabel("Search Course");
	private JTextField searchField = new JTextField(15);
	private JButton searchButton = new JButton("Search");
	
	private JButton returning = new JButton("Return to course");

	private SpringLayout layout2 = new SpringLayout();
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public StudentsOfCourPanel(String courName, ArrayList<String> enrStud, ArrayList<String> expStud, ArrayList<String> appliedStud) {
		this.setVisible(true);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));

		this.supPanel.setVisible(true);
		this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
		this.supPanel.setBackground(Color.decode("#228B22"));
		this.supPanel.setLayout(layout);
		
		this.imgLabel.setVisible(true);
		this.imgLabel.setBounds(0,0,200,200);
		
		this.homeLabel = new JLabel("Students - " +courName);
		this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(30f));
		this.professor.setFont(this.professor.getFont().deriveFont(15f));
		this.signOut.setForeground(Color.RED);
	
		
		this.supPanel.add(imgLabel);
		this.supPanel.add(homeLabel);
		this.supPanel.add(professor);
		this.supPanel.add(signOut);
		
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
		layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.professor, 0, SpringLayout.HORIZONTAL_CENTER, this.signOut);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
		

		Font font = this.enrolLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

		for (String s : enrStud) {
			this.enrolModel.addElement(s);
		}
		this.enrolList = new JList<String>(enrolModel);
		this.enrolPane = new JScrollPane(this.enrolList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.enrolPane.setPreferredSize(new Dimension(250, 500));
		this.enrolList.setBackground(Color.decode("#B5B5B5"));
		this.enrolLabel.setFont(font.deriveFont(attributes));
		this.enrolLabel.setFont(this.enrolLabel.getFont().deriveFont(15f));

		for (String s : expStud) {
			this.expelModel.addElement(s);
		}
		this.expelList = new JList<String>(expelModel);
		this.expelPane = new JScrollPane(this.expelList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.expelPane.setPreferredSize(new Dimension(250, 500));
		this.expelList.setBackground(Color.decode("#20B2AA"));
		this.expelLabel.setFont(font.deriveFont(attributes));
		this.expelLabel.setFont(this.expelLabel.getFont().deriveFont(15f));

		for (String s : appliedStud) {
			this.appliedModel.addElement(s);
		}
		this.appliedList = new JList<String>(appliedModel);
		this.appliedPane = new JScrollPane(this.appliedList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.appliedPane.setPreferredSize(new Dimension(250, 500));
		this.appliedList.setBackground(Color.decode("#20B2AA"));
		this.appliedLabel.setFont(font.deriveFont(attributes));
		this.appliedLabel.setFont(this.appliedLabel.getFont().deriveFont(15f));

		this.searchLabel.setLabelFor(this.searchField);

		this.add(this.supPanel);
		this.add(this.enrolPane);
		this.add(this.expelPane);
		this.add(this.appliedPane);
		this.add(this.appliedLabel);
		this.add(this.appliedAcceptButton);
		this.add(this.appliedRejectButton);
		this.add(this.expelLabel);
		this.add(this.enrolLabel);
		this.add(this.enrolButton);
		this.add(this.expelButton);
		this.add(this.expelButton);
		this.add(this.searchButton);
		this.add(this.searchLabel);
		this.add(this.searchField);
		this.add(this.returning);
		

		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);

		layout2.putConstraint(SpringLayout.EAST, this.enrolPane, -100, SpringLayout.WEST, this.expelPane);
		layout2.putConstraint(SpringLayout.NORTH, this.enrolPane, 150, SpringLayout.NORTH, this);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.enrolLabel, 0, SpringLayout.HORIZONTAL_CENTER,
				this.enrolPane);
		layout2.putConstraint(SpringLayout.SOUTH, this.enrolLabel, -10, SpringLayout.NORTH, this.enrolPane);
		
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.enrolButton, 0, SpringLayout.HORIZONTAL_CENTER,this.enrolPane);
		layout2.putConstraint(SpringLayout.NORTH, this.enrolButton, 10, SpringLayout.SOUTH, this.enrolPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.expelPane, -100, SpringLayout.HORIZONTAL_CENTER,
				this);
		layout2.putConstraint(SpringLayout.NORTH, this.expelPane, 0, SpringLayout.NORTH, this.enrolPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.expelLabel, 0, SpringLayout.HORIZONTAL_CENTER,
				this.expelPane);
		layout2.putConstraint(SpringLayout.SOUTH, this.expelLabel, -10, SpringLayout.NORTH, this.expelPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.expelButton, 0, SpringLayout.HORIZONTAL_CENTER,
				this.expelPane);
		layout2.putConstraint(SpringLayout.NORTH, this.expelButton, 10, SpringLayout.SOUTH, this.expelPane);

		layout2.putConstraint(SpringLayout.WEST, this.appliedPane, 100, SpringLayout.EAST, this.expelPane);
		layout2.putConstraint(SpringLayout.NORTH, this.appliedPane, 0, SpringLayout.NORTH, this.enrolPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedLabel, 0, SpringLayout.HORIZONTAL_CENTER,
				this.appliedPane);
		layout2.putConstraint(SpringLayout.SOUTH, this.appliedLabel, -10, SpringLayout.NORTH, this.appliedPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedAcceptButton, -50, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
		layout2.putConstraint(SpringLayout.NORTH, this.appliedAcceptButton, 10, SpringLayout.SOUTH, this.appliedPane);
		
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.appliedRejectButton, 50, SpringLayout.HORIZONTAL_CENTER, this.appliedPane);
		layout2.putConstraint(SpringLayout.NORTH, this.appliedRejectButton, 10, SpringLayout.SOUTH, this.appliedPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchLabel, 0, SpringLayout.HORIZONTAL_CENTER,
				this.searchField);
		layout2.putConstraint(SpringLayout.SOUTH, this.searchLabel, -10, SpringLayout.NORTH, this.searchField);

		layout2.putConstraint(SpringLayout.EAST, this.searchField, -50, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.searchField, 0, SpringLayout.NORTH, this.enrolPane);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.searchButton, 0, SpringLayout.HORIZONTAL_CENTER,
				this.searchField);
		layout2.putConstraint(SpringLayout.NORTH, this.searchButton, 10, SpringLayout.SOUTH, this.searchField);
	
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.returning, 0, SpringLayout.HORIZONTAL_CENTER, this.searchButton);
		layout2.putConstraint(SpringLayout.NORTH, this.returning, 0, SpringLayout.NORTH, this.appliedAcceptButton);
	}
	public void addEnrCourse(String name){
		this.enrolModel.addElement(name);
	}
	
	public void delEnrCourse(String name){
		this.enrolModel.removeElement(name);
	}
	
	public void addApplyCourse(String name){
		this.expelModel.addElement(name);
	}
	
	public void delApplyCourse(String name){
		this.expelModel.removeElement(name);
	}
	
	public void addAppliedCourse(String name){
		this.appliedModel.addElement(name);
	}
	
	public void delAppliedCourse(String name){
		this.appliedModel.removeElement(name);
	}
}
