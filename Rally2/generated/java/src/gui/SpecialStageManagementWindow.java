package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpecialStageManagementWindow extends JFrame {

	private JPanel contentPane;
	private static SpecialStage ss;
	private JFrame frame;
	private static JList<String> list_stages;
	private static JList<String> list_teams;
	private JButton btnAddStage;
	private JButton btnEventResults;
	private JLabel lblName;
	private JLabel lblLoc;
	private JLabel lblStart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpecialStageManagementWindow frame = new SpecialStageManagementWindow(new SpecialStage());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpecialStageManagementWindow(SpecialStage ss) {
		this.ss = ss;
		this.frame = this;
		setBounds(100, 100, 758, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_stages = new DefaultListModel<>();
		list_stages = new JList<String>(l_stages);
		list_stages.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		StageManagementWindow tmw = new StageManagementWindow(getStage(list_stages.getModel().getElementAt(index)), ss);
		        		tmw.setVisible(true);
		        		frame.setEnabled(false);
		        		tmw.addWindowListener(new java.awt.event.WindowAdapter() {
		        		    @Override
		        		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        		        frame.setEnabled(true);
		        		    }
		        		});
		        		
		        	}
		        }
		    }
		});
		JScrollPane stages_listScroller = new JScrollPane();
		stages_listScroller.setViewportView(list_stages);
		stages_listScroller.setBounds(31, 105, 296, 271);
		contentPane.add(stages_listScroller);
		
		DefaultListModel<String> l_teams = new DefaultListModel<>();
		list_teams = new JList<String>(l_teams);
		JScrollPane teams_listScroller = new JScrollPane();
		teams_listScroller.setViewportView(list_teams);
		teams_listScroller.setBounds(414, 105, 296, 271);
		contentPane.add(teams_listScroller);
		
		btnAddStage = new JButton("Add Stage");
		btnAddStage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStageWindow ct = new AddStageWindow(ss);
				ct.setVisible(true);
				frame.setEnabled(false);
        		ct.addWindowListener(new java.awt.event.WindowAdapter() {
        		    @Override
        		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        		        frame.setEnabled(true);
        		    }
        		});
			}
		});
		btnAddStage.setBounds(108, 403, 130, 23);
		contentPane.add(btnAddStage);
		
		btnEventResults = new JButton("Event Results");
		btnEventResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpecialStageResultsWindow ct = new SpecialStageResultsWindow(ss);
				ct.setVisible(true);
				frame.setEnabled(false);
        		ct.addWindowListener(new java.awt.event.WindowAdapter() {
        		    @Override
        		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        		        frame.setEnabled(true);
        		    }
        		});
			}
		});
		btnEventResults.setBounds(504, 403, 130, 23);
		contentPane.add(btnEventResults);
		
		lblName = new JLabel(ss.name);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(31, 24, 296, 14);
		contentPane.add(lblName);
		
		lblLoc = new JLabel(ss.location);
		lblLoc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoc.setBounds(31, 49, 296, 14);
		contentPane.add(lblLoc);
		
		lblStart = new JLabel("Start: " + formatDate(ss.beginning_date));
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStart.setBounds(414, 24, 296, 14);
		contentPane.add(lblStart);
		
		JLabel lblEnd = new JLabel("End: " + formatDate(ss.end_date));
		lblEnd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnd.setBounds(414, 49, 296, 14);
		contentPane.add(lblEnd);
		
		updateLists();
	}

	public static void updateLists() {
		DefaultListModel<String> l_stages = new DefaultListModel<>();
		Iterator<Stage> ir_stages = ss.stages.iterator();
		while(ir_stages.hasNext()){
			Stage stage = ir_stages.next();
			l_stages.addElement(stage.name);
		}
		
		DefaultListModel<String> l_teams = new DefaultListModel<>();
		Iterator<Team> ir_teams = ss.teams.iterator();
		while(ir_teams.hasNext()){
			Team team = ir_teams.next();
			l_teams.addElement(team.name);
		}
		
		list_stages.setModel(l_stages);
		list_stages.repaint();
		
		list_teams.setModel(l_teams);
		list_teams.repaint();
	}
	
	private Stage getStage(String name){
		Iterator<Stage> ir_stages = ss.stages.iterator();
		while(ir_stages.hasNext()){
			Stage stage = ir_stages.next();
			if(stage.name.equals(name))
				return stage;
		}
		return null;
	}
	
	public static String formatDate(Date date){
		return String.format("%02d:%02d:%02d %02d/%02d/%04d", date.timestamp.hours, date.timestamp.minutes, date.timestamp.seconds, date.calendardate.day, date.calendardate.month, date.calendardate.year);
	}
}
