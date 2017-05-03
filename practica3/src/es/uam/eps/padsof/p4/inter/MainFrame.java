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
	
	/* Panels */
	private LoginPanel lp = new LoginPanel();
	private HomePanelTeacher hpt;
	private HomePanelStudent hps;
	private CreateCoursePanel ccp;
	private CreateNotePanel cnp;
	private SearchCourStudentPanel scsp;
	private CourseStudentPanel csp;
	private AppliedCourPanel acp;
	private NotAppliedCourPanel nacp;
	private CourseTeacherPanel ctp;
	private StudentsOfCourPanel socp;
	private SearchCourTeacherPanel sctp;
	
	/* Controllers */
	private LoginPanelController lpc = new LoginPanelController(lp);
	private HomePanelTeacherController hptc;
	private HomePanelStudentController hpsc;
	private CreateCoursePanelController ccpc;
	private CreateNotePanelController cnpc;
	private SearchCourStudentPanelController scspc;
	private CourseStudentPanelController cspc;
	private AppliedCourPanelController acpc;
	private NotAppliedCourPanelController nacpc;
	private CourseTeacherPanelController ctpc;
	private StudentsOfCourPanelController socpc;
	private SearchCourTeacherPanelController sctpc;
	
	
	



	/**
	 * Private constructor of Educagram, it creates an instance of the
	 * several ArrayLists
	 */
	
	private MainFrame(String name){
		super(name);
		this.lp.setController(lpc);
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
		this.hptc = new HomePanelTeacherController(hpt);
		hpt.setController(this.hptc);
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



	/**
	 * @return the ccp
	 */
	public CreateCoursePanel getCcp() {
		return ccp;
	}



	/**
	 * @param ccp the ccp to set
	 */
	public void setCcp(CreateCoursePanel ccp) {
		this.ccpc = new CreateCoursePanelController(ccp);
		ccp.setController(this.ccpc);
		this.ccp = ccp;
	}



	/**
	 * @return the cnp
	 */
	public CreateNotePanel getCnp() {
		return cnp;
	}



	/**
	 * @param cnp the cnp to set
	 */
	public void setCnp(CreateNotePanel cnp) {
		this.cnpc = new CreateNotePanelController(cnp);
		cnp.setController(this.cnpc);
		this.cnp = cnp;
	}



	/**
	 * @return the scsp
	 */
	public SearchCourStudentPanel getScsp() {
		return scsp;
	}



	/**
	 * @param scsp the scsp to set
	 */
	public void setScsp(SearchCourStudentPanel scsp) {
		this.scspc = new SearchCourStudentPanelController(scsp);
		scsp.setController(this.scspc);
		this.scsp = scsp;
	}



	/**
	 * @return the csp
	 */
	public CourseStudentPanel getCsp() {
		return csp;
	}



	/**
	 * @param csp the csp to set
	 */
	public void setCsp(CourseStudentPanel csp, Course course) {
		this.cspc = new CourseStudentPanelController(csp, course);
		csp.setController(this.cspc);
		this.csp = csp;
	}
	
	/**
	 * @return the acp
	 */
	public AppliedCourPanel getAcp() {
		return acp;
	}



	/**
	 * @param acp the acp to set
	 */
	public void setAcp(AppliedCourPanel acp, Course course) {
		this.acpc = new AppliedCourPanelController(acp, course);
		acp.setController(this.acpc);
		this.acp = acp;
	}



	/**
	 * @return the nacp
	 */
	public NotAppliedCourPanel getNacp() {
		return nacp;
	}



	/**
	 * @param nacp the nacp to set
	 */
	public void setNacp(NotAppliedCourPanel nacp, Course course) {
		this.nacpc = new NotAppliedCourPanelController(nacp, course);
		nacp.setController(this.nacpc);
		this.nacp = nacp;
	}



	/**
	 * @return the ctp
	 */
	public CourseTeacherPanel getCtp() {
		return ctp;
	}



	/**
	 * @param ctp the ctp to set
	 */
	public void setCtp(CourseTeacherPanel ctp, Course course) {
		this.ctpc = new CourseTeacherPanelController(ctp, course);
		ctp.setController(this.ctpc);
		this.ctp = ctp;
	}



	/**
	 * @return the socp
	 */
	public StudentsOfCourPanel getSocp() {
		return socp;
	}



	/**
	 * @param socp the socp to set
	 */
	public void setSocp(StudentsOfCourPanel socp, Course course) {
		this.socpc = new StudentsOfCourPanelController(socp, course);
		socp.setController(this.socpc);
		this.socp = socp;
	}



	/**
	 * @return the sctp
	 */
	public SearchCourTeacherPanel getSctp() {
		return sctp;
	}



	/**
	 * @param sctp the sctp to set
	 */
	public void setSctp(SearchCourTeacherPanel sctp) {
		this.sctpc = new SearchCourTeacherPanelController(sctp);
		sctp.setController(this.sctpc);
		this.sctp = sctp;
	}
	
	
	
}