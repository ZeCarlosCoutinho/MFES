package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Rally2.*;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class AddStageWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private SpecialStage ss;
	private JTextField txt_name;
	private JTextField txt_distance;
	private JTextField txt_number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStageWindow frame = new AddStageWindow(new SpecialStage());
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
	public AddStageWindow(SpecialStage ss) {
		this.ss = ss;
		this.frame = this;
		setBounds(100, 100, 334, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Start Date");
		label.setBounds(10, 163, 89, 14);
		contentPane.add(label);
		
		JSpinner spinner_date = new JSpinner();
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance(); 
		c.set(ss.beginning_date.calendardate.year.intValue(), ss.beginning_date.calendardate.month.intValue() - 1, ss.beginning_date.calendardate.day.intValue(), ss.beginning_date.timestamp.hours.intValue(),  ss.beginning_date.timestamp.minutes.intValue(), ss.beginning_date.timestamp.seconds.intValue());
		c2.set(ss.beginning_date.calendardate.year.intValue(), ss.beginning_date.calendardate.month.intValue() - 1, ss.beginning_date.calendardate.day.intValue(), ss.beginning_date.timestamp.hours.intValue() + 1,  ss.beginning_date.timestamp.minutes.intValue(), ss.beginning_date.timestamp.seconds.intValue());
		spinner_date.setModel(new SpinnerDateModel(c2.getTime(), c.getTime(), null, Calendar.DAY_OF_YEAR));
		spinner_date.setBounds(153, 160, 129, 20);
		contentPane.add(spinner_date);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setBounds(10, 31, 46, 14);
		contentPane.add(label_1);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(113, 28, 169, 20);
		contentPane.add(txt_name);
		
		JLabel lblDistance = new JLabel("Distance");
		lblDistance.setBounds(10, 119, 46, 14);
		contentPane.add(lblDistance);
		
		txt_distance = new JTextField();
		txt_distance.setColumns(10);
		txt_distance.setBounds(113, 116, 169, 20);
		contentPane.add(txt_distance);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 75, 46, 14);
		contentPane.add(lblNumber);
		
		txt_number = new JTextField();
		txt_number.setColumns(10);
		txt_number.setBounds(113, 72, 169, 20);
		contentPane.add(txt_number);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setVisible(false);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 191, 298, 20);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		button.setBounds(45, 222, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Done");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_name.getText().equals("") || txt_distance.getText().equals("") || txt_number.getText().equals("")){
					lblNewLabel.setText("Please fill all the fields");
					lblNewLabel.setVisible(true);
				}else if(!isNumeric(txt_number.getText()) || !isNumeric(txt_distance.getText()) ){
					lblNewLabel.setText("Fields number and/or distance not a natural number");
					lblNewLabel.setVisible(true);
				}else{
					Date date = (Date) spinner_date.getModel().getValue();
					Calendar c = new GregorianCalendar();
					c.setTime(date);
					Rally2.Date date_start = new Rally2.Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
					
					ss.addStage(txt_name.getText(), Integer.parseInt(txt_number.getText()), Integer.parseInt(txt_distance.getText()), date_start);
					SpecialStageManagementWindow.updateLists();
					button.doClick();
				}
			}
		});
		button_1.setBounds(172, 222, 89, 23);
		contentPane.add(button_1);
		
		
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
