package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.*;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import es.uam.eps.padsof.p3.course.*;
import es.uam.eps.padsof.p3.exercise.Exercise;

public class CourseTeacherPanel extends JPanel{
	//Superior Panel
	private JPanel supPanel = new JPanel();
	private ImageIcon image = new ImageIcon("logov3.png");
	private JLabel imgLabel = new JLabel(image);
	private JLabel homeLabel = new JLabel("Course page");
	private JLabel courses = new JLabel("All courses:");
	private JComboBox<String> listCourses;
	private JButton go = new JButton("Go");
	private JButton searchCour = new JButton("Search Course");
	private JButton createCourse = new JButton ("Create Course");
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	private String[] strCourses = {};
	
	private JLabel courseLabel;
	private JTextArea courseDesc;
	private JScrollPane courseDescPane;
	private JButton createUnit = new JButton("Create unit");
	private JButton studentsButton = new JButton("Students");
	private JButton globalStats = new JButton("Global Statistics");
	
	private JPanel commonButtons = new JPanel();
	private JButton delete = new JButton("Delete");
	private JButton edit = new JButton("Edit");
	private JCheckBox hide = new JCheckBox("Hidden");
	
	private JPanel unitButtons = new JPanel();
	private JButton createNote = new JButton("Create note");
	private JButton createExer = new JButton("Create exercise");
	
	private JPanel otherButtons = new JPanel();
	private JButton createSubunit = new JButton("Create subunit");
	private JButton stats = new JButton("View stats");
	private JButton view = new JButton("View Note");
	
	//Tree
	private DefaultMutableTreeNode root;
	private DefaultTreeModel courseModel;
	private JTree courTree;
	private JScrollPane coursePane;
	
	private JTextArea desc;
	private JScrollPane descPane;
	private JLabel descLabel = new JLabel("Description");

	private SpringLayout layout2 = new SpringLayout();
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public CourseTeacherPanel( Course cour, ArrayList<String> allCour){
		this.setVisible(true);
		this.setSize(screenSize.width, screenSize.height);
		this.setLayout(layout2);
		this.setBackground(Color.decode("#98FB98"));
		
		this.supPanel.setVisible(true);
		this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
		this.supPanel.setBackground(Color.decode("#228B22"));
		this.supPanel.setLayout(layout);
		
		int i = 0;
		
		for(String c: allCour){
			strCourses = Arrays.copyOf(strCourses, strCourses.length+1);
			strCourses[i] = c;
			i++;
		}
		
		this.listCourses = new JComboBox<String>(strCourses);
		this.listCourses.setPrototypeDisplayValue("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
		this.listCourses.setSelectedItem(null);
		this.imgLabel.setVisible(true);
		this.imgLabel.setBounds(0,0,200,200);
		
		this.homeLabel.setFont(this.homeLabel.getFont().deriveFont(30f));
		this.professor.setFont(this.professor.getFont().deriveFont(15f));
		this.signOut.setForeground(Color.RED);
	
		this.go.setPreferredSize(new Dimension(53,25));
		
		this.supPanel.add(imgLabel);
		this.supPanel.add(homeLabel);
		this.supPanel.add(courses);
		this.supPanel.add(listCourses);
		this.supPanel.add(searchCour);
		this.supPanel.add(professor);
		this.supPanel.add(signOut);
		this.supPanel.add(createCourse);
		this.supPanel.add(go);
		
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.homeLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
		layout.putConstraint(SpringLayout.WEST, this.homeLabel, 10, SpringLayout.EAST, this.imgLabel);
		
		layout.putConstraint(SpringLayout.NORTH, this.courses, 45, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.courses, 0, SpringLayout.WEST, this.listCourses);
		
		layout.putConstraint(SpringLayout.NORTH, this.listCourses, 40, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.EAST, this.listCourses, 60, SpringLayout.HORIZONTAL_CENTER, this.supPanel);

		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.go, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
		layout.putConstraint(SpringLayout.WEST, this.go, 10, SpringLayout.EAST, this.listCourses);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.searchCour, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
		layout.putConstraint(SpringLayout.WEST, this.searchCour, 10, SpringLayout.EAST, this.go);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.createCourse, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
		layout.putConstraint(SpringLayout.WEST, this.createCourse, 10, SpringLayout.EAST, this.searchCour);
		
		layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.professor, 0, SpringLayout.HORIZONTAL_CENTER, this.signOut);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.signOut, 0, SpringLayout.VERTICAL_CENTER, this.listCourses);
		layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
		
