package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class TeamEventResultsWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private Team team;
	private JList<String> list;
	private JLabel lblEvent;
	private JLabel lblPos;
	private JLabel lblCar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamEventResultsWindow frame = new TeamEventResultsWindow(new Team());
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
	public TeamEventResultsWindow(Team t) {
		this.frame = this;
		this.team = t;
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l = new DefaultListModel<>();
		list = new JList<String>(l);
		list.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane list_listScroller = new JScrollPane();
		list_listScroller.setViewportView(list);
		list_listScroller.setBounds(10, 44, 414, 244);
		contentPane.add(list_listScroller);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnDone.setBounds(174, 317, 89, 23);
		contentPane.add(btnDone);
		
		lblEvent = new JLabel("Event");
		lblEvent.setBounds(41, 19, 46, 14);
		contentPane.add(lblEvent);
		
		lblPos = new JLabel("Pos");
		lblPos.setBounds(310, 19, 46, 14);
		contentPane.add(lblPos);
		
		lblCar = new JLabel("Car #");
		lblCar.setBounds(345, 19, 46, 14);
		contentPane.add(lblCar);
		
		updateList();
	}
	
	private void updateList(){
		DefaultListModel<String> l = new DefaultListModel<>();
		Iterator<CarResult> ir_results = team.getEventResults().iterator();
		while(ir_results.hasNext()){
			CarResult cr = ir_results.next();
			l.addElement(String.format("%-40s", cr.event.name) + " - " + String.format("%02d", cr.position) + " - " + String.format("%02d", cr.car.number));
		}
		System.out.println(team.getEventResults().size());
		System.out.println(team.name);
		System.out.println(team.events.size());
		list.setModel(l);
		list.repaint();
	}
}
