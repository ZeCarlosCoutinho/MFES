package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import Rally2.*;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;
import javax.swing.ListModel;

public class StartWindow {
	public static ArrayList<Team> teams= new ArrayList<>();
	public static ArrayList<Event> events= new ArrayList<>();

	private JFrame frame;
	public static JList<String> team_list;
	public static JList<String> event_list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCriarEquipa = new JButton("criar equipa");
		btnCriarEquipa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CreateTeamWindow ct = new CreateTeamWindow();
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
		btnCriarEquipa.setBounds(98, 360, 89, 23);
		frame.getContentPane().add(btnCriarEquipa);
		
		
		DefaultListModel<String> l1 = new DefaultListModel<>();
		for(Team t : teams){
			l1.addElement(t.name);
		}
		team_list = new JList<String>(l1);
		team_list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		TeamManagementWindow tmw = new TeamManagementWindow(getTeam(team_list.getModel().getElementAt(index)));
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
		JScrollPane team_listScroller = new JScrollPane();
		team_listScroller.setViewportView(team_list);
		team_listScroller.setBounds(47, 27, 243, 185);
        frame.getContentPane().add(team_listScroller);
		
		DefaultListModel<String> l2 = new DefaultListModel<>();  
		for(Event e : events){
			l1.addElement(e.name);
		}
		event_list = new JList<String>(l2);
		event_list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		System.out.println(index);
		        	}
		        }
		    }
		});
		JScrollPane event_listScroller = new JScrollPane();
		event_listScroller.setViewportView(event_list);
		event_listScroller.setBounds(391, 27, 243, 185);
		frame.getContentPane().add(event_listScroller);
	}
	
	
	public static void updateLists(){
		DefaultListModel<String> l1 = new DefaultListModel<>();  
		for(Team t : teams){
			l1.addElement(t.name);
		}
		team_list.setModel(l1);
		
		DefaultListModel<String> l2 = new DefaultListModel<>();  
		for(Event e : events){
			l1.addElement(e.name);
		}
		event_list.setModel(l2);
		
		team_list.repaint();
		event_list.repaint();
	}
	
	public static Team getTeam(String name){
		for (Team t : teams){
			if(t.name.equals(name))
				return t;
		}
		return null;
	}
	
	public static Event getEvent(String name){
		for (Event t : events){
			if(t.name.equals(name))
				return t;
		}
		return null;
	}
}