		this.courseLabel = new JLabel(cour.getTitle());
		
		this.root = new DefaultMutableTreeNode(cour);
		this.courseModel = new DefaultTreeModel(this.root);
		this.courTree = new JTree(courseModel);
		this.courTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.courTree.setBackground(this.getBackground());
		this.courTree.setVisible(true);
		this.coursePane = new JScrollPane(this.courTree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.coursePane.setPreferredSize(new Dimension(500,500));
		this.coursePane.setBorder(null);
		this.coursePane.setPreferredSize(new Dimension(500, 600));
		
		Map attributes = this.courseLabel.getFont().getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(attributes));
		this.courseLabel.setFont(this.courseLabel.getFont().deriveFont(30f));
		this.courseDesc = new JTextArea(cour.getDesc());
		this.courseDesc.setEditable(false);
		this.courseDesc.setBackground(this.getBackground());
		this.courseDesc.setLineWrap(true);
		this.courseDesc.setWrapStyleWord(true);
		this.courseDescPane = new JScrollPane(this.courseDesc, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.courseDescPane.setBorder(null);
		this.courseDescPane.setPreferredSize(new Dimension(500, 60));
		
		this.descLabel.setFont(this.descLabel.getFont().deriveFont(attributes));
		
		this.commonButtons.setLayout(new FlowLayout());
		this.commonButtons.setPreferredSize(new Dimension (300,50));
		this.commonButtons.setVisible(true);
		this.commonButtons.setBackground(this.getBackground());
		
		this.hide.setBackground(this.getBackground());

		this.commonButtons.add(this.delete);
		this.commonButtons.add(this.edit);
		this.commonButtons.add(this.hide);

		this.unitButtons.setLayout(new FlowLayout());
		this.unitButtons.setPreferredSize(new Dimension (300,50));
		this.unitButtons.setVisible(true);
		this.unitButtons.setBackground(this.getBackground());
		
		this.unitButtons.add(this.createNote);
		this.unitButtons.add(this.createExer);
		
		this.otherButtons.setLayout(new FlowLayout());
		this.otherButtons.setPreferredSize(new Dimension (400,50));
		this.otherButtons.setVisible(true);
		this.otherButtons.setBackground(this.getBackground());
		
		this.otherButtons.add(this.view);
		this.otherButtons.add(this.createSubunit);
		this.otherButtons.add(this.stats);
		
		this.desc = new JTextArea();
		this.desc.setEditable(false);
		this.desc.setText("cachopo");
		this.desc.setBackground(Color.decode("#D3D3D3"));
		this.desc.setLineWrap(true);
		this.desc.setWrapStyleWord(true);
		this.descPane = new JScrollPane(this.desc, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.descPane.setBorder(null);
		this.descPane.setPreferredSize(new Dimension(500, 300));
		
		this.add(this.descPane);
		this.add(this.supPanel);
		this.add(this.courseLabel);
		this.add(this.courseDescPane);
		this.add(this.studentsButton);
		this.add(this.createUnit);
		this.add(this.coursePane);
		this.add(this.unitButtons);
		this.add(this.commonButtons);
		this.add(this.unitButtons);
		this.add(this.descLabel);
		this.add(this.otherButtons);
		this.add(this.globalStats);
		
		
		layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
		layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.WEST, this.createUnit, 50, SpringLayout.EAST, this.courseLabel);
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.createUnit, 0, SpringLayout.VERTICAL_CENTER, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.WEST, this.courseLabel, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.courseLabel, 10, SpringLayout.SOUTH, this.supPanel);
		
