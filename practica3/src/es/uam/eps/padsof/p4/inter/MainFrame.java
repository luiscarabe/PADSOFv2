/**
 * 
 */
package es.uam.eps.padsof.p4.inter;


import java.awt.*;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.controllers.*;

import java.util.*;


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
	
	private LoginPanel lp = new LoginPanel();
	private HomePanelTeacher hpt = new HomePanelTeacher((ArrayList<Course>)Educagram.getInstance().getCourses());
	private HomePanelStudent hps;
	
	//estos dos a lo mejor se pueden hacer en el controller "anterior" check this.constructor
	private LoginPanelController lpc = new LoginPanelController(lp);
	private HomePanelTeacherController hptc = new HomePanelTeacherController(hpt);
	
	private HomePanelStudentController hpsc;
	
	
	/**
	 * Private constructor of Educagram, it creates an instance of the
	 * several ArrayLists
	 */
	
	private MainFrame(String name){
		super(name);
		this.lp.setController(lpc);
		this.hpt.setController(hptc);
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
		this.hpsc = new HomePanelStudentController(hps);
		hps.setController(this.hpsc);
		this.hps = hps;
	}
	
	
}
