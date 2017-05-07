package es.uam.eps.padsof.p4.inter.stats;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import es.uam.eps.padsof.p3.exercise.Option;

public class OTResolvedPanel extends JPanel {
	private JTextArea questionArea = new JTextArea();
	private JScrollPane questionPane = new JScrollPane(this.questionArea,
			ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	// Solution
	private DefaultListModel<Option> solutionModel = new DefaultListModel<Option>();
	private JList<Option> solutionList;
	private JScrollPane solutionPane;

	private SpringLayout layout2 = new SpringLayout();

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private OTResolvedPanel( String nameExer, String nameQues, float wei) {
		this.setVisible(true);
		this.setSize(new Dimension(screenSize.width, 800));
		this.setPreferredSize(new Dimension(screenSize.width, 800));
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));

		
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

		this.add(this.questionPane);
		this.add(this.solutionPane);

		layout2.putConstraint(SpringLayout.WEST, this.questionPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.questionPane, 10, SpringLayout.NORTH, this);

		layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);
	}

	public OTResolvedPanel( String nameExer, String nameQues, float wei, ArrayList<Option> op) {
		this( nameExer, nameQues, wei);
		for (Option o : op) {
			this.solutionModel.addElement(o);
		}
		this.solutionList = new JList<Option>(solutionModel);
		this.solutionPane = new JScrollPane(this.solutionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.solutionPane.setPreferredSize(new Dimension(400, 200));
		this.solutionPane.setBackground(this.getBackground());
	}

	public OTResolvedPanel( String nameExer, String nameQues, float wei, Option op, boolean correct) {
		this( nameExer, nameQues, wei);

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
		
		layout2.putConstraint(SpringLayout.WEST, this.solutionPane, 0, SpringLayout.WEST, this.questionPane);
		layout2.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.questionPane);

		layout2.putConstraint(SpringLayout.NORTH, yourMark, 10, SpringLayout.SOUTH, this.solutionPane);
		layout2.putConstraint(SpringLayout.WEST, yourMark, 0, SpringLayout.WEST, this.solutionPane);
	}
}
