package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddStageResultWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private SpecialStage ss;
	private Stage stage;
	private JTextField txt_minutes;
	private JTextField txt_seconds;
	private JTextField txt_millis;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStageResultWindow frame = new AddStageResultWindow(new Stage(), new SpecialStage());
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
	public AddStageResultWindow(Stage s, SpecialStage ss) {
		this.stage = s;
		this.ss = ss;
		this.frame = this;
		setBounds(100, 100, 384, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Minutes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(93, 11, 55, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSeconds = new JLabel("Seconds");
		lblSeconds.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSeconds.setBounds(158, 11, 75, 14);
		contentPane.add(lblSeconds);
		
		JLabel lblMilliseconds = new JLabel("Milliseconds");
		lblMilliseconds.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMilliseconds.setBounds(243, 11, 86, 14);
		contentPane.add(lblMilliseconds);
		
		txt_minutes = new JTextField();
		txt_minutes.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_minutes.setBounds(102, 36, 46, 20);
		contentPane.add(txt_minutes);
		txt_minutes.setColumns(10);
		
		txt_seconds = new JTextField();
		txt_seconds.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_seconds.setColumns(10);
		txt_seconds.setBounds(187, 36, 46, 20);
		contentPane.add(txt_seconds);
		
		txt_millis = new JTextField();
		txt_millis.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_millis.setColumns(10);
		txt_millis.setBounds(283, 36, 46, 20);
		contentPane.add(txt_millis);
		
		JLabel lblStageTime = new JLabel("Stage Time");
		lblStageTime.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStageTime.setBounds(22, 39, 55, 14);
		contentPane.add(lblStageTime);
		
		JLabel lblNewLabel_1 = new JLabel("Car");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(31, 89, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(104, 86, 225, 20);
		contentPane.add(comboBox);
		
		setComboBox();
		
		JLabel lblWarning = new JLabel("");
		lblWarning.setVisible(false);
		lblWarning.setForeground(Color.RED);
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setBounds(22, 127, 336, 14);
		contentPane.add(lblWarning);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(68, 156, 89, 23);
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
						s.addStageResult(getCar((String) comboBox.getSelectedItem()), formatStageTime(txt_minutes.getText(), txt_seconds.getText(), txt_millis.getText()));
						StageManagementWindow.updateList();
						btnCancel.doClick();
					}
				}
					
			}
		});
		btnDone.setBounds(240, 156, 89, 23);
		contentPane.add(btnDone);
	}
	
	public int formatStageTime(String m, String s, String mil){
		return Integer.parseInt(m) * 60000 + Integer.parseInt(s) * 1000 + Integer.parseInt(mil);
	}
	
	public Car getCar(String s){
		String number = s.split(" - ")[0];
		String team = s.split(" - ")[1];
		
		Iterator<Team> ir_teams = ss.teams.iterator();
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
		comboBox.removeAll();
		
		ArrayList<Car> all_cars = new ArrayList<>();
		ArrayList<Car> set_cars = new ArrayList<>();
		
		Iterator<Team> ir_teams = ss.teams.iterator();
		while(ir_teams.hasNext()){
			Iterator<Car> ir_cars = ir_teams.next().cars.iterator();
			while(ir_cars.hasNext()){
				all_cars.add(ir_cars.next());
			}
		}
		
		Iterator<StageResult> ir_srs = stage.stage_results.iterator();
		while(ir_srs.hasNext()){
			set_cars.add(ir_srs.next().car);
		}
		
		for(Car c : all_cars){
			if(!set_cars.contains(c))
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
