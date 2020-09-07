package views;

import javax.swing.JPanel;

import controllers.TaskHandler;
import controllers.UserController;
import helpers.Env;
import models.Task;
import models.User;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class UpdateTaskForm extends JPanel {
	private Task task;
	private JTextField textField;
	private JTable supervisorList;
	private JTable workerList;
	private DefaultTableModel supervisor;
	private DefaultTableModel worker;
	/**
	 * Create the panel.
	 */
	public UpdateTaskForm(String TaskId) {
		setSize(new Dimension(1400, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Update Task Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Supervisor");
		lblNewLabel_1.setBounds(42, 28, 78, 14);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 53, 500, 200);
		panel_1.add(scrollPane);
		
		supervisor = new DefaultTableModel();
		supervisor.setColumnIdentifiers(Env.UPDATETASK);
		getSupervisor();
		supervisorList = new JTable(supervisor);
		scrollPane.setViewportView(supervisorList);
		
		JLabel lblNewLabel_2 = new JLabel("Worker");
		lblNewLabel_2.setBounds(705, 28, 46, 14);
		panel_1.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(704, 53, 500, 200);
		panel_1.add(scrollPane_1);
		
		worker = new DefaultTableModel();
		worker.setColumnIdentifiers(Env.UPDATETASK);
		getWorkerr();
		workerList = new JTable(worker);
		scrollPane.setViewportView(workerList);
		
		JLabel lblNewLabel_3 = new JLabel("TItle");
		lblNewLabel_3.setBounds(42, 296, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(138, 293, 210, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Note");
		lblNewLabel_4.setBounds(42, 383, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(138, 378, 210, 128);
		panel_1.add(textArea);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TaskHandler.updateTask(TaskId, textField.getText(), workerList.getValueAt(workerList.getSelectedRow(), 0).toString(), supervisorList.getValueAt(supervisorList.getSelectedRow(), 0).toString(), task.getScore(), textArea.getText());
			}
		});
		panel_2.add(btnNewButton);
		

	}
	
	public void getTask(String id) {
		task = TaskHandler.getTask(id);
	}
	
	public void getSupervisor() {
		supervisor.setRowCount(0);
		ArrayList<User> usert = UserController.getUserByRole("Supervisor");
		
		for (User user : usert) {
			String supervisorId = user.getUserID().toString();
			String supervisorName = user.getUsername();
			
			supervisor.addRow(new Object[] {supervisorId, supervisorName});
		}
	}
	
	public void getWorkerr() {
		worker.setRowCount(0);
		ArrayList<User> usert = UserController.getUserByRole("Worker");
		
		for (User user : usert) {
			String workerId = user.getUserID().toString();
			String workerName = user.getUsername();
			
			supervisor.addRow(new Object[] {workerId, workerName});
		}
	}
}
