package es.uam.eps.padsof.p4.inter;

import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

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
			this.date.setPreferredSize(new Dimension(400,100));
			this.date.add(this.iniDateLabel);
			this.date.add(this.iniDate);
			this.date.add(this.finDateLabel);
			this.date.add(this.finDate);
			this.date.setBackground(this.getBackground());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yy");
			this.iniDate.setEditor(new JSpinner.DateEditor(this.iniDate, model.toPattern()));
			this.finDate.setEditor(new JSpinner.DateEditor(this.finDate, model.toPattern()));
			
			
			this.add(this.supPanel);
			this.add(this.descLabel);
			this.add(this.titleLabel);
			this.add(this.descPane);
			this.add(this.titleField);
			this.add(this.date);
		
			
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
			layout2.putConstraint(SpringLayout.WEST, this.date, 0, SpringLayout.WEST, this.descLabel);
			
		}

}
