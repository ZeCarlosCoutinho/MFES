package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.ListModel;
import javax.swing.JLabel;
import java.awt.Font;

public class AtoBManagementWindow extends JFrame {

	private JPanel contentPane;
	private static AtoB atob;
	private JFrame frame;
	private static JList<String> list_rounds;
	private static JList<String> list_teams;
	private JButton btnShowResults;
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
					AtoBManagementWindow frame = new AtoBManagementWindow(new AtoB());
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
	public AtoBManagementWindow(AtoB atob) {
		this.frame = this;
		this.atob = atob;
		setBounds(100, 100, 836, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_rounds = new DefaultListModel<>();
		list_rounds = new JList<String>(l_rounds);
		list_rounds.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) {
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		Team team = StartWindow.getTeam(list.getModel().getElementAt(index).split(" - ")[3].trim());
		        		CarInfoWindow tmw = new CarInfoWindow(team.getCar(Integer.parseInt(list.getModel().getElementAt(index).split(" - ")[2].trim())));
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
		JScrollPane rounds_listScroller = new JScrollPane();
		rounds_listScroller.setViewportView(list_rounds);
		rounds_listScroller.setBounds(23, 90, 391, 284);
		contentPane.add(rounds_listScroller);
		
		JButton btnNewButton = new JButton("Add Round");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoundWindow ct = new AddRoundWindow(atob);
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
		btnNewButton.setBounds(136, 401, 156, 23);
		contentPane.add(btnNewButton);
		
		DefaultListModel<String> l_teams = new DefaultListModel<>();
		list_teams = new JList<String>(l_teams);
		JScrollPane teams_listScroller = new JScrollPane();
		teams_listScroller.setViewportView(list_teams);
		teams_listScroller.setBounds(442, 91, 355, 282);
		contentPane.add(teams_listScroller);
		
		btnShowResults = new JButton("Show Results");
		btnShowResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtoBResultsWindow ct = new AtoBResultsWindow(atob);
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
		btnShowResults.setBounds(546, 401, 156, 23);
		contentPane.add(btnShowResults);
		
		lblName = new JLabel(atob.name);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(23, 24, 391, 14);
		contentPane.add(lblName);
		
		lblLoc = new JLabel(atob.location);
		lblLoc.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoc.setBounds(23, 49, 391, 14);
		contentPane.add(lblLoc);
		
		lblStart = new JLabel("Start: " + formatDate(atob.beginning_date));
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblStart.setBounds(442, 24, 355, 14);
		contentPane.add(lblStart);
		
		JLabel lblEnd = new JLabel("End: " + formatDate(atob.end_date));
		lblEnd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnd.setBounds(442, 49, 355, 14);
		contentPane.add(lblEnd);
		
		updateLists();
	}

	public static void updateLists(){
		DefaultListModel<String> l_rounds = new DefaultListModel<>();
		Iterator<Round> ir_rounds = atob.round.iterator();
		while(ir_rounds.hasNext()){
			Round round = ir_rounds.next();
			l_rounds.addElement(formatDate(round.date) + " - " + formatTime(round.round_time.intValue()) + " - " + String.format("%02d", round.car.number.intValue()) + " - " + round.car.team.name);
		}
		
		list_rounds.setModel(l_rounds);
		list_rounds.repaint();
		
		DefaultListModel<String> l_teams = new DefaultListModel<>();
		Iterator<Team> ir_teams = atob.teams.iterator();
		while(ir_teams.hasNext()){
			Team team = ir_teams.next();
			l_teams.addElement(team.name);
		}
		
		list_teams.setModel(l_teams);
		list_teams.repaint();
	}
	
	public static String formatTime(int millis){
		int m = millis % 1000;
		int seconds=(millis/1000)%60;
		int minutes=((millis-seconds)/1000)/60;
		
		return String.format("%02d:%02d.%03d", minutes, seconds, m);
	}
	
	public static String formatDate(Date date){
		return String.format("%02d:%02d:%02d %02d/%02d/%04d", date.timestamp.hours, date.timestamp.minutes, date.timestamp.seconds, date.calendardate.day, date.calendardate.month, date.calendardate.year);
	}
}
