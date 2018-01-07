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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.overture.codegen.runtime.VDMSeq;

import Rally2.*;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AtoBResultsWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private AtoB atob;
	private JList<String> list;
	private JLabel lblPos;
	private JLabel lblTime;
	private JLabel lblCar;
	private JLabel lblTeam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtoBResultsWindow frame = new AtoBResultsWindow(new AtoB());
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
	public AtoBResultsWindow(AtoB atob) {
		this.frame = this;
		this.atob = atob;
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_results = new DefaultListModel<>();
		list = new JList<String>(l_results);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) {
		        		System.out.println("here");
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
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(list);
		listScroller.setBounds(10, 49, 414, 225);
		contentPane.add(listScroller);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnNewButton.setBounds(173, 308, 89, 23);
		contentPane.add(btnNewButton);
		
		lblPos = new JLabel("Pos");
		lblPos.setBounds(10, 24, 46, 14);
		contentPane.add(lblPos);
		
		lblTime = new JLabel("Time");
		lblTime.setBounds(49, 24, 46, 14);
		contentPane.add(lblTime);
		
		lblCar = new JLabel("Car #");
		lblCar.setBounds(94, 24, 46, 14);
		contentPane.add(lblCar);
		
		lblTeam = new JLabel("Team");
		lblTeam.setBounds(142, 24, 46, 14);
		contentPane.add(lblTeam);
		
		updateList();
	}
	
	private void updateList() {
		int pos = 1;
		VDMSeq results = atob.getFinalResults();
		DefaultListModel<String> l_results = new DefaultListModel<>();
		Iterator<Round> ir_results = results.iterator();
		while(ir_results.hasNext()){
			Round r = ir_results.next();
			l_results.addElement(String.format("%02d", pos) + " - " + formatTime(r.round_time.intValue()) + " - " + String.format("%02d", r.car.number) + " - " + r.car.team.name);
			pos++;
		}
		
		list.setModel(l_results);
		list.repaint();
		
	}

	
	public static String formatTime(int millis){
		int m = millis % 1000;
		int seconds=(millis/1000)%60;
		int minutes=((millis-seconds)/1000)/60;
		
		return String.format("%02d:%02d.%03d", minutes, seconds, m);
	}

}
