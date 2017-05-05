package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.*;

public class ModifyQuestionTFPanel extends JDialog{
	
	private JPanel jp = new JPanel();
	
	private JLabel firstLabel = new JLabel("True/False Question");
	
	private JLabel titleLabel = new JLabel("Question:");
	private JTextField titleField = new JTextField(20);
	
	private JLabel solutionLabel = new JLabel("Choose the solution:");
	private JRadioButton solutionT = new JRadioButton("True");
	private JRadioButton solutionF = new JRadioButton("False");
	private ButtonGroup solutionGroup = new ButtonGroup();
	private JPanel solutionPanel = new JPanel(new GridLayout(1, 2));
	
	private JLabel weightLabel = new JLabel("Weight:");
	private JTextField weightField = new JTextField(10);
	
	private JButton create = new JButton("Create");
	private JButton cancel = new JButton("Cancel");
			
	private SpringLayout layout = new SpringLayout();
	
	public ModifyQuestionTFPanel(String title, String wei, boolean sol){
		this.jp.setVisible(true);
		this.jp.setPreferredSize(new Dimension(300, 300));
		this.jp.setLayout(layout);

		this.solutionGroup.add(solutionT);
		this.solutionGroup.add(solutionF);
		this.solutionPanel.add(this.solutionT);
		this.solutionPanel.add(this.solutionF);
		
		this.solutionPanel.setPreferredSize(new Dimension(200, 50));
		
		Map attributes = this.firstLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.firstLabel.setFont(this.firstLabel.getFont().deriveFont(attributes));
		
		this.titleField.setText(title);
		this.weightField.setText(wei);
		if(sol == true)
			this.solutionT.setSelected(true);
		else
			this.solutionF.setSelected(true);
			

		this.jp.add(this.solutionLabel);
		this.jp.add(this.titleLabel);
		this.jp.add(this.titleField);
		this.jp.add(this.solutionPanel);
		this.jp.add(this.firstLabel);
		this.jp.add(this.create);
		this.jp.add(this.cancel);
		this.jp.add(this.weightField);
		this.jp.add(this.weightLabel);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.firstLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.firstLabel, 10, SpringLayout.NORTH, this.jp);
		
		layout.putConstraint(SpringLayout.WEST, this.titleLabel, 10, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.titleLabel, 10, SpringLayout.SOUTH, this.firstLabel);

		layout.putConstraint(SpringLayout.WEST, this.titleField, 10, SpringLayout.EAST, this.titleLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.titleField, 0, SpringLayout.VERTICAL_CENTER, this.titleLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.solutionLabel, 10, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.solutionLabel, 20, SpringLayout.SOUTH, this.titleLabel);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.solutionPanel, 0, SpringLayout.VERTICAL_CENTER, this.solutionLabel);
		layout.putConstraint(SpringLayout.WEST, this.solutionPanel, 10, SpringLayout.EAST, this.solutionLabel);

		layout.putConstraint(SpringLayout.WEST, this.weightLabel, 0, SpringLayout.WEST, this.titleLabel);
		layout.putConstraint(SpringLayout.NORTH, this.weightLabel, 10, SpringLayout.SOUTH, this.solutionPanel);
		
		layout.putConstraint(SpringLayout.WEST, this.weightField, 10, SpringLayout.EAST, this.weightLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.weightField, 0, SpringLayout.VERTICAL_CENTER, this.weightLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.create, 150, SpringLayout.WEST, this.jp);
		layout.putConstraint(SpringLayout.NORTH, this.create, 50, SpringLayout.SOUTH, this.weightLabel);
		
		layout.putConstraint(SpringLayout.WEST, this.cancel, 10, SpringLayout.EAST, this.create);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.cancel, 0, SpringLayout.VERTICAL_CENTER, this.create);		
		
		this.setContentPane(this.jp);
		this.setVisible(true);
		this.setSize(new Dimension(500,300));
	}
}
