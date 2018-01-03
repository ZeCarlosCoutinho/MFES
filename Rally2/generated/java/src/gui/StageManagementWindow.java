package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;

public class StageManagementWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StageManagementWindow frame = new StageManagementWindow();
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
	public StageManagementWindow() {
		setBounds(100, 100, 451, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(41, 97, 355, 275);
		contentPane.add(list);
		
		JButton btnAddStageResult = new JButton("Add Stage Result");
		btnAddStageResult.setBounds(244, 406, 115, 23);
		contentPane.add(btnAddStageResult);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(82, 406, 115, 23);
		contentPane.add(btnDone);
	}

}
