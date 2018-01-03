package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;


import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class TeamAssignWindow extends JFrame {

	private JPanel contentPane;
	private static JList<String> list_members;
	private static JList<String> list_cars;
	private static Team team;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamAssignWindow frame = new TeamAssignWindow(new Team());
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
	public TeamAssignWindow(Team t) {
		this.team = t;
		this.frame = this;
		setBounds(100, 100, 426, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_members = new DefaultListModel<>();
		list_members = new JList<String>(l_members);
		list_members.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane members_listScroller = new JScrollPane();
		members_listScroller.setViewportView(list_members);
		members_listScroller.setBounds(33, 72, 339, 186);
		contentPane.add(members_listScroller);
		
		DefaultListModel<String> l_cars = new DefaultListModel<>();
		list_cars = new JList<String>(l_cars);
		list_cars.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane cars_listScroller = new JScrollPane();
		cars_listScroller.setViewportView(list_cars);
		cars_listScroller.setBounds(33, 324, 339, 133);
		contentPane.add(cars_listScroller);
		
		updateLists(true);
		
		JLabel lblWarning = new JLabel("Please select a Team Member and a Car");
		lblWarning.setVisible(false);
		lblWarning.setForeground(Color.RED);
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setBounds(33, 474, 339, 30);
		contentPane.add(lblWarning);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_members.getSelectedIndex() == -1 || list_cars.getSelectedIndex() == -1)
					lblWarning.setVisible(true);
				else{
					String s_member = list_members.getSelectedValue();
					String member_name = s_member.split("- ")[1];
					String member_role = s_member.split("- ")[0].trim();
					String s_car = list_cars.getSelectedValue();
					int number = Integer.parseInt(s_car.split(" -")[0]);
					
					Car car = team.getCar(number);
					TeamMember tm = team.getMember(member_name);
					switch(member_role){
					case "Driver":
						team.assignDriver(car, (Driver) tm);
						break;
					case "Co-Driver":
						team.assignCoDriver(car, (CoDriver) tm);
						break;
					case "Mechanic":
						team.assignMechanic(car, (Mechanic) tm);
						break;
					}
					updateLists(false);
				}
					
			}
		});
		btnAssign.setBounds(244, 515, 89, 23);
		contentPane.add(btnAssign);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnDone.setBounds(65, 515, 89, 23);
		contentPane.add(btnDone);
		
		JLabel lblTeamMembers = new JLabel("Team Members");
		lblTeamMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamMembers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamMembers.setBounds(148, 37, 98, 24);
		contentPane.add(lblTeamMembers);
		
		JLabel lblCars = new JLabel("Cars");
		lblCars.setHorizontalAlignment(SwingConstants.CENTER);
		lblCars.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCars.setBounds(148, 289, 98, 24);
		contentPane.add(lblCars);
	}
	
	public static void updateLists(boolean updateCars){
		DefaultListModel<String> l_members = new DefaultListModel<>();
		ArrayList<TeamMember> members = TeamManagementWindow.getNonAssignedMembers(team);
		for(TeamMember tm : members){
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
		list_members.setModel(l_members);
		list_members.repaint();
		
		if(updateCars){
			DefaultListModel<String> l_cars = new DefaultListModel<>();
			Iterator<Car> ir_car = team.cars.iterator();
			while(ir_car.hasNext()){
				Car car = ir_car.next();
				l_cars.addElement(car.number + " - " + car.make + " - " + car.model);
			}
			list_cars.setModel(l_cars);
			list_cars.repaint();
		}
	}

}
