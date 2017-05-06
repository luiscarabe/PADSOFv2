package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;

import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.CMark;

public class CourseMarksPanel extends JPanel {
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel;

	private JLabel student;
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	
	private JLabel courName;
	private JLabel courMark;
	
	private JPanel marksPanel = new JPanel();
	private JScrollPane marksPane = new JScrollPane(this.marksPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton back = new JButton("Return");
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public CourseMarksPanel(String nameStud, CMark cm){
		this.setVisible(true);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));
		
		// Sup Panel
		
		this.supPanel.setVisible(true);
		this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
		this.supPanel.setBackground(Color.decode("#228B22"));
		this.supPanel.setLayout(layout);
		
		this.homeLabel = new JLabel("Marks");
		this.student = new JLabel("Student: "+ nameStud);
		this.imgLabel.setVisible(true);
		this.imgLabel.setBounds(0,0,200,200);
		
		this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(25f));
		this.student.setFont(this.student.getFont().deriveFont(15f));
		this.signOut.setForeground(Color.RED);
		
		
		this.supPanel.add(imgLabel);
		this.supPanel.add(homeLabel);
		this.supPanel.add(student);
		this.supPanel.add(signOut);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
		layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
		
		this.courName = new JLabel(cm.getCourse().getTitle());
		Map attributes = this.courName.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.courName.setFont(this.courName.getFont().deriveFont(attributes));
		this.courName.setFont(this.courName.getFont().deriveFont(23f));
		
		this.courMark = new JLabel("Global: "+cm.calculateCMark());
		this.courMark.setFont(this.courMark.getFont().deriveFont(attributes));
		this.courMark.setFont(this.courMark.getFont().deriveFont(18f));
		
		this.marksPanel.setVisible(true);
		this.marksPanel.setLayout(new GridLayout(cm.getStudent().getAnswers().size(),1));
		for(Answer a: cm.getStudent().getAnswers()){
			if(a.getExercise().getCourse().equals(cm.getCourse())){
				JLabel jl = new JLabel(a.getExercise().getTitle() + ": "+a.getMarkOut10()+" (Weight: "+ a.getExercise().getWeight()+")");
				this.marksPanel.add(jl);
			}
		}
		this.marksPane.setPreferredSize(new Dimension(500,300));
		this.marksPane.setBorder(null);
		this.marksPanel.setBackground(this.getBackground());
		this.marksPane.setBackground(this.getBackground());
		
		this.add(this.supPanel);	
		this.add(this.courName);
		this.add(this.courMark);
		this.add(this.marksPane);
		this.add(this.back);
		
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.courName, 10, SpringLayout.SOUTH, this.supPanel);
		layout2.putConstraint(SpringLayout.WEST, this.courName, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.courMark, 20, SpringLayout.SOUTH, this.courName);
		layout2.putConstraint(SpringLayout.WEST, this.courMark, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.marksPane, 20, SpringLayout.SOUTH, this.courMark);
		layout2.putConstraint(SpringLayout.WEST, this.marksPane, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.back, 20, SpringLayout.SOUTH, this.marksPane);
		layout2.putConstraint(SpringLayout.WEST, this.back, 10, SpringLayout.WEST, this);
		
	}
		
}
