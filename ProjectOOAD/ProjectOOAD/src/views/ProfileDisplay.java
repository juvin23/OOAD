package views;


import javax.swing.JFrame;

import models.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;

import controllers.UserController;
import helpers.Env;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ProfileDisplay extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public JButton btnEditProfile;
	public JButton btnResetPassword;
	public JButton btnChangePassword;
	private UserController uc = new UserController();
	
	private static ProfileDisplay pd;

	public static ProfileDisplay getInstance(User user) {
		if(pd == null) {
			pd = new ProfileDisplay(user);
		}
		return pd;
	}
	
	public ProfileDisplay(User user) {
		initialize(user);
	}

	private void initialize(User user) {
		this.setBounds(100, 100, 332, 392);
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Font LABELFONT = new Font("Tahoma", Font.PLAIN, 12);
		
		JLabel Header = new JLabel("My Profile");
		Header.setFont(new Font("Sitka Banner", Font.PLAIN, 20));
		Header.setBounds(118, 0, 86, 46);

		JLabel lblUsernameLabel = new JLabel("Username");
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		JLabel lblRole = new JLabel("Role");
		JLabel lblAddress = new JLabel("Address");
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(LABELFONT);
		

		lblUsernameLabel.setBounds(95, 50, 60, 20);
		lblDateOfBirth.setBounds(80, 75, 80, 20);
		lblRole.setBounds(130, 95, 60, 20);
		lblAddress.setBounds(105, 110, 60, 20);
		lblPhone.setBounds(100, 175, 60, 20);

		lblUsernameLabel.setFont(LABELFONT);
		lblRole.setFont(LABELFONT);
		lblDateOfBirth.setFont(LABELFONT);
		lblAddress.setFont(LABELFONT);
		
		this.add(Header);
		this.add(lblUsernameLabel);
		this.add(lblDateOfBirth);		
		this.add(lblAddress);		
		this.add(lblPhone);
	
		this.add(lblRole);

		JLabel Role = new JLabel(user.getRole());
		JLabel DateOfBirth = new JLabel(user.getDOB().toString());
		JLabel Username = new JLabel(user.getUsername());
		
		DateOfBirth.setBounds(163, 77, 95, 14);
		Role.setBounds(163, 96, 95, 14);
		Username.setBounds(162, 56, 95, 14);
			
		this.add(Username);
		this.add(DateOfBirth);
		this.add(Role);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 44, 269, 2);
		this.getContentPane().add(separator);
		
		btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.setBounds(46, 277, 228, 19);
		this.getContentPane().add(btnEditProfile);
		btnEditProfile.addActionListener(this);
		
		btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBounds(46, 217, 228, 19);
		this.getContentPane().add(btnResetPassword);
		btnResetPassword.addActionListener(this);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(46, 247, 228, 19);
		this.getContentPane().add(btnChangePassword);
		btnChangePassword.addActionListener(this);
				
		JLabel Phone = new JLabel(user.getTelp());
		Phone.setBounds(162, 179, 95, 14);
		this.getContentPane().add(Phone);
		
		JButton btnHome = new JButton(". . .");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnHome.setBounds(136, 306, 43, 35);
		this.getContentPane().add(btnHome);
		
		JLabel Address = new JLabel(user.getAddress());
		Address.setBounds(164, 118, 108, 53);
		this.getContentPane().add(Address);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnResetPassword) {
			uc.resetPassword(Env.user.getUserID().toString());
			JOptionPane.showMessageDialog(null, "Password is set to deafault (yyyy-MM-dd)!");
			this.dispose();
			MainDisplay.getInstance().setVisible(true);
		}else if(e.getSource() == btnEditProfile) {
			EditProfileView.getInstance();
		} 
	}
}
