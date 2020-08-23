package views;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.TaskRequestHandler;
import models.TaskRequest;

public class AllTaskRequestDisplay extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AllTaskRequestDisplay(){
		// TODO Auto-generated constructor stub
		
		
		
		//masi 1 layer, gw pikirnya ini harusnya bisa pake bbrp layer frame  biar lebih rapi
		
	
		this.setLayout(null);
		this.setBorder(new EmptyBorder(50, 80, 100, 30));// atas, kiri, bawah kanan
		this.add(new JLabel("All Task Request"));
		
		//accept button masih barbar
		JButton AcceptButton = new JButton("Accept");
		AcceptButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//get info yang diperluin
				
				JLabel label_id = new JLabel("Task Request Id: ");
				label_id.setFont(new Font("Times New Roman", Font.PLAIN, 32));
				add(label_id);
				//set posisinya?
				JTextField id = new JTextField();
				add(id);
				
				
				JButton proceedButton = new JButton("Proceed");
				proceedButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TaskRequestHandler.acceptTaskRequest(id.getText());
						
					}
				});
				
			}
		});
		//reject task masih barbar
		JButton RejectButton = new JButton("Accept");
		RejectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//get info yang diperluin
				JLabel label_id = new JLabel("Task Request Id: ");
				label_id.setFont(new Font("Times New Roman", Font.PLAIN, 32));
				add(label_id);
				//set posisinya?
				JTextField id = new JTextField();
				add(id);
				
				
				
				JButton proceedButton = new JButton("Proceed");
				proceedButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TaskRequestHandler.rejectTaskRequest(id.getText());
						
					}
				});
			}
		});
		
		
		
		JButton CreateButton = new JButton("Create");
		CreateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//bikin buat dapetin title dll.
				
				
				
				
				
				TaskRequestHandler.createTaskRequest(title, supervisorID, workerID, note);
			}
		});
		
		this.setVisible(true);
		
		
	}
	
	
	 
	

	
}
