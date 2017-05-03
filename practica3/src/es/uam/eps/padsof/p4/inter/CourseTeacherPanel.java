package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import es.uam.eps.padsof.p3.course.*;

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
	private JButton globalStats = new JButton ("Global Statistics");
	private JButton createCourse = new JButton ("Create Course");
	private JLabel professor = new JLabel("Professor");
	private JButton signOut = new JButton("Sign out");
	private SpringLayout layout = new SpringLayout();
	private String[] strCourses = {};
	
	private JLabel courseLabel;
	private JTextArea courseDesc;
	private JScrollPane courseDescPane;
	private JButton studentsButton = new JButton("Students");
	private JButton delete = new JButton("Delete");
	private JButton edit = new JButton("Edit");
	private JButton stats = new JButton("View stats");
	private JButton view = new JButton("View Note");
	private JButton createNote = new JButton("Create note");
	private JButton createExer = new JButton("Create exercise");
	private JButton createSubunit = new JButton("Create subunit");
	private JCheckBox hide = new JCheckBox("Hidden");
	private JPanel allButtons = new JPanel();
	
	//Tree
	private DefaultMutableTreeNode root;
	private DefaultTreeModel courseModel;
	private JTree courTree;
	private JScrollPane coursePane;
	private HashMap<DefaultMutableTreeNode, ArrayList<DefaultMutableTreeNode>> units = new HashMap<DefaultMutableTreeNode, ArrayList<DefaultMutableTreeNode>>();
	

	
	private JButton createUnit = new JButton("Create unit");
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
		layout.putConstraint(SpringLayout.EAST, this.listCourses, 0, SpringLayout.HORIZONTAL_CENTER, this.supPanel);

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
		this.courTree.setBackground(this.getBackground());
		this.courTree.setVisible(true);
		this.coursePane = new JScrollPane(this.courTree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.coursePane.setPreferredSize(new Dimension(500,500));
		this.coursePane.setBorder(null);
		this.coursePane.setPreferredSize(new Dimension(500, 60));
		
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
		
		this.allButtons.setLayout(new FlowLayout());
		this.allButtons.setPreferredSize(new Dimension (450,100));
		this.allButtons.setVisible(true);
		this.allButtons.setBackground(this.getBackground());
		this.hide.setBackground(this.getBackground());
		
		this.allButtons.add(this.createNote);
		this.allButtons.add(this.createExer);
		this.allButtons.add(this.createSubunit);
		this.allButtons.add(this.delete);
		this.allButtons.add(this.edit);
		this.allButtons.add(this.stats);
		this.allButtons.add(this.view);
		
		
		this.add(this.supPanel);
		this.add(this.courseLabel);
		this.add(this.courseDescPane);
		this.add(this.studentsButton);
		this.add(this.createUnit);
		this.add(this.coursePane);
		this.add(this.allButtons);
		this.add(this.hide);
		
		
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
		
		layout2.putConstraint(SpringLayout.NORTH, this.coursePane, 20, SpringLayout.SOUTH, this.courseDescPane);
		layout2.putConstraint(SpringLayout.WEST, this.coursePane, 0, SpringLayout.WEST, this.courseLabel);
		
		layout2.putConstraint(SpringLayout.NORTH, this.allButtons, -5, SpringLayout.NORTH, this.studentsButton);
		layout2.putConstraint(SpringLayout.WEST, this.allButtons, 600, SpringLayout.WEST, this);
		
		layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.hide, -20, SpringLayout.VERTICAL_CENTER, this.allButtons);
		layout2.putConstraint(SpringLayout.WEST, this.hide, 5, SpringLayout.EAST, this.allButtons);
		
	}
	
	public void addUnit(Unit u){
		this.units.put(new DefaultMutableTreeNode(u), null);
		DefaultMutableTreeNode aux = null;
		for(Map.Entry<DefaultMutableTreeNode, ArrayList<DefaultMutableTreeNode>> e : this.units.entrySet()){
			aux = e.getKey();
			if (aux.equals(u))
				break;
		}
		this.courseModel.insertNodeInto(aux, this.root, this.courseModel.getChildCount(this.root));
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
		
		if(this.units.get(aux) == null)
			this.units.put(aux, new ArrayList<DefaultMutableTreeNode>());
			
		DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(subunit);
		this.units.get(aux).add(subNode);
		this.courseModel.insertNodeInto(subNode, aux, this.courseModel.getChildCount(aux));
	}
	
	public void setController(ActionListener c){
		this.signOut.addActionListener(c);
		this.studentsButton.addActionListener(c);
		this.go.addActionListener(c);
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
	 * @return the courseLabel
	 */
	public JLabel getCourseLabel() {
		return courseLabel;
	}


	/**
	 * @return the studentsButton
	 */
	public JButton getStudentsButton() {
		return studentsButton;
	}

	/**
	 * @return the layout2
	 */
	public SpringLayout getLayout2() {
		return layout2;
	}
	
	/**
	 * @return the go
	 */
	public JButton getGo(){
		return go;
	}
	
}