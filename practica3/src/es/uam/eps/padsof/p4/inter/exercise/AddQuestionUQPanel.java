package es.uam.eps.padsof.p4.inter.exercise;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.*;

public class AddQuestionUQPanel extends JDialog{
	
	private JPanel jp = new JPanel();
	
	private JLabel firstLabel = new JLabel("Uniq choice Question");
	
	private JLabel titleLabel = new JLabel("Question:");
	private JTextField titleField = new JTextField(20);
	
	private JLabel solutionLabel = new JLabel("Add an option");
	private JButton addSolution = new JButton("Add");
	private JTextField solutionField = new JTextField(20);
	private DefaultListModel<String> solutionModel = new DefaultListModel<String>(); 
	JList<String> solutionList;
	private JButton delSolution = new JButton("Delete solution");
	private JScrollPane solutionPane;
	
	private DefaultListModel<String> optionsModel = new DefaultListModel<String>(); 
	JList<String> optionsList;
	private JButton option2Solution = new JButton("Add solution");
	private JScrollPane optionsPane;
	
	private JLabel weightLabel = new JLabel("Weight:");
	private JTextField weightField = new JTextField(10);
	
	private JCheckBox aleat = new JCheckBox("Aleat order");
	private JButton create = new JButton("Create");
	private JButton cancel = new JButton("Cancel");
			
	private SpringLayout layout = new SpringLayout();
	
	public AddQuestionUQPanel(){
		this.jp.setVisible(true);
		this.jp.setPreferredSize(new Dimension(500, 500));
		this.jp.setLayout(layout);

		Map attributes = this.firstLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.firstLabel.setFont(this.firstLabel.getFont().deriveFont(attributes));
		
		this.solutionList = new JList<String>(solutionModel);
		this.solutionPane = new JScrollPane(this.solutionList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.solutionPane.setPreferredSize(new Dimension(200,200));
		
		this.optionsList = new JList<String>(optionsModel);
		this.optionsPane = new JScrollPane(this.optionsList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.optionsPane.setPreferredSize(new Dimension(200,200));
		this.optionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.jp.add(this.solutionLabel);
		this.jp.add(this.titleLabel);
		this.jp.add(this.titleField);
		this.jp.add(this.firstLabel);
		this.jp.add(this.create);
		this.jp.add(this.cancel);
		this.jp.add(this.weightField);
		this.jp.add(this.weightLabel);
		this.jp.add(this.solutionPane);
		this.jp.add(this.solutionField);
		this.jp.add(this.addSolution);
		this.jp.add(this.delSolution);
		this.jp.add(this.optionsPane);
		this.jp.add(this.option2Solution);
		this.jp.add(this.aleat);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.firstLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.firstLabel, 10, SpringLayout.NORTH, this.jp);
		
		layout.putConstraint(SpringLayout.WEST, this.titleLabel, 10, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.titleLabel, 10, SpringLayout.SOUTH, this.firstLabel);

		layout.putConstraint(SpringLayout.WEST, this.titleField, 0, SpringLayout.WEST, this.solutionField);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.titleField, 0, SpringLayout.VERTICAL_CENTER, this.titleLabel);

		layout.putConstraint(SpringLayout.WEST, this.solutionLabel, 10, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.solutionLabel, 20, SpringLayout.SOUTH, this.titleLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.addSolution, 10, SpringLayout.EAST, this.solutionField);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.addSolution, 0, SpringLayout.VERTICAL_CENTER, this.solutionLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.solutionPane, 10, SpringLayout.EAST, this.optionsPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.optionsPane, 0, SpringLayout.VERTICAL_CENTER, this.solutionPane);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.option2Solution, 0, SpringLayout.HORIZONTAL_CENTER, this.optionsPane);
		layout.putConstraint(SpringLayout.NORTH, this.option2Solution, 10, SpringLayout.SOUTH, this.solutionPane);
		
		layout.putConstraint(SpringLayout.WEST, this.solutionField, 10, SpringLayout.EAST, this.solutionLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.solutionField, 0, SpringLayout.VERTICAL_CENTER, this.solutionLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.optionsPane, 10, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.solutionPane, 10, SpringLayout.SOUTH, this.solutionLabel);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.delSolution, 0, SpringLayout.HORIZONTAL_CENTER, this.solutionPane);
		layout.putConstraint(SpringLayout.NORTH, this.delSolution, 10, SpringLayout.SOUTH, this.solutionPane);
		
		layout.putConstraint(SpringLayout.WEST, this.weightLabel, 0, SpringLayout.WEST, this.titleLabel);
		layout.putConstraint(SpringLayout.NORTH, this.weightLabel, 20, SpringLayout.SOUTH, this.delSolution);
		
		layout.putConstraint(SpringLayout.WEST, this.weightField, 10, SpringLayout.EAST, this.weightLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.weightField, 0, SpringLayout.VERTICAL_CENTER, this.weightLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.aleat, 0, SpringLayout.WEST, this.titleLabel);
		layout.putConstraint(SpringLayout.NORTH, this.aleat, 10, SpringLayout.SOUTH, this.weightField);
		
		layout.putConstraint(SpringLayout.WEST, this.create, 150, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.create, 50, SpringLayout.SOUTH, this.weightLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.cancel, 10, SpringLayout.EAST, this.create);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.cancel, 0, SpringLayout.VERTICAL_CENTER, this.create);		
		
		this.setContentPane(this.jp);
		this.setVisible(true);
		this.setSize(new Dimension(500,500));
	}
}
