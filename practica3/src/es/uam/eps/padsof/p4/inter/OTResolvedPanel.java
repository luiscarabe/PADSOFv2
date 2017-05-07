package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import es.uam.eps.padsof.p3.exercise.Option;

public class OTResolvedPanel extends JPanel {
	// Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel;

	private JLabel student;
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();

	private JTextArea questionArea = new JTextArea();
	private JScrollPane questionPane = new JScrollPane(this.questionArea,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	// Inf
	private JButton previous = new JButton("Previous");
	private JButton returning = new JButton("Return");
	private JButton next = new JButton("Next");
	private JPanel infPanel = new JPanel();

	// Solution
	private DefaultListModel<Option> solutionModel = new DefaultListModel<Option>();
	private JList<Option> solutionList;
	private JScrollPane solutionPane;

	private SpringLayout layout2 = new SpringLayout();

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private OTResolvedPanel(String nameStud, String nameExer, String nameQues, float wei) {
		this.setVisible(true);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));

		// Sup Panel

		this.supPanel.setVisible(true);
		this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
		this.supPanel.setBackground(Color.decode("#228B22"));
		this.supPanel.setLayout(layout);

		this.homeLabel = new JLabel("Solution of " + nameExer);
		this.student = new JLabel("Student: " + nameStud);
		this.imgLabel.setVisible(true);
		this.imgLabel.setBounds(0, 0, 200, 200);

		this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(25f));
		this.student.setFont(this.student.getFont().deriveFont(15f));
		this.signOut.setForeground(Color.RED);

		this.supPanel.add(imgLabel);
		this.supPanel.add(homeLabel);
		this.supPanel.add(student);
		this.supPanel.add(signOut);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER,
				this.imgLabel);
		layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);

		layout.putConstraint(SpringLayout.NORTH, this.student, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.student, 0, SpringLayout.EAST, this.signOut);

		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50, SpringLayout.EAST, this.supPanel);

		// Inf panel

		this.infPanel.setVisible(true);
		this.infPanel.setPreferredSize(new Dimension(350, 80));
		this.infPanel.setBackground(this.getBackground());
		this.infPanel.setLayout(new FlowLayout());
		this.infPanel.add(this.previous);
		this.infPanel.add(this.returning);
		this.infPanel.add(this.next);

		this.questionArea.setText("(" + wei + " points) " + nameQues);
		this.questionArea.setFont(this.questionArea.getFont().deriveFont(15f));
		this.questionArea.setLineWrap(true);
		this.questionArea.setWrapStyleWord(true);
		this.questionPane.setPreferredSize(new Dimension(500, 80));
		this.questionArea.setEditable(false);
		this.questionArea.setBackground(this.getBackground());
		this.questionPane.setBorder(null);
		
		this.solutionList = new JList<Option>(solutionModel);
		this.solutionPane = new JScrollPane(this.solutionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.solutionPane.setPreferredSize(new Dimension(400, 200));
		this.solutionPane.setBackground(this.getBackground());

		this.add(this.supPanel);
		this.add(this.infPanel);
		this.add(this.questionPane);
		this.add(this.solutionPane);

		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);

		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.infPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.SOUTH, this.infPanel, -10, SpringLayout.SOUTH, this);

		layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.SOUTH, this.supPanel);

		layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);
	}

	public OTResolvedPanel(String nameStud, String nameExer, String nameQues, float wei, ArrayList<Option> op) {
		this(nameStud, nameExer, nameQues, wei);
		for (Option o : op) {
			this.solutionModel.addElement(o);
		}
		this.solutionList = new JList<Option>(solutionModel);
		this.solutionPane = new JScrollPane(this.solutionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.solutionPane.setPreferredSize(new Dimension(400, 200));
		this.solutionPane.setBackground(this.getBackground());
	}

	public OTResolvedPanel(String nameStud, String nameExer, String nameQues, float wei, Option op, boolean correct) {
		this(nameStud, nameExer, nameQues, wei);

		this.solutionModel.addElement(op);
		this.solutionList = new JList<Option>(solutionModel);
		this.solutionPane = new JScrollPane(this.solutionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.solutionPane.setPreferredSize(new Dimension(400, 200));
		this.solutionPane.setBackground(this.getBackground());

		JLabel yourMark = new JLabel();
		if (correct == true)
			yourMark.setText("Your mark: " + wei + "/" + wei);
		else
			yourMark.setText("Yout mark: 0/" + wei);
		this.add(yourMark);
		this.homeLabel.setText("Answers of " + nameExer);
		
		layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);

		layout2.putConstraint(SpringLayout.NORTH, yourMark, 10, SpringLayout.SOUTH, this.solutionPane);
		layout2.putConstraint(SpringLayout.WEST, yourMark, 0, SpringLayout.WEST, this.solutionPane);
	}
}
