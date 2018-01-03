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
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class SpecialStageResults extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JList<String> list;
	private SpecialStage ss;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpecialStageResults frame = new SpecialStageResults(new SpecialStage());
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
	public SpecialStageResults(SpecialStage ss) {
		this.ss = ss;
		this.frame = this;
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
		listScroller.setBounds(10, 26, 414, 248);
		contentPane.add(listScroller);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnNewButton.setBounds(173, 308, 89, 23);
		contentPane.add(btnNewButton);
		
		updateList();
	}

	private void updateList() {
		int pos = 1;
		Stage stage = ss.getResults();
		DefaultListModel<String> l_results = new DefaultListModel<>();
		Iterator<StageResult> ir_results = stage.stage_results.iterator();
		while(ir_results.hasNext()){
			StageResult sr = ir_results.next();
			if(participatedInAllStages(sr.car, ss)){
				l_results.addElement(String.format("%02d", pos) + " - " + formatTime(sr.stage_time.intValue()) + " - " + String.format("%02d", sr.car.number) + " - " + sr.car.team.name);
				pos++;
			}
		}
		
		list.setModel(l_results);
		list.repaint();
		
	}
	
	public boolean participatedInAllStages(Car car, SpecialStage ss){
		Iterator<Stage> ir_stages = ss.stages.iterator();
		while(ir_stages.hasNext()){
			boolean flag = false;
			Stage stage = ir_stages.next();
			Iterator<StageResult> ir_srs = stage.stage_results.iterator();
			while(ir_srs.hasNext()){
				StageResult sr = ir_srs.next();
				if(sr.car == car)
					flag = true;
			}
			if(flag == false)
				return false;
		}
		return true;
	}
	
	public static String formatTime(int millis){
		int m = millis % 1000;
		int seconds=(millis/1000)%60;
		int minutes=((millis-seconds)/1000)/60;
		
		return String.format("%02d:%02d.%03d", minutes, seconds, m);
	}

}
