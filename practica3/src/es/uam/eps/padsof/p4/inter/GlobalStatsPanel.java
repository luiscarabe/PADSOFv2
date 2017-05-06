package es.uam.eps.padsof.p4.inter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.*;

import es.uam.eps.padsof.p3.stat.Answer;
import es.uam.eps.padsof.p3.stat.CMark;
import es.uam.eps.padsof.p3.stat.CourseStat;

public class GlobalStatsPanel extends JPanel {
	//Superior Panel
		private JPanel supPanel = new JPanel();
		private ImageIcon image = new ImageIcon("logov3.png");
		private JLabel imgLabel = new JLabel(image);
		private JLabel creatLabel = new JLabel("Global stats");
		private JLabel professor = new JLabel("Professor");
		private JButton signOut = new JButton("Sign out");
		private SpringLayout layout = new SpringLayout();
		
		private JLabel courName;
		private JLabel globAvMedia;
		
		private JPanel marksPanel = new JPanel();
		private JScrollPane marksPane = new JScrollPane(this.marksPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		private JButton back = new JButton("Return");
		
		private SpringLayout layout2 = new SpringLayout();
		
		public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		public GlobalStatsPanel(CourseStat cs){
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
			layout.putConstraint(SpringLayout.EAST, this.professor, 0, SpringLayout.EAST, this.signOut);
			
			layout.putConstraint(SpringLayout.NORTH, this.signOut, 40, SpringLayout.NORTH, this.supPanel);
			layout.putConstraint(SpringLayout.EAST, this.signOut, -50 , SpringLayout.EAST, this.supPanel);
			
			this.courName = new JLabel(cs.getcMarks().get(0).getCourse().getTitle());
			Map attributes = this.courName.getFont().getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			this.courName.setFont(this.courName.getFont().deriveFont(attributes));
			this.courName.setFont(this.courName.getFont().deriveFont(23f));
			
			cs.calculateCstat();
			this.globAvMedia = new JLabel("Global average mark: " + cs.getAverageMark());
			this.globAvMedia.setFont(this.globAvMedia.getFont().deriveFont(20f));
			
			this.marksPanel.setVisible(true);
			this.marksPanel.setLayout(new GridLayout(cs.getcMarks().size(),1));
			for(CMark a: cs.getcMarks()){
				JLabel jl = new JLabel(a.getStudent().getName() + ": "+a.getCourseMark());
				this.marksPanel.add(jl);
			}
			this.marksPane.setPreferredSize(new Dimension(500,300));
			this.marksPane.setBorder(null);
			this.marksPanel.setBackground(this.getBackground());
			this.marksPane.setBackground(this.getBackground());
			
			this.add(this.supPanel);
			this.add(this.courName);
			this.add(this.marksPane);
			this.add(this.globAvMedia);
			this.add(this.back);
		
			
			layout2.putConstraint(SpringLayout.NORTH, this.supPanel, 0, SpringLayout.NORTH, this);
			layout2.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.supPanel, 30, SpringLayout.HORIZONTAL_CENTER, this);
			layout2.putConstraint(SpringLayout.EAST, this.supPanel, 0, SpringLayout.EAST, this);
			layout2.putConstraint(SpringLayout.WEST, this.supPanel, 0, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.NORTH, this.courName, 10, SpringLayout.SOUTH, this.supPanel);
			layout2.putConstraint(SpringLayout.WEST, this.courName, 10, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.NORTH, this.globAvMedia, 10, SpringLayout.SOUTH, this.courName);
			layout2.putConstraint(SpringLayout.WEST, this.globAvMedia, 10, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.NORTH, this.marksPane, 20, SpringLayout.SOUTH, this.globAvMedia);
			layout2.putConstraint(SpringLayout.WEST, this.marksPane, 10, SpringLayout.WEST, this);
			
			layout2.putConstraint(SpringLayout.NORTH, this.back, 20, SpringLayout.SOUTH, this.marksPane);
			layout2.putConstraint(SpringLayout.WEST, this.back, 10, SpringLayout.WEST, this);
			
			
		}
}
