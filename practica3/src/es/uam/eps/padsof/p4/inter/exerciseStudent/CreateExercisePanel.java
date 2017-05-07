package es.uam.eps.padsof.p4.inter.exerciseStudent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.swing.*;

import es.uam.eps.padsof.p3.exercise.Question;

public class CreateExercisePanel extends JPanel{
	//Superior Panel
		private JPanel supPanel = new JPanel();
		private ImageIcon image = new ImageIcon("logov3.png");
		private JLabel imgLabel = new JLabel(image);
		private JLabel creatLabel = new JLabel("Create Exercise");
		private JLabel professor = new JLabel("Professor");
		private JButton signOut = new JButton("Sign out");
		private SpringLayout layout = new SpringLayout();
		
		private JLabel titleLabel = new JLabel("Title:");
		private JTextField titleField = new JTextField(30);
		private JLabel descLabel = new JLabel("Description: ");
		private JTextArea descField = new JTextArea();
		private JScrollPane descPane = new JScrollPane(this.descField,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		private JLabel iniDateLabel = new JLabel("Start date:");
		private JLabel finDateLabel = new JLabel("Finish date:");
		private JSpinner iniDate = new JSpinner(new SpinnerDateModel());
		private JSpinner finDate =  new JSpinner(new SpinnerDateModel());
		private JPanel date = new JPanel();
		
		private JLabel addQuesLabel= new JLabel("Add question:");
		private JButton addTFQues = new JButton("T/F");
		private JButton addMultiQues = new JButton("Multichoice");
		private JButton addUniqQues = new JButton("Uniq choice");
		private JButton addOpenQues = new JButton("Open text");
		private JCheckBox eqValued = new JCheckBox("Questions equally valued");
		private JPanel addQuesPanel = new JPanel();
		
		private DefaultListModel<Question> quesModel = new DefaultListModel<Question>();
		JList<Question> quesList;
		private JScrollPane quesPane;
		private JLabel quesLabel = new JLabel("Questions");
		private JButton edit = new JButton("Edit");
		private JButton delete = new JButton("Delete");
		
		private JCheckBox hide = new JCheckBox("Hide until start");
		private JCheckBox aleatOrder = new JCheckBox("Aleatory order");
		private JLabel penalLabel = new JLabel("Penalty (0 if not wanted) out of 10:");
		private JTextField penalField = new JTextField(10);
		private JLabel weightLabel = new JLabel("Weight of this exercise in the course:");
		private JTextField weightField = new JTextField(10);
		 
		private JButton create = new JButton("Create");
		private JButton cancel = new JButton("Cancel");
		
		private SpringLayout layout2 = new SpringLayout();
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		public CreateExercisePanel(){
			this.setVisible(true);
			this.setSize(screenSize.width, screenSize.height);
			this.setLayout(layout2);
			this.setBackground(Color.decode("#98FB98"));
			
			this.supPanel.setVisible(true);
			this.supPanel.setPreferredSize(new Dimension(screenSize.width, 80));
			this.supPanel.setBackground(Color.decode("#228B22"));
			this.supPanel.setLayout(layout);
			
			this.imgLabel.setVisible(true);
			this.imgLabel.setBounds(0,0,200,200);
			
			this.creatLabel.setFont(this.creatLabel.getFont().deriveFont(30f));
			this.professor.setFont(this.professor.getFont().deriveFont(15f));
			this.signOut.setForeground(Color.RED);
			
			this.supPanel.add(imgLabel);
			this.supPanel.add(creatLabel);
			this.supPanel.add(professor);
			this.supPanel.add(signOut);
			
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.imgLabel, 40, SpringLayout.WEST, this.supPanel);
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.imgLabel, 40, SpringLayout.NORTH, this.supPanel);
			
			layout.putConstraint(SpringLayout.VERTICAL_CENTER, this.creatLabel, 0, SpringLayout.VERTICAL_CENTER, this.imgLabel);
			layout.putConstraint(SpringLayout.WEST, this.creatLabel, 10, SpringLayout.EAST, this.imgLabel);
			
