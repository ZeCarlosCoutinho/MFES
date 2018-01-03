package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import Rally2.*;

public class CreateTeamWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_name2;
	private JTextField textField_nat;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTeamWindow frame = new CreateTeamWindow();
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
	public CreateTeamWindow() {
		frame = this;
		setBounds(100, 100, 359, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 43, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblManager = new JLabel("Manager");
		lblManager.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManager.setBounds(147, 68, 68, 20);
		contentPane.add(lblManager);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(10, 102, 46, 14);
		contentPane.add(lblName_1);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setBounds(11, 143, 67, 14);
		contentPane.add(lblNationality);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTeam.setBounds(153, 15, 46, 14);
		contentPane.add(lblTeam);
		
		JLabel lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseFillAll.setForeground(Color.RED);
		lblPleaseFillAll.setBounds(49, 171, 238, 14);
		lblPleaseFillAll.setVisible(false);
		contentPane.add(lblPleaseFillAll);
		
		textField_name = new JTextField();
		textField_name.setBounds(87, 40, 200, 20);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_name2 = new JTextField();
		textField_name2.setColumns(10);
		textField_name2.setBounds(87, 99, 200, 20);
		contentPane.add(textField_name2);
		
		textField_nat = new JTextField();
		textField_nat.setColumns(10);
		textField_nat.setBounds(87, 140, 200, 20);
		contentPane.add(textField_nat);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(49, 196, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_name2.getText().equals("") || textField_name.getText().equals("") || textField_nat.getText().equals(""))
					lblPleaseFillAll.setVisible(true);
				else{
					Manager manager = new Manager(textField_name2.getText(), textField_nat.getText());
					Team team = new Team(textField_name.getText(), manager);
					StartWindow.teams.add(team);
					StartWindow.updateLists();
					btnCancel.doClick();
				}
					
			}
		});
		btnDone.setBounds(198, 196, 89, 23);
		contentPane.add(btnDone);
		
		
	}
}
