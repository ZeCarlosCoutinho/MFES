package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Rally2.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class TeamMemberInfoWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Team team;
	private TeamMember tm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamMemberInfoWindow frame = new TeamMemberInfoWindow(new TeamMember(), new Team());
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
	public TeamMemberInfoWindow(TeamMember tm, Team t) {
		this.frame = this;
		this.tm = tm;
		this.team = t;
		
		setBounds(100, 100, 368, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblName.setBounds(10, 26, 90, 14);
		contentPane.add(lblName);
		
		JLabel label_1 = new JLabel(tm.name);
		label_1.setBounds(110, 26, 201, 14);
		contentPane.add(label_1);
		
		JLabel lblNationality = new JLabel("Nationality : ");
		lblNationality.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNationality.setBounds(10, 66, 90, 14);
		contentPane.add(lblNationality);
		
		JLabel label_2 = new JLabel(tm.nationality);
		label_2.setBounds(110, 66, 201, 14);
		contentPane.add(label_2);
		
		JLabel lblRole = new JLabel("Role : ");
		lblRole.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRole.setBounds(10, 103, 90, 14);
		contentPane.add(lblRole);
		
		JLabel label_3 = new JLabel(getMemberRole(tm));
		label_3.setBounds(110, 103, 201, 14);
		contentPane.add(label_3);
		
		JLabel lblTeam = new JLabel("Team : ");
		lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTeam.setBounds(10, 139, 90, 14);
		contentPane.add(lblTeam);
		
		JLabel label_4 = new JLabel(team.name);
		label_4.setBounds(110, 139, 201, 14);
		contentPane.add(label_4);
		
		JButton button = new JButton("Done");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		button.setBounds(128, 175, 89, 23);
		contentPane.add(button);
	}
	
	public static String getMemberRole(TeamMember tm){
		if(tm instanceof Driver)
			return "Driver";
		if(tm instanceof CoDriver)
			return "Co-Driver";
		if(tm instanceof Mechanic)
			return "Mechanic";
		if(tm instanceof Manager)
			return "Manager";
		return "";
	}

}
