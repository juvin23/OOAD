package views;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;


import controllers.TaskHandler;
import controllers.TaskRequestHandler;
import controllers.UserController;
import helpers.Env;
import models.User;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TaskForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JList<User> list;
	private DefaultListModel<User> userlist;
	private JTextField title;
	
	private static TaskForm tf;
	
	public static TaskForm getInstance() {
		if(tf == null) {
			tf = new TaskForm();
		}
		return tf;
	}

	public TaskForm() {
		User user = Env.user;
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
		
		JScrollPane scrollpane = new JScrollPane();
		panel_2.add(scrollpane);
		
		userlist = new DefaultListModel();
		if(user.getRole().equals("Supervisor")) {
			setListModel(UserController.getInstance().getUserByRole("Worker"));
		}else {
			setListModel(UserController.getInstance().getUserByRole("Supervisor"));
		}
		
		scrollpane.setViewportView(list);
		
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
		
		list = new JList(userlist);
		list.setBounds(41, 11, 733, 155);
		panel_2.add(list);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
					TaskHandler.CreateTask(title.getText(), user.getUserID().toString(), list.getSelectedValue().getUserID().toString(), note.getText());
				}
				else {
					TaskRequestHandler.createTaskRequest(title.getText(), list.getSelectedValue().getUserID(), user.getUserID(), note.getText());
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

	private void setListModel(ArrayList<User> list) {
		for (User u : list) {
			userlist.addElement(u);
		}
	}
}
