/**
 * 
 */
package es.uam.eps.padsof.p4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import es.uam.eps.padsof.p3.course.Course;
import es.uam.eps.padsof.p3.course.CourseElement;
import es.uam.eps.padsof.p3.course.Note;
import es.uam.eps.padsof.p3.course.Unit;
import es.uam.eps.padsof.p3.educagram.Educagram;
import es.uam.eps.padsof.p3.exercise.Exercise;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.HomePanelTeacher;
import es.uam.eps.padsof.p4.inter.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;

/**
 * @author Miguel
 *
 */
public class CourseStudentPanelController implements ActionListener, TreeSelectionListener{
	private static final long serialVersionUID = 1L;
	
	private CourseStudentPanel view;
	private Educagram edu = Educagram.getInstance();
	private Course course;
	private Object nodo;
	private Object padre;
	public CourseStudentPanelController(CourseStudentPanel view, Course course) {
		this.view = view;
		this.course = course;
		this.nodo = null;
		this.padre = null;
		
		List<CourseElement> courelem = course.getCourseElements();
		List<CourseElement> subunits = new ArrayList<CourseElement>();
		List<CourseElement> notes = new ArrayList<CourseElement>();
		List<CourseElement> exercises = new ArrayList<CourseElement>();
		Unit u;
		
		for(CourseElement aux1: courelem){
			if(aux1 instanceof Unit){
				for(CourseElement aux2: courelem){
					if(aux2 instanceof Unit){
						if(aux1 != aux2){
							u = (Unit)aux2;
							if(u.getCourseElements().contains(aux1)){
								subunits.add(aux1);
							}
						}
					}
				}
			}else if(aux1 instanceof Note){
				notes.add(aux1);
			}else if(aux1 instanceof Exercise){
				exercises.add(aux1);
			}
		}
		
		for(CourseElement aux1: courelem){
			if(aux1 instanceof Unit){
				if(((Unit)aux1).isHidden() == false){
					if(subunits.contains(aux1) == false){
						this.view.addUnit((Unit) aux1);
					}
				}
			}
		}
		
		for(CourseElement aux1: courelem){
			if(aux1 instanceof Unit){
				if(((Unit)aux1).isHidden() == false){
					for(CourseElement aux2: subunits){
						if(((Unit)aux2).isHidden() == false){
							if(((Unit)aux1).getCourseElements().contains(aux2)){
								this.view.addSubunit((Unit)aux2, (Unit)aux1);
							}
						}
					}
					if(subunits.contains(aux1) == false){
						for(CourseElement aux2: notes){
							if(((Note)aux2).isHidden() == false){
								if(((Unit)aux1).getCourseElements().contains(aux2)){
									this.view.addNote((Note)aux2, (Unit)aux1);
								}
							}
						}
						for(CourseElement aux2: exercises){
							if(((Exercise)aux2).isHidden() == false){
								if(((Unit)aux1).getCourseElements().contains(aux2)){
									this.view.addExercise((Exercise)aux2, (Unit)aux1);
								}
							}
						}
					}
				}
			}
		}
		for(CourseElement aux1: subunits){
			if(((Unit)aux1).isHidden() == false){
				for(CourseElement aux2: notes){
					if(((Note)aux2).isHidden() == false){
						if(((Unit)aux1).getCourseElements().contains(aux2)){
							this.view.addNote((Note)aux2, (Unit)aux1);
						}
					}
				}
				for(CourseElement aux2: exercises){
					if(((Exercise)aux2).isHidden() == false){
						if(((Unit)aux1).getCourseElements().contains(aux2)){
							this.view.addExercise((Exercise)aux2, (Unit)aux1);
						}
					}
				}
			}
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		Student current = (Student)edu.getCurrentUser();
		ArrayList<Course> enrolled = (ArrayList<Course>) current.getEnrolledCourses();
		ArrayList<Application> applied = (ArrayList<Application>) current.getAppliedCourses();
		ArrayList<Course> forapply = new ArrayList<Course>();
		ArrayList<Course> expelled = (ArrayList<Course>) current.getExpelledCourses();
		ArrayList<String> enrNames = new ArrayList<String>();
		ArrayList<String> appNames = new ArrayList<String>();
		ArrayList<String> foraNames = new ArrayList<String>();
		ArrayList<String> expNames = new ArrayList<String>();
		int flag = 0;
		
		for(Course aux1: edu.getCourses()){
			flag = 0;
			if(!enrolled.contains(aux1)){
				for(Application aux2: applied){
					if(aux1.equals(aux2.getCourse())){
						flag = 1;
					}
				}
				if(expelled.contains(aux1)){
					flag = 1;
				}
				if(flag == 0){
					forapply.add(aux1);
				}
			}
		}
		
		
		for(Course c: enrolled){
			enrNames.add(c.getTitle());
		}
		for(Application a: applied){
			appNames.add(a.getCourse().getTitle());
		}
		for(Course c: forapply){
			foraNames.add(c.getTitle());
		}
		for(Course c: expelled){
			expNames.add(c.getTitle());
		}
		
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
		}else if(source == this.view.getSearchCour()){
			MainFrame.getInstance().setScsp(new SearchCourStudentPanel(current.getName(), enrNames, foraNames, appNames, expNames));
			newview = MainFrame.getInstance().getScsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getGo()){
			String name = this.view.getListCourses().getSelectedItem().toString();
			if(name == null){
				return;
			}
			Course course = edu.searchCourse(name);
			MainFrame.getInstance().setCsp(new CourseStudentPanel(current.getName(), enrNames, course), course);
			newview = MainFrame.getInstance().getCsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
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
			System.out.println("Course " + ((Course)source).getDesc());
			System.out.println("Course 2 " + ((Course)source).getTitle());
			this.nodo = null;
			this.padre = null;
			/*this.view.setDescription(((Course)source).getDesc());*/
			this.view.getDesc().setText(((Course)source).getTitle() + ":\n" + ((Course)source).getDesc());
			this.view.getDesc().repaint();
			this.view.getDesc().revalidate();
			
			this.view.getOtherButtons().setVisible(false);
			this.view.getExerReady().setVisible(false);
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			this.view.getExerReady().validate();
			this.view.getExerReady().repaint();
			
			return;
		}else if(source instanceof Unit){
			System.out.println("Unit o subunit");
			this.nodo = (CourseElement) source;
			this.padre = null;
			/*this.view.setDescription(((Unit)source).getDesc());*/
			this.view.getDesc().setText(((Unit)source).getTitle() + ":\n" + ((Unit)source).getDesc());
			this.view.getDesc().revalidate();
			
			this.view.getOtherButtons().setVisible(false);
			this.view.getExerReady().setVisible(false);
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			this.view.getExerReady().validate();
			this.view.getExerReady().repaint();
			
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
			
			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(true);
			this.view.getTake().setVisible(false);
			this.view.getMarks().setVisible(false);
			
			this.view.getExerReady().setVisible(false);
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			this.view.getExerReady().validate();
			this.view.getExerReady().repaint();
			
			this.view.validate();
			this.view.repaint();
			return;
		}else if(source instanceof Exercise){
			this.nodo = (CourseElement) source;
			this.padre = (CourseElement) parent;
			/*this.view.setDescription(((Exercise)source).getDesc());*/
			this.view.getDesc().setText(((Exercise)source).getTitle() + ":\n" + ((Exercise)source).getDesc());
			this.view.getDesc().revalidate();
			
			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(false);
			this.view.getTake().setVisible(true);
			this.view.getMarks().setVisible(true);
			
			this.view.getExerReady().setVisible(true);
			
			this.view.getOtherButtons().validate();
			this.view.getOtherButtons().repaint();
			this.view.getExerReady().validate();
			this.view.getExerReady().repaint();
			
			this.view.validate();
			this.view.repaint();
			
		
			return;
		}
	}
}