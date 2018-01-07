package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.SwingConstants;

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
		setBounds(100, 100, 451, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_srs = new DefaultListModel<>();
		list = new JList<String>(l_srs);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) {
		        		System.out.println("here");
		        		int index = list.locationToIndex(evt.getPoint());
		        		Team team = StartWindow.getTeam(list.getModel().getElementAt(index).split("          ")[2].trim());
		        		CarInfoWindow tmw = new CarInfoWindow(team.getCar(Integer.parseInt(list.getModel().getElementAt(index).split("          ")[1].trim())));
		        		tmw.setVisible(true);
		        		frame.setEnabled(false);
		        		tmw.addWindowListener(new java.awt.event.WindowAdapter() {
		        		    @Override
		        		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        		        frame.setEnabled(true);
		        		    }
		        		});
		        	}
		        }
		    }
		});
		JScrollPane srs_listScroller = new JScrollPane();
		srs_listScroller.setViewportView(list);
		srs_listScroller.setBounds(42, 95, 355, 275);
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
		btnAddStageResult.setBounds(235, 404, 136, 23);
		contentPane.add(btnAddStageResult);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnDone.setBounds(62, 404, 136, 23);
		contentPane.add(btnDone);
		
		JLabel lblTimeTeam = new JLabel("       time                #           team");
		lblTimeTeam.setBounds(42, 70, 355, 14);
		contentPane.add(lblTimeTeam);
		
		JLabel lblName = new JLabel(s.name);
		lblName.setBounds(42, 11, 156, 14);
		contentPane.add(lblName);
		
		JLabel lblNumber = new JLabel("Stage #" + s.number);
		lblNumber.setBounds(42, 36, 156, 14);
		contentPane.add(lblNumber);
		
		JLabel lblDist = new JLabel(s.distance + " m");
		lblDist.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDist.setBounds(215, 36, 156, 14);
		contentPane.add(lblDist);
		
		JLabel lblDate = new JLabel(formatDate(s.date));
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setBounds(215, 11, 156, 14);
		contentPane.add(lblDate);
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
	
	public static String formatDate(Date date){
		return String.format("%02d:%02d:%02d %02d/%02d/%04d", date.timestamp.hours, date.timestamp.minutes, date.timestamp.seconds, date.calendardate.day, date.calendardate.month, date.calendardate.year);
	}

}
