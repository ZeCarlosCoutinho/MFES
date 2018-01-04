package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class AddRoundWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private AtoB atob;
	private JTextField txt_minutes;
	private JTextField txt_seconds;
	private JTextField txt_millis;
	private JSpinner spinner;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoundWindow frame = new AddRoundWindow(new AtoB());
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
	public AddRoundWindow(AtoB atob) {
		this.frame = this;
		this.atob = atob;
		setBounds(100, 100, 376, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Minutes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(81, 26, 55, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSeconds = new JLabel("Seconds");
		lblSeconds.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSeconds.setBounds(146, 26, 75, 14);
		contentPane.add(lblSeconds);
		
		JLabel lblMilliseconds = new JLabel("Milliseconds");
		lblMilliseconds.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMilliseconds.setBounds(231, 26, 86, 14);
		contentPane.add(lblMilliseconds);
		
		txt_minutes = new JTextField();
		txt_minutes.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_minutes.setBounds(90, 51, 46, 20);
		contentPane.add(txt_minutes);
		txt_minutes.setColumns(10);
		
		txt_seconds = new JTextField();
		txt_seconds.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_seconds.setColumns(10);
		txt_seconds.setBounds(175, 51, 46, 20);
		contentPane.add(txt_seconds);
		
		txt_millis = new JTextField();
		txt_millis.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_millis.setColumns(10);
		txt_millis.setBounds(271, 51, 46, 20);
		contentPane.add(txt_millis);
		
		JLabel lblStageTime = new JLabel("Stage Time");
		lblStageTime.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStageTime.setBounds(10, 54, 55, 14);
		contentPane.add(lblStageTime);
		
		JLabel lblNewLabel_1 = new JLabel("Car");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(19, 146, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(92, 143, 225, 20);
		contentPane.add(comboBox);
		
		setComboBox();
		
		JLabel lblWarning = new JLabel("");
		lblWarning.setVisible(false);
		lblWarning.setForeground(Color.RED);
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setBounds(10, 188, 336, 14);
		contentPane.add(lblWarning);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(56, 213, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txt_millis.getText().equals("") || txt_seconds.getText().equals("") || txt_minutes.getText().equals("")){
					lblWarning.setText("Please fill all the fields");
					lblWarning.setVisible(true);
				}else{
					if(!isNumeric(txt_millis.getText()) || !isNumeric(txt_seconds.getText()) || !isNumeric(txt_minutes.getText())){
						lblWarning.setText("Fields must be integers greater than one");
						lblWarning.setVisible(true);
					}else{
						Date date = (Date) spinner.getModel().getValue();
						Calendar c = new GregorianCalendar();
						c.setTime(date);
						Rally2.Date date_start = new Rally2.Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
						atob.addRound(getCar((String) comboBox.getSelectedItem()), formatStageTime(txt_minutes.getText(), txt_seconds.getText(), txt_millis.getText()), date_start);
						AtoBManagementWindow.updateLists();
						btnCancel.doClick();
					}
				}
			}
		});
		btnDone.setBounds(228, 213, 89, 23);
		contentPane.add(btnDone);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setBounds(19, 100, 46, 14);
		contentPane.add(lblDate);
		
		spinner = new JSpinner();
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();  
		Round round = (Round) atob.round.get(atob.round.size() - 1);
		Rally2.Date end_date = atob.end_date;
		Rally2.Date round_date;
		if(round == null)
			round_date = atob.beginning_date;
		else
			round_date = round.date;
		
		c.set(round_date.calendardate.year.intValue(), round_date.calendardate.month.intValue() - 1, round_date.calendardate.day.intValue(), round_date.timestamp.hours.intValue(),  round_date.timestamp.minutes.intValue(), round_date.timestamp.seconds.intValue());
		c2.set(round_date.calendardate.year.intValue(), round_date.calendardate.month.intValue() - 1, round_date.calendardate.day.intValue(), round_date.timestamp.hours.intValue(),  round_date.timestamp.minutes.intValue() + 10, round_date.timestamp.seconds.intValue());
		c3.set(end_date.calendardate.year.intValue(), end_date.calendardate.month.intValue() - 1, end_date.calendardate.day.intValue(), end_date.timestamp.hours.intValue(),  end_date.timestamp.minutes.intValue(), end_date.timestamp.seconds.intValue());
		spinner.setModel(new SpinnerDateModel(c2.getTime(), c.getTime(), c3.getTime(), Calendar.DAY_OF_YEAR));
		spinner.setBounds(92, 97, 225, 17);
		contentPane.add(spinner);
	}
	
	public int formatStageTime(String m, String s, String mil){
		return Integer.parseInt(m) * 60000 + Integer.parseInt(s) * 1000 + Integer.parseInt(mil);
	}
	
	public Car getCar(String s){
		String number = s.split(" - ")[0];
		String team = s.split(" - ")[1];
		
		Iterator<Team> ir_teams = atob.teams.iterator();
		while(ir_teams.hasNext()){
			Iterator<Car> ir_cars = ir_teams.next().cars.iterator();
			while(ir_cars.hasNext()){
				Car c  = ir_cars.next();
				if(c.number.toString().equals(number) && c.team.name.equals(team))
					return c;
			}
		}
		return null;
	}
	
	public void setComboBox(){
		comboBox.removeAllItems();
		ArrayList<Car> all_cars = new ArrayList<>();
		
		Iterator<Team> ir_teams = atob.teams.iterator();
		while(ir_teams.hasNext()){
			Iterator<Car> ir_cars = ir_teams.next().cars.iterator();
			while(ir_cars.hasNext()){
				all_cars.add(ir_cars.next());
			}
		}
		
		for(Car c : all_cars){
			comboBox.addItem(c.number + " - " + c.team.name);
		}
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