			layout.putConstraint(SpringLayout.NORTH, this.professor, 5, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.professor, 0, SpringLayout.HORIZONTAL_CENTER, this.signOut);
			
			layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
								

			this.titleLabel.setLabelFor(this.titleField);
			
			this.descField.setLineWrap(true);
			this.descField.setWrapStyleWord(true);
			this.descLabel.setLabelFor(this.descField);
		
			this.descPane.setPreferredSize(new Dimension(700,70));
			
			
			this.date.setVisible(true);
			this.date.setLayout(new FlowLayout());
			this.date.setPreferredSize(new Dimension(400,50));
			this.date.add(this.iniDateLabel);
			this.date.add(this.iniDate);
			this.date.add(this.finDateLabel);
			this.date.add(this.finDate);
			this.date.setBackground(this.getBackground());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yy");
			this.iniDate.setEditor(new JSpinner.DateEditor(this.iniDate, model.toPattern()));
			this.finDate.setEditor(new JSpinner.DateEditor(this.finDate, model.toPattern()));
			
			this.addQuesPanel.setVisible(true);
			this.addQuesPanel.setLayout(new FlowLayout());
			this.addQuesPanel.setPreferredSize(new Dimension(800,100));
			this.addQuesPanel.setBackground(this.getBackground());
			this.eqValued.setBackground(this.getBackground());
			this.addQuesPanel.add(this.addQuesLabel);
			this.addQuesPanel.add(this.addTFQues);
			this.addQuesPanel.add(this.addMultiQues);
			this.addQuesPanel.add(this.addUniqQues);
			this.addQuesPanel.add(this.addOpenQues);
			this.addQuesPanel.add(this.eqValued);
			
			Map attributes = this.quesLabel.getFont().getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			
			this.quesList = new JList<Question>(this.quesModel);
			this.quesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.quesPane = new JScrollPane(this.quesList,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.quesPane.setPreferredSize(new Dimension(200,200));
			this.quesLabel.setFont(this.quesLabel.getFont().deriveFont(attributes));
			this.quesList.setBackground(Color.decode("#20B2AA"));
			
			this.hide.setBackground(this.getBackground());
			this.aleatOrder.setBackground(this.getBackground());
			
			
			this.add(this.supPanel);
			this.add(this.descLabel);
			this.add(this.titleLabel);
			this.add(this.descPane);
			this.add(this.titleField);
			this.add(this.date);
			this.add(this.addQuesPanel);
			this.add(this.quesPane);
			this.add(this.quesLabel);
			this.add(this.edit);
			this.add(this.delete);
			this.add(this.aleatOrder);
			this.add(this.hide);
			this.add(this.penalLabel);
			this.add(this.penalField);
			this.add(this.hide);
			this.add(this.aleatOrder);
			this.add(this.create);
			this.add(this.cancel);
			this.add(this.weightField);
			this.add(this.weightLabel);
		
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.NORTH, this.titleLabel, 20, SpringLayout.SOUTH, this.supPanel);
			layout2.putConstraint(SpringLayout.WEST, this.titleLabel, 30, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.titleField, 0, SpringLayout.VERTICAL_CENTER, this.titleLabel);
			layout2.putConstraint(SpringLayout.WEST, this.titleField, 60, SpringLayout.EAST, this.titleLabel);
			
			layout2.putConstraint(SpringLayout.NORTH, this.descLabel,10, SpringLayout.SOUTH, this.titleLabel);
			layout2.putConstraint(SpringLayout.WEST, this.descLabel, 0, SpringLayout.WEST, this.titleLabel);
			
			layout2.putConstraint(SpringLayout.NORTH, this.descPane, 10, SpringLayout.SOUTH, this.titleLabel);
			layout2.putConstraint(SpringLayout.WEST, this.descPane, 0, SpringLayout.WEST, this.titleField);
			
