package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import Rally2.*;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;
import javax.swing.ListModel;

import org.overture.codegen.runtime.*;

public class StartWindow {
	public static ArrayList<Team> teams= new ArrayList<>();
	public static ArrayList<Event> events= new ArrayList<>();

	private JFrame frame;
	public static JList<String> team_list;
	public static JList<String> event_list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}
	
	private void createWorld(){
		Manager manager = new Manager("Malcom Wilson", "British");
		Manager manager2 = new Manager("Tommi Mäkinen", "Finish");
		
		Team team = new Team("M-Sport", manager);
		Team team2 = new Team("TOYOTA GAZOO RACING WRT", manager2);
		
		Driver driver1 = new Driver("Sébastien Ogier", "French");
		Driver driver2 = new Driver("Ott Tänak", "Estonian");
		Driver driver3 = new Driver("Elfyn Evans", "British");
		CoDriver co1 = new CoDriver("Julien Ingrassia", "French");
		CoDriver co2 = new CoDriver("Martin Järveoja", "Estonian");
		CoDriver co3 = new CoDriver("Daniel Barritt", "British");
		Mechanic mec1 = new Mechanic("William Lawrence", "British");
		Mechanic mec2 = new Mechanic("Reiner Buchwald", "German");
		Mechanic mec3 = new Mechanic("Roderick Gilbert", "Scottish");
		Mechanic mec4 = new Mechanic("Felipe Warner", "Brazilian");
		
		Driver driver12 = new Driver("Jari-Matti Latvala", "Finish");
		Driver driver22 = new Driver("Esapekka Lappi", "Finish");
		Driver driver32 = new Driver("Kai Hynninen", "Finish");
		CoDriver co12 = new CoDriver("Miikka Anttila", "Finish");
		CoDriver co22 = new CoDriver("Janne Ferm", "Finish");
		CoDriver co32 = new CoDriver("Hannes Pennanen", "Finish");
		Mechanic mec12 = new Mechanic("Heikki Saikkonen", "Finish");
		Mechanic mec22 = new Mechanic("Atte Ritala", "Finish");
		Mechanic mec32 = new Mechanic("Aki Heikkilä", "Finish");
		Mechanic mec42 = new Mechanic("Henri Venäläinen	", "Finish");
		
		team.team_members.addAll(new ArrayList<TeamMember>() {{ add(driver1); add(driver2); add(driver3);}});
		team.team_members.addAll(new ArrayList<TeamMember>() {{ add(co1); add(co2); add(co3);}});
		team.team_members.addAll(new ArrayList<TeamMember>() {{ add(mec1); add(mec2); add(mec3); add(mec4);}});
		
		team2.team_members.addAll(new ArrayList<TeamMember>() {{ add(driver12); add(driver22); add(driver32);}});
		team2.team_members.addAll(new ArrayList<TeamMember>() {{ add(co12); add(co22); add(co32);}});
		team2.team_members.addAll(new ArrayList<TeamMember>() {{ add(mec12); add(mec22); add(mec32); add(mec42);}});
		
		Car car1 = new Car("Ford", "Fiesta WRC", 2017, 380, "4WD", 1190, team, 1);
		Car car2 = new Car("Ford", "Fiesta WRC", 2017, 380, "4WD", 1190, team, 2);
		Car car6 = new Car("Ford", "Fiesta WRC", 2017, 380, "4WD", 1190, team, 3);
		Car car3 = new Car("Toyota", "Yaris WRC", 2017, 380, "4WD", 1190, team2, 10);
		Car car4 = new Car("Toyota", "Yaris WRC", 2017, 380, "4WD", 1190, team2, 11);
		Car car5 = new Car("Toyota", "Yaris WRC", 2017, 380, "4WD", 1190, team2, 12);
		
		car1.driver = driver1; car1.co_driver = co1; car1.mechanics.add(mec1); car1.mechanics.add(mec2);
		car2.driver = driver2; car2.co_driver = co2; car2.mechanics.add(mec3);
		car6.driver = driver3; car6.co_driver = co3; car6.mechanics.add(mec4);
		
		car3.driver = driver12; car3.co_driver = co12; car3.mechanics.add(mec12); car1.mechanics.add(mec22);
		car4.driver = driver22; car4.co_driver = co22; car4.mechanics.add(mec32);
		car5.driver = driver32; car5.co_driver = co32; car5.mechanics.add(mec42);	

		team.cars.addAll(new ArrayList<Car>() {{ add(car1); add(car2); add(car6);}});
		team2.cars.addAll(new ArrayList<Car>() {{ add(car3); add(car4); add(car5);}});
		
		teams.add(team);
		teams.add(team2);
		
		Rally2.Date date1 = new Rally2.Date(2017, 10, 17, 10, 0, 0);
		Rally2.Date date2 = new Rally2.Date(2017, 10, 21, 20, 0, 0);
		Rally2.Date date3 = new Rally2.Date(2017, 12, 3, 12, 0, 0);
		Rally2.Date date4 = new Rally2.Date(2017, 12, 8, 22, 0, 0);
	
		SpecialStage ss = new SpecialStage("Vodafone Rally de Portugal", "Portugal", date3, date4);
		
		Stage s1 = new Stage("SS1 LOUSADA", 3360, 1, new Date(2017, 5, 3, 14, 0, 0), ss);
		Stage s2 = new Stage("SS2 VIANA DO CASTELO 1", 26700, 2, new Date(2017, 5, 3, 16, 0, 0), ss);
		Stage s3 = new Stage("SS3 CAMINHA 1", 18100, 3, new Date(2017, 5, 3, 18, 0, 0), ss);
		
		StageResult stageResult1 = new StageResult(car1, 157300);
		StageResult stageResult2 = new StageResult(car2, 147200);
		StageResult stageResult3 = new StageResult(car3, 145800);
		StageResult stageResult4 = new StageResult(car4, 115400);
		
		StageResult stageResult5 = new StageResult(car1, 939922);
		StageResult stageResult6 = new StageResult(car2, 929227);
		StageResult stageResult7 = new StageResult(car3, 941544);
		StageResult stageResult8= new StageResult(car4, 940578);
		
		StageResult stageResult9 = new StageResult(car1, 643099);
		StageResult stageResult10 = new StageResult(car2, 645199);
		StageResult stageResult11 = new StageResult(car3, 658434);
		StageResult stageResult12 = new StageResult(car4, 637224);
		
		s1.stage_results.addAll(new ArrayList<StageResult>() {{ add(stageResult1); add(stageResult2); add(stageResult3); add(stageResult4);}});
		s2.stage_results.addAll(new ArrayList<StageResult>() {{ add(stageResult5); add(stageResult6); add(stageResult7); add(stageResult8);}});
		s3.stage_results.addAll(new ArrayList<StageResult>() {{ add(stageResult9); add(stageResult10); add(stageResult11); add(stageResult12);}});
		
		ss.teams.addAll(new ArrayList<Team>() {{ add(team); add(team2);}});
		ss.stages.addAll(new ArrayList<Stage>() {{ add(s1); add(s2); add(s3);}});
		
		Rally2.Date date5 = new Date(2010, 9, 21, 10, 30, 00);
		Rally2.Date date6 = new Date(2010, 9, 23, 20, 30, 00);
		AtoB atob =  new AtoB("Pikes Peak International Hill Climb", "Pikes Peak, Colorado, USA", date5, date6);
		
		atob.addRound(car1, 546157, new Rally2.Date(2010, 9, 21, 10, 40, 00));
		atob.addRound(car2, 546249, new Rally2.Date(2010, 9, 21, 10, 50, 00));
		atob.addRound(car3, 548578, new Rally2.Date(2010, 9, 21, 11, 00, 00));
		atob.addRound(team.getCar(2), 547122, new Rally2.Date(2010, 9, 21, 11, 10, 00));
		
		atob.addRound(car1, 557935, new Rally2.Date(2010, 9, 21, 11, 20, 00));
		atob.addRound(car2, 548239, new Rally2.Date(2010, 9, 21, 11, 30, 00));
		atob.addRound(car3, 559935, new Rally2.Date(2010, 9, 21, 11, 40, 00));
		atob.addRound(car4, 554679, new Rally2.Date(2010, 9, 21, 11, 50, 00));
		
		atob.addRound(car1, 562486, new Rally2.Date(2010, 9, 21, 12, 00, 00));
		atob.addRound(car2, 551356, new Rally2.Date(2010, 9, 21, 12, 10, 00));
		atob.addRound(car3, 566036, new Rally2.Date(2010, 9, 21, 12, 20, 00));
		atob.addRound(car4, 541092, new Rally2.Date(2010, 9, 21, 12, 30, 00));
		
		atob.teams.addAll(new ArrayList<Team>() {{ add(team); add(team2);}});
		
		events.add(ss);
		events.add(atob);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 353);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createWorld();
		
		JButton btnCriarEquipa = new JButton("Criar Equipa");
		btnCriarEquipa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CreateTeamWindow ct = new CreateTeamWindow();
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
		btnCriarEquipa.setBounds(114, 257, 102, 23);
		frame.getContentPane().add(btnCriarEquipa);
		
		DefaultListModel<String> l1 = new DefaultListModel<>();
		team_list = new JList<String>(l1);
		team_list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		TeamManagementWindow tmw = new TeamManagementWindow(getTeam(team_list.getModel().getElementAt(index)));
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
		JScrollPane team_listScroller = new JScrollPane();
		team_listScroller.setViewportView(team_list);
		team_listScroller.setBounds(47, 27, 258, 185);
        frame.getContentPane().add(team_listScroller);
		
		DefaultListModel<String> l2 = new DefaultListModel<>();
		event_list = new JList<String>(l2);
		event_list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	Rectangle r = list.getCellBounds(0, list.getLastVisibleIndex()); 
		        	if (r != null && r.contains(evt.getPoint())) { 
		        		JFrame f = null;
		        		int index = list.locationToIndex(evt.getPoint()); 
		        		Event e = getEvent(event_list.getModel().getElementAt(index));
		        		if(e instanceof SpecialStage)
		        			f = new SpecialStageManagementWindow((SpecialStage) e);
		        		else if(e instanceof AtoB)
		        			f = new AtoBManagementWindow((AtoB) e);
		        		if(f != null){
		        			System.out.println("ola");
		        			f.setVisible(true);
			        		frame.setEnabled(false);
			        		f.addWindowListener(new java.awt.event.WindowAdapter() {
			        		    @Override
			        		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			        		        frame.setEnabled(true);
			        		    }
			        		});
		        		}
		        			
		        	}
		        }
		    }
		});
		JScrollPane event_listScroller = new JScrollPane();
		event_listScroller.setViewportView(event_list);
		event_listScroller.setBounds(376, 27, 258, 185);
		frame.getContentPane().add(event_listScroller);
		
		JButton btnCriarEvento = new JButton("Criar Evento");
		btnCriarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateEventWindow ct = new CreateEventWindow();
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
		btnCriarEvento.setBounds(456, 257, 102, 23);
		frame.getContentPane().add(btnCriarEvento);
		
		updateLists();
	}
	
	
	public static void updateLists(){
		DefaultListModel<String> l1 = new DefaultListModel<>();  
		for(Team t : teams){
			l1.addElement(t.name);
		}
		team_list.setModel(l1);
		
		DefaultListModel<String> l2 = new DefaultListModel<>();  
		for(Event e : events){
			l2.addElement(e.name);
		}
		event_list.setModel(l2);
		
		team_list.repaint();
		event_list.repaint();
	}
	
	public static Team getTeam(String name){
		for (Team t : teams){
			if(t.name.equals(name))
				return t;
		}
		return null;
	}
	
	public static Event getEvent(String name){
		for (Event t : events){
			if(t.name.equals(name))
				return t;
		}
		return null;
	}
	
	public static ArrayList<Event> getTeamEvents(Team team){
		ArrayList<Event> ret = new ArrayList<>();
		for (Event event : events){
			if(event.teams.contains(team))
				ret.add(event);
		}
		return ret;
	}
}
