package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;


public class CreateCoursePanel extends JPanel {
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel creatLabel = new JLabel("Create Course");
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	
	private JLabel nameLabel = new JLabel("Write the name of the course:");
	private JLabel descLabel = new JLabel("Write the description of the course:");
	private JTextField nameField = new JTextField(30);
	private JTextArea descField = new JTextArea(3, 30);
	private JButton ok = new JButton("Create");
	private JButton cancel = new JButton("Cancel");
	
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public CreateCoursePanel(){
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
		//this.imgLabel.setPreferredSize(new Dimension(100,100));
		
		this.creatLabel.setFont(this.creatLabel.getFont().deriveFont(30f));
		this.professor.setFont(this.professor.getFont().deriveFont(15f));
		this.signOut.setForeground(Color.RED);
		
		this.supPanel.add(imgLabel,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(creatLabel,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(professor,SpringLayout.VERTICAL_CENTER);
		this.supPanel.add(signOut,SpringLayout.VERTICAL_CENTER);
		
		layout.putConstraint(SpringLayout.NORTH, this.imgLabel, 10, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.imgLabel, 10, SpringLayout.WEST, this.supPanel);
		
		layout.putConstraint(SpringLayout.NORTH, this.creatLabel, 20, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.creatLabel, 30, SpringLayout.EAST, this.imgLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.professor, 1100, SpringLayout.WEST, this.supPanel);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.WEST, this.signOut, 1100, SpringLayout.WEST, this.supPanel);
		
		this.nameLabel.setLabelFor(this.nameField);
		this.descLabel.setLabelFor(this.descField);
		

		this.add(this.supPanel, SpringLayout.VERTICAL_CENTER);
		this.add(this.nameField, SpringLayout.HORIZONTAL_CENTER);
		this.add(this.descField, SpringLayout.VERTICAL_CENTER);
		this.add(this.descLabel, SpringLayout.VERTICAL_CENTER);
		this.add(this.nameLabel, SpringLayout.VERTICAL_CENTER);
		
	
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.nameLabel, 100, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.WEST, this.nameLabel, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.nameField, 100, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.WEST, this.nameField, 10, SpringLayout.EAST, this.nameLabel);
		
		layout2.putConstraint(SpringLayout.NORTH, this.descLabel, 50, SpringLayout.SOUTH, this.nameLabel);
		layout2.putConstraint(SpringLayout.WEST, this.descLabel, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.descField, 50, SpringLayout.NORTH, this.nameField);
		layout2.putConstraint(SpringLayout.WEST, this.descField, 10, SpringLayout.EAST, this.descLabel);

		

	}
}
