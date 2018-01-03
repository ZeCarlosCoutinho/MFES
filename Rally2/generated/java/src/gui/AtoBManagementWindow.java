package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class AtoBManagementWindow extends JFrame {

	private JPanel contentPane;
	private AtoB atob;
	private JFrame frame;
	private JList<String> list_rounds;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtoBManagementWindow frame = new AtoBManagementWindow(new AtoB());
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
	public AtoBManagementWindow(AtoB atob) {
		this.frame = this;
		this.atob = atob;
		setBounds(100, 100, 823, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_rounds = new DefaultListModel<>();
		list_rounds = new JList<String>(l_rounds);
		list_rounds.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) {
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		Team team = StartWindow.getTeam(list.getModel().getElementAt(index).split(" - ")[3].trim());
		        		CarInfoWindow tmw = new CarInfoWindow(team.getCar(Integer.parseInt(list.getModel().getElementAt(index).split(" - ")[2].trim())));
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
		JScrollPane rounds_listScroller = new JScrollPane();
		rounds_listScroller.setViewportView(list_rounds);
		rounds_listScroller.setBounds(23, 90, 391, 284);
		contentPane.add(rounds_listScroller);
		
		JButton btnNewButton = new JButton("Add Round");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(136, 401, 156, 23);
		contentPane.add(btnNewButton);
		
		updateLists();
	}

	public void updateLists(){
		DefaultListModel<String> l_rounds = new DefaultListModel<>();
		Iterator<Round> ir_rounds = atob.round.iterator();
		while(ir_rounds.hasNext()){
			Round round = ir_rounds.next();
			l_rounds.addElement(formatDate(round.date) + " - " + formatTime(round.round_time.intValue()) + " - " + String.format("%02d", round.car.number.intValue()) + " - " + round.car.team.name);
		}
		
		list_rounds.setModel(l_rounds);
		list_rounds.repaint();
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
