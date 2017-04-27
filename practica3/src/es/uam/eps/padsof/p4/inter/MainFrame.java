/**
 * 
 */
package es.uam.eps.padsof.p4.inter;


import javax.swing.*;


/**
 * @author yo
 *
 */
public class MainFrame extends JFrame{
	private JFrame educagram;
	private static final MainFrame Instance = new MainFrame();
	
	/**
	 * Private constructor of Educagram, it creates an instance of the
	 * several ArrayLists
	 */
	
	private MainFrame(){
		this.educagram = new JFrame("Educagram");
	}
    
	/**
	 * @return
	 */
	public static MainFrame getInstance(){
		return MainFrame.Instance;
	}
	
	public void addPanel(JPanel panel){
		educagram.add(panel);
	}
	
	
}
