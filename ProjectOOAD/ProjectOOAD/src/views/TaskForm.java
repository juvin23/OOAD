package views;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.UUID;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.MouseEvent;

import controllers.TaskHandler;
import controllers.TaskRequestHandler;
import controllers.UserController;
import helpers.Env;
import models.Task;
import models.User;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TaskForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel userlist;
	private JTextField title;
	private ArrayList<Task> temp;
	private int selRow, selCol;
	private String value;
	private static TaskForm tf;
	
	
	public static TaskForm getInstance() {
		if(tf == null) {
			tf = new TaskForm();
		}
		return tf;
	}

	public TaskForm() {
//		User user = Env.user;
		User user = UserController.getInstance().getByUname("testing");
		this.setSize(new Dimension(800, 600));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Create Task");
		panel.add(lblNewLabel);
		getContentPane().add(panel, BorderLayout.NORTH);
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(744, 200);
		scrollPane.setLocation(30, 30);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		userlist = new DefaultTableModel();
		userlist.setColumnIdentifiers(Env.TASKFORM);
		if(user.getRole().equals("Supervisor")) {
			userlist.setRowCount(0);
			ArrayList<User> temp;
			temp = UserController.getInstance().getUserByRole("Worker");
			for (User users : temp) {
				String worker = users.getUsername();
				
				userlist.addRow(new Object[] {worker});
			}
		}else {
			userlist.setRowCount(0);
			ArrayList<User> temp;
			temp = UserController.getInstance().getUserByRole("Supervisor");
			for (User users : temp) {
				String supervisor = users.getUsername();
				
				userlist.addRow(new Object[] {supervisor});
			}
		}
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(41, 279, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		title = new JTextField();
		title.setBounds(97, 276, 677, 20);
		panel_2.add(title);
		title.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Note");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(41, 355, 46, 14);
		panel_2.add(lblNewLabel_2);
		
		JTextArea note = new JTextArea();
		note.setBounds(99, 333, 675, 60);
		panel_2.add(note);
		
		table = new JTable(userlist);
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				selRow = table.getSelectedRow();
				selCol = table.getSelectedColumn();
				value = table.getValueAt(selRow, selCol).toString();
			}
		});

		
		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(20, 10, 20, 10));
		getContentPane().add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		
		JButton btnNewButton = new JButton("Confirm");
		panel3.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user.getRole().equals("Supervisor")) {
					TaskHandler.CreateTask(title.getText(), user.getUserID().toString(), UserController.getInstance().getByUname(value).toString(), note.getText());
				}
				else {
					TaskRequestHandler.createTaskRequest(title.getText(), UUID.fromString(UserController.getInstance().getByUname(value).toString()), user.getUserID(), note.getText());
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancel");
		panel3.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.setVisible(true);
	}
