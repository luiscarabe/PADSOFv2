/**
 * 
 */
package es.uam.eps.padsof.p4.inter;


import java.awt.*;

import javax.swing.*;


/**
 * @author yo
 *
 */
public class MainFrame extends JFrame{
	private static final MainFrame Instance = new MainFrame("Educagram");
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/**
	 * Private constructor of Educagram, it creates an instance of the
	 * several ArrayLists
	 */
	
	private MainFrame(String name){
		super(name);
	}
    
	/**
	 * @return
	 */
	public static MainFrame getInstance(){
		return MainFrame.Instance;
	}
	
	public void addPanel(JPanel panel){
		this.add(panel);
	}
	
	
}
