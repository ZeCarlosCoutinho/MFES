package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import org.overture.codegen.runtime.*;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TeamManagementWindow extends JFrame {

	private JPanel contentPane;
	public static Team team;
	private JFrame frame;
	public static JList<String> list_members;
	public static JList<String> list_cars;
	public static JList<String> list_events;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamManagementWindow frame = new TeamManagementWindow(new Team());
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
	public TeamManagementWindow(Team t) {
		frame = this;
		this.team = t;
		setBounds(100, 100, 846, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_members = new DefaultListModel<>();
		list_members = new JList<String>(l_members);
		list_members.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint());
		        		TeamMemberInfoWindow tmw = new TeamMemberInfoWindow(team.getMember(list_members.getModel().getElementAt(index).split(" - ")[1].trim()), team);
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
		list_members.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane members_listScroller = new JScrollPane();
		members_listScroller.setViewportView(list_members);
		members_listScroller.setBounds(24, 101, 372, 339);
		contentPane.add(members_listScroller);
		
		DefaultListModel<String> l_cars = new DefaultListModel<>();
		list_cars = new JList<String>(l_cars);
		list_cars.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint());
		        		CarInfoWindow tmw = new CarInfoWindow(team.getCar(Integer.parseInt(list_cars.getModel().getElementAt(index).split(" - ")[0].trim())));
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
		list_cars.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane cars_listScroller = new JScrollPane();
		cars_listScroller.setViewportView(list_cars);
		cars_listScroller.setBounds(448, 101, 372, 137);
		contentPane.add(cars_listScroller);
		
		DefaultListModel<String> l_events = new DefaultListModel<>();
		list_events = new JList<String>(l_events);
		list_events.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane events_listScroller = new JScrollPane();
		events_listScroller.setViewportView(list_events);
		events_listScroller.setBounds(454, 303, 366, 137);
		contentPane.add(events_listScroller);
		
		updateLists();
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TeamAddMemberWindow ct = new TeamAddMemberWindow(team);
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
		btnAddMember.setBounds(24, 468, 156, 23);
		contentPane.add(btnAddMember);
		
		JButton btnAssignMember = new JButton("Assign Member");
		btnAssignMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TeamAssignWindow ct = new TeamAssignWindow(team);
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
		btnAssignMember.setBounds(240, 468, 156, 23);
		contentPane.add(btnAssignMember);
		
		JButton btnAddCar = new JButton("Add Car");
		btnAddCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamAddCarWindow ct = new TeamAddCarWindow(team);
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
		btnAddCar.setBounds(454, 468, 156, 23);
		contentPane.add(btnAddCar);
		
		JButton btnEventResults = new JButton("Event Results");
		btnEventResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamEventResultsWindow ct = new TeamEventResultsWindow(team);
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
		btnEventResults.setBounds(664, 468, 156, 23);
		contentPane.add(btnEventResults);
		
		JLabel lblNewLabel = new JLabel(team.name);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(154, 38, 524, 23);
		contentPane.add(lblNewLabel);
	}
	
	public static void updateLists(){
		DefaultListModel<String> l_members = new DefaultListModel<>();
		Iterator<TeamMember> ir_mem = team.team_members.iterator();
		while(ir_mem.hasNext()){
			TeamMember tm = ir_mem.next();
			String s = "";
			if(tm instanceof Driver)
				s = s + "Driver    - ";
			if(tm instanceof CoDriver)
				s = s + "Co-Driver - ";
			if(tm instanceof Mechanic)
				s = s + "Mechanic  - ";
			if(tm instanceof Manager)
				s = s + "Manager   - ";
			
			l_members.addElement(s + tm.name);
		}
		
		DefaultListModel<String> l_cars = new DefaultListModel<>();
		Iterator<Car> ir_car = team.cars.iterator();
		while(ir_car.hasNext()){
			Car car = ir_car.next();
			l_cars.addElement(car.number + " - " + car.make + " - " + car.model);
		}
		
		DefaultListModel<String> l_events = new DefaultListModel<>();
		for(Event e : StartWindow.getTeamEvents(team)){
			l_events.addElement(e.name);
		}
		
		
		list_members.setModel(l_members);
		list_members.repaint();
		list_cars.setModel(l_cars);
		list_cars.repaint();
		list_events.setModel(l_events);
		list_events.repaint();
	}
	
	public static ArrayList<TeamMember> getNonAssignedMembers(Team team){
		ArrayList<TeamMember> ret = new ArrayList<>();
		ArrayList<TeamMember> assigned = new ArrayList<>();
		
		Iterator<Car> ir_car = team.cars.iterator();
		while(ir_car.hasNext()){
			Car car = ir_car.next();
			assigned.add(car.driver);
			assigned.add(car.co_driver);
			Iterator<Mechanic> ir_mec = car.mechanics.iterator();
			while(ir_mec.hasNext()){
				assigned.add(ir_mec.next());
			}
		}
		
		Iterator<TeamMember> ir_mem = team.team_members.iterator();
		while(ir_mem.hasNext()){
			TeamMember tm = ir_mem.next();
			if(!assigned.contains(tm) && !(tm instanceof Manager))
				ret.add(tm);
		}
		return ret;
	}
}
