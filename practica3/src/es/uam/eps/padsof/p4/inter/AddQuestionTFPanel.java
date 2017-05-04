package es.uam.eps.padsof.p4.inter;

import javax.swing.*;

public class AddQuestionTFPanel extends JPanel{
	
	private JDialog jd = new JDialog();
	
	private JLabel titleLabel = new JLabel("Question:");
	private JTextField titleField = new JTextField(20);
	
	private SpringLayout layout = new SpringLayout();
	
	public AddQuestionTFPanel(){
		this.setVisible(true);
		this.getPreferredSize(500, 500);
		this.setLayout(layout);
		
		this.add(this.titleLabel);
		this.add(this.titleField);
		
		layout.putConstraint(SpringLayout.WEST, this.titleLabel, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, this.titleLabel, 10, SpringLayout.NORTH, this);

		layout.putConstraint(SpringLayout.WEST, this.titleField, 10, SpringLayout.EAST, this.titleLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.titleField, 0, SpringLayout.VERTICAL_CENTER, this.titleLabel);
		
		this.jd.setContentPane(this);
		this.jd.setVisible(true);
		this.jd.setSize(width, height);
	}
}
