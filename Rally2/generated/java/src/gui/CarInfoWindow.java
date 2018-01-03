package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import Rally2.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class CarInfoWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Car car;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarInfoWindow frame = new CarInfoWindow(new Car());
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
	public CarInfoWindow(Car c) {
		this.frame = this;
		this.car = c;
		setBounds(100, 100, 363, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Number : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 29, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(car.number.toString());
		lblNewLabel_1.setBounds(110, 29, 201, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMake = new JLabel("Make : ");
		lblMake.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMake.setBounds(10, 62, 90, 14);
		contentPane.add(lblMake);
		
		JLabel label_1 = new JLabel(car.make);
		label_1.setBounds(110, 62, 201, 14);
		contentPane.add(label_1);
		
		JLabel lblModel = new JLabel("Model : ");
		lblModel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblModel.setBounds(10, 98, 90, 14);
		contentPane.add(lblModel);
		
		JLabel label_3 = new JLabel(car.model);
		label_3.setBounds(110, 98, 201, 14);
		contentPane.add(label_3);
		
		JLabel lblYear = new JLabel("Traction : ");
		lblYear.setHorizontalAlignment(SwingConstants.TRAILING);
		lblYear.setBounds(10, 133, 90, 14);
		contentPane.add(lblYear);
		
		JLabel label_5 = new JLabel(car.traction);
		label_5.setBounds(110, 133, 201, 14);
		contentPane.add(label_5);
		
		JLabel lblYear_1 = new JLabel("Year : ");
		lblYear_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblYear_1.setBounds(10, 167, 90, 14);
		contentPane.add(lblYear_1);
		
		JLabel label_7 = new JLabel(car.year.toString());
		label_7.setBounds(110, 167, 201, 14);
		contentPane.add(label_7);
		
		JLabel lblBhp = new JLabel("BHP : ");
		lblBhp.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBhp.setBounds(10, 203, 90, 14);
		contentPane.add(lblBhp);
		
		JLabel label_9 = new JLabel(car.bhp.toString());
		label_9.setBounds(110, 203, 201, 14);
		contentPane.add(label_9);
		
		JLabel lblWeight = new JLabel("Weight : ");
		lblWeight.setHorizontalAlignment(SwingConstants.TRAILING);
		lblWeight.setBounds(10, 238, 90, 14);
		contentPane.add(lblWeight);
		
		JLabel label_11 = new JLabel(car.weight.toString());
		label_11.setBounds(110, 238, 201, 14);
		contentPane.add(label_11);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnDone.setBounds(120, 314, 89, 23);
		contentPane.add(btnDone);
		
		JLabel lblTeam = new JLabel("Team : ");
		lblTeam.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTeam.setBounds(10, 268, 90, 14);
		contentPane.add(lblTeam);
		
		JLabel label_2 = new JLabel(car.team.name);
		label_2.setBounds(110, 268, 201, 14);
		contentPane.add(label_2);
	}
}