		layout2.putConstraint(SpringLayout.WEST, this.courseDescPane, 10, SpringLayout.WEST, this);
		layout2.putConstraint(SpringLayout.NORTH, this.courseDescPane, 10, SpringLayout.SOUTH, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.studentsButton, 0, SpringLayout.VERTICAL_CENTER, this.createUnit);
		layout2.putConstraint(SpringLayout.WEST, this.studentsButton, 10, SpringLayout.EAST, this.createUnit);
		
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.globalStats, 0, SpringLayout.VERTICAL_CENTER, this.createUnit);
		layout2.putConstraint(SpringLayout.WEST, this.globalStats, 10, SpringLayout.EAST, this.studentsButton);
		
		layout2.putConstraint(SpringLayout.NORTH, this.coursePane, 20, SpringLayout.SOUTH, this.courseDescPane);
		layout2.putConstraint(SpringLayout.WEST, this.coursePane, 0, SpringLayout.WEST, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.NORTH, this.commonButtons, -5, SpringLayout.NORTH, this.studentsButton);
		layout2.putConstraint(SpringLayout.WEST, this.commonButtons, 650, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.NORTH, this.unitButtons, -5, SpringLayout.SOUTH, this.commonButtons);
		layout2.putConstraint(SpringLayout.WEST, this.unitButtons, 0, SpringLayout.WEST, this.commonButtons);
		
		layout2.putConstraint(SpringLayout.NORTH, this.unitButtons, -5, SpringLayout.SOUTH, this.commonButtons);
		layout2.putConstraint(SpringLayout.WEST, this.unitButtons, 0, SpringLayout.WEST, this.commonButtons);
		
		layout2.putConstraint(SpringLayout.NORTH, this.otherButtons, -5, SpringLayout.SOUTH, this.unitButtons);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.otherButtons, 0, SpringLayout.HORIZONTAL_CENTER, this.commonButtons);
			
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.descLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.commonButtons);
		layout2.putConstraint(SpringLayout.NORTH, this.descLabel, 30, SpringLayout.SOUTH, this.otherButtons);

		layout2.putConstraint(SpringLayout.NORTH, this.descPane, 5, SpringLayout.SOUTH, this.descLabel);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.descPane, 0, SpringLayout.HORIZONTAL_CENTER, this.descLabel);
	}
	
	public void addUnit(Unit u){
		DefaultMutableTreeNode aux = new DefaultMutableTreeNode(u);
		this.courseModel.insertNodeInto(aux, this.root, this.courseModel.getChildCount(this.root));
	}
	
	public void removeUnit(Unit u){
		int numNodes = this.courseModel.getChildCount(root);
		DefaultMutableTreeNode aux = null;
		for (int i = 0; i < numNodes; i++){
			if (this.courseModel.getChild(root, i).toString().equals(u.toString())){
				aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
				break;
			}
		}
		this.courseModel.removeNodeFromParent(aux);
		this.courTree.setLeadSelectionPath(new TreePath(root.getPath()));
	}
	
	public void addSubunit(Unit subunit, Unit parentUnit){
		int numNodes = this.courseModel.getChildCount(root);
		DefaultMutableTreeNode aux = null;
		for (int i = 0; i < numNodes; i++){
			if (this.courseModel.getChild(root, i).toString().equals(parentUnit.toString())){
				aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
				break;
			}
		}
			
		DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(subunit);
		this.courseModel.insertNodeInto(subNode, aux, this.courseModel.getChildCount(aux));
	}
	
	public void removeSubunit(Unit subunit, Unit parentUnit){
		int numNodes = this.courseModel.getChildCount(root);
		DefaultMutableTreeNode aux = null;
		DefaultMutableTreeNode aux2 = null;
		for (int i = 0; i < numNodes; i++){
			if (this.courseModel.getChild(root, i).toString().equals(parentUnit.toString())){
				aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
				break;
			}
		}
		numNodes = this.courseModel.getChildCount(aux);
		for (int i = 0; i < numNodes; i++){
			if (this.courseModel.getChild(aux, i).toString().equals(subunit.toString())){
				aux2 = (DefaultMutableTreeNode) this.courseModel.getChild(aux, i);
				break;
			}
		}
		this.courseModel.removeNodeFromParent(aux2);
	}
	
	public void addNote(Note note, Unit u){
		int numNodes = this.courseModel.getChildCount(root);
		int numNodes1 = 0;
		int flag = 0;
		DefaultMutableTreeNode aux = null;
		DefaultMutableTreeNode aux2 = null;
		for (int i = 0; i < numNodes; i++){
			aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
			if (this.courseModel.getChild(root, i).toString().equals(u.toString()))
				break;
			numNodes1 = aux.getChildCount();
			for(int j = 0; j < numNodes1; j++){
				if(this.courseModel.getChild(aux, j).toString().equals(u.toString())){
					aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, j);
					flag = 1;
					break;
				}
			}
			if(flag==1)
				break;
		}
		if(flag == 1)
			this.courseModel.insertNodeInto(new DefaultMutableTreeNode(note), aux2, this.courseModel.getChildCount(aux2));
		else
			this.courseModel.insertNodeInto(new DefaultMutableTreeNode(note), aux, this.courseModel.getChildCount(aux));
	}
	
	public void removeNote(Note note, Unit u){
		int numNodes = this.courseModel.getChildCount(root);
		int numNodes1 = 0;
		int flag = 0;
		DefaultMutableTreeNode aux = null;
		DefaultMutableTreeNode aux2 = null;
		for (int i = 0; i < numNodes; i++){
			aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
			if (this.courseModel.getChild(root, i).toString().equals(u.toString()))
				break;
			numNodes1 = aux.getChildCount();
			for(int j = 0; j < numNodes1; j++){
				if(this.courseModel.getChild(aux, j).toString().equals(u.toString())){
					aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, j);
					flag = 1;
					break;
				}
			}
			if(flag==1)
				break;
		}
		if(flag == 1){
			numNodes1 = aux2.getChildCount();
			for(int i = 0; i < numNodes1; i++){
				if(this.courseModel.getChild(aux2, i).toString().equals(note.toString())){
					aux = (DefaultMutableTreeNode)this.courseModel.getChild(aux2, i);
					break;
				}
			}
			this.courseModel.removeNodeFromParent(aux);
		}
			
		else
			numNodes1 = aux.getChildCount();
			for(int i = 0; i < numNodes1; i++){
				if(this.courseModel.getChild(aux, i).toString().equals(note.toString())){
					aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, i);
					break;
				}
			}
			this.courseModel.removeNodeFromParent(aux2);
	}
	
	public void addExercise(Exercise exer, Unit u){
		int numNodes = this.courseModel.getChildCount(root);
		int numNodes1 = 0;
		int flag = 0;
		DefaultMutableTreeNode aux = null;
		DefaultMutableTreeNode aux2 = null;
		for (int i = 0; i < numNodes; i++){
			aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
			if (this.courseModel.getChild(root, i).toString().equals(u.toString()))
				break;
			numNodes1 = aux.getChildCount();
			for(int j = 0; j < numNodes1; j++){
				if(this.courseModel.getChild(aux, j).toString().equals(u.toString())){
					aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, j);
					flag = 1;
					break;
				}
			}
			if(flag==1)
				break;
		}
		if(flag == 1)
			this.courseModel.insertNodeInto(new DefaultMutableTreeNode(exer), aux2, this.courseModel.getChildCount(aux2));
		else
			this.courseModel.insertNodeInto(new DefaultMutableTreeNode(exer), aux, this.courseModel.getChildCount(aux));
	}
	
	public void removeExercise(Exercise exer, Unit u){
		int numNodes = this.courseModel.getChildCount(root);
		int numNodes1 = 0;
		int flag = 0;
		DefaultMutableTreeNode aux = null;
		DefaultMutableTreeNode aux2 = null;
		for (int i = 0; i < numNodes; i++){
			aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
			if (this.courseModel.getChild(root, i).toString().equals(u.toString()))
				break;
			numNodes1 = aux.getChildCount();
			for(int j = 0; j < numNodes1; j++){
				if(this.courseModel.getChild(aux, j).toString().equals(u.toString())){
					aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, j);
					flag = 1;
					break;
				}
			}
			if(flag==1)
				break;
		}
		
		if(flag == 1){
			numNodes1 = aux2.getChildCount();
			for(int i = 0; i < numNodes1; i++){
				if(this.courseModel.getChild(aux2, i).toString().equals(exer.toString())){
					aux = (DefaultMutableTreeNode)this.courseModel.getChild(aux2, i);
					break;
				}
			}
			this.courseModel.removeNodeFromParent(aux);
		}
			
		else
			numNodes1 = aux.getChildCount();
			for(int i = 0; i < numNodes1; i++){
				if(this.courseModel.getChild(aux, i).toString().equals(exer.toString())){
					aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, i);
					break;
				}
			}
			this.courseModel.removeNodeFromParent(aux2);
	}
	
	public void setDescription(String desc){
		this.desc = new JTextArea(desc);
		this.desc.setEditable(false);
		this.desc.setBackground(Color.decode("#D3D3D3"));
		this.desc.setLineWrap(true);
		this.desc.setWrapStyleWord(true);
		this.descPane = new JScrollPane(this.desc, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.descPane.setBorder(null);
		this.descPane.setPreferredSize(new Dimension(500, 300));
		
		this.add(this.descPane);
		
		layout2.putConstraint(SpringLayout.NORTH, this.descPane, 5, SpringLayout.SOUTH, this.descLabel);
		layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.descPane, 0, SpringLayout.HORIZONTAL_CENTER, this.descLabel);
	}
	
	public void setController(EventListener c){
		this.signOut.addActionListener((ActionListener) c);
		this.studentsButton.addActionListener((ActionListener) c);
		this.go.addActionListener((ActionListener) c);
		this.edit.addActionListener((ActionListener) c);
		this.createUnit.addActionListener((ActionListener) c);
		this.createSubunit.addActionListener((ActionListener) c);
		this.delete.addActionListener((ActionListener) c);
		this.courTree.addTreeSelectionListener((TreeSelectionListener) c);
		/*this.courseModel.addTreeModelListener((TreeModelListener) c);*/
	}
	
	public Object getParent(CourseElement c){
		int numNodes = this.courseModel.getChildCount(root);
		int numNodes1 = 0;
		int numNodes2 = 0;
		DefaultMutableTreeNode aux = null;
		DefaultMutableTreeNode aux2 = null;
		for (int i = 0; i < numNodes; i++){
			aux = (DefaultMutableTreeNode) this.courseModel.getChild(root, i);
			if (this.courseModel.getChild(root, i).toString().equals(c.toString()))
				return this.root;
			numNodes1 = aux.getChildCount();
			for(int j = 0; j < numNodes1; j++){
				aux2 = (DefaultMutableTreeNode)this.courseModel.getChild(aux, j);
				if(this.courseModel.getChild(aux, j).toString().equals(c.toString())){
					return aux;
				}
				numNodes2 = aux2.getChildCount();
				for(int k = 0; k < numNodes2; k++){
					if(this.courseModel.getChild(aux2, k).toString().equals(c.toString())){
						return aux2;
					}
				}
				
			}
		}
		return null;
	}

	/**
	 * @return the supPanel
	 */
	public JPanel getSupPanel() {
		return supPanel;
	}

	/**
	 * @return the image
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * @return the imgLabel
	 */
	public JLabel getImgLabel() {
		return imgLabel;
	}

	/**
	 * @return the homeLabel
	 */
	public JLabel getHomeLabel() {
		return homeLabel;
	}

	/**
	 * @return the courses
	 */
	public JLabel getCourses() {
		return courses;
	}

	/**
	 * @return the listCourses
	 */
	public JComboBox<String> getListCourses() {
		return listCourses;
	}

	/**
	 * @return the go
	 */
	public JButton getGo() {
		return go;
	}

	/**
	 * @return the searchCour
	 */
	public JButton getSearchCour() {
		return searchCour;
	}

	/**
	 * @return the globalStats
	 */
	public JButton getGlobalStats() {
		return globalStats;
	}

	/**
	 * @return the createCourse
	 */
	public JButton getCreateCourse() {
		return createCourse;
	}

	/**
	 * @return the professor
	 */
	public JLabel getProfessor() {
		return professor;
	}

	/**
	 * @return the signOut
	 */
	public JButton getSignOut() {
		return signOut;
	}

	/**
	 * @return the layout
	 */
	public SpringLayout getLayout() {
		return layout;
	}

	/**
	 * @return the strCourses
	 */
	public String[] getStrCourses() {
		return strCourses;
	}

	/**
	 * @return the courseLabel
	 */
	public JLabel getCourseLabel() {
		return courseLabel;
	}

	/**
	 * @return the courseDesc
	 */
	public JTextArea getCourseDesc() {
		return courseDesc;
	}

	/**
	 * @return the courseDescPane
	 */
	public JScrollPane getCourseDescPane() {
		return courseDescPane;
	}

	/**
	 * @return the createUnit
	 */
	public JButton getCreateUnit() {
		return createUnit;
	}

	/**
	 * @return the studentsButton
	 */
	public JButton getStudentsButton() {
		return studentsButton;
	}

	/**
	 * @return the commonButtons
	 */
	public JPanel getCommonButtons() {
		return commonButtons;
	}

	/**
	 * @return the delete
	 */
	public JButton getDelete() {
		return delete;
	}

	/**
	 * @return the edit
	 */
	public JButton getEdit() {
		return edit;
	}

	/**
	 * @return the hide
	 */
	public JCheckBox getHide() {
		return hide;
	}

	/**
	 * @return the unitButtons
	 */
	public JPanel getUnitButtons() {
		return unitButtons;
	}

	/**
	 * @return the createNote
	 */
	public JButton getCreateNote() {
		return createNote;
	}

	/**
	 * @return the createExer
	 */
	public JButton getCreateExer() {
		return createExer;
	}

	/**
	 * @return the otherButtons
	 */
	public JPanel getOtherButtons() {
		return otherButtons;
	}

	/**
	 * @return the createSubunit
	 */
	public JButton getCreateSubunit() {
		return createSubunit;
	}

	/**
	 * @return the stats
	 */
	public JButton getStats() {
		return stats;
	}

	/**
	 * @return the view
	 */
	public JButton getView() {
		return view;
	}

	/**
	 * @return the root
	 */
	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	/**
	 * @return the courseModel
	 */
	public DefaultTreeModel getCourseModel() {
		return courseModel;
	}

	/**
	 * @return the courTree
	 */
	public JTree getCourTree() {
		return courTree;
	}

	/**
	 * @return the coursePane
	 */
	public JScrollPane getCoursePane() {
		return coursePane;
	}

	/**
	 * @return the desc
	 */
	public JTextArea getDesc() {
		return desc;
	}

	/**
	 * @return the descPane
	 */
	public JScrollPane getDescPane() {
		return descPane;
	}

	/**
	 * @return the descLabel
	 */
	public JLabel getDescLabel() {
		return descLabel;
	}

	/**
	 * @return the layout2
	 */
	public SpringLayout getLayout2() {
		return layout2;
	}

	
	
}