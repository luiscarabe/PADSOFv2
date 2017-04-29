/**
 * 
 */
package es.uam.eps.padsof.p4.inter;


import java.awt.*;
import javax.swing.*;
/**
 * @author e341020
 *
 */
public class LoginPanel extends JPanel{
	private JButton enter = new JButton("Enter");
	private JPanel logWindow = new JPanel();
	private JLabel idLabel = new JLabel("ID:");
	private JTextField idField = new JTextField(20);
	private JLabel pswLabel = new JLabel("Password:");
	private JPasswordField pswField = new JPasswordField(20);
	private SpringLayout layout = new SpringLayout();
	private SpringLayout layout2 = new SpringLayout();
	private ImageIcon image = new ImageIcon("fase2.1.jpg");
	private JLabel imgLabel = new JLabel(image);
	private JLabel eastLabel = new JLabel(" ");
	private JLabel westLabel = new JLabel(" ");
	private JLabel southLabel = new JLabel(" ");
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public LoginPanel(){
		imgLabel.setVisible(true);
		imgLabel.setBounds(15,20,200,200);
		//eastLabel.setVisible(true);
		//eastLabel.setBounds(15,20,200,200);
		//westLabel.setVisible(true);
		//westLabel.setBounds(15,20,200,200);
		//southLabel.setVisible(true);
		//southLabel.setBounds(15,20,200,200);

		this.logWindow.setPreferredSize(new Dimension(600,600));
		this.logWindow.setMaximumSize(new Dimension(600,600));
		//this.logWindow.setSize(100, 100);
		this.logWindow.setLayout(layout);
		this.logWindow.add(idLabel, SpringLayout.HORIZONTAL_CENTER);
		this.logWindow.add(idField);
		idLabel.setLabelFor(idField);
		this.logWindow.add(pswLabel);
		pswLabel.setLabelFor(pswField);
		this.logWindow.add(pswField);
		this.logWindow.add(enter);
		this.logWindow.setBackground(Color.GREEN);
		this.setPreferredSize(new Dimension(screenSize.width,screenSize.height));
		
		//layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.idLabel, 10, SpringLayout.HORIZONTAL_CENTER, this.logWindow);
		layout.putConstraint(SpringLayout.NORTH, this.idLabel, 10, SpringLayout.NORTH, this.logWindow);
		
		layout.putConstraint(SpringLayout.NORTH, this.pswLabel, 15, SpringLayout.SOUTH, this.idLabel);
		layout.putConstraint(SpringLayout.WEST, this.pswLabel, 10, SpringLayout.WEST, this.logWindow);
		
		layout.putConstraint(SpringLayout.WEST, this.idField, 10, SpringLayout.EAST, this.pswLabel);
		layout.putConstraint(SpringLayout.NORTH, this.idField, 10, SpringLayout.NORTH, this.logWindow);
		
		layout.putConstraint(SpringLayout.NORTH, this.pswField, 10, SpringLayout.SOUTH, this.idField);
		layout.putConstraint(SpringLayout.WEST, this.pswField, 10, SpringLayout.EAST, this.pswLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.enter, 10, SpringLayout.SOUTH, this.pswField);
		layout.putConstraint(SpringLayout.WEST, this.enter, 250, SpringLayout.WEST, this.logWindow);
		
		this.setLayout(layout2);
		this.setSize(screenSize.width, screenSize.height);
		this.logWindow.setVisible(true);
		this.add(this.imgLabel, SpringLayout.HORIZONTAL_CENTER);
		layout2.putConstraint(SpringLayout.EAST, this.imgLabel, 80, SpringLayout.EAST, this);
		this.add(this.logWindow, BorderLayout.CENTER);
		//this.add(this.southLabel, BorderLayout.SOUTH);
		//this.add(this.eastLabel, BorderLayout.EAST);
		//this.add(this.westLabel, BorderLayout.WEST);
		this.setBackground(Color.WHITE);
		this.setVisible(true);

		
	}
	
}
