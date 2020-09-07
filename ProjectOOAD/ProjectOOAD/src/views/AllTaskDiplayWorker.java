package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.TaskHandler;
import helpers.Env;
import models.Task;
import models.User;

public class AllTaskDiplayWorker extends JPanel {
	private JTable table;
	private DefaultTableModel userlist;
	private JTextField searching;
	/**
	 * Create the panel.
	 */
	public AllTaskDiplayWorker() {
		setSize(new Dimension(1400, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("View All Task");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(100, 50, 100, 50));
		add(panel_2, BorderLayout.EAST);
		
		JButton btnNewButton_2 = new JButton("Submit Task");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TaskHandler.submitTask((String) table.getValueAt(table.getSelectedRow(), 0));
				views();
			}
		});
		
		
		panel_2.setLayout(new GridLayout(0, 1, 0, 20));
		panel_2.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Id :");
		panel_2.add(lblNewLabel_1);
		
		JComboBox searchBy = new JComboBox();
		searchBy.setModel(new DefaultComboBoxModel(Env.SEARCHBY));
		panel_1.add(searchBy);
		
		searching = new JTextField();
		panel_1.add(searching);
		searching.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searching((String) searchBy.getSelectedItem(), searching.getText());
			}
		});
		panel_1.add(btnNewButton);
		
		JComboBox sortBy = new JComboBox();
		sortBy.setModel(new DefaultComboBoxModel(Env.SORTBY));
		panel_1.add(sortBy);
		
		JComboBox sortDirection = new JComboBox();
		sortDirection.setModel(new DefaultComboBoxModel(Env.SORTDIRECTION));
		panel_1.add(sortDirection);
		
		JButton btnNewButton_1 = new JButton("Sort");
		btnNewButton_1.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sorted((String) sortBy.getSelectedItem(), (String) sortDirection.getSelectedItem());
			}
		});
		panel_1.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		userlist = new DefaultTableModel();
		userlist.setColumnIdentifiers(Env.TASKATRI);
		views();
		table = new JTable();
		table.addMouseListener(new MouseAdapter(){
			@Override
            public void mouseClicked(MouseEvent e){
				lblNewLabel_1.setText("Id : " + (String) table.getValueAt(table.getSelectedRow(), 0));
            }
        });
		table.getColumnModel().getColumn(10).setPreferredWidth(700);
		scrollPane.setViewportView(table);

	}
	
	public void searching(String searchby, String search) {
		userlist.setRowCount(0);
		ArrayList<Task> temp;
		String query;
		if(searchby.equals("Title")) {
			query = "SELECT * FROM task WHERE"+ searchby +" = '" + search +"'";
			temp = TaskHandler.searchTask(query);
			for (Task task : temp) {
				String taskId = task.getId().toString();
				String worker = User.get(task.getWorkerID().toString()).getUsername();
				String supervisor = User.get(task.getSupervisorID().toString()).getUsername();
				String title = task.getTitle();
				Integer revisionCount = task.getRevisionCount();
				Integer score = task.getScore();
				Boolean isSubmitted = task.getIsSubmitted();
				java.sql.Timestamp approveAt = task.getApprovedAt();
				String note = task.getNote();
				 			
				userlist.addRow(new Object[] {taskId, worker, supervisor, title, revisionCount, score, isSubmitted, approveAt, note});
			}
		}
//		if(searchby.equals("Supervisor Name") || searchby.equals("Worker Name")) {
//			User.
//			query = "SELECT * FROM task WHERE"+ searchby +" = '" + search +"'";
//		}
	}
	
	public void sorted(String sortby, String sortdirection) {
		userlist.setRowCount(0);
		ArrayList<Task> temp;
		temp = TaskHandler.sortTask(sortby, sortdirection);
		for (Task task : temp) {
			String taskId = task.getId().toString();
			String worker = User.get(task.getWorkerID().toString()).getUsername();
			String supervisor = User.get(task.getSupervisorID().toString()).getUsername();
			String title = task.getTitle();
			Integer revisionCount = task.getRevisionCount();
			Integer score = task.getScore();
			Boolean isSubmitted = task.getIsSubmitted();
			java.sql.Timestamp approveAt = task.getApprovedAt();
			String note = task.getNote();
			 
			userlist.addRow(new Object[] {taskId, worker, supervisor, title, revisionCount, score, isSubmitted, approveAt, note});
		}
	}

	public void views() {
		userlist.setRowCount(0);
		ArrayList<Task> temp;
		temp = TaskHandler.getAllTask();
		for (Task task : temp) {
			String taskId = task.getId().toString();
			String worker = User.get(task.getWorkerID().toString()).getUsername();
			String supervisor = User.get(task.getSupervisorID().toString()).getUsername();
			String title = task.getTitle();
			Integer revisionCount = task.getRevisionCount();
			Integer score = task.getScore();
			Boolean isSubmitted = task.getIsSubmitted();
			java.sql.Timestamp approveAt = task.getApprovedAt();
			String note = task.getNote();
			 
			userlist.addRow(new Object[] {taskId, worker, supervisor, title, revisionCount, score, isSubmitted, approveAt, note});
		}
	}
}
