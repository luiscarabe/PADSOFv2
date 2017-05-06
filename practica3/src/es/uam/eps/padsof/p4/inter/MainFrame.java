/**
 * 
 */
package es.uam.eps.padsof.p4.inter;


import java.awt.*;

import javax.swing.*;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.exercise.Question;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p4.controllers.*;
import es.uam.eps.padsof.p4.inter.courseStudent.AppliedCourPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.NotAppliedCourPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.ViewNoteStudentPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateCoursePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateNotePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateSubUnitPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.CreateUnitPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifyCoursePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifyNotePanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifySubunitPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ModifyUnitPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.SearchCourTeacherPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.StudentsOfCourPanel;
import es.uam.eps.padsof.p4.inter.courseTeacher.ViewNoteTeacherPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionOTPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.AddQuestionUQPanel;
import es.uam.eps.padsof.p4.inter.exerciseStudent.CreateExercisePanel;

import java.util.*;
import java.util.List;


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
	private ModifyCoursePanel mcp;
	private CreateUnitPanel cup;
	private CreateSubUnitPanel csup;
	private ModifyUnitPanel mup;
	private ModifySubunitPanel msup;
	private ModifyNotePanel mnp;
	private ViewNoteTeacherPanel vntp;
	private ViewNoteStudentPanel vnsp;
	private CreateExercisePanel cep;
	private AddQuestionTFPanel aqtfp;
	private AddQuestionOTPanel aqotp;
	private AddQuestionUQPanel aquqp;
	
	
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
	private ModifyCoursePanelController mcpc;
	private CreateUnitPanelController cupc;
	private CreateSubUnitPanelController csupc;
	private ModifyUnitPanelController mupc;
	private ModifySubUnitPanelController msupc;
	private ModifyNotePanelController mnpc;
	private ViewNoteTeacherPanelController vntpc;
	private ViewNoteStudentPanelController vnspc;
	private CreateExercisePanelController cepc;
	private AddQuestionTFPanelController aqtfpc;
	private AddQuestionOTPanelController aqotpc;
	private AddQuestionUQPanelController aquqpc;
	
	
	



	



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
	public void setCnp(CreateNotePanel cnp, Course course, Unit unit) {
		this.cnpc = new CreateNotePanelController(cnp, course, unit);
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



	/**
	 * @return the mcp
	 */
	public ModifyCoursePanel getMcp() {
		return mcp;
	}



	/**
	 * @param mcp the mcp to set
	 */
	public void setMcp(ModifyCoursePanel mcp, Course course) {
		this.mcpc = new ModifyCoursePanelController(mcp, course);
		mcp.setController(this.mcpc);
		this.mcp = mcp;
	}
	
	/**
	 * @return the cup
	 */
	public CreateUnitPanel getCup() {
		return cup;
	}



	/**
	 * @param cup the cup to set
	 */
	public void setCup(CreateUnitPanel cup, Course course) {
		this.cupc = new CreateUnitPanelController(cup, course);
		cup.setController(this.cupc);
		this.cup = cup;
	}



	/**
	 * @return the csup
	 */
	public CreateSubUnitPanel getCsup() {
		return csup;
	}



	/**
	 * @param csup the csup to set
	 */
	public void setCsup(CreateSubUnitPanel csup, Course course, Unit unit) {
		this.csupc = new CreateSubUnitPanelController(csup, course, unit);
		csup.setController(this.csupc);
		this.csup = csup;
	}



	/**
	 * @return the mup
	 */
	public ModifyUnitPanel getMup() {
		return mup;
	}



	/**
	 * @param mup the mup to set
	 */
	public void setMup(ModifyUnitPanel mup, Course course, Unit unit) {
		this.mupc = new ModifyUnitPanelController(mup, course, unit);
		mup.setController(this.mupc);
		this.mup = mup;
	}



	/**
	 * @return the msup
	 */
	public ModifySubunitPanel getMsup() {
		return msup;
	}



	/**
	 * @param msup the msup to set
	 */
	public void setMsup(ModifySubunitPanel msup, Course course, Unit subunit) {
		this.msupc = new ModifySubUnitPanelController(msup, course, subunit);
		msup.setController(this.msupc);
		this.msup = msup;
	}



	/**
	 * @return the mnp
	 */
	public ModifyNotePanel getMnp() {
		return mnp;
	}



	/**
	 * @param mnp the mnp to set
	 */
	public void setMnp(ModifyNotePanel mnp, Course course, Note note) {
		this.mnpc = new ModifyNotePanelController(mnp, course, note);
		mnp.setController(this.mnpc);
		this.mnp = mnp;
	}



	/**
	 * @return the vntp
	 */
	public ViewNoteTeacherPanel getVntp() {
		return vntp;
	}



	/**
	 * @param vntp the vntp to set
	 */
	public void setVntp(ViewNoteTeacherPanel vntp) {
		this.vntpc = new ViewNoteTeacherPanelController(vntp);
		vntp.setController(this.vntpc);
		this.vntp = vntp;
	}



	/**
	 * @return the vnsp
	 */
	public ViewNoteStudentPanel getVnsp() {
		return vnsp;
	}



	/**
	 * @param vnsp the vnsp to set
	 */
	public void setVnsp(ViewNoteStudentPanel vnsp) {
		this.vnspc = new ViewNoteStudentPanelController(vnsp);
		vnsp.setController(this.vnspc);
		this.vnsp = vnsp;
	}



	/**
	 * @return the cep
	 */
	public CreateExercisePanel getCep() {
		return cep;
	}



	/**
	 * @param cep the cep to set
	 */
	public void setCep(CreateExercisePanel cep, Course course, Unit unit) {
		this.cepc = new CreateExercisePanelController(cep, course, unit);
		cep.setController(this.cepc);
		this.cep = cep;
	}



	/**
	 * @return the aqtfp
	 */
	public AddQuestionTFPanel getAqtfp() {
		return aqtfp;
	}



	/**
	 * @param aqtfp the aqtfp to set
	 */
	public void setAqtfp(AddQuestionTFPanel aqtfp, Course course, List<Question> questions, boolean eqValue) {
		this.aqtfpc = new AddQuestionTFPanelController(aqtfp, course, questions, eqValue);
		aqtfp.setController(this.aqtfpc);
		this.aqtfp = aqtfp;
	}



	/**
	 * @return the aqotp
	 */
	public AddQuestionOTPanel getAqotp() {
		return aqotp;
	}



	/**
	 * @param aqotp the aqotp to set
	 */
	public void setAqotp(AddQuestionOTPanel aqotp, Course course, List<Question> questions, boolean eqValue) {
		this.aqotpc = new AddQuestionOTPanelController(aqotp, course, questions, eqValue);
		aqotp.setController(this.aqotpc);
		this.aqotp = aqotp;
	}



	/**
	 * @return the aquqp
	 */
	public AddQuestionUQPanel getAquqp() {
		return aquqp;
	}



	/**
	 * @param aquqp the aquqp to set
	 */
	public void setAquqp(AddQuestionUQPanel aquqp, Course course, List<Question> questions, boolean eqValue) {
		this.aquqpc = new AddQuestionUQPanelController(aquqp, course, questions, eqValue);
		aquqp.setController(this.aquqpc);
		this.aquqp = aquqp;
	}
	
	
	
}