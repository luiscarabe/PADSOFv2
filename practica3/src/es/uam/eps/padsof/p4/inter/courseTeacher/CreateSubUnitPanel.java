/**
* @author Luis Carabe 
* @author Alejo Polania 
*/
package es.uam.eps.padsof.p4.inter.courseTeacher;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateSubUnitPanel extends JPanel{
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel creatLabel = new JLabel("Create Subunit");
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	
	private JLabel nameLabel = new JLabel("Write the name of the subunit:");
	private JLabel descLabel = new JLabel("Write the description of the subunit:");
	private JTextField nameField = new JTextField(30);
	private JTextArea descField = new JTextArea();
	private JScrollPane textpane = new JScrollPane(this.descField,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton ok = new JButton("Create");
	private JButton cancel = new JButton("Cancel");
	
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Constructor of CreateSubUnitPanel
	 */
	public CreateSubUnitPanel(){
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
		
		this.creatLabel.setFont(this.creatLabel.getFont().deriveFont(30f));
		this.professor.setFont(this.professor.getFont().deriveFont(15f));
		this.signOut.setForeground(Color.RED);
		
		this.supPanel.add(imgLabel);
		this.supPanel.add(creatLabel);
		this.supPanel.add(professor);
		this.supPanel.add(signOut);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.creatLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
		layout.putConstraint(SpringLayout.WEST, this.creatLabel, 10, SpringLayout.EAST, this.imgLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.professor, 0, SpringLayout.HORIZONTAL_CENTER, this.signOut);
		
		layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
		
		this.nameLabel.setLabelFor(this.nameField);
		
		this.descField.setLineWrap(true);
		this.descField.setWrapStyleWord(true);
		this.textpane.setPreferredSize(new Dimension(700,300));
													
		this.add(this.textpane);
		this.add(this.supPanel);
		this.add(this.nameField);
		this.add(this.descLabel);
		this.add(this.nameLabel);
		this.add(this.ok);
		this.add(this.cancel);
	
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.nameLabel, 100, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.WEST, this.nameLabel, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.nameField, 100, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.WEST, this.nameField, 0, SpringLayout.WEST, this.textpane);
		
		layout2.putConstraint(SpringLayout.NORTH, this.descLabel, 50, SpringLayout.SOUTH, this.nameLabel);
		layout2.putConstraint(SpringLayout.WEST, this.descLabel, 10, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.textpane, 0, SpringLayout.NORTH, this.descLabel);
		layout2.putConstraint(SpringLayout.WEST, this.textpane, 10, SpringLayout.EAST, this.descLabel);
		
		layout2.putConstraint(SpringLayout.NORTH, this.cancel, 10, SpringLayout.SOUTH, this.textpane);
		layout2.putConstraint(SpringLayout.EAST, this.cancel, 0, SpringLayout.EAST, this.textpane);
		
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.ok, 0, SpringLayout.VERTICAL_CENTER, this.cancel);
		layout2.putConstraint(SpringLayout.EAST, this.ok, -10, SpringLayout.WEST, this.cancel);
		

	}
	/**
	 * 
	 */
	
	public String getName() {
		return this.nameField.getText();
	}
	/**
	 * 
	 * @return
	 */
	
	public String getDesc() {
		return this.descField.getText();
	}
	/**
	 * Method to set the controllers
	 * @param c
	 */
	
	public void setController(ActionListener c) {
		this.signOut.addActionListener(c);
		this.ok.addActionListener(c);
		this.cancel.addActionListener(c);
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
	 * @return the creatLabel
	 */
	public JLabel getCreatLabel() {
		return creatLabel;
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
	 * @return the nameLabel
	 */
	public JLabel getNameLabel() {
		return nameLabel;
	}

	/**
	 * @return the descLabel
	 */
	public JLabel getDescLabel() {
		return descLabel;
	}

	/**
	 * @return the nameField
	 */
	public JTextField getNameField() {
		return nameField;
	}

	/**
	 * @return the descField
	 */
	public JTextArea getDescField() {
		return descField;
	}

	/**
	 * @return the textpane
	 */
	public JScrollPane getTextpane() {
		return textpane;
	}

	/**
	 * @return the ok
	 */
	public JButton getOk() {
		return ok;
	}

	/**
	 * @return the cancel
	 */
	public JButton getCancel() {
		return cancel;
	}

	/**
	 * @return the layout2
	 */
	public SpringLayout getLayout2() {
		return layout2;
	}
}