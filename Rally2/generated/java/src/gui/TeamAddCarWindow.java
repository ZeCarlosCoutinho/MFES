package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import Rally2.*;

public class TeamAddCarWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblMake;
	private JTextField textField_make;
	private JLabel lblModel;
	private JTextField textField_model;
	private JLabel lblTraction;
	private JComboBox<String> comboBox_traction;
	private JLabel lblWeight;
	private JTextField textField_weight;
	private JLabel lblBhp;
	private JTextField textField_year;
	private JTextField textField_bhp;
	private JButton btnCancel;
	private JButton btnDone;
	private JLabel lblPleaseFillAll;
	private JFrame frame;
	private Team team;
	private JLabel lblNumber;
	private JTextField textField_number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamAddCarWindow frame = new TeamAddCarWindow(new Team());
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
	public TeamAddCarWindow(Team t) {
		this.frame = this;
		this.team = t;
		
		setBounds(100, 100, 339, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Year");
		lblNewLabel.setBounds(10, 206, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblMake = new JLabel("Make");
		lblMake.setBounds(10, 60, 46, 14);
		contentPane.add(lblMake);
		
		textField_make = new JTextField();
		textField_make.setBounds(106, 57, 203, 20);
		contentPane.add(textField_make);
		textField_make.setColumns(10);
		
		lblModel = new JLabel("Model");
		lblModel.setBounds(10, 110, 46, 14);
		contentPane.add(lblModel);
		
		textField_model = new JTextField();
		textField_model.setColumns(10);
		textField_model.setBounds(106, 107, 203, 20);
		contentPane.add(textField_model);
		
		lblTraction = new JLabel("Traction");
		lblTraction.setBounds(10, 159, 46, 14);
		contentPane.add(lblTraction);
		
		comboBox_traction = new JComboBox<String>();
		comboBox_traction.setModel(new DefaultComboBoxModel<String>(new String[] {"FWD", "RWD", "4WD", "AWD"}));
		comboBox_traction.setBounds(106, 156, 202, 20);
		contentPane.add(comboBox_traction);
		
		lblWeight = new JLabel("Weight");
		lblWeight.setBounds(10, 250, 46, 14);
		contentPane.add(lblWeight);
		
		textField_weight = new JTextField();
		textField_weight.setColumns(10);
		textField_weight.setBounds(106, 247, 203, 20);
		contentPane.add(textField_weight);
		
		lblBhp = new JLabel("BHP");
		lblBhp.setBounds(10, 290, 46, 14);
		contentPane.add(lblBhp);
		
		textField_year = new JTextField();
		textField_year.setColumns(10);
		textField_year.setBounds(106, 203, 203, 20);
		contentPane.add(textField_year);
		
		textField_bhp = new JTextField();
		textField_bhp.setColumns(10);
		textField_bhp.setBounds(106, 287, 203, 20);
		contentPane.add(textField_bhp);
		
		lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setVisible(false);
		lblPleaseFillAll.setForeground(Color.RED);
		lblPleaseFillAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseFillAll.setBounds(37, 330, 247, 31);
		contentPane.add(lblPleaseFillAll);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(47, 372, 89, 23);
		contentPane.add(btnCancel);
		
		btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField_number.getText().equals("") || textField_make.getText().equals("") || textField_model.getText().equals("") || textField_year.getText().equals("") || textField_weight.getText().equals("") || textField_bhp.getText().equals("")){
					lblPleaseFillAll.setText("Please fill all the fields");
					lblPleaseFillAll.setVisible(true);
				}else if(!isNumeric(textField_number.getText()) || !isNumeric(textField_year.getText()) || !isNumeric(textField_bhp.getText()) || !isNumeric(textField_weight.getText())){
					lblPleaseFillAll.setText("<html>Numeric values bigger than zero requiered in year, bhp and weight</html>");
					lblPleaseFillAll.setVisible(true);
				}else{
					team.addCar(textField_make.getText(), textField_model.getText(), Integer.parseInt(textField_year.getText()), Integer.parseInt(textField_bhp.getText()), (String) comboBox_traction.getSelectedItem(), Integer.parseInt(textField_weight.getText()), Integer.parseInt(textField_number.getText()));
					TeamManagementWindow.updateLists();
					btnCancel.doClick();
				}
			}
		});
		btnDone.setBounds(180, 372, 89, 23);
		contentPane.add(btnDone);
		
		lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 14, 46, 14);
		contentPane.add(lblNumber);
		
		textField_number = new JTextField();
		textField_number.setColumns(10);
		textField_number.setBounds(106, 11, 203, 20);
		contentPane.add(textField_number);
		
		
	}
	
	public static boolean isNumeric(String str)  
	{
		int n;
		try  
		{  
			n = Integer.parseInt(str);
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}
		if(n > 0)
			return true;
		else
			return false;
	}
}
