package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Rally2.*;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class TeamAddMemberWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JTextField textField_nat;
	private Team team;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamAddMemberWindow frame = new TeamAddMemberWindow(new Team());
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
	public TeamAddMemberWindow(Team t) {
		frame = this;
		this.team = t;
		setBounds(100, 100, 382, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> comboBox_type = new JComboBox<String>();
		comboBox_type.setBounds(109, 30, 217, 27);
		comboBox_type.addItem("Driver");
		comboBox_type.addItem("CoDriver");
		comboBox_type.addItem("Mechanic");
		contentPane.add(comboBox_type);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 89, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_name = new JTextField();
		textField_name.setBounds(109, 83, 217, 27);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setBounds(10, 139, 90, 14);
		contentPane.add(lblNationality);
		
		textField_nat = new JTextField();
		textField_nat.setColumns(10);
		textField_nat.setBounds(109, 133, 217, 27);
		contentPane.add(textField_nat);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(52, 210, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setForeground(Color.RED);
		lblPleaseFillAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseFillAll.setBounds(43, 183, 283, 14);
		lblPleaseFillAll.setVisible(false);
		contentPane.add(lblPleaseFillAll);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_name.getText().equals("") || textField_nat.getText().equals(""))
					lblPleaseFillAll.setVisible(true);
				else{
					switch((int) comboBox_type.getSelectedIndex()){
					case 0:
						t.addDriver(textField_name.getText(), textField_nat.getText());
						break;
					case 1:
						t.addCoDriver(textField_name.getText(), textField_nat.getText());
						break;
					case 2:
						t.addMechanic(textField_name.getText(), textField_nat.getText());
						break;
					}
					TeamManagementWindow.updateLists();
					btnCancel.doClick();
				}
					
			}
		});
		btnDone.setBounds(225, 210, 89, 23);
		contentPane.add(btnDone);
	}
}
