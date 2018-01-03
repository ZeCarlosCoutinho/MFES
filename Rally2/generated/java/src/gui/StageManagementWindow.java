package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StageManagementWindow extends JFrame {

	private JPanel contentPane;
	private static Stage stage;
	private static SpecialStage ss;
	private JFrame frame;
	private static JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StageManagementWindow frame = new StageManagementWindow(new Stage(), new SpecialStage());
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
	public StageManagementWindow(Stage s, SpecialStage ss) {
		this.stage = s;
		this.ss= ss;
		this.frame = this;
		setBounds(100, 100, 451, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_srs = new DefaultListModel<>();
		list = new JList<String>(l_srs);
		JScrollPane srs_listScroller = new JScrollPane();
		srs_listScroller.setViewportView(list);
		srs_listScroller.setBounds(41, 97, 355, 275);
		contentPane.add(srs_listScroller);
		
		updateList();
		
		JButton btnAddStageResult = new JButton("Add Stage Result");
		btnAddStageResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AddStageResultWindow ct = new AddStageResultWindow(s, ss);
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
		btnAddStageResult.setBounds(234, 406, 136, 23);
		contentPane.add(btnAddStageResult);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnDone.setBounds(61, 406, 136, 23);
		contentPane.add(btnDone);
		
		JLabel lblTimeTeam = new JLabel("       time                #           team");
		lblTimeTeam.setBounds(41, 72, 355, 14);
		contentPane.add(lblTimeTeam);
	}
	
	public static void updateList(){
		stage.sortResults();
		
		DefaultListModel<String> l_srs = new DefaultListModel<>();
		Iterator<StageResult> ir_srs = stage.stage_results.iterator();
		while(ir_srs.hasNext()){
			StageResult sr = ir_srs.next();
			l_srs.addElement(String.format(formatTime((int) sr.stage_time) + "          " + "%02d", sr.car.number) + "          " + sr.car.team.name);
		}
		
		list.setModel(l_srs);
		list.repaint();
	}
	
	public static String formatTime(int millis){
		int m = millis % 1000;
		int seconds=(millis/1000)%60;
		int minutes=((millis-seconds)/1000)/60;
		
		return String.format("%02d:%02d.%03d", minutes, seconds, m);
	}

}
