package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.NotificationController;
import models.Notification;
import models.User;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class NotificationHistoryDisplay extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	
	private NotificationHistoryDisplay display;
	
	
	public NotificationHistoryDisplay getInstance(User user) {
		if(display == null) {
			display = new NotificationHistoryDisplay(user);
		}
		
		return display;
	}
	
	
	public NotificationHistoryDisplay(User user) {
	
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		this.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Your Notification");
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		this.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Mark All as Read");
		panel_1.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NotificationController.readAllNotification(user.getUserID());
			}
		});
		
		JButton btnNewButton_1 = new JButton("Back");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//back to main menu
				MainDisplay.getInstance();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		model = new DefaultTableModel();
		
		loadRow(user);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Notification ID", "User ID", "Message", "Read At"
			}
		));
		scrollPane.setViewportView(table);
		
	
	}

	public void loadRow(User user) {
		model.setRowCount(0);
		ArrayList<Notification> ntfList = NotificationController.getAllNotification();
		for(Notification ntf : ntfList) {
			
			if(ntf.getUserID().toString().equals(user.getUserID().toString())) {
				String id = ntf.getId().toString();
				String userid = ntf.getUserID().toString();
				String message = ntf.getMessage();
				Timestamp readAt = ntf.getReadAt();
				model.addRow(new Object[] {id, userid, message, readAt});
			}
		}

	}
	
}
