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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final MainFrame Instance = new MainFrame("Educagram");
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	LoginPanel lp = new LoginPanel();
	HomePanelTeacher hpt = new HomePanelTeacher();
	HomePanelStudent hps;
	
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
	
	
	
	/**
	 * @return the lp
	 */
	public LoginPanel getLp() {
		return lp;
	}



	/**
	 * @param lp the lp to set
	 */
	public void setLp(LoginPanel lp) {
		this.lp = lp;
	}



	/**
	 * @return the hpt
	 */
	public HomePanelTeacher getHpt() {
		return hpt;
	}

	/**
	 * @param hpt the hpt to set
	 */
	public void setHpt(HomePanelTeacher hpt) {
		this.hpt = hpt;
	}



	/**
	 * @return the hps
	 */
	public HomePanelStudent getHps() {
		return hps;
	}



	/**
	 * @param hps the hps to set
	 */
	public void setHps(HomePanelStudent hps) {
		this.hps = hps;
	}
	
	
}
