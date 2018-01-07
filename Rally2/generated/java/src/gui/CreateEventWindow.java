package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Rally2.*;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.AbstractListModel;

public class CreateEventWindow extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField txt_name;
	private JTextField txt_loc;
	private JLabel lblPleaseFillAll;
	private JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventWindow frame = new CreateEventWindow();
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
	public CreateEventWindow() {
		this.frame = this;
		setBounds(100, 100, 354, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSpinner spinner_start = new JSpinner();
		spinner_start.setModel(new SpinnerDateModel(new Date(1514894400000L), null, null, Calendar.DAY_OF_YEAR));
		spinner_start.setBounds(126, 154, 169, 20);
		contentPane.add(spinner_start);
		
		JLabel lblDateBeginning = new JLabel("Start Date");
		lblDateBeginning.setBounds(23, 157, 89, 14);
		contentPane.add(lblDateBeginning);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(23, 199, 89, 14);
		contentPane.add(lblEndDate);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(23, 23, 46, 14);
		contentPane.add(lblName);
		
		txt_name = new JTextField();
		txt_name.setBounds(126, 20, 169, 20);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(23, 67, 72, 14);
		contentPane.add(lblLocation);
		
		txt_loc = new JTextField();
		txt_loc.setColumns(10);
		txt_loc.setBounds(126, 64, 169, 20);
		contentPane.add(txt_loc);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(23, 113, 46, 14);
		contentPane.add(lblType);
		
		JComboBox<String> cmb_type = new JComboBox<String>();
		cmb_type.setModel(new DefaultComboBoxModel<String>(new String[] {"AtoB", "Special Stage"}));
		cmb_type.setBounds(126, 110, 169, 17);
		contentPane.add(cmb_type);
		
		JSpinner spinner_end = new JSpinner();
		spinner_end.setModel(new SpinnerDateModel(new Date(1514894400000L), null, null, Calendar.DAY_OF_YEAR));
		spinner_end.setBounds(126, 196, 169, 20);
		contentPane.add(spinner_end);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(52, 459, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_name.getText().equals("") || txt_loc.getText().equals("")){
					lblPleaseFillAll.setText("Please fill all the fields");
					lblPleaseFillAll.setVisible(true);
				}else{
					Date start = (Date) spinner_start.getModel().getValue();
					Date end = (Date) spinner_end.getModel().getValue();
					
					Calendar c_start = new GregorianCalendar();
					Calendar c_end = new GregorianCalendar();
					c_start.setTime(start);
					c_end.setTime(end);
					
					Rally2.Date date_start = new Rally2.Date(c_start.get(Calendar.YEAR), c_start.get(Calendar.MONTH) + 1, c_start.get(Calendar.DAY_OF_MONTH), c_start.get(Calendar.HOUR_OF_DAY), c_start.get(Calendar.MINUTE), c_start.get(Calendar.SECOND));
					Rally2.Date date_end = new Rally2.Date(c_end.get(Calendar.YEAR), c_end.get(Calendar.MONTH) + 1, c_end.get(Calendar.DAY_OF_MONTH), c_end.get(Calendar.HOUR_OF_DAY), c_end.get(Calendar.MINUTE), c_end.get(Calendar.SECOND));
					
					if(start.before(end)){
						if(list.getSelectedValuesList().size() == 0){
							lblPleaseFillAll.setText("Please choose the teams");
							lblPleaseFillAll.setVisible(true);
						}else{
							Event event = null;
							switch((int) cmb_type.getSelectedIndex()){
							case 0:
								event = new AtoB(txt_name.getText(), txt_loc.getText(), date_start, date_end);
								break;
							case 1:
								event = new SpecialStage(txt_name.getText(), txt_loc.getText(), date_start, date_end);
								break;
							}
							for(String s : list.getSelectedValuesList()){
								event.addTeam(StartWindow.getTeam(s));
							}
							StartWindow.events.add(event);
							StartWindow.updateLists();
							btnCancel.doClick();
						}
					}else{
						lblPleaseFillAll.setText("End Date is more recent than Start Date");
						lblPleaseFillAll.setVisible(true);
					}
				}
			}
		});
		btnDone.setBounds(179, 459, 89, 23);
		contentPane.add(btnDone);
		
		lblPleaseFillAll = new JLabel("Please fill all the fields");
		lblPleaseFillAll.setVisible(false);
		lblPleaseFillAll.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseFillAll.setForeground(Color.RED);
		lblPleaseFillAll.setBounds(23, 423, 272, 25);
		contentPane.add(lblPleaseFillAll);
		
		DefaultListModel<String> l1 = new DefaultListModel<>();
		list = new JList<String>(l1);
		list.setSelectionModel(new DefaultListSelectionModel() {
		    @Override
		    public void setSelectionInterval(int index0, int index1) {
		        if(super.isSelectedIndex(index0)) {
		            super.removeSelectionInterval(index0, index1);
		        }
		        else {
		            super.addSelectionInterval(index0, index1);
		        }
		    }
		});
		JScrollPane event_listScroller = new JScrollPane();
		event_listScroller.setViewportView(list);
		event_listScroller.setBounds(23, 273, 272, 139);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		contentPane.add(event_listScroller);
		
		JLabel lblTeams = new JLabel("Teams");
		lblTeams.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeams.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeams.setBounds(136, 248, 46, 14);
		contentPane.add(lblTeams);
		
		list.getSelectedValuesList();
		updateList();
	}
	
	private void updateList(){
		DefaultListModel<String> l1 = new DefaultListModel<>();
		for (Team t : StartWindow.teams){
			l1.addElement(t.name);
		}
		
		list.setModel(l1);
		list.repaint();
	}
}
