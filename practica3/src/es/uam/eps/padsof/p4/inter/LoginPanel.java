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

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public LoginPanel(){
		imgLabel.setVisible(true);
		imgLabel.setBounds(15,20,200,200);
		//imgLabel.setPreferredSize(new Dimension(1000,1000));

		this.logWindow.setPreferredSize(new Dimension(370,100));
		this.logWindow.setMaximumSize(new Dimension(500,500));
		
		//this.logWindow.setBounds(1, 1, 100, 100);
		this.logWindow.setLayout(layout);
		this.logWindow.add(idLabel, SpringLayout.VERTICAL_CENTER);
		this.logWindow.add(idField, SpringLayout.VERTICAL_CENTER);
		idLabel.setLabelFor(idField);
		this.logWindow.add(pswLabel, SpringLayout.VERTICAL_CENTER);
		pswLabel.setLabelFor(pswField);
		this.logWindow.add(pswField, SpringLayout.VERTICAL_CENTER);
		this.logWindow.add(enter, SpringLayout.VERTICAL_CENTER);
		this.logWindow.setBackground(Color.decode("#556B2F"));
		this.setPreferredSize(new Dimension(screenSize.width,screenSize.height));
		
		layout.putConstraint(SpringLayout.WEST, this.idLabel, 10, SpringLayout.WEST, this.logWindow);
		layout.putConstraint(SpringLayout.NORTH, this.idLabel, 10, SpringLayout.NORTH, this.logWindow);
		
		layout.putConstraint(SpringLayout.NORTH, this.pswLabel, 15, SpringLayout.SOUTH, this.idLabel);
		layout.putConstraint(SpringLayout.WEST, this.pswLabel, 10, SpringLayout.WEST, this.logWindow);
		
		layout.putConstraint(SpringLayout.WEST, this.idField, 10, SpringLayout.EAST, this.pswLabel);
		layout.putConstraint(SpringLayout.NORTH, this.idField, 10, SpringLayout.NORTH, this.logWindow);
		
		layout.putConstraint(SpringLayout.NORTH, this.pswField, 10, SpringLayout.SOUTH, this.idField);
		layout.putConstraint(SpringLayout.WEST, this.pswField, 10, SpringLayout.EAST, this.pswLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.enter, 10, SpringLayout.SOUTH, this.pswField);
		layout.putConstraint(SpringLayout.WEST, this.enter, 150, SpringLayout.WEST, this.logWindow);
		
		this.setLayout(layout2);
		this.setSize(screenSize.width, screenSize.height);
		this.logWindow.setVisible(true);
		this.add(this.imgLabel);
		this.add(this.logWindow);
		
		layout2.putConstraint(SpringLayout.WEST, this.imgLabel, 300, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.imgLabel, 30, SpringLayout.NORTH, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.logWindow, 425, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.logWindow, 30, SpringLayout.SOUTH, this.imgLabel);

		
		this.setBackground(Color.WHITE);
		this.setVisible(true);

		
	}
	
}
