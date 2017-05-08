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
import es.uam.eps.padsof.p3.stat.CMark;
import es.uam.eps.padsof.p3.stat.CourseStat;
import es.uam.eps.padsof.p3.stat.ExerciseStat;
import es.uam.eps.padsof.p3.user.Application;
import es.uam.eps.padsof.p3.user.Student;
import es.uam.eps.padsof.p4.inter.Educagram.MainFrame;
import es.uam.eps.padsof.p4.inter.courseStudent.CourseStudentPanel;
import es.uam.eps.padsof.p4.inter.courseStudent.SearchCourStudentPanel;
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
import es.uam.eps.padsof.p4.inter.exerciseTeacher.CreateExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.ModifyExercisePanel;
import es.uam.eps.padsof.p4.inter.exerciseTeacher.ModifyQuestionTFPanel;
import es.uam.eps.padsof.p4.inter.stats.CourseMarksPanel;
import es.uam.eps.padsof.p4.inter.stats.ExerciseStatPanel;
import es.uam.eps.padsof.p4.inter.stats.GlobalStatsPanel;

/**
 * @author Miguel
 *
 */
public class CourseTeacherPanelController implements ActionListener, TreeSelectionListener {
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

		List<CourseElement> courelem = course.getCourseElements();
		List<CourseElement> subunits = new ArrayList<CourseElement>();
		List<CourseElement> notes = new ArrayList<CourseElement>();
		List<CourseElement> exercises = new ArrayList<CourseElement>();
		Unit u;

		for (CourseElement aux1 : courelem) {
			if (aux1 instanceof Unit) {
				for (CourseElement aux2 : courelem) {
					if (aux2 instanceof Unit) {
						if (aux1 != aux2) {
							u = (Unit) aux2;
							if (u.getCourseElements().contains(aux1)) {
								subunits.add(aux1);
							}
						}
					}
				}
			} else if (aux1 instanceof Note) {
				notes.add(aux1);
			} else if (aux1 instanceof Exercise) {
				exercises.add(aux1);
			}
		}

		for (CourseElement aux1 : courelem) {
			if (aux1 instanceof Unit) {
				if (subunits.contains(aux1) == false) {
					this.view.addUnit((Unit) aux1);
				}
			}
		}

