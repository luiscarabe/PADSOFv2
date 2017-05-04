/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.CourseElement;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.CourseTeacherPanel;
import es.uam.eps.padsof.p4.inter.CreateSubUnitPanel;
import es.uam.eps.padsof.p4.inter.CreateUnitPanel;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.ModifyCoursePanel;
import es.uam.eps.padsof.p4.inter.ModifyUnitPanel;
import es.uam.eps.padsof.p4.inter.SearchCourStudentPanel;
import es.uam.eps.padsof.p4.inter.StudentsOfCourPanel;

/**
 * @author Miguel
 *
 */
public class CourseTeacherPanelController implements ActionListener, TreeSelectionListener{
	private static final long serialVersionUID = 1L;
	
	private CourseTeacherPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Object nodo;
	private Object padre;
	public CourseTeacherPanelController(CourseTeacherPanel view, Course course) {
		this.view = view;
		this.course = course;
		this.nodo = null;
		this.padre = null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		
		if(source == this.view.getSignOut()){
			try{
				
				Educagram.getInstance().signOut();
				
				
				newview = MainFrame.getInstance().getLp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}else if(source == this.view.getStudentsButton()){
			ArrayList<String> enrNames = new ArrayList<String>();
			ArrayList<String> expNames = new ArrayList<String>();
			ArrayList<String> appNames = new ArrayList<String>();
			ArrayList<Application> app = (ArrayList<Application>) course.getApplications();
			
			
			ArrayList<Student> enr = (ArrayList<Student>) course.getEnrolledStudents();
			for(Student aux: enr){
				enrNames.add(aux.getName());
			}
			
			enr = (ArrayList<Student>) course.getExpelledStudents();
			for(Student aux: enr){
				expNames.add(aux.getName());
			}
			
			for(Application aux: app){
				appNames.add(aux.getAppliedStudent().getName());
			}
			
			MainFrame.getInstance().setSocp(new StudentsOfCourPanel(course.getTitle(), enrNames, expNames, appNames), course);
			newview = MainFrame.getInstance().getSocp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getGo()){
			ArrayList<String> allNames = new ArrayList<String>();
			for(Course aux : edu.getCourses()){
				allNames.add(aux.getTitle());
			}
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			Course course = edu.searchCourse(name);
			MainFrame.getInstance().setCtp(new CourseTeacherPanel( course, allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getCreateUnit()){
			MainFrame.getInstance().setCup(new CreateUnitPanel(), this.course);
			newview = MainFrame.getInstance().getCup();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getCreateSubunit()){
			MainFrame.getInstance().setCsup(new CreateSubUnitPanel(), this.course, (Unit) this.nodo);
			newview = MainFrame.getInstance().getCsup();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getDelete()){
			if(this.padre == null){
				this.view.removeUnit((Unit) this.nodo);
				this.course.deleteUnit((Unit) this.nodo);
				
				
				this.view.getCourTree().revalidate();
				this.view.getCourTree().repaint();
				this.view.getDesc().setText(this.course.getTitle() + ":\n" + this.course.getDesc());
				this.view.getDesc().repaint();
				this.view.getDesc().revalidate();
				
				this.view.getCommonButtons().setVisible(true);
				this.view.getEdit().setVisible(true);
				this.view.getDelete().setVisible(false);
				this.view.getHide().setVisible(false);
				
				this.view.getUnitButtons().setVisible(false);
				
				this.view.getOtherButtons().setVisible(false);
				
				this.view.getCommonButtons().validate();
				this.view.getCommonButtons().repaint();
				
				this.view.getUnitButtons().validate();
				this.view.getUnitButtons().repaint();
				
				this.view.getOtherButtons().validate();
				this.view.getOtherButtons().repaint();
				return;
			}
			if(this.padre instanceof Unit){
				this.view.removeSubunit((Unit) this.nodo, (Unit) this.padre);
				((Unit) this.padre).deleteSubUnit((Unit) this.nodo);
				
				
				this.view.getCourTree().revalidate();
				this.view.getCourTree().repaint();
				this.view.getDesc().setText(this.course.getTitle() + ":\n" + this.course.getDesc());
				this.view.getDesc().repaint();
				this.view.getDesc().revalidate();
				
				this.view.getCommonButtons().setVisible(true);
				this.view.getEdit().setVisible(true);
				this.view.getDelete().setVisible(false);
				this.view.getHide().setVisible(false);
				
				this.view.getUnitButtons().setVisible(false);
				
				this.view.getOtherButtons().setVisible(false);
				
				this.view.getCommonButtons().validate();
				this.view.getCommonButtons().repaint();
				
				this.view.getUnitButtons().validate();
				this.view.getUnitButtons().repaint();
				
				this.view.getOtherButtons().validate();
				this.view.getOtherButtons().repaint();
				return;
			}
		}else if(source == this.view.getEdit()){
			if(this.nodo instanceof Course || this.nodo == null){
				MainFrame.getInstance().setMcp(new ModifyCoursePanel( course.getTitle(), course.getDesc()), course);
				newview = MainFrame.getInstance().getMcp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}else if(this.nodo instanceof Unit){
				if(this.padre == null){
					MainFrame.getInstance().setMup(new ModifyUnitPanel(((Unit) this.nodo).getTitle(), ((Unit) this.nodo).getDesc()), course, ((Unit) this.nodo));
					newview = MainFrame.getInstance().getMup();
					MainFrame.getInstance().setContentPane(newview);
					newview.setVisible(true);
					view.setVisible(false);
					return;
				}
			}
		}
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		/*Object source = ((DefaultTreeModel)this.view.getCourseModel()).get;*/
		if(e.getNewLeadSelectionPath() == null){
			return;
		}
		Object source = ((DefaultMutableTreeNode)e.getNewLeadSelectionPath().getLastPathComponent()).getUserObject();
		Object parent = null;
		if(source instanceof CourseElement){
			parent = ((DefaultMutableTreeNode)((DefaultMutableTreeNode)e.getNewLeadSelectionPath().getLastPathComponent()).getParent()).getUserObject();
		}
		System.out.println("Holap");
		System.out.println(source.getClass());
		System.out.println(source.toString());
		
		System.out.println(source instanceof Course);
		if(source instanceof Course){
			System.out.println("Course" + ((Course)source).getDesc());
			this.nodo = null;
			this.padre = null;
			/*this.view.setDescription(((Course)source).getDesc());*/
			this.view.getDesc().setText(((Course)source).getTitle() + ":\n" + ((Course)source).getDesc());
			this.view.getDesc().repaint();
			this.view.getDesc().revalidate();
			
			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(false);
			this.view.getHide().setVisible(false);
			
			this.view.getUnitButtons().setVisible(false);
			
			this.view.getOtherButtons().setVisible(false);
			
			this.view.getCommonButtons().validate();
			this.view.getCommonButtons().repaint();
			
			this.view.getUnitButtons().validate();
			this.view.getUnitButtons().repaint();
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			
			return;
		}else if(source instanceof Unit){
			System.out.println("Unit o subunit");
			this.nodo = (CourseElement) source;
			this.padre = null;
			/*this.view.setDescription(((Unit)source).getDesc());*/
			this.view.getDesc().setText(((Unit)source).getTitle() + ":\n" + ((Unit)source).getDesc());
			this.view.getDesc().revalidate();
			
			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(true);
			this.view.getHide().setVisible(true);
			
			this.view.getUnitButtons().setVisible(true);
			
			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(false);
			if(parent instanceof Unit){
				this.padre = (CourseElement) parent;
				this.view.getCreateSubunit().setVisible(false);
			}else{
				this.view.getCreateSubunit().setVisible(true);
			}
			this.view.getStats().setVisible(false);
			
			this.view.getCommonButtons().validate();
			this.view.getCommonButtons().repaint();
			
			this.view.getUnitButtons().validate();
			this.view.getUnitButtons().repaint();
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			
			this.view.validate();
			this.view.repaint();
			return;
		}else if(source instanceof Note){
			System.out.println("Note");
			
			this.nodo = (CourseElement) source;
			this.padre = (CourseElement) parent;
			/*this.view.setDescription(((Note)source).getDesc());*/
			this.view.getDesc().setText(((Note)source).getTitle() + ":\n" + ((Note)source).getDesc());
			this.view.getDesc().revalidate();
			
			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(true);
			this.view.getHide().setVisible(true);
			
			this.view.getUnitButtons().setVisible(false);
			
			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(true);
			
			this.view.getCreateSubunit().setVisible(false);
			
			this.view.getStats().setVisible(false);
			
			this.view.getCommonButtons().validate();
			this.view.getCommonButtons().repaint();
			
			this.view.getUnitButtons().validate();
			this.view.getUnitButtons().repaint();
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			
			this.view.validate();
			this.view.repaint();
			return;
		}else if(source instanceof Exercise){
			this.nodo = (CourseElement) source;
			this.padre = (CourseElement) parent;
			/*this.view.setDescription(((Exercise)source).getDesc());*/
			this.view.getDesc().setText(((Exercise)source).getTitle() + ":\n" + ((Exercise)source).getDesc());
			this.view.getDesc().revalidate();
			
			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(true);
			this.view.getHide().setVisible(true);
			
			this.view.getUnitButtons().setVisible(false);
			
			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(false);
			
			this.view.getCreateSubunit().setVisible(false);
			
			this.view.getStats().setVisible(true);
			
			this.view.getCommonButtons().validate();
			this.view.getCommonButtons().repaint();
			
			this.view.getUnitButtons().validate();
			this.view.getUnitButtons().repaint();
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			
			this.view.validate();
			this.view.repaint();
			return;
		}
		
		
	}

}