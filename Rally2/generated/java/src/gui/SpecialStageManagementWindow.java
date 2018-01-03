package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;
import javax.swing.JList;

public class SpecialStageManagementWindow extends JFrame {

	private JPanel contentPane;
	private static SpecialStage ss;
	private JFrame frame;
	private static JList<String> list_stages;
	private static JList<String> list_teams;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpecialStageManagementWindow frame = new SpecialStageManagementWindow(new SpecialStage());
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
	public SpecialStageManagementWindow(SpecialStage ss) {
		this.ss = ss;
		this.frame = this;
		setBounds(100, 100, 758, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DefaultListModel<String> l_stages = new DefaultListModel<>();
		list_stages = new JList<String>(l_stages);
		list_stages.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		StageManagementWindow tmw = new StageManagementWindow(getStage(list_stages.getModel().getElementAt(index)));
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
		JScrollPane stages_listScroller = new JScrollPane();
		stages_listScroller.setViewportView(list_stages);
		stages_listScroller.setBounds(31, 105, 296, 271);
		contentPane.add(stages_listScroller);
		
		DefaultListModel<String> l_teams = new DefaultListModel<>();
		list_teams = new JList<String>(l_teams);
		JScrollPane teams_listScroller = new JScrollPane();
		teams_listScroller.setViewportView(list_teams);
		teams_listScroller.setBounds(414, 105, 296, 271);
		contentPane.add(teams_listScroller);
		
		updateStages();
	}

	private static void updateStages() {
		DefaultListModel<String> l_stages = new DefaultListModel<>();
		Iterator<Stage> ir_stages = ss.stages.iterator();
		while(ir_stages.hasNext()){
			Stage stage = ir_stages.next();
			l_stages.addElement(stage.name);
		}
		
		DefaultListModel<String> l_teams = new DefaultListModel<>();
		Iterator<Team> ir_teams = ss.teams.iterator();
		while(ir_teams.hasNext()){
			Team team = ir_teams.next();
			l_teams.addElement(team.name);
		}
		
		list_stages.setModel(l_stages);
		list_stages.repaint();
		
		list_teams.setModel(l_teams);
		list_teams.repaint();
	}
	
	private Stage getStage(String name){
		Iterator<Stage> ir_stages = ss.stages.iterator();
		while(ir_stages.hasNext()){
			Stage stage = ir_stages.next();
			if(stage.name.equals(name))
				return stage;
		}
		return null;
	}
}
