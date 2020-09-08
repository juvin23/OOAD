package views;


import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.TaskRequest;
import models.User;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import controllers.TaskRequestHandler;
import helpers.Env;
//import helpers.Env;
//import helpers.Utils;

public class AllTaskRequestDisplay extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	
	private static AllTaskRequestDisplay display;
	public static AllTaskRequestDisplay getInstance(User user) {
		if(display == null) {
			display = new AllTaskRequestDisplay(user);
		}
		return display;
	}
	
	

	public AllTaskRequestDisplay(User user) {
		//this.getInstance(user);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String supervisorID = Env.user.getUserID().toString();
		
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("All Task Request Display");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		this.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("SupervisorID: "+ supervisorID);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		this.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Accept");
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reject");
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Back to Main");
		panel_2.add(btnNewButton_2);
		
		JPanel panel_3 = new JPanel();
		this.getContentPane().add(panel_3, BorderLayout.CENTER);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				TaskRequestHandler.acceptTaskRequest(id);
				loadRow(user);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				TaskRequestHandler.rejectTaskRequest(id);
				loadRow(user);
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calling main menu
				display.dispose();
				MainDisplay.getInstance().setVisible(true);
			}
		});
		
		//table panel
		JScrollPane scrollPane = new JScrollPane();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);


		model = new DefaultTableModel();
		loadRow(user);
		table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Task ID", "Worker ID", "Title", "Note"
			}
		));
		scrollPane.setViewportView(table);
	}

	
	public void loadRow(User user) {
		model.setRowCount(0);
		ArrayList<TaskRequest> trList = TaskRequestHandler.getAllTaskRequest();
		for(TaskRequest tr : trList) {
			
			if(tr.getSupervisorID().toString().equals(user.getUserID().toString())) {
				String taskID = tr.getId().toString();
				String workerID = User.get(tr.getWorkerID().toString()).getUsername();
				String title = tr.getTitle();
				String note = tr.getNote();
				
				model.addRow(new Object[] {taskID,  workerID, title, note});
			}
			
		}

	}
	
}
