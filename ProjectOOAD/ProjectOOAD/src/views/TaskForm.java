package views;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.collections.SetListenerHelper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import controllers.TaskHandler;
import controllers.TaskRequestHandler;
import controllers.UserController;
import models.Task;
import models.User;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class TaskForm extends JPanel {
	private JList<User> list;
	private DefaultListModel userlist;
	private JTextField title;

	/**
	 * Create the panel.
	 */
	public TaskForm(User user) {
		setSize(new Dimension(1400, 800));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Create Task");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 15, 1280, 250);
		panel_2.add(scrollPane);
		
		userlist = new DefaultListModel();
		if(user.getRole().equals("Supervisor")) {
			setListModel(UserController.getUserByRole("Worker"));
		}
		else {
			setListModel(UserController.getUserByRole("Supervisor"));
		}
		
		list = new JList(userlist);
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(78, 314, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		title = new JTextField();
		title.setBounds(242, 311, 1078, 20);
		panel_2.add(title);
		title.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Note");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(78, 391, 46, 14);
		panel_2.add(lblNewLabel_2);
		
		JTextArea note = new JTextArea();
		note.setBounds(242, 386, 1078, 136);
		panel_2.add(note);
		
		JPanel panel3 = new JPanel();
		panel3.setBorder(new EmptyBorder(20, 10, 20, 10));
		add(panel3, BorderLayout.SOUTH);
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
		
	}

	private void setListModel(ArrayList<User> list) {
		// TODO Auto-generated method stub
		for (User u : list) {
			userlist.addElement(u);
		}
	}
}