			layout2.putConstraint(SpringLayout.NORTH, this.date, 10, SpringLayout.SOUTH, this.descPane);
			layout2.putConstraint(SpringLayout.WEST, this.date, -35, SpringLayout.WEST, this.descLabel);
			
			layout2.putConstraint(SpringLayout.NORTH, this.addQuesPanel, 0, SpringLayout.SOUTH, this.date);
			layout2.putConstraint(SpringLayout.WEST, this.addQuesPanel, -43, SpringLayout.WEST, this.descLabel);
			
			layout2.putConstraint(SpringLayout.NORTH, this.quesPane, 5, SpringLayout.SOUTH, this.quesLabel);
			layout2.putConstraint(SpringLayout.WEST, this.quesPane, 0, SpringLayout.WEST, this.descLabel);
			
			layout2.putConstraint(SpringLayout.NORTH, this.quesLabel, 0, SpringLayout.SOUTH, this.addQuesPanel);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.quesLabel, 0, SpringLayout.HORIZONTAL_CENTER, this.quesPane);

			layout2.putConstraint(SpringLayout.NORTH, this.edit, 10, SpringLayout.SOUTH, this.quesPane);
			layout2.putConstraint(SpringLayout.WEST, this.edit, 10, SpringLayout.WEST, this.quesPane);

			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.delete, 0, SpringLayout.VERTICAL_CENTER, this.edit);
			layout2.putConstraint(SpringLayout.WEST, this.delete, 10, SpringLayout.EAST, this.edit);
			
			layout2.putConstraint(SpringLayout.NORTH, this.edit, 10, SpringLayout.SOUTH, this.quesPane);
			layout2.putConstraint(SpringLayout.WEST, this.edit, 10, SpringLayout.WEST, this.quesPane);
			
			layout2.putConstraint(SpringLayout.NORTH, this.penalLabel, 10, SpringLayout.NORTH, this.quesPane);
			layout2.putConstraint(SpringLayout.WEST, this.penalLabel, 10, SpringLayout.EAST, this.quesPane);

			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.penalField, 0, SpringLayout.VERTICAL_CENTER, this.penalLabel);
			layout2.putConstraint(SpringLayout.WEST, this.penalField, 0, SpringLayout.WEST, this.weightField);

			layout2.putConstraint(SpringLayout.NORTH, this.hide, 10, SpringLayout.SOUTH, this.weightLabel);
			layout2.putConstraint(SpringLayout.WEST, this.hide, 0, SpringLayout.WEST, this.penalLabel);

			layout2.putConstraint(SpringLayout.NORTH, this.aleatOrder, 10, SpringLayout.SOUTH, this.hide);
			layout2.putConstraint(SpringLayout.WEST, this.aleatOrder, 0, SpringLayout.WEST, this.hide);		
			
			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.create, 0, SpringLayout.VERTICAL_CENTER, this.edit);
			layout2.putConstraint(SpringLayout.WEST, this.create, 500, SpringLayout.WEST, this);		
			
			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.cancel, 0, SpringLayout.VERTICAL_CENTER, this.create);
			layout2.putConstraint(SpringLayout.WEST, this.cancel, 10, SpringLayout.EAST, this.create);		

			layout2.putConstraint(SpringLayout.NORTH, this.weightLabel, 10, SpringLayout.SOUTH, this.penalLabel);
			layout2.putConstraint(SpringLayout.WEST, this.weightLabel, 0, SpringLayout.WEST, this.penalLabel);	

			layout2.putConstraint(SpringLayout.VERTICAL_CENTER, this.weightField, 0, SpringLayout.VERTICAL_CENTER, this.weightLabel);
			layout2.putConstraint(SpringLayout.WEST, this.weightField, 10, SpringLayout.EAST, this.weightLabel);	
		}
		
		public String getName(){
			return this.titleField.getText();
		}
		
		public String getDesc(){
			return this.descField.getText();
		}
		
		public void setController(ActionListener c) {
			this.signOut.addActionListener(c);
			this.create.addActionListener(c);
			this.cancel.addActionListener(c);
			this.edit.addActionListener(c);
			this.delete.addActionListener(c);
			this.addTFQues.addActionListener(c);
			this.addMultiQues.addActionListener(c);
			this.addOpenQues.addActionListener(c);
			this.addUniqQues.addActionListener(c);
			this.eqValued.addActionListener(c);
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
		 * @return the creatLabel
		 */
		public JLabel getCreatLabel() {
			return creatLabel;
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
		 * @return the titleLabel
		 */
		public JLabel getTitleLabel() {
			return titleLabel;
		}

		/**
		 * @return the titleField
		 */
		public JTextField getTitleField() {
			return titleField;
		}

		/**
		 * @return the descLabel
		 */
		public JLabel getDescLabel() {
			return descLabel;
		}

		/**
		 * @return the descField
		 */
		public JTextArea getDescField() {
			return descField;
		}

		/**
		 * @return the descPane
		 */
		public JScrollPane getDescPane() {
			return descPane;
		}

		/**
		 * @return the iniDateLabel
		 */
		public JLabel getIniDateLabel() {
			return iniDateLabel;
		}

		/**
		 * @return the finDateLabel
		 */
		public JLabel getFinDateLabel() {
			return finDateLabel;
		}

		/**
		 * @return the iniDate
		 */
		public JSpinner getIniDate() {
			return iniDate;
		}

		/**
		 * @return the finDate
		 */
		public JSpinner getFinDate() {
			return finDate;
		}

		/**
		 * @return the date
		 */
		public JPanel getDate() {
			return date;
		}

		/**
		 * @return the addQuesLabel
		 */
		public JLabel getAddQuesLabel() {
			return addQuesLabel;
		}

		/**
		 * @return the addTFQues
		 */
		public JButton getAddTFQues() {
			return addTFQues;
		}

		/**
		 * @return the addMultiQues
		 */
		public JButton getAddMultiQues() {
			return addMultiQues;
		}

		/**
		 * @return the addUniqQues
		 */
		public JButton getAddUniqQues() {
			return addUniqQues;
		}

		/**
		 * @return the addOpenQues
		 */
		public JButton getAddOpenQues() {
			return addOpenQues;
		}

		/**
		 * @return the eqValued
		 */
		public JCheckBox getEqValued() {
			return eqValued;
		}

		/**
		 * @return the addQuesPanel
		 */
		public JPanel getAddQuesPanel() {
			return addQuesPanel;
		}

		/**
		 * @return the quesModel
		 */
		public DefaultListModel<Question> getQuesModel() {
			return quesModel;
		}

		/**
		 * @return the quesList
		 */
		public JList<Question> getQuesList() {
			return quesList;
		}

		/**
		 * @return the quesPane
		 */
		public JScrollPane getQuesPane() {
			return quesPane;
		}

		/**
		 * @return the quesLabel
		 */
		public JLabel getQuesLabel() {
			return quesLabel;
		}

		/**
		 * @return the edit
		 */
		public JButton getEdit() {
			return edit;
		}

		/**
		 * @return the delete
		 */
		public JButton getDelete() {
			return delete;
		}

		/**
		 * @return the hide
		 */
		public JCheckBox getHide() {
			return hide;
		}

		/**
		 * @return the aleatOrder
		 */
		public JCheckBox getAleatOrder() {
			return aleatOrder;
		}

		/**
		 * @return the penalLabel
		 */
		public JLabel getPenalLabel() {
			return penalLabel;
		}

		/**
		 * @return the penalField
		 */
		public JTextField getPenalField() {
			return penalField;
		}

		/**
		 * @return the create
		 */
		public JButton getCreate() {
			return create;
		}

		/**
		 * @return the cancel
		 */
		public JButton getCancel() {
			return cancel;
		}

		/**
		 * @return the layout2
		 */
		public SpringLayout getLayout2() {
			return layout2;
		}
		
		

}
