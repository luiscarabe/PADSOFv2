/**
* @author Luis Carabe 
* @author Alejo Polania 
*/
package es.uam.eps.padsof.p4.inter.courseTeacher;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewNoteTeacherPanel extends JPanel{
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel creatLabel = new JLabel("View Note");
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	
	private JTextField nameField = new JTextField(30);
	private JTextArea descField = new JTextArea();
	private JTextArea contentField = new JTextArea();
	private JScrollPane textpane = new JScrollPane(this.descField,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane contentPane = new JScrollPane(this.contentField,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton ok = new JButton("Return");
	
	
	
	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * Constructor of ViewNoteTeacherPanel
	 * @param name
	 * @param desc
	 * @param content
	 */
	public ViewNoteTeacherPanel(String name, String desc, String content){
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
		
		
		this.nameField.setText(name);
		this.nameField.setEditable(false);
		this.nameField.setFont(this.nameField.getFont().deriveFont(30f));
		this.nameField.setBackground(this.getBackground());
		this.nameField.setBorder(null);
		this.descField.setText(desc);
		this.descField.setEditable(false);
		this.descField.setBackground(this.getBackground());
		this.textpane.setBorder(null);
		this.contentField.setText(content);
		this.contentField.setEditable(false);
		this.contentField.setBackground(this.getBackground());
		this.contentPane.setBorder(null);
		this.descField.setLineWrap(true);
		this.descField.setWrapStyleWord(true);
	
		this.textpane.setPreferredSize(new Dimension(700,100));
		this.contentPane.setPreferredSize(new Dimension(700,300));
													
		this.add(this.textpane);
		this.add(this.supPanel);
		this.add(this.nameField);
		this.add(this.ok);
		this.add(this.contentPane);
	
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.nameField, 100, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.WEST, this.nameField, 10, SpringLayout.WEST, this);

		layout2.putConstraint(SpringLayout.NORTH, this.textpane, 50, SpringLayout.SOUTH, this.nameField);
		layout2.putConstraint(SpringLayout.WEST, this.textpane, 0, SpringLayout.WEST, this.nameField);

		layout2.putConstraint(SpringLayout.NORTH, this.contentPane, 50, SpringLayout.SOUTH, this.textpane);
		layout2.putConstraint(SpringLayout.WEST, this.contentPane, 0, SpringLayout.WEST, this.textpane);
		
		layout2.putConstraint(SpringLayout.EAST, this.ok, 0, SpringLayout.EAST, this.contentPane);
		layout2.putConstraint(SpringLayout.NORTH, this.ok, 10, SpringLayout.SOUTH, this.contentPane);
		

	}
	/**
	 * 
	 * @param c
	 */
	public void setController(ActionListener c) {
		this.signOut.addActionListener(c);
		this.ok.addActionListener(c);
	}
	/**
	 * 
	 * @return the signOut
	 */
	public JButton getSignOut() {
		return signOut;
	}
	/**
	 * 
	 * @return the descField
	 */

	public String getDesc() {
		return descField.getText();
	}
	/**
	 * 
	 * @return the contentField
	 */

	public String getContent() {
		return contentField.getText();
	}
	/**
	 * 
	 * @return the ok
	 */

	public JButton getOk() {
		return ok;
	}


}