		for (CourseElement aux1 : courelem) {
			if (aux1 instanceof Unit) {
				for (CourseElement aux2 : subunits) {
					if (((Unit) aux1).getCourseElements().contains(aux2)) {
						this.view.addSubunit((Unit) aux2, (Unit) aux1);
					}
				}
				if (subunits.contains(aux1) == false) {
					for (CourseElement aux2 : notes) {
						if (((Unit) aux1).getCourseElements().contains(aux2)) {
							this.view.addNote((Note) aux2, (Unit) aux1);
						}
					}
					for (CourseElement aux2 : exercises) {
						if (((Unit) aux1).getCourseElements().contains(aux2)) {
							this.view.addExercise((Exercise) aux2, (Unit) aux1);
						}
					}
				}
			}
		}
		for (CourseElement aux1 : subunits) {
			for (CourseElement aux2 : notes) {
				if (((Unit) aux1).getCourseElements().contains(aux2)) {
					this.view.addNote((Note) aux2, (Unit) aux1);
				}
			}
			for (CourseElement aux2 : exercises) {
				if (((Unit) aux1).getCourseElements().contains(aux2)) {
					this.view.addExercise((Exercise) aux2, (Unit) aux1);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		JComponent source = (JComponent) e.getSource();
		ArrayList<String> allNames = new ArrayList<String>();

		if (source == this.view.getSignOut()) {
			try {

				Educagram.getInstance().signOut();

				newview = MainFrame.getInstance().getLp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} else if (source == this.view.getCreateCourse()) {
			MainFrame.getInstance().setCcp(new CreateCoursePanel());
			newview = MainFrame.getInstance().getCcp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if (source == this.view.getGo()) {
			if (this.view.getListCourses().getSelectedItem() == null) {
				return;
			}
			for (Course aux : edu.getCourses()) {
				allNames.add(aux.getTitle());
			}
			String name = this.view.getListCourses().getSelectedItem().toString();
			if (name == null) {
				return;
			}
			Course course = edu.searchCourse(name);
			MainFrame.getInstance().setCtp(new CourseTeacherPanel(course, allNames), course);
			newview = MainFrame.getInstance().getCtp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		} else if (source == this.view.getSearchCour()) {
			for (Course aux : edu.getCourses()) {
				allNames.add(aux.getTitle());
			}
			MainFrame.getInstance().setSctp(new SearchCourTeacherPanel(allNames));
			newview = MainFrame.getInstance().getSctp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
		} else if (source == this.view.getStudentsButton()) {
			ArrayList<String> enrNames = new ArrayList<String>();
			ArrayList<String> expNames = new ArrayList<String>();
			ArrayList<String> appNames = new ArrayList<String>();
			ArrayList<Application> app = (ArrayList<Application>) course.getApplications();

			ArrayList<Student> enr = (ArrayList<Student>) course.getEnrolledStudents();
			for (Student aux : enr) {
				enrNames.add(aux.getName());
			}

			enr = (ArrayList<Student>) course.getExpelledStudents();
			for (Student aux : enr) {
				expNames.add(aux.getName());
			}

			for (Application aux : app) {
				appNames.add(aux.getAppliedStudent().getName());
			}

			MainFrame.getInstance().setSocp(new StudentsOfCourPanel(course.getTitle(), enrNames, expNames, appNames),
					course);
			newview = MainFrame.getInstance().getSocp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if (source == this.view.getCreateUnit()) {
			MainFrame.getInstance().setCup(new CreateUnitPanel(), this.course);
			newview = MainFrame.getInstance().getCup();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if (source == this.view.getCreateSubunit()) {
			MainFrame.getInstance().setCsup(new CreateSubUnitPanel(), this.course, (Unit) this.nodo);
			newview = MainFrame.getInstance().getCsup();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if (source == this.view.getCreateNote()) {
			MainFrame.getInstance().setCnp(new CreateNotePanel(), this.course, (Unit) this.nodo);
			newview = MainFrame.getInstance().getCnp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if (source == this.view.getCreateExer()) {
			MainFrame.getInstance().setCep(new CreateExercisePanel(), this.course, (Unit) this.nodo);
			newview = MainFrame.getInstance().getCep();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if (source == this.view.getDelete()) {
			if (this.padre == null) {
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
			} else if (this.padre instanceof Unit) {
				if (this.nodo instanceof Unit) {
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
				} else if (this.nodo instanceof Note) {
					this.view.removeNote((Note) this.nodo, (Unit) this.padre);
					((Unit) this.padre).deleteNote((Note) this.nodo);

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
				} else if (this.nodo instanceof Exercise) {
					// codigo ejercicio

					return;
				}
			}
		} else if (source == this.view.getEdit()) {
			if (this.nodo instanceof Course || this.nodo == null) {
				MainFrame.getInstance().setMcp(new ModifyCoursePanel(course.getTitle(), course.getDesc()), course);
				newview = MainFrame.getInstance().getMcp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			} else if (this.nodo instanceof Unit) {
				if (this.padre == null) {
					MainFrame.getInstance().setMup(
							new ModifyUnitPanel(((Unit) this.nodo).getTitle(), ((Unit) this.nodo).getDesc()), course,
							((Unit) this.nodo));
					newview = MainFrame.getInstance().getMup();
					MainFrame.getInstance().setContentPane(newview);
					newview.setVisible(true);
					view.setVisible(false);
					return;
				} else if (this.padre instanceof Unit) {
					MainFrame.getInstance().setMsup(
							new ModifySubunitPanel(((Unit) this.nodo).getTitle(), ((Unit) this.nodo).getDesc()), course,
							((Unit) this.nodo));
					newview = MainFrame.getInstance().getMsup();
					MainFrame.getInstance().setContentPane(newview);
					newview.setVisible(true);
					view.setVisible(false);
					return;
				}
			} else if (this.nodo instanceof Note) {
				MainFrame.getInstance().setMnp(new ModifyNotePanel(((Note) this.nodo).getTitle(),
						((Note) this.nodo).getDesc(), ((Note) this.nodo).getText()), course, ((Note) this.nodo));
				newview = MainFrame.getInstance().getMnp();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			} else if (this.nodo instanceof Exercise) {
				MainFrame.getInstance().setMep(new ModifyExercisePanel((Exercise) this.nodo, ((Exercise) this.nodo).isEqValue(), ((Exercise) this.nodo).isHidden(), 
						((Exercise) this.nodo).isRandomness()), course, (Unit)this.padre, (Exercise)this.nodo);
				newview = MainFrame.getInstance().getMep();
				MainFrame.getInstance().setContentPane(newview);
				newview.setVisible(true);
				view.setVisible(false);
				return;
			}
		} else if (source == this.view.getView()) {
			MainFrame.getInstance().setVntp(new ViewNoteTeacherPanel(((Note) this.nodo).getTitle(),
					((Note) this.nodo).getDesc(), ((Note) this.nodo).getText()));
			newview = MainFrame.getInstance().getVntp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		} else if(source == this.view.getGlobalStats()){
			CourseStat del = null;
			List<CMark> cmarks = new ArrayList<CMark>();
			CourseStat cstat = new CourseStat();
			for(Student aux: this.course.getEnrolledStudents()){
				for(CMark aux2: aux.getcMarks()){
					if(aux2.getCourse().equals(this.course)){
						cmarks.add(aux2);
					}
				}
			}
			cstat.setcMarks(cmarks);
			cstat.calculateCstat();
			
			
			MainFrame.getInstance().setGsp(new GlobalStatsPanel(cstat, this.course.getTitle()));
			newview = MainFrame.getInstance().getGsp();
			MainFrame.getInstance().setContentPane(newview);
			newview.setVisible(true);
			view.setVisible(false);
			return;
		}else if(source == this.view.getStats()){
			ExerciseStat es = new ExerciseStat((Exercise)this.nodo);
			es.setAll();
			MainFrame.getInstance().setEsp(new ExerciseStatPanel(es));
			return;
		}else if (source == this.view.getHide()) {
			if (this.nodo instanceof Unit) {
				if (this.padre == null) {
					if (this.view.getHide().isSelected() == true) {
						((Unit) this.nodo).unitHide();
						this.view.getHide().setSelected(true);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
					} else {
						((Unit) this.nodo).setHidden(false);
						;
						this.view.getHide().setSelected(false);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
					}
					return;
				} else if (this.padre instanceof Unit) {
					if (this.view.getHide().isSelected() == true) {
						((Unit) this.nodo).unitHide();
						this.view.getHide().setSelected(true);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
						return;
					} else if (this.view.getHide().isSelected() == false) {
						if (((Unit) this.padre).isHidden() == true) {
							((Unit) this.nodo).unitHide();
							this.view.getHide().setSelected(true);
							this.view.getHide().revalidate();
							this.view.getHide().repaint();
						} else {
							((Unit) this.nodo).setHidden(false);
							;
							this.view.getHide().setSelected(false);
							this.view.getHide().revalidate();
							this.view.getHide().repaint();
						}
						return;
					} else {
						return;
					}
				}
			} else if (this.nodo instanceof Note) {
				if (this.view.getHide().isSelected() == true) {
					((Note) this.nodo).setHidden(true);
					this.view.getHide().setSelected(true);
					this.view.getHide().revalidate();
					this.view.getHide().repaint();
					return;
				} else if (this.view.getHide().isSelected() == false) {
					if (((Unit) this.padre).isHidden() == true) {
						((Note) this.nodo).setHidden(true);
						this.view.getHide().setSelected(true);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
					} else {
						((Note) this.nodo).setHidden(false);
						;
						this.view.getHide().setSelected(false);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
					}
					return;
				} else {
					return;
				}
			} else if (this.nodo instanceof Exercise) {
				if (this.view.getHide().isSelected() == true) {
					((Exercise) this.nodo).setHidden(true);
					this.view.getHide().setSelected(true);
					this.view.getHide().revalidate();
					this.view.getHide().repaint();
					return;
				} else if (this.view.getHide().isSelected() == false) {
					if (((Unit) this.padre).isHidden() == true) {
						((Exercise) this.nodo).setHidden(true);
						this.view.getHide().setSelected(true);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
					} else {
						((Exercise) this.nodo).setHidden(false);
						;
						this.view.getHide().setSelected(false);
						this.view.getHide().revalidate();
						this.view.getHide().repaint();
					}
					return;
				} else {
					return;
				}
			}
			return;
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		JPanel newview;
		/*
		 * Object source = ((DefaultTreeModel)this.view.getCourseModel()).get;
		 */
		if (e.getNewLeadSelectionPath() == null) {
			return;
		}
		Object source = ((DefaultMutableTreeNode) e.getNewLeadSelectionPath().getLastPathComponent()).getUserObject();
		Object parent = null;
		if (source instanceof CourseElement) {
			parent = ((DefaultMutableTreeNode) ((DefaultMutableTreeNode) e.getNewLeadSelectionPath()
					.getLastPathComponent()).getParent()).getUserObject();
		}
		System.out.println("Holap");
		System.out.println(source.getClass());
		System.out.println(source.toString());

		System.out.println(source instanceof Course);
		if (source instanceof Course) {
			System.out.println("Course" + ((Course) source).getDesc());
			this.nodo = null;
			this.padre = null;
			/* this.view.setDescription(((Course)source).getDesc()); */
			this.view.getDesc().setText(((Course) source).getTitle() + ":\n" + ((Course) source).getDesc());
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
		} else if (source instanceof Unit) {
			System.out.println("Unit o subunit");
			this.nodo = (CourseElement) source;
			this.padre = null;
			/* this.view.setDescription(((Unit)source).getDesc()); */
			this.view.getDesc().setText(((Unit) source).getTitle() + ":\n" + ((Unit) source).getDesc());
			this.view.getDesc().revalidate();

			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(true);
			this.view.getHide().setVisible(true);

			this.view.getUnitButtons().setVisible(true);

			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(false);
			if (parent instanceof Unit) {
				this.padre = (CourseElement) parent;
				this.view.getCreateSubunit().setVisible(false);
				if (((Unit) this.nodo).isHidden() == false && ((Unit) this.padre).isHidden() == true) {
					((Unit) this.nodo).setHidden(true);
					this.view.getHide().setSelected(true);
				} else {
					if (((Unit) this.nodo).isHidden() == true) {
						this.view.getHide().setSelected(true);
					} else {
						this.view.getHide().setSelected(false);
					}
				}

			} else {
				if (((Unit) this.nodo).isHidden() == true) {
					this.view.getHide().setSelected(true);
				} else {
					this.view.getHide().setSelected(false);
				}
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
		} else if (source instanceof Note) {
			System.out.println("Note");

			this.nodo = (CourseElement) source;
			this.padre = (CourseElement) parent;
			/* this.view.setDescription(((Note)source).getDesc()); */
			this.view.getDesc().setText(((Note) source).getTitle() + ":\n" + ((Note) source).getDesc());
			this.view.getDesc().revalidate();

			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(true);
			this.view.getHide().setVisible(true);

			this.view.getUnitButtons().setVisible(false);

			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(true);

			this.view.getCreateSubunit().setVisible(false);

			if (((Note) this.nodo).isHidden() == false && ((Unit) this.padre).isHidden() == true) {
				((Note) this.nodo).setHidden(true);
				this.view.getHide().setSelected(true);
			} else {
				if (((Note) this.nodo).isHidden() == true) {
					this.view.getHide().setSelected(true);
				} else {
					this.view.getHide().setSelected(false);
				}
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
		} else if (source instanceof Exercise) {
			this.nodo = (CourseElement) source;
			this.padre = (CourseElement) parent;
			/* this.view.setDescription(((Exercise)source).getDesc()); */
			this.view.getDesc().setText(((Exercise) source).getTitle() + ":\n" + ((Exercise) source).getDesc());
			this.view.getDesc().revalidate();

			this.view.getCommonButtons().setVisible(true);
			this.view.getEdit().setVisible(true);
			this.view.getDelete().setVisible(true);
			this.view.getHide().setVisible(true);

			this.view.getUnitButtons().setVisible(false);

			this.view.getOtherButtons().setVisible(true);
			this.view.getView().setVisible(false);

			this.view.getCreateSubunit().setVisible(false);

			if (((Exercise) this.nodo).isHidden() == false && ((Unit) this.padre).isHidden() == true) {
				((Exercise) this.nodo).setHidden(true);
				this.view.getHide().setSelected(true);
			} else {
				if (((Exercise) this.nodo).isHidden() == true) {
					this.view.getHide().setSelected(true);
				} else {
					this.view.getHide().setSelected(false);
				}
			}

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